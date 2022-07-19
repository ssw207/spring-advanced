package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Order(2)
    @Aspect
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()") // 포인트컷(적용되는 위치), 프록시 메서드만 적용됨
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // advice(적용로직)
            log.info("[log] {}", joinPoint.getSignature()); // join poin 시그니처
            return joinPoint.proceed();
        }
    }

    @Order(1)
    @Aspect
    public static class TxAspect {
        // hello.aop.order 하위 패키지 , 클래스이름이 *Service
        @Around("hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()")
        public Object dotransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트렌젝션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트렌젝션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜젝션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈 롤백] {}", joinPoint.getSignature());
            }
        }
    }
}
