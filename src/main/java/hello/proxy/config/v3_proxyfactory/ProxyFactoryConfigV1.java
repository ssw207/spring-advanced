package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
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
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        ProxyFactory proxyFactory = new ProxyFactory(new OrderRepositoryV1Impl());
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
        log.info("proxyFactory proxy={}", proxy);
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*", "save*");

        LogTracedAdvice advice = new LogTracedAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
