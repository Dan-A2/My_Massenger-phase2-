package org.openjfx.controller.modelController;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.openjfx.controller.Config;
import org.openjfx.controller.saveLoad.SaveNLoad;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageController {

    private final File mainFile = new File(Config.getConfig("saveNLoad").getProperty(String.class, "imageAddress"));

    public Image getImage(int id) {
        try {
            for (File file : mainFile.listFiles()) {
                if (file.getName().equals(id + ".png")) {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    return image;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int saveImage(Image image) {
        org.openjfx.models.Image image1 = new org.openjfx.models.Image();
        try {
            File data = new File(mainFile, image1.getId() + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", data);
            SaveNLoad.saveImageId();
            return image1.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}