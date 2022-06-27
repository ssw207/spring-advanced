package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.trace.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"}; //패텀에 매칭되는경우만 필터를 호출한다

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1(logTrace));

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader() //클래스 로더
                , new Class[]{OrderControllerV1.class} //대상 인터페이스 클래스
                , new LogTraceFilterHandler(target, logTrace, PATTERNS)); // 핸들러

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader() //클래스 로더
                , new Class[]{OrderServiceV1.class} //대상 인터페이스 클래스
                , new LogTraceFilterHandler(target, logTrace, PATTERNS)); // 핸들러

        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        // 프록시 target
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader() //클래스 로더
                , new Class[]{OrderRepositoryV1.class} //대상 인터페이스 클래스
                , new LogTraceFilterHandler(target, logTrace, PATTERNS)); // 핸들러

        return proxy;
    }
}
