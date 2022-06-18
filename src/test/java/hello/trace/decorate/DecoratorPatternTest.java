package hello.trace.decorate;

import hello.trace.decorate.code.DecoratorPatternClient;
import hello.trace.decorate.code.MessageDecorator;
import hello.trace.decorate.code.RealComponent;
import hello.trace.decorate.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    public void noDecorator() throws Exception {
        DecoratorPatternClient client = new DecoratorPatternClient(new RealComponent());
        client.execute();
    }

    @Test
    public void decorator1() throws Exception {
        DecoratorPatternClient client = new DecoratorPatternClient(new MessageDecorator(new RealComponent()));
        client.execute();
    }

    @Test
    public void decorator2() throws Exception {
        DecoratorPatternClient client = new DecoratorPatternClient(new TimeDecorator(new MessageDecorator(new RealComponent())));
        client.execute();
    }
}
