package com.wuzz.rest;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 18:03
 * Description 描述:
 */
@ConfigurationProperties(prefix = "restful.api")
public class ApiProperties {

//    private  final String DEFAULT_SCANPACKAGE="com.wuzz.demo";

    private String scanPackage;


    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }
}
