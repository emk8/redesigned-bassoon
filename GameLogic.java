package connect4;

import javax.swing.*;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 * Class used to handle all of the game logic for the Connect 4 Game.
 *
 * Created by Eric on 2/23/2016.
 */
public class GameLogic {
    private Connect4UI gui;  //GUI for game
    private int[][] gamePieces;  //internal int array storing location of game pieces
    //0 if no piece, 1 if piece placed by human player, 10 if piece played by computer played

    public GameLogic(){
        gui = new Connect4UI(this);
        gamePieces = new int[8][8];
        for(int i = 0; i <8; i++){
            for(int j =0; j < 8; j++){
                gamePieces[i][j] = 0;
            }
        }
    }

    /**
     * Changes one element of the boardState array to indicate a player has placed a piece in that location
     * @param x X coordinate of placed piece
     * @param y Y coordinate of placed piece
     * @param player  Player that controls the piece
     */

    public void setBoardState(int x, int y, int player){

       if (player == 1) gamePieces[x][y] = 1;
       else gamePieces[x][y] = 10;
    }

    /**
     * Declares the winner and asks if the player would like to play again.
     * @param team the winning team
     */

    public void declareWinner(int team){
        int again;
        if(team == 1)  // if player won display win dialog
            again = JOptionPane.showConfirmDialog(null, "YOU WIN! Would you like to play again?" , "", JOptionPane.YES_NO_OPTION);
        else  //if player lost display lose dialog
            again = JOptionPane.showConfirmDialog(null, "You lose. Would you like to try again?" , "", JOptionPane.YES_NO_OPTION);
        if (again == JOptionPane.YES_OPTION){  //if player indicates they want to start a new game, restart game
            resetGame();
            gui.getBoard().resetGamePieces();
        }
    }

