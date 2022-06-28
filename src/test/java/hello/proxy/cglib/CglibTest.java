package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        //cglib를 만드는 코드
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); // 구체클래스를 기반으로 생서
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 핸들러
        ConcreteService proxy = (ConcreteService) enhancer.create();// 프록시 생성 - ConcreteService 클래스를 상속받아 생성하므로 캐스팅가능
        log.info("targetClas={}", target.getClass()); //targetClas=class hello.proxy.common.service.ConcreteService
        log.info("proxcyClass={}", proxy.getClass()); //proxcyClass=class hello.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$25d6b0e3

        proxy.call();
    }
}
