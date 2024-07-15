import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GridPanel extends JPanel{

  
    public GridPanel(GridLayout gridLayout){
        super();
        setLayout(gridLayout);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {     

                ImagePanel imagePanel = (ImagePanel)(getComponentAt(e.getX(), e.getY()));
                ImagePanel blank = isNeighbourBlank(imagePanel);
                if(blank != null){
                    Image imageBlank = blank.getImage();
                    String nameBlank = blank.getName();
                    blank.setImage(imagePanel.getImage());
                    blank.setName(imagePanel.getName());
                    imagePanel.setImage(imageBlank);
                    imagePanel.setName(nameBlank);
                    repaint();
                }       
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
    }

    public ImagePanel isNeighbourBlank(ImagePanel to){

        int left = to.getX()-to.getWidth()+1;
        int right = to.getX()+to.getWidth()+1;
        int top = to.getY()-to.getHeight()+1;
        int bottom = to.getY()+to.getHeight()+1;


        ImagePanel leftPanel;
        if(super.getComponentAt(left, to.getY()) instanceof ImagePanel){
           leftPanel = (ImagePanel) super.getComponentAt(left, to.getY());
        }else{
            leftPanel = null;
        }

        ImagePanel rightPanel;
        if(super.getComponentAt(right, to.getY()) instanceof ImagePanel){
           rightPanel = (ImagePanel) super.getComponentAt(right, to.getY());
        }else{
            rightPanel = null;
        }

        ImagePanel topPanel;
        if(super.getComponentAt(to.getX(), top) instanceof ImagePanel){
           topPanel = (ImagePanel) super.getComponentAt(to.getX(), top);
        }else{
            topPanel = null;
        }

        ImagePanel bottomPanel;
        if(getComponentAt(to.getX(), bottom) instanceof ImagePanel){
           bottomPanel = (ImagePanel) super.getComponentAt(to.getX(), bottom);
        }else{
            bottomPanel = null;
        }
     
        

            if(!(leftPanel == null)){
                if(leftPanel.getName().equals("blank")){
                    return leftPanel;
                }
            }
            
            if(!(rightPanel == null)){
                if(rightPanel.getName().equals("blank")){
                    return rightPanel;
                }
            }
            
            if(!(topPanel == null)){
                if(topPanel.getName().equals("blank")){
                    return topPanel;
                }
            }
            
            if(!(bottomPanel == null)){
                if(bottomPanel.getName().equals("blank")){
                    return bottomPanel;
                }
            }

            return null;
    }
}
