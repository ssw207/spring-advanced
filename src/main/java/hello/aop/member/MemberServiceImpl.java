package hello.aop.member;

import hello.aop.log.LogTarget;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    @LogTarget
    public String hello(String params) {
        return "ok";
    }

    public String internal(String params) {
        return "ok";
    }
}
