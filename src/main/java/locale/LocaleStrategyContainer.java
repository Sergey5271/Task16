package locale;

import java.util.HashMap;
import java.util.Map;

public class LocaleStrategyContainer {

    private Map<String, LocaleStrategy> map = new HashMap<>();

    public LocaleStrategyContainer() {
        initStrategies();
    }

    public LocaleStrategy get(String key) {
        return map.get(key);
    }

    private void initStrategies() {
        map.put("COOKIES", new CookieLocaleStrategy());
        map.put("SESSION", new SessionLocaleStrategy());

    }

}
