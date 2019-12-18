package service;

import entity.Captcha;

public interface CaptchaService {

    void save(Captcha captcha);

    void delete(long id);

    Captcha get(long id);
}
