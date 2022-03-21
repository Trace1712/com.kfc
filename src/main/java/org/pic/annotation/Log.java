package org.pic.annotation;

import java.lang.annotation.*;

/**
 * @author wucy03
 * @since 2022/3/21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {
}
