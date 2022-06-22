package hello.proxy.pureproxy.code;

import hello.comm.CommUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        CommUtils.sleep(1000);
        return "data";
    }
}
