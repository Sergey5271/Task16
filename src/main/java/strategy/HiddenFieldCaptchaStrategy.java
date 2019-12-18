package strategy;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenFieldCaptchaStrategy implements CaptchaStrategy {
    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.setAttribute("captchaId", captcha.getId());
    }

    @Override
    public long getCaptchaId(HttpServletRequest request, HttpServletResponse response) {
        return Long.parseLong(request.getParameter("captchaId"));
    }
}
