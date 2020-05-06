package com.wuzz.rest.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:注解在接口参数上
 */
@Target(ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ApiMethod {

    /**
     * 接口名称
     * @return
     */
    String name() default "";

    /**
     * 接口描述
     * @return
     */
    String desc() default "";

    /**
     * 请求参数
     * @return
     */
    ApiRequestParam [] requestParams() default {};

    /**
     * 响应参数
     * @return
     */
    ApiResponseParam [] responseParams() default {};
}
