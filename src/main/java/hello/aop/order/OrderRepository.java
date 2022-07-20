package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    public void save(String itemId) {
        log.info("OrderRepository 실행");
        if ("ex".equals(itemId)) {
            throw new IllegalArgumentException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
