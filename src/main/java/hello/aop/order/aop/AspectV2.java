package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect // 실제 포인트컷과 어드바이스, 어드바이서를 내부적으로 생성함
public class AspectV2 {

    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){}  // 포인트컷 시그니쳐

    @Around("allOrder()") // 포인트컷(적용되는 위치), 프록시 메서드만 적용됨
    public  Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // advice(적용로직)
        log.info("[log] {}", joinPoint.getSignature()); // join poin 시그니처
        return joinPoint.proceed();
    }
}
