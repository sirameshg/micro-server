package com.aol.micro.server.s3;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class S3Configuration {
	
	private final String accessKey;
	private final String secretKey;
	private final String sessionToken;
	private final String region;

	@Autowired
	public S3Configuration(@Value("${s3.accessKey}") String accessKey, @Value("${s3.secretKey}") String secretKey, 
			@Value("${s3.sessionToken:#{null}}") String sessionToken, @Value("${s3.region:#{null}}") String region) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.sessionToken = sessionToken;
		this.region = region;
	}	
}
