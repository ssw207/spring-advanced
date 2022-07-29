package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        /**
         * external만 aop가 걸린다
         *
         * 1.external 호출시 프록시를 호출
         * 2.target실행
         * 3.callService.external 안에서 internal 호출시 프록시를 호출하지 않고 target의 internal 메서드를 호출
         * 4.internal은 프록시를 호출하지 않았기 때문에 aop가 적용안된다.
         *
         */
        log.info("call external");
        this.internal(); // 내부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
