package hello.trace.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        return "data";
    }
}
