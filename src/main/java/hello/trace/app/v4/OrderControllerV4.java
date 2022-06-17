package hello.trace.app.v4;

import hello.trace.trace.logtrace.LogTrace;
import hello.trace.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderServiceve;
    private final LogTrace trace;

    @GetMapping("/v4/req")
    public String request(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {

            @Override
            protected String call() {
                orderServiceve.orderItem(itemId);
                return "ok";
            }
        };

        return template.execute("OrderControllerV1.request()");
    }

}
