package hello.advanced.app.v4;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryV3;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {

            @Override
            protected String call() {
                orderRepositoryV3.save(itemId);
                return null;
            }
        };

        template.execute("orderService.orderItem()");
    }
}
