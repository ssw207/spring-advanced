package hello.trace.trace.stratgy;

import hello.trace.trace.stratgy.code.template.Callback;
import hello.trace.trace.stratgy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴
     * @throws Exception
     */
    @Test
    public void callbackV1() throws Exception {

        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스로직1 실행"));
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니소 로직2 실행");
            }
        });
    }
}
