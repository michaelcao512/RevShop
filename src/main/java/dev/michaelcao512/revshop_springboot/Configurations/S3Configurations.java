package dev.michaelcao512.revshop_springboot.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Configurations {
    private final String ACCESS_KEY;
    private final String SECRET_KEY;
    private final String REGION;

    public S3Configurations(
            @Value("${s3.access.key}")String ACCESS_KEY,
            @Value("${s3.secret.key}")String SECRET_KEY,
            @Value("${s3.region}") String REGION) {
        this.ACCESS_KEY = ACCESS_KEY;
        this.SECRET_KEY = SECRET_KEY;
        this.REGION = REGION;
    }

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        return S3Client.builder()
                .credentialsProvider(() -> awsBasicCredentials)
                .region(Region.of(REGION))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        return S3Presigner.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(Region.of(REGION))
                .build();
    }
}
