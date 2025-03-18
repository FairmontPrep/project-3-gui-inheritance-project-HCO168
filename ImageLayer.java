import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

// Class to hold each image and its path
public class ImageLayer {
    private String imagePath;
    private BufferedImage image;

    public ImageLayer(String path) {
        this.imagePath = path;
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getPath() {
        return imagePath;
    }
}

// Main frame class to hold and display layers
