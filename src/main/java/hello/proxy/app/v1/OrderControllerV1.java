package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping //스프링 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러 인식한다.
@ResponseBody // http 메시지컨버터를 이용해 리턴한다..
public interface OrderControllerV1 {

    @GetMapping("/v1/req")
    String request(@RequestParam("itemId") String itemId); // 인터페이스는 @RequestParam("itemId") 넣어주지 않으면 컴파일시 인식이 안되는경우가 있다.

    @GetMapping("/v1/no-log")
    String noLog();
}
