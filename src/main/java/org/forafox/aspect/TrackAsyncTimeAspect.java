package org.forafox.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.forafox.domain.enums.AnnotationType;
import org.forafox.kafka.service.messaging.event.SendMethodDataEvent;
import org.forafox.kafka.service.messaging.producer.Producer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TrackAsyncTimeAspect {
    private final Producer producer;
    @Pointcut("@annotation(org.forafox.annotation.TrackAsyncTime)")
    public void asyncRunnerPointcut() {}

    @Around("asyncRunnerPointcut()")
    public Object asyncRunner(ProceedingJoinPoint joinPoint) throws Throwable {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            try {
                long startNano = System.nanoTime();
                long startMillis = System.currentTimeMillis();

                Object proceed = joinPoint.proceed();

                long executionNanoTime = System.nanoTime() - startNano;
                long executionNanoMilli = System.currentTimeMillis() - startMillis;
                var methodData = new SendMethodDataEvent();
                methodData.setMethodName(joinPoint.getSignature().toShortString().replace("(..)", ""));
                methodData.setExecuteNanoTime(executionNanoTime);
                methodData.setExecuteMilliTime(executionNanoMilli);
                methodData.setExecuteDate(new Date());
                methodData.setAnnotationType(AnnotationType.ASYNC);
                producer.sendAsyncMethodData(methodData);

                return proceed;
            } catch (Throwable e) {
                log.error("Error AsyncRunnerAspect", e);
                throw new RuntimeException(e);
            }
        });

        return future.get();
    }


}
