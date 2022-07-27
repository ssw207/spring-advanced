package hello;

import hello.trace.trace.logtrace.LogTrace;
import hello.trace.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@Import(AppV1Config.class) // @Configuration 명시적으로 설정파일을 등록할때 사용
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
//@Import(AopConfig.class)
//@SpringBootApplication(scanBasePackages = "hello.proxy.app") // SpringBootApplication 컴포넌트 스캔의 기본위치는 현재 클래스 기준으로 스캔한다.

//@Import(AspectV1.class) // bean으로 등록됨
//@Import(AspectV2.class) // bean으로 등록됨
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
//@Import(AspectV6Advice.class)
@SpringBootApplication(scanBasePackages = {"hello.aop.order", "hello.aop.member", "hello.aop.exam"}) // SpringBootApplication 컴포넌트 스캔의 기본위치는 현재 클래스 기준으로 스캔한다.
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}

