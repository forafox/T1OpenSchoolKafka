package org.forafox.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.forafox.domain.MethodData;
import org.forafox.domain.enums.AnnotationType;
import org.forafox.service.MethodDataService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackTimeAspect {
    private final MethodDataService methodDataService;

    @Around("@annotation(org.forafox.annotation.TrackTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNano = System.nanoTime();
        long startMilli = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTimeNano = System.nanoTime() - startNano;
        long executionTimeMilli = System.currentTimeMillis() - startMilli;

        var methodData = new MethodData();
        methodData.setMethodName(joinPoint.getSignature().toShortString().replace("(..)", ""));
        methodData.setExecuteNanoTime(executionTimeNano);
        methodData.setExecuteMilliTime(executionTimeMilli);
        methodData.setExecuteDate(new Date());
        methodData.setAnnotationType(AnnotationType.NoASYNC);
        methodDataService.save(methodData);
        return proceed;
    }


}
