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
@Target({ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ApiRequestParam {

    /**
     * 名称
     * @return
     */
    String name() default "";

    /**
     * 说明
     * @return
     */
    String desc() default "";


    /**
     * 是否必传
     * @return
     */
    boolean  mustInput() default true;
}
