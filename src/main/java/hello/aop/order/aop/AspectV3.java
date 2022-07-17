package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect // 실제 포인트컷과 어드바이스, 어드바이서를 내부적으로 생성함
public class AspectV3 {

    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {
    }  // 포인트컷 시그니쳐

    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    @Around("allOrder()") // 포인트컷(적용되는 위치), 프록시 메서드만 적용됨
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // advice(적용로직)
        log.info("[log] {}", joinPoint.getSignature()); // join poin 시그니처
        return joinPoint.proceed();
    }

    // hello.aop.order 하위 패키지 , 클래스이름이 *Service
    @Around("allOrder() && allService()")
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
