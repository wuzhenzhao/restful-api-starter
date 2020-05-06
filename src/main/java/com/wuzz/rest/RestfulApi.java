package com.wuzz.rest;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 15:44
 * Description 描述: restful Api封装类
 */
public class RestfulApi {


    private String interfaceName;

    private String interfaceDesc;

    private String requestMethods;

    private String requestMappingUrl;

    private List<ApiParam> requestParam;

    private List<ApiParam> responseParam;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceDesc() {
        return interfaceDesc;
    }

    public void setInterfaceDesc(String interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
    }

    public String getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(RequestMethod[] requestMethods) {

        if (requestMethods.length == 0) {
            this.requestMethods = "GET";
            return;
        }
        this.requestMethods="";
        for (RequestMethod requestMethod : requestMethods) {
            this.requestMethods += requestMethod.name() + " ";
        }
    }

    public String getRequestMappingUrl() {
        return requestMappingUrl;
    }

    public void setRequestMappingUrl(String requestMappingUrl) {
        this.requestMappingUrl = requestMappingUrl;
    }

    public List<ApiParam> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(List<ApiParam> requestParam) {
        this.requestParam = requestParam;
    }

    public List<ApiParam> getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(List<ApiParam> responseParam) {
        this.responseParam = responseParam;
    }

    @Override
    public String toString() {
        return "RestfulApi{" +
                "requestMethods='" + requestMethods + '\'' +
                ", requestMappingUrl='" + requestMappingUrl + '\'' +
                ", requestParam=" + requestParam +
                ", responseParam=" + responseParam +
                '}';
    }
}
