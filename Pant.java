import java.awt.image.BufferedImage;

public class Pant extends Upperwear{
    public static String type;
    static {
        if(Math.random()<0.5){
            type="short";
        }else{
            type="skirt";
        }
    }
    public String getText() {
        return super.getText()+"a "+type+", ";
    }
    public Pant() {
        super("images/"+type+".png");
    }
    public Pant(String path) {
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
