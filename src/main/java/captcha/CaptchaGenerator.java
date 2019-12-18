package captcha;

import entity.Captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

public class CaptchaGenerator {

    private static final int TOTAL_CHARS = 6;
    private static final int HEIGHT = 40;
    private static final int WIDTH = 150;

    public Captcha generationCaptcha() {
        Font fntStyle1 = new Font("Century Gothic", Font.BOLD, 30);
        Random randomGenerator = new SecureRandom();
        String sImageCode = Integer.toString(randomGenerator.nextInt(899999) + 100000);

        BufferedImage biImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

        g2dImage.setFont(fntStyle1);
        for (int i = 0; i < TOTAL_CHARS; i++) {
            g2dImage.setColor(new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255)));
            if (i % 2 == 0) {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
            } else {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
            }
        }
        g2dImage.dispose();
        return new Captcha(biImage, sImageCode);
    }
}
