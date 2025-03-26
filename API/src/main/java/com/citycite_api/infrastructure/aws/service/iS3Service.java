package com.citycite_api.infrastructure.aws.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.*;
import java.io.File;
import java.util.List;

@Service
@Transactional
public interface iS3Service {
    public boolean testConnection();
    public PutObjectResponse putObject(String bucketName, String key, File file) throws S3Exception;
    public List<String> generateObjectUrls(String bucketName, ListObjectsV2Response listObjectsResponse) throws S3Exception;
    public String generateObjectUrl(String bucketName, String key) throws S3Exception;
    public ListObjectsV2Response listObjects(String bucketName) throws S3Exception;
    public ListObjectsV2Response listObjects(String bucketName, String prefix) throws S3Exception;
    public DeleteObjectResponse deleteObject(String bucketName, String key) throws S3Exception;
    public DeleteObjectsResponse deleteObjectsWithPrefix(String bucketName, String prefix) throws S3Exception;
}
