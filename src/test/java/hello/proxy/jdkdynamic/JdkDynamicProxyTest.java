package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        // target 생성
        AImpl target = new AImpl();
        
        // handler 생성 - 동적프록시에 적용할 핸들러 로직
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        
        /*
            proxy 생성
            - AInterface.class.getClassLoader() : 어디에 생성할지,
            - new Class[] {AInterface.class} : 어떤 인터페이스를 이용해 생성할지
            - handler : 프록시 로직
         */
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("targetCall={}",target.getClass()); // class hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyCall={}", proxy.getClass()); // class com.sun.proxy.$Proxy9 -> AInterface인터페이스를 이용해서 구현
    }

    @Test
    void dynamicB() {
        // target 생성
        BImpl target = new BImpl();

        // handler 생성
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetCall={}",target.getClass()); // class hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyCall={}", proxy.getClass()); // class com.sun.proxy.$Proxy9 -> AInterface인터페이스를 이용해서 구현
    }
}
