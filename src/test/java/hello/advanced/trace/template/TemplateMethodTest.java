package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    public void tmplateMethodVo0() throws Exception {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }

    /**
     * 템플릿 메소드 패턴
     * @throws Exception
     */
    @Test
    public void templateMethodV1() throws Exception {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.excute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.excute();
    }
    
    @Test
    public void templateMethod2() throws Exception {
        AbstractTemplate abstractTemplate = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("바로 만든 비즈니스 로직1 실행");
            }
        };

        /*
        클래스이름=class hello.advanced.trace.template.ContextV1Test$1
        ContextV1Test 클래스 내부에 이름없는 클래스이므로 임의로 자바가 ContextV1Test$1로 만듬
         */
        log.info("클래스이름={}", abstractTemplate.getClass()); 
        abstractTemplate.excute();

        AbstractTemplate abstractTemplate2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("바로 만든 비즈니스 로직2 실행");
            }
        };

        log.info("클래스이름={}", abstractTemplate.getClass());
        abstractTemplate2.excute();
    }
}
