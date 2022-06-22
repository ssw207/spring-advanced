package hello.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component target;

    public MessageDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        String result = target.operation();
        String decoResult = "*****" + result + "*****";

        log.info("꾸미기전 {}, 꾸미기 후 {}", result, decoResult);
        return decoResult;
    }
}
