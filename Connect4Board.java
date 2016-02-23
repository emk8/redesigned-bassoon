package aaron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Eric on 2/10/2016.
 */
public class Connect4Board extends JPanel implements MouseListener{
	private GameLogic game;
    private GamePiece[][] gamePieces = {{new GamePiece(0,0,0), new GamePiece(0,75,0), new GamePiece(0,150,0), new GamePiece(0,225,0), new GamePiece(0,300,0),new GamePiece(0,375,0), new GamePiece(0,450,0), new GamePiece(0,525,0) },
    							{new GamePiece(0,0,75), new GamePiece(0,75,75), new GamePiece(0,150,75), new GamePiece(0,225,75), new GamePiece(0,300,75), new GamePiece(0,375,75), new GamePiece(0,450,75), new GamePiece(0,525,75)},
    							{new GamePiece(0,0,150), new GamePiece(0,75,150), new GamePiece(0,150,150), new GamePiece(0,225,150), new GamePiece(0,300,150), new GamePiece(0,375,150), new GamePiece(0,450,150), new GamePiece(0,525,150)},
    							{new GamePiece(0,0,225), new GamePiece(0,75,225), new GamePiece(0,150,225), new GamePiece(0,225,225), new GamePiece(0,300,225), new GamePiece(0,375,225), new GamePiece(0,450,225), new GamePiece(0,525,225)},
    							{new GamePiece(0,0,300), new GamePiece(0,75,300), new GamePiece(0,150,300), new GamePiece(0,225,300), new GamePiece(0,300,300), new GamePiece(0,375,300), new GamePiece(0,450,300), new GamePiece(0,525,300)},
    							{new GamePiece(0,0,375), new GamePiece(0,75,375), new GamePiece(0,150,375), new GamePiece(0,225,375), new GamePiece(0,300,375), new GamePiece(0,375,375), new GamePiece(0,450,375), new GamePiece(0,525,375)},
    							{new GamePiece(0,0,450), new GamePiece(0,75,450), new GamePiece(0,150,450), new GamePiece(0,225,450), new GamePiece(0,300,450), new GamePiece(0,375,450), new GamePiece(0,450,450), new GamePiece(0,525,450)},
    							{new GamePiece(0,0,525), new GamePiece(0,75,525), new GamePiece(0,150,525), new GamePiece(0,225,525), new GamePiece(0,300,525), new GamePiece(0,375,525), new GamePiece(0,450,525), new GamePiece(0,525,525)},};
    
    private final static int TILE_SIZE = 75;

    public Connect4Board(GameLogic game){
		this.game = game;
        this.addMouseListener(this);        	
        this.setSize(600, 650);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                if ((i+j)%2==0) g.setColor(new Color(238, 170, 114));
                else g.setColor(new Color(107, 55, 34));
                g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.fillOval(gamePieces[i][j].getXCoordinate(), gamePieces[i][j].getYCoordinate(), TILE_SIZE, TILE_SIZE);
            }
        }
        for(int i = 0; i < 8; i ++){
        	for (int h = 0; h < 8; h++){
        		if (gamePieces[i][h].getTeam() == 1){
        			g.setColor(new Color(100, 255, 100));
        			g.fillOval(gamePieces[h][i].getXCoordinate(), gamePieces[h][i].getYCoordinate(), TILE_SIZE, TILE_SIZE);
        		}
        		else if (gamePieces[i][h].getTeam() == 2){
        			g.setColor(Color.black);
        			g.fillOval(gamePieces[h][i].getXCoordinate(), gamePieces[h][i].getYCoordinate(), TILE_SIZE, TILE_SIZE);
        		}
        		
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
        if(game.moveIsGood(point.x/75, point.y/75)){

			game.setBoardState(point.x/75, point.y/75, 1);
        	gamePieces[point.x/75][point.y/75].setTeam(1);
			game.getColumnCounters()[point.x/75]--;
        	repaint(point.x/75*75, point.y/75*75, TILE_SIZE, TILE_SIZE);

        	Point p = game.computerTurn();
			game.setBoardState(p.x, p.y, 2);
        	gamePieces[p.x][p.y].setTeam(2);
			game.getColumnCounters()[p.x]--;
        	repaint(p.x*75, p.y*75, TILE_SIZE, TILE_SIZE);
        }
        else JOptionPane.showMessageDialog(null, "Move not allowed.");
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


	public GamePiece[][] getGamePieces(){
		return gamePieces;
	}
}

