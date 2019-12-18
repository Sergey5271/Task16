package entity;

import java.awt.image.BufferedImage;

public class Captcha {
    private long id;

    private BufferedImage bufferedImage;

    private String captchaText;

    public Captcha(BufferedImage bufferedImage, String captchaText) {
        this.bufferedImage = bufferedImage;
        this.captchaText = captchaText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }
}
