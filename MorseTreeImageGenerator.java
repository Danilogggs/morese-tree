import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MorseTreeImageGenerator {
    public static void saveTreeImage(TreeNode root, String outputPath) throws IOException {
        saveTreeImageWithPath(root, "", outputPath);
    }

    public static void saveTreeImageWithPath(TreeNode root, String codeSequences, String outputPath) throws IOException {
        int width = 1200, height = 800, radius = 20;
        Set<String> prefixes = new HashSet<>();
        prefixes.add("");
        for (String token : codeSequences.split(" ")) {
            if (token.equals("/")) continue;
            for (int i = 1; i <= token.length(); i++) {
                prefixes.add(token.substring(0, i));
            }
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        drawNodeWithPath(g2, root, width/2, 50, width/4, radius, prefixes, "");
        g2.dispose();
        ImageIO.write(image, "png", new File(outputPath));
    }

    private static void drawNodeWithPath(Graphics2D g2, TreeNode node, int x, int y, int xOffset, int r, Set<String> prefixes, String path) {
        if (node == null) return;
        boolean onPath = prefixes.contains(path);
        g2.setColor(onPath ? Color.RED : Color.BLACK);
        g2.drawOval(x - r, y - r, 2*r, 2*r);
        FontMetrics fm = g2.getFontMetrics();
        String value = node.getValue().isEmpty() ? "*" : node.getValue();
        int textWidth = fm.stringWidth(value);
        g2.drawString(value, x - textWidth/2, y + fm.getAscent()/2);

        if (node.getLeft() != null) {
            String leftPath = path + ".";
            int childX = x - xOffset, childY = y + 80;
            g2.setColor(prefixes.contains(leftPath) ? Color.RED : Color.BLACK);
            g2.drawLine(x, y + r, childX, childY - r);
            drawNodeWithPath(g2, node.getLeft(), childX, childY, xOffset/2, r, prefixes, leftPath);
        }
        if (node.getRight() != null) {
            String rightPath = path + "-";
            int childX = x + xOffset, childY = y + 80;
            g2.setColor(prefixes.contains(rightPath) ? Color.RED : Color.BLACK);
            g2.drawLine(x, y + r, childX, childY - r);
            drawNodeWithPath(g2, node.getRight(), childX, childY, xOffset/2, r, prefixes, rightPath);
        }
    }
}
