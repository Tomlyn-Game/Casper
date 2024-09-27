package moe.protasis.casper.resolver;

import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.function.Supplier;

public class SimpleTypeResolver<T> implements HandlerMethodArgumentResolver {
    private final Supplier<T> supplier;
    private final Class<T> tClass;

    public SimpleTypeResolver(Class<T> tClass, Supplier<T> supplier) {
        this.supplier = supplier;
        this.tClass = tClass;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(tClass);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return supplier.get();
    }
}
