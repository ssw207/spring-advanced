package hello.comm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommUtils {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