    /**
     * After a game has been won or the player clicks the new game button, resets everything
     * back to the new game state.
     */
    public void resetGame(){
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                gamePieces[i][j] = 0;  //change every element of gamePiece array to 0 to indicate no piece there
            }
        }
    }

    /**
     * Determines whether a piece can legally be placed on a given space on the current board.
     *
     * @param x X coordinate of potential move
     * @param y Y coordinate of potential move
     * @return True/False whether a given move can be made
     */
    public boolean moveIsGood(int x, int y){
        if(y < 7) //piece is not on the first row
            if(gamePieces[x][y] == 0 && gamePieces[x][y+1] != 0) return true;
        return gamePieces[x][y] == 0 && y == 7;  //piece is on the first row
    }

    /**
     * Determines whether a piece can legally be placed on the board passed as argument
     * @param x X coordinate of potential move
     * @param y Y coordinate of potential move
     * @param boardState
     * @return True/false whether a given move can be made
     */
    public boolean moveIsGood(int x, int y, int[][] boardState){
        if(y < 7) //piece is not on the first row
            if(boardState[x][y] == 0 && boardState[x][y+1] != 0) return true;
        return boardState[x][y] == 0 && y == 7;  //piece is on the first row
    }

    /**
     * Function used to calculate the next move of the AI.  Recursively generates boards up to depth provided to
     * function.  Uses min-max algorithm to determine the best move.  Calls evaluateBoard() for every board generated
     * to determine how good/bad each board is.
     * @param sd
     * @return
     */
    public Point calculateAITurn(int sd){
        Vector<Point> moves = new Vector<>();  //container of possible moves
        int score = Integer.MAX_VALUE;  //score of best move
        int indexOfHighestScore = 1;  //index within container of best move

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (moveIsGood(i, j)){  //searches for every legal move
                    moves.add(new Point(i, j));  //adds legal move found to container
                    int[][] newBoard = new int[8][8];
                    arrayCopy(gamePieces, newBoard);  //copy current gamePiece array to new array
                    newBoard[i][j] = 10;  //places computer controlled piece for the move found
                    int possibleScore = recursiveMove(newBoard, sd-1);  //recursively create new boards
                    if (possibleScore < score) {  //if this move is better than the previous best, make this the best move
                        score = possibleScore;
                        indexOfHighestScore = moves.size();
                        //System.out.println(possibleScore);
                        //System.out.println(moves.size());
                    }
                    if (possibleScore == score){  //if this move is equal to the best move, randomly select between the moves
                        if(new Random().nextInt(3) == 1) indexOfHighestScore = moves.size();
                    }
                }
            }
        }


        if(score == Integer.MAX_VALUE && sd == 5){
            return calculateAITurn(3);  //the computer sometimes gives up and makes random moves if the situation is hopeless
            //however, this isn't satisfying to play against, so this makes the algorithm "dumber" to keep it playing
        }
        return moves.elementAt(indexOfHighestScore-1);
    }


    /**
     * Used internally by the class to copy 2 dimensional arrays
     * @param aSource  array to be copied
     * @param aDestination array to be copied to
     */
    private static void arrayCopy(int[][] aSource, int[][] aDestination) {
        for (int i = 0; i < aSource.length; i++) {
            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
        }
    }

    /**
     * Function used internally to recursively generate boards to the specified search depth.
     * @param board  Board to be evaluated
     * @param depth  The remaining number of levels that need to be searched
     * @return  Score of this board
     */
    private int recursiveMove(int[][] board, int depth) {
        int boardEvaluation = evaluateBoard(board);
        if (boardEvaluation == Integer.MAX_VALUE || boardEvaluation == Integer.MIN_VALUE) return boardEvaluation;  //if a player has won, no need to look at any more moves resulting from this board
        else if (depth == 0) return boardEvaluation; //limit of search depth has been reached

        else {
                boardEvaluation = Integer.MIN_VALUE;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (moveIsGood(i, j, board)) {  //iterates through every valid move
                            int[][] newBoard = new int[8][8];
                            arrayCopy(board, newBoard);  //create new board with that new move on it
                            if (depth%2 == 1) {
                                newBoard[i][j] = 10;  //if the board generated was for a computer move
                            }


                            else newBoard[i][j] = 1;  //if the board generated was for a human move
                            int score = recursiveMove(newBoard, depth-1);  //recursively call this function with new board
                            if (score > boardEvaluation) boardEvaluation = score;  //function will the the score of the least of its children
                        }
                    }
                }
        }
        return boardEvaluation;
    }

    /**
     * Evaluates the board passed to it.  The heuristic looks at the number of "threats" of each player.
     * A threat is considered to be 4 adjacent spaces with 3 of the spaces controlled by 1 player and the other
     * space left empty.  The score is the (Number of Threats Controlled by Human Player) - (Num of Computer Threats).
     * So, a high positive score indicates a board favorable towards the human player and a high negative score
     * indicates a board favorable towards the computer player.
     *
     * The function returns the value Integer.MAX_VALUE if the human player has won, or Integer.MIN_VALUE if the computer has won.
     *
     * @param boardPieces  Board to be evaluated
     * @return  Score of board
     */
    public int evaluateBoard(int[][] boardPieces){
        int boardScore = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i+3 < 8){
                    //Looks at possible horizontal patterns
                    int numPlayerPiece = boardPieces[i][j] + boardPieces[i+1][j] + boardPieces[i+2][j] + boardPieces[i+3][j];
                    if (numPlayerPiece == 4) return Integer.MAX_VALUE;
                    else if (numPlayerPiece  == 3) boardScore++;
                    else if (numPlayerPiece == 40) return Integer.MIN_VALUE;
                    else if (numPlayerPiece == 30) boardScore--;
                }
                if(j+3 < 8) {
                    //Looks at possible vertical patterns
                    int numPlayerPiece = boardPieces[i][j] + boardPieces[i][j+1] + boardPieces[i][j+2] + boardPieces[i][j+3];
                    if (numPlayerPiece == 4) return Integer.MAX_VALUE;
                    else if (numPlayerPiece  == 3) boardScore++;
                    else if (numPlayerPiece == 40) return Integer.MIN_VALUE;
                    else if (numPlayerPiece == 30) boardScore--;
                }
                if(i+3 < 8 && j+3 < 8){
                    //Looks at possible diagonal patterns "\"
                    int numPlayerPiece = boardPieces[i][j] + boardPieces[i+1][j+1] + boardPieces[i+2][j+2] + boardPieces[i+3][j+3];
                    if (numPlayerPiece == 4) return Integer.MAX_VALUE;
                    else if (numPlayerPiece  == 3) boardScore++;
                    else if (numPlayerPiece == 40) return Integer.MIN_VALUE;
                    else if (numPlayerPiece == 30) boardScore--;
                }
                if(i-3 >= 0 && j+3 < 8) {
                    //Looks at possible diagonal patterns "/"
                    int numPlayerPiece = boardPieces[i][j] + boardPieces[i-1][j+1] + boardPieces[i-2][j+2] + boardPieces[i-3][j+3];
                    if (numPlayerPiece == 4) return Integer.MAX_VALUE;
                    else if (numPlayerPiece  == 3) boardScore++;
                    else if (numPlayerPiece == 40) return Integer.MIN_VALUE;
                    else if (numPlayerPiece == 30) boardScore--;
                }
            }
        }
        return boardScore;
    }


    public int[][] getGamePieces(){
        return gamePieces;
    }

    public static void main(String[] args){
        GameLogic game = new GameLogic() ;
    }

}
