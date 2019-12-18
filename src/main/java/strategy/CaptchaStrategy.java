package strategy;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaStrategy {

    void saveCaptcha(HttpServletRequest request,
                     HttpServletResponse response, Captcha captcha);

    long getCaptchaId(HttpServletRequest request,
                      HttpServletResponse response);

}
