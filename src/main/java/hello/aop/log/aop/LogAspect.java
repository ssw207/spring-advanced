package hello.aop.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution(* hello.aop..*(..))")
    public void all() {}

    @Around("all()")
    public void timeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            //log.info("[{}][{}][시작]", uuid, joinPoint.getSignature());
            joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long lastTaskTimeMillis = stopWatch.getLastTaskTimeMillis();
            log.info("[{}][{}mms]", joinPoint.getSignature(), lastTaskTimeMillis);
        }
    }

    @Before("all() && @annotation(hello.aop.log.LogTarget)")
    public void paramLog(JoinPoint joinPoint) {
        log.info("[{}][{}]", joinPoint.getSignature(), joinPoint.getArgs());
    }
}
