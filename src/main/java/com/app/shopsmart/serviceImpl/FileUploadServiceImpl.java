package com.app.shopsmart.serviceImpl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.shopsmart.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

  @Value("${aws.accessKey}")
  private String accessKey;

  @Value("${aws.secretKey}")
  private String secretKey;

  @Value("${aws.s3.bucketName}")
  private String bucketName;

  @Override
  public void uploadFile(InputStream inputStream) {
    try {
      BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
      AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
              .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
              .build();

      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType("application/octet-stream");
      metadata.setContentLength(inputStream.available());

      PutObjectRequest request = new PutObjectRequest(bucketName, UUID.randomUUID().toString(), inputStream, metadata);
      s3Client.putObject(request);
    } catch (IOException e) {
      // manejar excepción
    }
  }
}
