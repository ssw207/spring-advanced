package hello.trace.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void excute() {
        long startTime = System.currentTimeMillis();

        call(); // 위임한 비즈니스 로직을 실행

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }

    // 자식 클래스에 비즈니스 로직을 위임
    protected abstract void call();
}
