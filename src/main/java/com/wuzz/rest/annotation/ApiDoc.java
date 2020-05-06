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
 * Description 描述:用于属性上打注解
 */
@Target(ElementType.FIELD)//表示注解运行在哪里 这里表示只能注解再类上面
@Retention(RetentionPolicy.RUNTIME)//表示注解的(生命周期)哪来出现
public @interface ApiDoc {

    String value() default "";

    /**
     * 是否必传
     * @return
     */
    boolean  mustInput() default true;
}
