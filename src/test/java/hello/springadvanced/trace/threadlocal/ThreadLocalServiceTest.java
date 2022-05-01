package hello.springadvanced.trace.threadlocal;

import hello.springadvanced.trace.threadlocal.code.FieldService;
import hello.springadvanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    
    private ThreadLocalService threadLocalService = new ThreadLocalService();
    
    @Test
    public void field() throws Exception {
        Runnable userA = () -> threadLocalService.logic("userA");
        Runnable userB = () -> threadLocalService.logic("userB");
        
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        
        threadA.start();
//        sleep(2000); //동시성 문제 X
        sleep(100);
        threadB.start();
        
        sleep(3000); //메인 쓰레드 종료 대기
        log.info("main exit");
    }
    
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
