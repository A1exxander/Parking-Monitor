package com.citycite_api.cloud.aws.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class S3Service implements iS3Service {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private S3Presigner s3Presigner;

    @Override
    public boolean testConnection() {

        try {
            s3Client.listBuckets(); // Test connection by attempting to list buckets
            return true;
        } catch (S3Exception e) {
            return false;
        }

    }

    @Override
    public PutObjectResponse putObject(String bucketName, String key, File file) throws S3Exception {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            return s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromFile(file)
            );

    }

    @Override
    public List<String> generateObjectUrls(String bucketName, ListObjectsV2Response listObjectsResponse) {
        return listObjectsResponse.contents().stream()
                .map(s3Object -> generateObjectUrl(bucketName, s3Object.key()))
                .collect(Collectors.toList());
    }

    @Override
    public String generateObjectUrl(String bucketName, String key) {

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(4))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();

    }

    @Override
    public ListObjectsV2Response listObjects(String bucketName) throws S3Exception {

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        return s3Client.listObjectsV2(request);

    }

    @Override
    public ListObjectsV2Response listObjects(String bucketName, String prefix) throws S3Exception {

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .build();

        return s3Client.listObjectsV2(request);

    }

    @Override
    public DeleteObjectResponse deleteObject(String bucketName, String key) throws S3Exception {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.deleteObject(request);

    }

    @Override
    public DeleteObjectsResponse deleteObjectsWithPrefix(String bucketName, String prefix) throws S3Exception {

        List<ObjectIdentifier> objectsToDelete = listObjects(bucketName, prefix).contents().stream()
                .map(s3Object -> ObjectIdentifier.builder().key(s3Object.key()).build())
                .collect(Collectors.toList());

        DeleteObjectsRequest deleteRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(Delete.builder().objects(objectsToDelete).build())
                .build();

        return s3Client.deleteObjects(deleteRequest);

    }

}
