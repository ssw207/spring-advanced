package hello.trace.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private Component target;

    public TimeDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("time decorator 실행");
        long start = System.currentTimeMillis();

        String result = target.operation();

        long end = System.currentTimeMillis();
        log.info("time decorator 종료 resultTime={}", end - start);
        return result;
    }
}
