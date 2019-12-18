package servlet;

import image.Image;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/img")
public class ImageServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(ImageServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Image imageProvider = (Image) getServletContext().getAttribute("image");
        String imageName = req.getParameter("image");
        System.out.println(imageName);
        File image = imageProvider.get(imageName);
        resp.setContentType(getServletContext().getMimeType(imageName));
        try (FileInputStream in = new FileInputStream(image);
             OutputStream out = resp.getOutputStream()) {
            int bufferSize = 1024;
            byte[] buf = new byte[bufferSize];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error ", e);
        }
    }
}
