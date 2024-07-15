import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;  
    

public class ImagePanel extends JPanel{

    private Image image;

    public ImagePanel(String pathname, int width, int height) {

       try {                
          Image readImage = ImageIO.read(new File(pathname));
          image =  readImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
       } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
       }
    }

    public ImagePanel(Image image){
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image){
        this.image = image;
    }

}
