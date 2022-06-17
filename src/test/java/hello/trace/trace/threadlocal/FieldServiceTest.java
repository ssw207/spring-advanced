package hello.trace.trace.threadlocal;

import hello.trace.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();
    
    @Test
    public void field() throws Exception {
        log.info("실행");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        //각각의 로직을 실행
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        
        threadA.start(); // A로직 실행
        //sleep(2000); // 2초를쉰다 동시성 문제가 발생하지 않는다.
        sleep(200); // 0.2초를쉰다 동시성 문제 발생함 (A처리전에 B가 동작하기 때문)
        threadB.start(); // B 시작

        sleep(2000); // 메인 쓰레드 종료 대기
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

