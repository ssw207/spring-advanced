package hello.trace.app.v3;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderServiceve;
    private final LogTrace trace;

    @GetMapping("/v3/req")
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
