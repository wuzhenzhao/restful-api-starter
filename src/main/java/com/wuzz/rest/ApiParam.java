package com.wuzz.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 19:36
 * Description 描述: APi入参出参封装类
 */
public class ApiParam {

    // 字段名
    private String filedName;

    // 字段类型
    private String filedType;

    // 字段描述
    private String filedDesc;

    //是否必须输入
    private  boolean mustInput=true;

    private List<ApiParam> subApiParam;

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    public String getFiledDesc() {
        return filedDesc;
    }

    public void setFiledDesc(String filedDesc) {
        this.filedDesc = filedDesc;
    }

    public List<ApiParam> getSubApiParam() {
        return subApiParam;
    }

    public void setSubApiParam(List<ApiParam> subApiParam) {
        this.subApiParam = subApiParam;
    }

    public boolean isMustInput() {
        return mustInput;
    }

    public void setMustInput(boolean mustInput) {
        this.mustInput = mustInput;
    }

    @Override
    public String toString() {
        return "ApiParam{" +
                "filedName='" + filedName + '\'' +
                ", filedType='" + filedType + '\'' +
                ", filedDesc='" + filedDesc + '\'' +
                ", mustInput=" + mustInput +
                ", subApiParam=" + subApiParam +
                '}';
    }
}
