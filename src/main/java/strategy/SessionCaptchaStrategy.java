package strategy;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCaptchaStrategy implements CaptchaStrategy {
    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        HttpSession session = request.getSession();
        session.setAttribute("captchaId", captcha.getId());
    }

    @Override
    public long getCaptchaId(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return (long) session.getAttribute("captchaId");
    }
}
