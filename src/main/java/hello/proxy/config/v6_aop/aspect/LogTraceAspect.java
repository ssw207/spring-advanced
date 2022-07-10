package hello.proxy.config.v6_aop.aspect;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace logTrace;

    // 어드바이저
    @Around("execution(* hello.proxy.app..*(..))") // 포인트컷
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 어드바이스 로직
        TraceStatus status = null;

//        joinPoint.getTarget(); // 실제호출대상
//        joinPoint.getArgs(); // 전달인자
//        joinPoint.getSignature(); // 조인포인트 시그니처
        try {
            String msg = joinPoint.getSignature().toShortString(); //메서드 정보
            status = logTrace.begin(msg);

            //로직호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception exception) {
            logTrace.exception(status, exception);
            throw exception;
        }
    }
}
