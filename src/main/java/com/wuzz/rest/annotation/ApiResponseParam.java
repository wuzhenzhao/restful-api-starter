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
 * Description 描述:
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ApiResponseParam {

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
}
