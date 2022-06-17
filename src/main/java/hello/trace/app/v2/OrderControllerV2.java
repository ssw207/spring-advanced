package hello.trace.app.v2;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceve;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/req")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("orderController.request()");
            orderServiceve.orderItem(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        return "ok";
    }

}
