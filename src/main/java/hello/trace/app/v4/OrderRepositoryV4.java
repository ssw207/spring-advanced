package hello.trace.app.v4;

import hello.trace.trace.logtrace.LogTrace;
import hello.trace.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {

            @Override
            protected String call() {
                //저장 로직
                if ("ex".equals(itemId)) {
                    throw new IllegalArgumentException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        };

        template.execute("orderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
