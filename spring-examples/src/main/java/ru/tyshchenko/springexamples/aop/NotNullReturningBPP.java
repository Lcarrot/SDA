package ru.tyshchenko.springexamples.aop;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.tyshchenko.springexamples.annotation.NotNullReturning;

@Component
public class NotNullReturningBPP implements BeanPostProcessor {

  private final Map<String, Set<Method>> interceptedMethod = new ConcurrentHashMap<>();

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    Method[] methods = bean.getClass().getDeclaredMethods();
    Set<Method> proxyMethods = new HashSet<>();
    for (Method method : methods) {
      if (method.getAnnotation(NotNullReturning.class) != null) {
        proxyMethods.add(method);
      }
    }
    if (!proxyMethods.isEmpty()) {
      interceptedMethod.put(beanName, proxyMethods);
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (interceptedMethod.containsKey(beanName)) {
      ProxyFactory proxyFactory = new ProxyFactory();
      proxyFactory.setTarget(bean);
      Advice advice = new NotNullReturningHandlerInterceptor(interceptedMethod.get(beanName), bean);
      proxyFactory.addAdvice(advice);
      return proxyFactory.getProxy();
    }
    return bean;
  }

  private record NotNullReturningHandlerInterceptor(Set<Method> methods,
                                                    Object sourceObject)
      implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
      Method method = invocation.getMethod();
      if (methods.contains(method)) {
        Object value = invocation.getMethod().invoke(sourceObject, invocation.getArguments());
        if (value == null) {
          throw new IllegalStateException(
              "Result of method " + sourceObject.getClass() + "." + method.getName()
              + " can't return null");
        }
        return value;
      }
      return method.invoke(sourceObject, invocation.getArguments());
    }
  }
}
