package hello.proxy.pureproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxTest {

    @Test
    void noProx() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }

    @Test
    void prox() {
        ConcreteLogic logic = new TimeProxy(new ConcreteLogic());
        ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }
}
