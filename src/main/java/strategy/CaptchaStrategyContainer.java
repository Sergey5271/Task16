package strategy;

import java.util.HashMap;
import java.util.Map;

public class CaptchaStrategyContainer {
    private Map<String, CaptchaStrategy> map  = new HashMap<>();

    public CaptchaStrategyContainer() {
        initStrategies();
    }

    public CaptchaStrategy get(String key) {
        return map.get(key);
    }

    private void initStrategies() {
        map.put("COOKIES", new CookieCaptchaStrategy());
        map.put("SESSION", new SessionCaptchaStrategy());
        map.put("HIDDEN_FIELD", new HiddenFieldCaptchaStrategy());
    }

}
