import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Eric on 2/10/2016.
 */
public class CheckersUI {
    private JFrame frame;
    private JMenuBar menuBar;

    public CheckersUI(){
        frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        CheckersBoard board = new CheckersBoard();
        frame.add(board);
        menuBar = new JMenuBar();

        JMenuItem newGameButton = new JMenuItem("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        menuBar.add(new JMenu().add(newGameButton));
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new CheckersUI();
    }

}
