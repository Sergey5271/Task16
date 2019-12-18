package strategy;

import entity.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCaptchaStrategy implements CaptchaStrategy {
    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        Cookie cookie = new Cookie("captchaId", String.valueOf(captcha.getId()));
        response.addCookie(cookie);
    }

    @Override
    public long getCaptchaId(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("captchaId")) {
                return Long.parseLong(cookie.getValue());
            }
        }
        throw new IllegalStateException("Captcha ID not found");
    }
}
