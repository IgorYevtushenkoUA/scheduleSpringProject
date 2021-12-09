package com.example.faculty.util.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Aspect
@Component
public class TimeMeasureAspect {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(public * *(..)) && @annotation(com.example.faculty.util.annotations.EvaluateTime)")
    public void evaluateTimeMethods() {
    }

    @Around("evaluateTimeMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        logger.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }
}
