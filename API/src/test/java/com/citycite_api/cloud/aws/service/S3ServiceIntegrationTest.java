package com.citycite_api.cloud.aws.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestClassOrder(ClassOrderer.OrderAnnotation.class) // Add this annotation
class S3ServiceIntegrationTest {

    @Autowired
    private iS3Service s3Service;
    private String bucketName;
    private String testPrefix;
    private String testKey;
    private File testFile;

    @BeforeAll
    void setUp() throws IOException {

        bucketName = "gocite-mvp";
        testPrefix = "test-" + "/";
        testKey = testPrefix + "test-file.txt";
        testFile = Files.createTempFile("test-file", ".txt").toFile();
        Files.write(testFile.toPath(), "Test content".getBytes());

    }

    @AfterAll
    void tearDown() {

        try {
            testFile.delete();
            s3Service.deleteObject(bucketName, testPrefix);
        }
        catch(Exception e) {}

    }

    @Nested
    @Order(1)
    class TestPutObject {

        @Test
        void shouldSuccessfullyPutObject() {

            PutObjectResponse response = s3Service.putObject(bucketName, testKey, testFile);
            assertNotNull(response.eTag());

        }

    }

    @Nested
    @Order(2)
    class TestListObjects {

        @Test
        void shouldReturnNonEmptyList() throws InterruptedException {

            Thread.sleep(5000); // Sleep to ensure object has been fully uploaded

            ListObjectsV2Response objectsInBucket = s3Service.listObjects(bucketName);
            assertAll(
                    () -> assertNotNull(objectsInBucket),
                    () -> assertFalse(objectsInBucket.contents().isEmpty())
            );

        }

        @Test
        void shouldReturnNonEmptyListInPrefix() {

            ListObjectsV2Response objectsInBucket = s3Service.listObjects(bucketName, testPrefix);
            assertAll(
                    () -> assertNotNull(objectsInBucket),
                    () -> assertFalse(objectsInBucket.contents().isEmpty())
            );

        }

    }

    @Nested
    @Order(3)
    class TestGenerateObjectURL {

        @Test
        void shouldGenerateValidURLForExistingObject() throws InterruptedException {

            String presignedUrl = s3Service.generateObjectUrl(bucketName, testKey);
            assertAll(
                    () -> assertNotNull(presignedUrl),
                    () -> assertTrue(presignedUrl.contains(bucketName)),
                    () -> assertTrue(presignedUrl.contains(testKey))
            );

        }

    }

    @Nested
    @Order(4)
    class TestDeleteObject {

        @Test
        void shouldSuccessfullyDeleteExistingObject() throws InterruptedException {

            DeleteObjectResponse response = assertDoesNotThrow(() -> { return s3Service.deleteObject(bucketName, "22"); });
            assertNotNull(response);

        }

    }

}
