package util;

import java.util.Map;

public interface BeanValidator<T> {
    Map<String, String> validate(T bean);
}
