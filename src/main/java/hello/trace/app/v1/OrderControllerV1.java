package hello.trace.app.v1;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderServiceve;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/req")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("orderController.request()");
            orderServiceve.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return "ok";
    }

}
