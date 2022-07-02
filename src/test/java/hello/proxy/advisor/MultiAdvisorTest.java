package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MultiAdvisorTest {
    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest1() {
        //proxy2(advisor2) -> proxy1(advisor1) -> target

        //proxy1 생성
        ProxyFactory proxyFactory = new ProxyFactory(new ServiceImpl());
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor1())); //advisor 주입. 주입하지 않으면 advice가 적용되지 않고 target 메서드만 실행됨
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory.getProxy();

        //proxy2 생성, target -> proxy1 입력
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        proxyFactory2.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor2())); //advisor 주입. 주입하지 않으면 advice가 적용되지 않고 target 메서드만 실행됨
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    @DisplayName("프록시1, 여러 어드바이저")
    void multiAdvisorTest2() {
        //client -> proxy -> advisor2 -> advisor1 -> target
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor2());

        //proxy1 생성
        ProxyFactory proxyFactory = new ProxyFactory(new ServiceImpl());
        //주입한 순서대로 실행
        proxyFactory.addAdvisor(advisor1); 
        proxyFactory.addAdvisor(advisor2);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory.getProxy();
        
        proxy1.save();
    }

    @Slf4j
    static class Advisor1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advise1 호출");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advisor2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advise2 호출");
            return invocation.proceed();
        }
    }
}
