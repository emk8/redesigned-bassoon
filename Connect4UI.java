package aaron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Eric on 2/10/2016.
 */
public class Connect4UI {
    private JFrame frame;
    private JMenuBar menuBar;
    private Connect4Board board;
    private GameLogic game;

    public Connect4UI(GameLogic game){
        this.game = game;
        frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        board = new Connect4Board(game);
        frame.add(board);
        frame.setSize(600, 650);
        menuBar = new JMenuBar();

        JMenuItem newGameButton = new JMenuItem("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePiece[][] pieces = board.getGamePieces();
                for(int i = 0; i < 8; i ++){
                    for(int j = 0; j < 8; j ++){
                        pieces[i][j].setTeam(0);
                        board.repaint();
                    }
                }

                game.resetGame();
            }
        });


        menuBar.add(new JMenu().add(newGameButton));
        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);

    }
}