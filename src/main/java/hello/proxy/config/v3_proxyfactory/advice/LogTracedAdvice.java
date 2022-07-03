package hello.proxy.config.v3_proxyfactory.advice;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTracedAdvice implements MethodInterceptor {

    private final LogTrace logTrace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            Method method = invocation.getMethod();
            String msg = method.getDeclaringClass().getSimpleName() + "," + method.getName() + "()";
            status = logTrace.begin(msg);

            //로직호출
            Object result = invocation.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception exception) {
            logTrace.exception(status, exception);
            throw exception;
        }
    }
}
