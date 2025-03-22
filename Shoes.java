import java.awt.image.BufferedImage;

public class Shoes extends Pant{

    public String getText() {
        return super.getText()+"a shoe. ";
    }
    public Shoes() {
        super("images/shoes.png");
    }

    @Override
    public String getPath() {
        return super.getPath();
    }
    public BufferedImage getImage() {
        return super.getImage();
    }
}
