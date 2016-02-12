import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by Eric on 2/10/2016.
 */
public class CheckersBoard extends JPanel implements MouseListener{
    private Vector<Point> availMoves;
    private Graphics g;
    private final static int TILE_SIZE = 75;

    public CheckersBoard(){
        this.addMouseListener(this);
        g = this.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                if ((i+j)%2==0) g.setColor(new Color(238,170,114));
                else g.setColor(new Color(107,55, 34));
                g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(600,600);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        System.out.println((point.x/75) + ", " + (point.y/75));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
