package ru.tyshchenko.aop;

import java.util.Optional;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;
import ru.tyshchenko.annotation.NotNullReturning;

@Singleton
@InterceptorBean(NotNullReturning.class)
public class NotNullReturningInterceptor implements MethodInterceptor<Object, Object> {

  @Override
  public Object intercept(MethodInvocationContext<Object, Object> context) {
    return Optional.ofNullable(context.proceed())
        .orElseThrow(() -> new IllegalStateException(
            "Result of method " + context.getDeclaringType() + "."+ context.getMethodName()
            + " can't return null"));
  }

  @Override
  public int getOrder() {
    return LOWEST_PRECEDENCE;
  }
}
