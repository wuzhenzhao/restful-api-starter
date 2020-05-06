package com.wuzz.rest;

import com.wuzz.rest.controller.RestfulApiController;
import com.wuzz.rest.processor.RestfulApiProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 18:03
 * Description 描述:
 */
@Configuration
@Import(EnabledApiInitializerConfiguration.class)
@ConditionalOnBean(EnabledApiMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(ApiProperties.class)
public class EnabledApiAutoConfiguration {

    @Bean
    public RestfulApiProcessor RestfulApiProcessor() {
        return new RestfulApiProcessor();
    }

    @Bean
    public RestfulApiController restfulApiController() {
        return new RestfulApiController();
    }


}
