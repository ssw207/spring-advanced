package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

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