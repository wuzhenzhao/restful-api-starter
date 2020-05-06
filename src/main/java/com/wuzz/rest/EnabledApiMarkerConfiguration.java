package com.wuzz.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 18:03
 * Description 描述:
 * {@link EnabledApiAutoConfiguration}
 */
@Configuration
public class EnabledApiMarkerConfiguration {

    @Bean
    public Marker eurekaServerMarkerBean() {
        return new Marker();
    }

    class Marker {
    }
}
