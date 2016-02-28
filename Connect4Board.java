package connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class used to display the gameboard and gamepieces, as well as handle user input
 *
 * Created by Eric on 2/10/2016.
 */
public class Connect4Board extends JPanel implements MouseListener{
    private boolean wonGame;  //flag used to indicate whether game has been won or not
	private GameLogic game;  //object handling game logic
    //array storing gamePieces
    private GamePiece[][] gamePieces = {{new GamePiece(0,0,0), new GamePiece(0,75,0), new GamePiece(0,150,0), new GamePiece(0,225,0), new GamePiece(0,300,0),new GamePiece(0,375,0), new GamePiece(0,450,0), new GamePiece(0,525,0) },
    							{new GamePiece(0,0,75), new GamePiece(0,75,75), new GamePiece(0,150,75), new GamePiece(0,225,75), new GamePiece(0,300,75), new GamePiece(0,375,75), new GamePiece(0,450,75), new GamePiece(0,525,75)},
    							{new GamePiece(0,0,150), new GamePiece(0,75,150), new GamePiece(0,150,150), new GamePiece(0,225,150), new GamePiece(0,300,150), new GamePiece(0,375,150), new GamePiece(0,450,150), new GamePiece(0,525,150)},
    							{new GamePiece(0,0,225), new GamePiece(0,75,225), new GamePiece(0,150,225), new GamePiece(0,225,225), new GamePiece(0,300,225), new GamePiece(0,375,225), new GamePiece(0,450,225), new GamePiece(0,525,225)},
    							{new GamePiece(0,0,300), new GamePiece(0,75,300), new GamePiece(0,150,300), new GamePiece(0,225,300), new GamePiece(0,300,300), new GamePiece(0,375,300), new GamePiece(0,450,300), new GamePiece(0,525,300)},
    							{new GamePiece(0,0,375), new GamePiece(0,75,375), new GamePiece(0,150,375), new GamePiece(0,225,375), new GamePiece(0,300,375), new GamePiece(0,375,375), new GamePiece(0,450,375), new GamePiece(0,525,375)},
    							{new GamePiece(0,0,450), new GamePiece(0,75,450), new GamePiece(0,150,450), new GamePiece(0,225,450), new GamePiece(0,300,450), new GamePiece(0,375,450), new GamePiece(0,450,450), new GamePiece(0,525,450)},
    							{new GamePiece(0,0,525), new GamePiece(0,75,525), new GamePiece(0,150,525), new GamePiece(0,225,525), new GamePiece(0,300,525), new GamePiece(0,375,525), new GamePiece(0,450,525), new GamePiece(0,525,525)},};


    private final static int TILE_SIZE = 75; //size of each tile in pixels

    public Connect4Board(GameLogic game){
		this.game = game;
        this.wonGame = false;
        this.addMouseListener(this);        	
        this.setSize(600, 650);
    }

    /**
     * Used to display the game board and the pieces on it
     * @param g Graphics object used by swing
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){  //paints board
                if ((i+j)%2==0) g.setColor(new Color(238, 170, 114));  //alternate between two colors
                else g.setColor(new Color(107, 55, 34));
                g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.fillOval(gamePieces[i][j].getXCoordinate(), gamePieces[i][j].getYCoordinate(), TILE_SIZE, TILE_SIZE);
            }
        }
        for(int i = 0; i < 8; i ++){
        	for (int h = 0; h < 8; h++){
        		if (gamePieces[i][h].getTeam() == 1){  //paints pieces of player 1
        			g.setColor(new Color(100, 255, 100));
        			g.fillOval(gamePieces[h][i].getXCoordinate(), gamePieces[h][i].getYCoordinate(), TILE_SIZE, TILE_SIZE);
        		}
        		else if (gamePieces[i][h].getTeam() == 2){  //paints pieces of player 2
        			g.setColor(Color.black);
        			g.fillOval(gamePieces[h][i].getXCoordinate(), gamePieces[h][i].getYCoordinate(), TILE_SIZE, TILE_SIZE);
        		}
        		
        	}
        }
    }

    /**
     * Set size of board to 600x600.
     * @return  size of board
     */
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(600,600);
    }

    /**
     * Used when creating a new game to reset all of the game pieces
     */
    public void resetGamePieces(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++)
                gamePieces[i][j].setTeam(0);

        }
        wonGame = false;
        repaint();
    }

    /**
     * Function handles placing pieces on board when the user clicks on tile
     * @param e Mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!wonGame) {  //if game hasn't been won yet (otherwise disallow clicking on board)
            Point point = e.getPoint();   // get position of click event
            if (game.moveIsGood(point.x / 75, point.y / 75)) { //if move is legal
                //add piece to the board
                game.setBoardState(point.x / 75, point.y / 75, 1);
                gamePieces[point.x / 75][point.y / 75].setTeam(1);
                repaint(point.x / 75 * 75, point.y / 75 * 75, TILE_SIZE, TILE_SIZE);
                if(game.evaluateBoard(game.getGamePieces()) == Integer.MAX_VALUE){  //if game has been won
                    wonGame = true;  //set won game flag
                    game.declareWinner(1);  //open Game Won Dialog box
                    return;
                }

                Point p = game.calculateAITurn(5);  //calculate AI turn in response to player move
                game.setBoardState(p.x, p.y, 2);  //place AI tile on board
                gamePieces[p.x][p.y].setTeam(2);
                repaint(p.x * 75, p.y * 75, TILE_SIZE, TILE_SIZE);
                if(game.evaluateBoard(game.getGamePieces()) == Integer.MIN_VALUE){  //if AI has won game
                    wonGame = true;
                    game.declareWinner(2);  //show the Game Lost dialog box
                }


            } else JOptionPane.showMessageDialog(null, "Move not allowed.");
        }
    }

    /**
     * Had to be implemented to implement MouseListener
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Had to be implemented to implement MouseListener
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Had to be implemented to implement MouseListener
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Had to be implemented to implement MouseListener
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Set wonGame flag
     * @param b Value wonGame flag will be set to
     */
    public void setWonGame(boolean b){
        wonGame = b;
    }

    /**
     * Return array of game pieces
     * @return Array of game pieces
     */
	public GamePiece[][] getGamePieces(){
		return gamePieces;
	}


}

