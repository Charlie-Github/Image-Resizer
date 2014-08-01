import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * This class will resize all the images in a given folder
 * @author pankaj
 *
 */
public class Resizer{

    public static void main(String[] args) throws IOException {
    	File file = new File("./pic/niu.jpg");
    	resizeImageFile(file);
    	System.out.println("DONE");
    }
    public static void resizeImageFile(File file) throws IOException{
    	
    	String format = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
    	BufferedImage img = null;
        img = ImageIO.read(file);
        int height = img.getHeight();
        int width = img.getWidth();
        double hwpercentage = (double)height / (double)width;
        int height_re = (int)(360 * hwpercentage);
        img = resizeImage(img, 360, height_re);
//        File resizedImg = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.pathSeparator) + 1) + "resizedImg" + format);
        ImageIO.write(img, format.substring(1), file);
    	
    }
    /**
     * This function resize the image file and returns the BufferedImage object that can be saved to file system.
     */
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}