
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    static int width = 600;
    static int height = 600;
    static int sizeGrid = 4;
    public static void main(String [] args){
        try{
            JFrame frame = new JFrame();
             frame.setLayout(new BorderLayout());
             frame.setSize(width, height+100); //100 are the size of the buttons
             frame.setResizable(false);
             frame.setBackground(Color.black);
             try {                
                Image readImage = ImageIO.read(new File("cartoon_network.jpg"));
                Image image =  readImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);

                Pieces pieces = new Pieces(sizeGrid, image);
                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
                JButton shuffleButton = new JButton("Shuffle");
                JButton restartButton = new JButton("Restart");

                restartButton.addActionListener(e -> {
                    pieces.restart();
                });
                shuffleButton.addActionListener(e -> {
                    pieces.shuffle2(5);
                });

                shuffleButton.setSize(50, 50);
                content.add(pieces.getPanel());
                content.add(shuffleButton);
                content.add(restartButton);
                frame.add(content);

             } catch (IOException ex) {
                  System.out.println(ex.getLocalizedMessage());
             }
             frame.setVisible(true);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}


