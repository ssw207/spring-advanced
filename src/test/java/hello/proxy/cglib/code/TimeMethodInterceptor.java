package hello.proxy.cglib.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor { // spring 라이브러리 사용

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("Time 프록시 실행");
        long start = System.currentTimeMillis();

        //target의 method를 args 파라미터로 실행
        Object result = methodProxy.invoke(target, args);// methodProxy가 속도가 더빠름

        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("Time 프록시 종료  resultTime={}", resultTime);
        return result;
    }
}
