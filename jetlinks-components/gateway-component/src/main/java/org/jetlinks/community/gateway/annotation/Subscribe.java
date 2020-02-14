package org.jetlinks.community.gateway.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 订阅来自消息网关的消息
 *
 * @author zhouhao
 * @see org.jetlinks.community.gateway.MessageSubscriber
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Subscribe {

    @AliasFor("value")
    String[] topics() default {};

    @AliasFor("topics")
    String[] value() default {};

    boolean shareCluster() default false;

}
