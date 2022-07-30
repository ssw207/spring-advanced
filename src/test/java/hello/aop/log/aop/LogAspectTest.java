package hello.aop.log.aop;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(LogAspect.class)
@SpringBootTest
class LogAspectTest {

    @Autowired
    MemberService memberService;

    @Test
    void log() {
        memberService.hello("helloA");
    }
}