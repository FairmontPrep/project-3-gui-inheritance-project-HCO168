import java.awt.image.BufferedImage;

public class Upperwear extends Headdress{

    public String getText() {
        return super.getText()+"a jacket, ";
    }
    public Upperwear() {
        super("images/cloth.png");
    }
    public Upperwear(String path) {
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
