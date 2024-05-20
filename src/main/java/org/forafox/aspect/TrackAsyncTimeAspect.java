package org.forafox.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.forafox.domain.MethodData;
import org.forafox.domain.enums.AnnotationType;
import org.forafox.service.MethodDataService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TrackAsyncTimeAspect {
    private final MethodDataService methodDataService;
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
                var methodData = new MethodData();
                methodData.setMethodName(joinPoint.getSignature().toShortString().replace("(..)", ""));
                methodData.setExecuteNanoTime(executionNanoTime);
                methodData.setExecuteMilliTime(executionNanoMilli);
                methodData.setExecuteDate(new Date());
                methodData.setAnnotationType(AnnotationType.ASYNC);
                methodDataService.save(methodData);

                return proceed;
            } catch (Throwable e) {
                log.error("Error AsyncRunnerAspect", e);
                throw new RuntimeException(e);
            }
        });

        return future.get();
    }


}
