package service.impl;

import entity.Captcha;
import service.CaptchaService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CaptchaServiceImpl implements CaptchaService {

    private Map<Long, Captcha> map = new HashMap<>();

    @Override
    public void save(Captcha captcha) {
        long id = new Date().getTime();
        captcha.setId(id);
        map.put(id, captcha);
    }

    @Override
    public void delete(long id) {
        map.remove(id);
    }

    @Override
    public Captcha get(long id) {
        return map.get(id);
    }
}
