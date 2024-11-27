package com.clientaws;

import java.nio.file.Paths;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class S3Downloader {

    public static void downloadFile(String bucketName, String objectKey, String downloadPath) {
        try (S3Client s3 = S3Client.builder().build()) {
            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();

            s3.getObject(getRequest, Paths.get(downloadPath));
            System.out.println("Archivo descargado en: " + downloadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
