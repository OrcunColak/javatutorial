package com.colak.proxy.mock_with_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Mocker implements InvocationHandler {

    private static final Mocker handler = new Mocker();

    private static final ThreadLocal<Method> methodThreadLocal = new ThreadLocal<>();

    private static final Map<String, Object> methodToReturnValue = new HashMap<>();

    public static <T> T createMock(Class<T> clazz) {
        // Returns a proxy instance for the specified interfaces
        // that dispatches method invocations to the specified invocation handler.
        Object proxy = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                handler
        );
        return (T) proxy;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        // when they call when(obj.getSomething)), we register it as a mocked method we are mocking at this point
        methodThreadLocal.set(method);
        if (methodToReturnValue.containsKey(method.getName())) {
            return methodToReturnValue.get(method.getName());
        }
        return null;
    }

    public static <T> OngoingMocking<T> when(T methodCall) {
        return new OngoingMocking<>();
    }

    public static class OngoingMocking<T> {
        public void thenReturn(T value) {
            Method method = methodThreadLocal.get();
            methodThreadLocal.remove();
            methodToReturnValue.put(method.getName(), value);
        }
    }

}




