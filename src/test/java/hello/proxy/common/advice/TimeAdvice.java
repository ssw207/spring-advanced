package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    //프록시 팩토리에서 target을 이미 넣어주기때문에 target을 주입하지 않아도됨

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Time 프록시 실행");
        long start = System.currentTimeMillis();
        
        Object result = invocation.proceed(); // 알아서 target을 찾아서 실행해줌. target클래스 정보는 methodInvocation 내에 있음

        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("Time 프록시 종료  resultTime={}", resultTime);
        return result;
    }
}
