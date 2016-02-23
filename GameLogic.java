import java.awt.Point;

/**
 * Created by Eric on 2/23/2016.
 */
public class GameLogic {
    private CheckersUI gui;
    private int[][] boardState;

    public GameLogic(){
        gui = new CheckersUI(this);
        boardState = new int[6][7];
    }

    //UNIMPLEMENTED
    /**
     * Determines whether a piece can legally be placed on a given space on the board.
     *
     * @param x X coordinate of potential move
     * @param y Y coordinate of potential move
     * @return True/False whether a given move can be made
     */
    public boolean isValidMove(int x, int y){
        return false;
    }

    //UNIMPLEMENTED
    /**
     * Determines whether a player has won the game, given the current board state.
     *
     * @return 0 if neither player has won, 1 if player 1 has won, 2 if player 2 has won
     */

    public int determineGameWinner(){
        return 0;
    }

    //UNIMPLEMENTED

    /**
     * Calculates the next move of the AI.
     *
     * @return Coordinates of the space the AI will place its next piece.
     */

    public Point determineAIMove(){
        return null;
    }

    //UNIMPLEMENTED

    /**
     * Changes one element of the boardState array to indicate a player has placed a piece in that location
     * @param x X coordinate of placed piece
     * @param y Y coordinate of placed piece
     * @param player  Player that controls the piece
     */

    public void setBoardState(int x, int y, int player){

    }

    //UNIMPLEMENTED

    /**
     * After a piece has been placed, lets GUI know so that the board graphics can be updated.
     */
    public void updateGUI(){

    }

    //UNIMPLEMENTED

    /**
     * After a game has been won or the player clicks the new game button, resets everything
     * back to the new game state.
     */
    public void resetGame(){

    }



}
