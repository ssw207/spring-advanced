package hello.trace.proxy.pureproxy;

import hello.trace.proxy.pureproxy.code.CacheProxy;
import hello.trace.proxy.pureproxy.code.ProxyPatternClient;
import hello.trace.proxy.pureproxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    
    @Test
    public void noProxyTest() throws Exception {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }
    
    @Test
    public void cacheProxyTest() throws Exception {
        ProxyPatternClient client = new ProxyPatternClient(new CacheProxy(new RealSubject()));
        client.execute();
        client.execute();
        client.execute();
    }
}

