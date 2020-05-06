package com.wuzz.rest.annotation;


import com.wuzz.rest.EnabledApiMarkerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnabledApiMarkerConfiguration.class)
public @interface EnableRestfulApi {
}
