package hello.trace.trace.callback;

import hello.trace.trace.TraceStatus;
import hello.trace.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String msg, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(msg);
            //로직 호출
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception exception) {
            trace.exception(status, exception);
            throw exception;
        }
    }
}
