package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Configuration
public class S3Config {

	
	@Value("${AWS_DEFAULT_REGION}")
	private String s3ClientRegion;
	
	@Value("${AWS_ACCESS_KEY_ID}")
	private String accessKey;

	@Value("${AWS_SECRET_ACCESS_KEY}")
	private String secretKey;
	  
	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
	    return AmazonS3ClientBuilder.standard()
	    		.withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .withRegion(s3ClientRegion).build();
	    }
}
