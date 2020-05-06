package com.wuzz.rest.test;

import com.wuzz.rest.processor.RestfulApiProcessor;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 15:03
 * Description 描述:
 */
public class MainTest {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        RestfulApiProcessor processor =new RestfulApiProcessor();

        processor.doScannerControl("com.wuzz.rest");

        processor.parseControlApi();

    }
}
