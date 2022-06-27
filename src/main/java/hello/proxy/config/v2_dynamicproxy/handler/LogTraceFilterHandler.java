package hello.proxy.config.v2_dynamicproxy.handler;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //메서드 이름 필터
        String methodNAme = method.getName();

        //패턴과 매칭이 안되면
        if (!PatternMatchUtils.simpleMatch(patterns, methodNAme)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String msg = method.getDeclaringClass().getSimpleName() + "," + method.getName() + "()";
            status = logTrace.begin(msg);

            //로직호출
            Object result = method.invoke(target, args); // target의 method를 args를 파라미터로 실행

            logTrace.end(status);
            return result;
        } catch (Exception exception) {
            logTrace.exception(status, exception);
            throw exception;
        }
    }
}
