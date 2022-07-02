package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {
    
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target); // 이미 팩토리에서 타겟정보를 알고 있음
        proxyFactory.addAdvice(new TimeAdvice()); // advice 주입
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass()); //proxyClass=class com.sun.proxy.$Proxy10 -> 인터페이스가 있으므로 jdk 동적프록시가 적용

        proxy.save(); // 프록시실행

        // 프록시 팩토리로 생성됐을때만 사용가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 cglib 동적 프록시 사용")
    void concreteProxy() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target); // 이미 팩토리에서 타겟정보를 알고 있음
        proxyFactory.addAdvice(new TimeAdvice()); // advice 주입
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass()); //class hello.proxy.common.service.ConcreteService$$EnhancerBySpringCGLIB$$d148537 -> 인터페이스가 없으므로 cglib으로 생성

        proxy.call(); // 프록시실행

        // 프록시 팩토리로 생성됐을때만 사용가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어서 cglib을 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target); // 이미 팩토리에서 타겟정보를 알고 있음
        proxyFactory.setProxyTargetClass(true); // 항상 cglib 기반으로 생성
        proxyFactory.addAdvice(new TimeAdvice()); // advice 주입
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass()); // class hello.proxy.common.service.ServiceImpl$$EnhancerBySpringCGLIB$$7f00e8d1

        proxy.save(); // 프록시실행

        // 프록시 팩토리로 생성됐을때만 사용가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }
}
