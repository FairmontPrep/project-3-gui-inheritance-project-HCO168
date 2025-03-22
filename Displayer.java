import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Displayer extends JFrame {

    private ArrayList<ImageLayer> layers;
    private ImagePanel panel;

    private String titleText = "";

    public Displayer(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layers = new ArrayList<>();
        setLocationRelativeTo(null); // center on screen

        panel = new ImagePanel();
        add(panel);

        setVisible(true);
    }

    // Setter for the custom title text
    public void setTitleText(String text) {
        this.titleText = text;
        panel.repaint();
    }
    // Add a new layer at the top (end of list)
    public void addLayer(ImageLayer layer) {
        layers.add(layer);
        panel.repaint();
    }

    // Insert a layer at a specific position
    public void insertLayer(int index, ImageLayer layer) {
        layers.add(index, layer);
        panel.repaint();
    }

    // Remove a layer by index
    public void removeLayer(int index) {
        if (index >= 0 && index < layers.size()) {
            layers.remove(index);
            panel.repaint();
        }
    }

    // Move a layer to a new index (optional enhancement)
    public void moveLayer(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < layers.size() && toIndex >= 0 && toIndex < layers.size()) {
            ImageLayer layer = layers.remove(fromIndex);
            layers.add(toIndex, layer);
            panel.repaint();
        }
    }

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (layers.isEmpty()) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Assuming all images are the same size as the base layer
            BufferedImage baseImage = layers.get(0).getImage();
            if (baseImage == null) return;

            int imgWidth = baseImage.getWidth();
            int imgHeight = baseImage.getHeight();

            // Calculate scale to fit and maintain aspect ratio
            double widthScale = (double) panelWidth / imgWidth;
            double heightScale = (double) panelHeight / imgHeight;
            double scale = Math.min(widthScale, heightScale);

            int drawWidth = (int) (imgWidth * scale);
            int drawHeight = (int) (imgHeight * scale);

            int xOffset = (panelWidth - drawWidth) / 2;
            int yOffset = (panelHeight - drawHeight) / 2;

            // ✅ FIRST: Draw all image layers (background layers)
            for (ImageLayer layer : layers) {
                BufferedImage img = layer.getImage();
                if (img != null) {
                    g2d.drawImage(img, xOffset, yOffset, drawWidth, drawHeight, null);
                }
            }

            // ✅ THEN: Draw the text OVER the images
            if (!titleText.isEmpty()) {
                Font font = new Font("Arial", Font.BOLD, 24);
                g2d.setFont(font);
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(titleText);

                int x = (panelWidth - textWidth) / 2;
                int y = yOffset - 10; // Just above the image

                // Make sure the text stays visible
                if (y < fm.getAscent() + 5) {
                    y = fm.getAscent() + 5;
                }

                // Draw background for readability (optional)
                g2d.setColor(new Color(0, 0, 0, 150)); // semi-transparent black background
                g2d.fillRect(x - 10, y - fm.getAscent(), textWidth + 20, fm.getHeight());

                // Draw the text in front
                g2d.setColor(Color.WHITE);
                g2d.drawString(titleText, x, y);
            }
        }

    }

    // Entry point for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Displayer frame = new Displayer("Resizable Image Layer Frame", 540, 1080);

            // Example layers (replace with your file paths)
            frame.addLayer(new Man());
            ImageLayer shoes = new Shoes();
            frame.addLayer(shoes);
            frame.addLayer(new Headdress());
            frame.addLayer(new Pant());
            frame.addLayer(new Upperwear());
            frame.setTitleText(shoes.getText());
        });
    }
}
