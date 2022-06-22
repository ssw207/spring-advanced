package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection() {
        Hello target = new Hello();

        // 공통 로직1 시작
        String result1 = target.callA();
        // 공통 로직1 종료

        // 공통 로직2 시작
        String result2 = target.callB();
        // 공통 로직2 종료

    }

    @Test
    void reflection1() throws Exception {
        // 클래스의 메타정보를 얻는다.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        //메서드를 실행할 객체
        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");// callA 메서드 정보를 얻어옴
        Object result1 = dynamicCall(target, methodCallA);
        log.info("result1={}", result1);

        Method methodB = classHello.getMethod("callB");
        Object result2 = dynamicCall(target, methodB);
        log.info("result2={}",result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스의 메타정보를 얻는다.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        //메서드를 실행할 객체
        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");// callA 메서드 정보를 얻어옴
        Object result1 = dynamicCall(target, methodCallA);
        log.info("result1={}", result1);

        Method methodB = classHello.getMethod("callB");
        Object result2 = dynamicCall(target, methodB);
        log.info("result2={}",result2);
    }

    private Object dynamicCall(Hello target, Method methodCallA) throws IllegalAccessException, InvocationTargetException {
        return methodCallA.invoke(target);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            return "a";
        }

        public String callB() {
            return "b";
        }
    }
}
