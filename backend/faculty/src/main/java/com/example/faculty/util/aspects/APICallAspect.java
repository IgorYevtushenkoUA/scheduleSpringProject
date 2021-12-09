package com.example.faculty.util.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class APICallAspect {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(public * *(..)) && within(@com.example.faculty.util.annotations.LogInfo *)")
    public void logParamsMethods() {
    }

    @Around("logParamsMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        StringBuffer buffer = new StringBuffer();
        String method = pjp.getSignature().getName();
        buffer.append("Call method: ");
        buffer.append(method);
        buffer.append("(");
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            buffer.append(args[i]).append(",");
        }
        if (args.length > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append(")");

        logger.info(buffer.toString());
        Object retval = pjp.proceed();
        logger.info("Method " + method + " returned: " + retval.toString());
        return retval;
    }
}
