package hello.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target; // 프록시가 호출할 대상

    /**
     * 
     * @param proxy
     * @param method 프록시가 호출한 메서드 (call)
     * @param args 호출한 메서드 파라미터
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Time 프록시 실행");
        long start = System.currentTimeMillis();

        Object result = method.invoke(target, args);// target의 method를 args 파라미터로 실행한다

        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("Time 프록시 종료  resultTime={}", resultTime);
        return result;
    }
}
