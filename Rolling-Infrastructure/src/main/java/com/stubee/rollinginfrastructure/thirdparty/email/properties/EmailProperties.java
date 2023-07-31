package com.stubee.rollinginfrastructure.thirdparty.email.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.mail.username")
public class EmailProperties {

    private String username;

}