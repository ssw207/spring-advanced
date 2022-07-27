package hello.aop.internalall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성된 의존성을 setter로 주입한다.
     * 스프링은 생성자로 객체를 생성 -> 주입하는 단계로 나뉘어 지는데
     * 생성자를 통해 자기자신을 주입받게하면 생성되지도 않은 자기자신을 참조하므로 순환참조 문제가 발생한다
     * setter로 주입받는경우 이미 생성된 자기자신을 주입받기 떄문에 순환참조가 발생하지 않는다.
     */


    public void external() {
        log.info("call external");
        internal(); // 내부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
