package hello.trace.app.v5;

import hello.trace.trace.callback.TraceTemplate;
import hello.trace.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/req")
    public String request(String itemId) {
        return template.execute("OrderControllerV1.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }

}
