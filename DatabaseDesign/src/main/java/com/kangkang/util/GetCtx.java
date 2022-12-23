package com.kangkang.util;

import com.kangkang.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GetCtx {
    private static final AnnotationConfigApplicationContext ctx;
    static {
        ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
    }
    public static AnnotationConfigApplicationContext getCtx(){
        return ctx;
    }
}
