package hello.aop.pointcust;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void name() {
        log.info("memberService proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void all() {}

        @Around("all()")
        public Object logArgs1 (ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{} {}",joinPoint.getSignature(), arg1);
            return  joinPoint.proceed();
        }

        @Around("all() && args(arg, ..)")
        public Object logArgs1 (ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2]{} {}",joinPoint.getSignature(), arg);
            return  joinPoint.proceed();
        }

        @Before("all() && args(arg, ..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] args={}", arg);
        }

        @Before("all() && this(obj)") // 프록시 객체
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this]{}, obj={}", joinPoint.getSignature(), obj.getClass());

        }

        @Before("all() && target(obj)") // 실제 대상구현체
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[targetArgs]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("all() && @target(annotation)") // 실제 대상구현체
        public void targetArgs2(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[targetArgs2]{}, obj={}", joinPoint.getSignature(), annotation);
        }

        @Before("all() && @within(annotation)") // 실제 대상구현체
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[atWithin]{}, obj={}", joinPoint.getSignature(), annotation);
        }

        @Before("all() && @@annotation(annotation)") // 실제 대상구현체
        public void atAnno(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[atAnno]{}, value={}", joinPoint.getSignature(), annotation.value());
        }
    }
}
