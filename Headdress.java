import java.awt.image.BufferedImage;

public class Headdress extends Man{

    public String getText() {
        return super.getText()+"a hat, ";
    }
    public Headdress() {
        super("images/hat.png");
    }
    public Headdress(String path) {
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
