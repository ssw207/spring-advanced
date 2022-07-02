package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {
    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //일반적인 어드바이저, 포인트컷1, 어드바이스1
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());// 항상참
        proxyFactory.addAdvisor(advisor); //advisor 주입. 주입하지 않으면 advice가 적용되지 않고 target 메서드만 실행됨
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 포인트컷")
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //일반적인 어드바이저, 포인트컷1, 어드바이스1
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());// 항상참
        proxyFactory.addAdvisor(advisor); //advisor 주입. 주입하지 않으면 advice가 적용되지 않고 target 메서드만 실행됨
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save(); // 포인트컷 필터 true -> advice 실행
        proxy.find(); // 포인트컷 필터 false ->  advice 실행x
    }

    @Test
    @DisplayName("스프링이 제공하는 포인트컷")
    void advisorTest3() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut(); // 이름기만 포인트컷
        pointcut.setMappedNames("save"); // 메서드 이름이 save이름인 경우에만 적용
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());// 항상참
        proxyFactory.addAdvisor(advisor); //advisor 주입. 주입하지 않으면 advice가 적용되지 않고 target 메서드만 실행됨
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save(); // 포인트컷 필터 true -> advice 실행
        proxy.find(); // 포인트컷 필터 false ->  advice 실행x
    }

    static class MyPointCut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";

        //매칭되는 메서드만 advice 적용
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 호출 method={}, targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false; //true면 아래 matches가 실행. false면 정적정보만 가져와 캐싱을통해 성능향상이 가능하기 때문
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
