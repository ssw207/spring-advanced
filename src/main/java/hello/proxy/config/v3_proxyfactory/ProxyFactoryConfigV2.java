package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTracedAdvice;
import hello.trace.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        ProxyFactory proxyFactory = new ProxyFactory(new OrderControllerV2(orderServiceV2(logTrace)));
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("proxyFactory OrderControllerV2 proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        ProxyFactory proxyFactory = new ProxyFactory(new OrderServiceV2(orderRepositoryV2(logTrace)));
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("proxyFactory OrderServiceV2 proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        ProxyFactory proxyFactory = new ProxyFactory(new OrderRepositoryV2());
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        log.info("proxyFactory orderRepositoryV2 proxy={}", proxy.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*", "save*");

        LogTracedAdvice advice = new LogTracedAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
