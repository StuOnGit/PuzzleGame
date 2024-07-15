import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;  


class Pieces {

    private GridPanel gridPanel;
    private int size;
    private ImagePanel imagePanels [][];
    private ImagePanel shuffledImagePanels [][];

    public Pieces(int size, Image image){

        this.size = size;
        int rows = size;
        int columns = size;
        gridPanel = new GridPanel(new GridLayout(rows, columns, 1, 1));
        
        BufferedImage bufferedImage = toBufferedImage(image);

        imagePanels = new ImagePanel[rows][columns];
        shuffledImagePanels = new ImagePanel[rows][columns];

       
        int pieceWidth = bufferedImage.getWidth()/columns;
        int pieceHeight = bufferedImage.getHeight()/rows;
        
        BufferedImage blank = null;
        try {
            Image readImage = ImageIO.read(new File("blank.png"));
            Image blankImage =  readImage.getScaledInstance(pieceWidth, pieceHeight, Image.SCALE_DEFAULT);
            blank = toBufferedImage(blankImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rand1 = (int) ((Math.random() * 100) % size);
        int rand2 = (int) ((Math.random() * 100) % size);
        for(int i = 0; i<size; i++){
            for(int j = 0 ; j < size; j++){
                if(!(i == rand1 && j == rand2)){
                 
                    ImagePanel imagePanel = new ImagePanel(bufferedImage.getSubimage(j*pieceHeight, i*pieceWidth, pieceWidth, pieceHeight));
                    ImagePanel imagecpy = new ImagePanel(bufferedImage.getSubimage(j*pieceHeight, i*pieceWidth, pieceWidth, pieceHeight));

                    imagePanel.setName("normal_image");
                    imagecpy.setName("normal_image");

                    imagePanels[i][j] = imagePanel;
                    shuffledImagePanels[i][j] = imagecpy;

                    gridPanel.add(imagecpy);
                }else{
                    ImagePanel imagePanel = new ImagePanel(blank);
                    ImagePanel imagecpy = new ImagePanel(blank);

                    imagePanel.setName("blank");
                    imagecpy.setName("blank");

                    imagePanels[i][j] = imagePanel;
                    shuffledImagePanels[i][j] = imagecpy;

                    gridPanel.add(imagecpy);
                }
             }
        }
    }

    private void repaint(){
        Thread guiThread = new Thread(new Runnable() {
            public void run(){
                gridPanel.removeAll();
                gridPanel.repaint();

                for(int i=0; i<size; i++)
                    for(int j=0; j<size; j++)
                        gridPanel.add(shuffledImagePanels[i][j]);
                
                gridPanel.validate();
            }
        
        });
        guiThread.start();
    }

    public void shuffle2(int num){
        if(shuffledImagePanels[0][0] == null){
            copyPanel(imagePanels, shuffledImagePanels);
        }

        for(int i = 0; i < num; i++){
            
            int rand1_1 = (int)((Math.random()*100) % size);
            int rand1_2 = (int) ((Math.random()*100) % size);
            int rand2_1 = (int) ((Math.random()*100) % size);
            int rand2_2 = (int) ((Math.random()*100) % size);


            ImagePanel tmp =  shuffledImagePanels[rand1_1][rand1_2];
            shuffledImagePanels[rand1_1][rand1_2] = shuffledImagePanels[rand2_1][rand2_2];
            shuffledImagePanels[rand2_1][rand2_2] = tmp;
           

        }

        repaint();
    }

    public void copyPanel(ImagePanel[][] from, ImagePanel[][] to){
        for(int i=0; i<from.length; i++)
            for(int j=0; j<from[i].length; j++){
                to[i][j].setName(from[i][j].getName());
                to[i][j].setImage(from[i][j].getImage());
            }

    }

    public JPanel getPanel(){
        return gridPanel;
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public void restart(){
        Thread guiThread = new Thread(new Runnable() {
            public void run(){
                gridPanel.removeAll();
                gridPanel.repaint();
                copyPanel(imagePanels, shuffledImagePanels);
                for(int i = 0; i<size; i++){
                    for(int j = 0 ; j < size; j++){
                        gridPanel.add(shuffledImagePanels[i][j]);
                     }
                }
                gridPanel.validate();
            }
        });
        guiThread.start(); 
    }

}