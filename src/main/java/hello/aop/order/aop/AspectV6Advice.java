package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()")
    public Object dotransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트렌젝션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트렌젝션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜젝션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈 롤백] {}", joinPoint.getSignature());
        }
    }

    // 조인포인트(타겟 객체)가 실행전에 자동으로 실행된다
    @Before("hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    // 조인포인트 실행후 실행. 조인포인트의 결과 값을 가져올수있으나 결과값을 변경해서 리턴할수는 없다
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) { // returning에 지정한 result이름으로 joinpoint 결과 값이 넘어온다
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) { // 자동으로 ex가 throw를 던진다
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex); 
    }

    // finally와 같음
    @After("hello.aop.order.aop.Pointcuts.allOrder() && hello.aop.order.aop.Pointcuts.allService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
