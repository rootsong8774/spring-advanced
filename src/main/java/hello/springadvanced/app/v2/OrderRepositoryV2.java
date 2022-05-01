package hello.springadvanced.app.v2;

import hello.springadvanced.trace.TraceId;
import hello.springadvanced.trace.TraceStatus;
import hello.springadvanced.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV2 {
    
    private final HelloTraceV2 trace;
    
    public void save(TraceId traceId, String itemId) {
    
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");
            //저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
        
    }
    
    private void sleep(int millis) {
    
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
