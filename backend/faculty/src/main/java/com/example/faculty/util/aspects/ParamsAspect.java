package com.example.faculty.util.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class ParamsAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(public * *(..)) && @annotation(com.example.faculty.util.annotations.LogParams)")
    public void logParamsMethods() {};

    @Around("logParamsMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Method params for ");
        buffer.append(pjp.getSignature().getName());
        buffer.append("(");

        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            buffer.append(args[i]).append(",");
        }
        if (args.length > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }

        buffer.append(") -> ");

        Object retval = pjp.proceed();
        buffer.append(retval);
        logger.info(buffer.toString());

        return retval;
    }
}
