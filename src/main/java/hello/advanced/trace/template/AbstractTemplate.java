package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/*
    템플릿 메서드 패턴 단점
    1. 자식 - 부모가 강결합되어 있음
       : 자식클래스는 부모클래스애 의존한다. 부모가 바뀌면 자식클래스의 모두 확인해야한다.
    2. 부모클래스의 기능이 필요없더라도 무조건 코드를 상속받아야한다.
 */
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String msg) {
        TraceStatus status = null;

        try {
            status = trace.begin(msg);

            //로직 호출
            T result = call();

            trace.end(status);
            return result;
        } catch (Exception exception) {
            trace.exception(status, exception);
            throw exception;
        }
    }

    // 자식메서드에서 구체적인 동작을 위임
    protected abstract T call();
}
