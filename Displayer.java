import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Displayer extends JFrame {

    private ArrayList<ImageLayer> layers;

    public Displayer(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layers = new ArrayList<>();
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    // Add a new layer at the top (end of list)
    public void addLayer(ImageLayer layer) {
        layers.add(layer);
        repaint();
    }

    // Insert a layer at a specific position
    public void insertLayer(int index, ImageLayer layer) {
        layers.add(index, layer);
        repaint();
    }

    // Remove a layer by index
    public void removeLayer(int index) {
        if (index >= 0 && index < layers.size()) {
            layers.remove(index);
            repaint();
        }
    }

    // Move a layer to a new index (optional enhancement)
    public void moveLayer(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < layers.size() && toIndex >= 0 && toIndex < layers.size()) {
            ImageLayer layer = layers.remove(fromIndex);
            layers.add(toIndex, layer);
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw all layers in order
        for (ImageLayer layer : layers) {
            BufferedImage img = layer.getImage();
            if (img != null) {
                g.drawImage(img, 0, 30, this); // 30 offset to account for title bar
            }
        }
    }

    // Entry point for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Displayer frame = new Displayer("Image Layer Frame", 540, 1080);

            // Example layers (replace with your file paths)
            frame.addLayer(new ImageLayer("images/man.png"));
            frame.addLayer(new ImageLayer("path/to/midground.png"));
            frame.addLayer(new ImageLayer("path/to/foreground.png"));
        });
    }
}