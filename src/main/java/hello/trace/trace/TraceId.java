package hello.trace.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = level;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        //aaaa-ddd-ccccf-ffff-dddd -> 앞만 자르면 중복될수 있지만 로그이기때문에 어쩌다가 겹쳐도 큰 문제는 없음.
        return UUID.randomUUID().toString().substring(0,8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
