import java.awt.image.BufferedImage;

public class Man extends ImageLayer {
    public String getText() {
        return "This man who wear ";
    }
    public Man() {
        super("images/man.png");
    }
    public Man(String path) {
        super(path);
    }

    @Override
    public String getPath() {
        return super.getPath();
    }
    public BufferedImage getImage() {
        return super.getImage();
    }
}
