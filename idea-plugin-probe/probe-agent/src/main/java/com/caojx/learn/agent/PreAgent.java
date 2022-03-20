package com.caojx.learn.agent;

import org.assertj.core.internal.bytebuddy.agent.builder.AgentBuilder;
import org.assertj.core.internal.bytebuddy.implementation.MethodDelegation;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * 探针入口
 * 此处的字节码增强方式，采用的 Byte-Buddy 字节码框架，它的使用方式更加简单，在使用的过程中有些像使用 AOP 的拦截方式一样，获取到你需要的信息。
 * <p>
 * 此外在 gradle 打包构建的时候，需要添加 shadowJar 模块，把 Premain-Class 打包进去。这部分代码中可以查看
 */
public class PreAgent {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            return builder
                    .method(ElementMatchers.named("executeInternal")) // 拦截任意方法
                    .intercept(MethodDelegation.to(MonitorMethod.class)); // 委托
        };

        new AgentBuilder
              .Default()
                .type(ElementMatchers.nameStartsWith("com.mysql.jdbc.PreparedStatement"))
                .transform(transformer)
                .installOn(inst);


    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
