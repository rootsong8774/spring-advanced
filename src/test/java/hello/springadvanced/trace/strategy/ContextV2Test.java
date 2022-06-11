package hello.springadvanced.trace.strategy;

import hello.springadvanced.trace.strategy.code.strategy.ContextV2;
import hello.springadvanced.trace.strategy.code.strategy.Strategy;
import hello.springadvanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.springadvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    
    /**
     * 전략 패턴 적용
     */
    @Test
    public void strategyV1() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }
    
    /**
     * 전략 패턴 적용 익명 내부 클래스
     */
    @Test
    public void strategyV2() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy(){
    
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        contextV2.execute(new Strategy(){
        
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
    }
    
    /**
     * 전략 패턴 적용 익명 내부 클래스 람다적용
     */
    @Test
    public void strategyV3() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비즈니스 로직 1 실행"));
        contextV2.execute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
