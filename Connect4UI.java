package connect4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that handles GUI logic.
 *
 * Created by Eric on 2/10/2016.
 *
 */
public class Connect4UI {
    private JFrame frame;  //Window for game
    private JMenuBar menuBar;  //Used to hold new game button
    private Connect4Board board;  //the board for the game
    private GameLogic game;  //the class handling game logic

    public Connect4UI(GameLogic game){
        //initialize window and game board
        this.game = game;
        frame = new JFrame("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        board = new Connect4Board(game);
        frame.add(board);
        frame.setSize(600, 650);
        menuBar = new JMenuBar();

        JMenuItem newGameButton = new JMenuItem("New Game");  //create new game button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePiece[][] pieces = board.getGamePieces();
                for(int i = 0; i < 8; i ++){
                    for(int j = 0; j < 8; j ++){
                        pieces[i][j].setTeam(0);  //reset array used to store game pieces
                        board.repaint();
                    }
                }
                board.setWonGame(false);  //reset wonGame flag
                game.resetGame();  //reset internal array used by the GameLogic object
            }
        });


        menuBar.add(new JMenu().add(newGameButton));
        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);

    }

    public Connect4Board getBoard(){
        return board;
    }
}