package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();
    
    @Test
    public void begin_end_lv2() throws Exception {
        //given
        TraceStatus st1 = trace.begin("hello1");
        TraceStatus st2 = trace.begin("hello2");

        trace.end(st2);
        trace.end(st1);
    }

    @Test
    public void begin_exception_lv2() throws Exception {
        //given
        TraceStatus st1 = trace.begin("hello1");
        TraceStatus st2 = trace.begin("hello2");

        trace.exception(st2, new IllegalArgumentException());
        trace.exception(st1, new IllegalArgumentException());
    }

}