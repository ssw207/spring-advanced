package hello.aop.internalall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV1 {

    //private final ApplicationContext context;
    private final ObjectProvider<CallServiceV1> callServiceProvider;

    public void external() {
        log.info("call external");
        CallServiceV1 callServiceV1 = callServiceProvider.getObject(); // 실제 객체를 사용하는 시점에서 빈을 조회한다
        callServiceV1.internal(); // 내부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
