package com.wuzz.rest.processor;

import com.alibaba.fastjson.JSONObject;
import com.wuzz.rest.ApiParam;
import com.wuzz.rest.RestfulApi;
import com.wuzz.rest.annotation.ApiDoc;
import com.wuzz.rest.annotation.ApiMethod;
import com.wuzz.rest.annotation.ApiRequestParam;
import com.wuzz.rest.annotation.ApiResponseParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 14:58
 * Description 描述: restful 接口Api处理类
 */
public class RestfulApiProcessor {

    //存放所扫描出来的类及其实例
    public Map<String, Object> waitProcessedControl = new HashMap<String, Object>();

    //保存url和Method的对应关系 可以抽象成一个对象Handler
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    public List<RestfulApi> restfulApiList = new ArrayList<RestfulApi>();


    //扫描出相关的Controller类 并且实例化保存
    public void doScannerControl(String scanPackage) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //scanPackage = com.wuzz.rest ，存储的是包路径
        //转换为文件路径，实际上就是把.替换为/就OK了
        //classpath
        URL url = this.getClass().getClassLoader().getResource("" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        if (classPath.listFiles().length == 0) {
            return;
        }
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScannerControl(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                //获取相应类文件的类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                //获取对应类的class
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(RestController.class)) {
                    Object instance = clazz.newInstance();
                    //Spring默认类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    //将ctroller 实例化缓存
                    waitProcessedControl.put(beanName, instance);
                }
            }
        }
    }

    //如果类名本身是小写字母，确实会出问题
    //但是我要说明的是：这个方法是我自己用，private的
    //传值也是自己传，类也都遵循了驼峰命名法
    //默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况
    //为了简化程序逻辑，就不做其他判断了，大家了解就OK
    //其实用写注释的时间都能够把逻辑写完了
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }


    //解析相关控制器的Api
    public void parseControlApi() throws IllegalAccessException, InstantiationException {
        if (waitProcessedControl.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : waitProcessedControl.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            //先获取类上面的路径
            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value()[0];
            }

            //默认获取所有的public方法
            for (Method method : clazz.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(ApiMethod.class)) {
                    continue;
                }
                RestfulApi restfulApi = new RestfulApi();
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    RequestMethod[] requestMethods = requestMapping.method();
                    //组装controller映射路径
                    String methodUrl = "";
                    if (requestMapping.value().length > 0) {
                        methodUrl = requestMapping.value()[0];
                    }
                    String url = ("/" + baseUrl + "/" + methodUrl)
                            .replaceAll("/+", "/");

                    restfulApi.setRequestMethods(requestMethods);
                    restfulApi.setRequestMappingUrl(url);
                    if (method.isAnnotationPresent(ApiMethod.class)) {
                        ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
                        restfulApi.setInterfaceName(apiMethod.name());
                        restfulApi.setInterfaceDesc(apiMethod.desc());


                        //  开始处理入参
                        ArrayList<ApiParam> apiRequestParams = new ArrayList<>();
                        Parameter[] parameters = method.getParameters();
                        if (parameters.length > 0) {
                            for (int i = 0; i < parameters.length; i++) {
                                ApiParam apiParam = new ApiParam();
                                Parameter parameter = parameters[i];
                                if (parameter.isAnnotationPresent(ApiRequestParam.class)) {
                                    ApiRequestParam requestParam = parameter.getAnnotation(ApiRequestParam.class);
                                    apiParam.setFiledDesc(requestParam.desc());
                                    apiParam.setFiledName(requestParam.name());
                                    apiParam.setFiledType(parameter.getType().getSimpleName());
                                    apiParam.setMustInput(requestParam.mustInput());
                                    apiRequestParams.add(apiParam);
                                    if (!isBasicType(parameter.getType())) {
                                        Object o = parameter.getType().newInstance();
                                        Field[] declaredFields = o.getClass().getDeclaredFields();
                                        apiParam.setSubApiParam(new ArrayList<ApiParam>());
                                        parseFileds(declaredFields, apiParam.getSubApiParam());
                                    }
                                }
                            }
                        }
                        restfulApi.setRequestParam(apiRequestParams);

                        //开始处理出参
                        ArrayList<ApiParam> apiResponseParams = new ArrayList<>();

                        if (!"void".equals(method.getReturnType().getSimpleName())) {
                            if (!isBasicType(method.getReturnType())) {
                                Object o = method.getReturnType().newInstance();
                                Field[] declaredFields = o.getClass().getDeclaredFields();
                                parseFileds(declaredFields, apiResponseParams);
                            } else {
                                if (apiMethod.responseParams().length>0) {
                                    for(ApiResponseParam responseParam:apiMethod.responseParams()){
                                        ApiParam apiResponseParam = new ApiParam();
                                        apiResponseParam.setFiledDesc(responseParam.desc());
                                        apiResponseParam.setFiledName(responseParam.name());
                                        apiResponseParam.setFiledType(method.getReturnType().getSimpleName());
                                        apiResponseParams.add(apiResponseParam);
                                    }
                                }
                            }
                        }
                        restfulApi.setResponseParam(apiResponseParams);
                        handlerMapping.put(url, method);
                    }


                }
                restfulApiList.add(restfulApi);
            }
        }
        System.out.println(JSONObject.toJSONString(restfulApiList, true));
    }


    private Boolean isBasicType(Class<?> parameter) {
        if (parameter == null) {
            return null;
        }

        /**
         * 基本类型、包装类型、String类型
         */
        List<String> types = Arrays.asList("java.lang.Integer", "java.lang.Double", "java.lang.Float",
                "java.lang.Long", "java.lang.Short", "java.lang.Byte", "java.lang.Boolean",
                "java.lang.Character", "java.lang.String", "int", "double", "long", "short",
                "byte", "boolean", "char", "float", "java.util.List", "java.util.Map");

        if (types.contains(parameter.getName())) {
            return true;
        }
        return false;
    }

    private List<ApiParam> parseFileds(Field[] declaredFields, List<ApiParam> apiParams) throws IllegalAccessException, InstantiationException {

        if (declaredFields.length == 0) {
            return apiParams;
        }
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ApiDoc.class)) {
                ApiDoc fieldAnnotation = field.getAnnotation(ApiDoc.class);
                ApiParam apiParam = new ApiParam();
                apiParam.setFiledName(field.getName());
                apiParam.setFiledType(field.getType().getSimpleName());
                apiParam.setFiledDesc(fieldAnnotation.value());
                apiParam.setMustInput(fieldAnnotation.mustInput());
                apiParams.add(apiParam);
                if (!isBasicType(field.getType())) {
                    apiParam.setSubApiParam(new ArrayList<ApiParam>());
                    Object o = field.getType().newInstance();
                    Field[] fields = o.getClass().getDeclaredFields();
                    parseFileds(fields, apiParam.getSubApiParam());
                }
            }
        }


        return apiParams;
    }
}
