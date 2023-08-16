package com.stubee.thirdparty.common.properties.s3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.s3")
public class S3Properties {

    private String bucket;
    private String region;
    private String accessKey;
    private String secretKey;

}