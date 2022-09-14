package ru.tyshchenko.springexamples.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NotNullReturningAspect {

  @Around("@annotation(ru.tyshchenko.springexamples.annotation.NotNullReturning)")
  public Object check(ProceedingJoinPoint pjp) throws Throwable {
    Object value = pjp.proceed();
    if (value == null) {
      throw new IllegalStateException(
          "Result of method " + pjp.getClass() + "." + pjp.getSignature().getName()
          + " can't return null");
    }
    return value;
  }
}
