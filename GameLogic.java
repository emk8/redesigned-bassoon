package aaron;

import java.awt.Point;
import java.util.Random;

/**
 * Created by Eric on 2/23/2016.
 */
public class GameLogic {
    private Connect4UI gui;
    private int[][] gamePieces;
    private int[] columnCounters = {7, 7, 7, 7, 7, 7, 7, 7};

    public GameLogic(){
        gui = new Connect4UI(this);
        gamePieces = new int[8][8];
        for(int i = 0; i <8; i++){
            for(int j =0; j < 8; j++){
                gamePieces[i][j] = 0;
            }
        }
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
     * Changes one element of the boardState array to indicate a player has placed a piece in that location
     * @param x X coordinate of placed piece
     * @param y Y coordinate of placed piece
     * @param player  Player that controls the piece
     */

    public void setBoardState(int x, int y, int player){
        gamePieces[x][y] = player;
    }

    /**
     * After a game has been won or the player clicks the new game button, resets everything
     * back to the new game state.
     */
    public void resetGame(){
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                gamePieces[i][j] = 0;
            }
        }

        for(int i = 0; i <8; i++){
            columnCounters[i] = 7;
        }
    }

    /**
     * Calculates the next move of the AI.
     *
     * @return Coordinates of the space the AI will place its next piece.
     */

    public Point computerTurn(){

        for(int i = 0; i < 8; i++){
            Point p = new Point();
            p = willWin(i, columnCounters[i]);
            if (p.x < 8 && p.x > -1 && p.y < 8 && p.y > -1 && moveIsGood(p.x, p.y)) return p;
            p = willBlockWin(i, columnCounters[i]);
            if (p.x < 8 && p.x > -1 && p.y < 8 && p.y > -1 && moveIsGood(p.x, p.y)) return p;
        }
        Random generator = new Random();
        int x = generator.nextInt(8);
        while (columnCounters[x] > 7 || !moveIsGood(x, columnCounters[x]))
            x = generator.nextInt(8);
        return new Point(x, columnCounters[x]);
    }

    /**
     * Determines whether a piece can legally be placed on a given space on the board.
     *
     * @param x X coordinate of potential move
     * @param y Y coordinate of potential move
     * @return True/False whether a given move can be made
     */
    public boolean moveIsGood(int x, int y){
        if(y == -1)return false;
        if(y < 7)
            if(gamePieces[x][y] == 0 && gamePieces[x][y+1] != 0) return true;
        return gamePieces[x][y] == 0 && y == 7;
    }

    private Point willWin(int x, int y){

        // check for line straight across
        for(int i = 0; i < 5; i++){
            if(columnCounters[i] >= 0){
                if (gamePieces[i+1][columnCounters[i]] == 1){
                    if (gamePieces[i+2][columnCounters[i]] == 1){
                        if (gamePieces[i+3][columnCounters[i]] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        //check for line diagonally upward
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] > 2){
                if (gamePieces[i+1][columnCounters[i]-1] == 1){
                    if (gamePieces[i+2][columnCounters[i]-2] == 1){
                        if (gamePieces[i+3][columnCounters[i]-3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        //check for line straight down
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] < 5){
                if (gamePieces[i][columnCounters[i]+1] == 1){
                    if (gamePieces[i][columnCounters[i]+2] == 1){
                        if (gamePieces[i][columnCounters[i]+3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        // check for line diagonally downward
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] < 5){
                if (gamePieces[i+1][columnCounters[i]+1] == 1){
                    if (gamePieces[i+2][columnCounters[i]+2] == 1){
                        if (gamePieces[i+3][columnCounters[i]+3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        return new Point(8, 8);
    }

    private Point willBlockWin(int x, int y){
        // check for line straight across
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] >= 0){
                if (gamePieces[i+1][columnCounters[i]] == 1){
                    if (gamePieces[i+2][columnCounters[i]] == 1){
                        if (gamePieces[i+3][columnCounters[i]] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        //check for line diagonally upward
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] > 2){
                if (gamePieces[i+1][columnCounters[i]-1] == 1){
                    if (gamePieces[i+2][columnCounters[i]-2] == 1){
                        if (gamePieces[i+3][columnCounters[i]-3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        //check for line straight down
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] < 5){
                if (gamePieces[i][columnCounters[i]+1] == 1){
                    if (gamePieces[i][columnCounters[i]+2] == 1){
                        if (gamePieces[i][columnCounters[i]+3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        // check for line diagonally downward
        for(int i = 0; i < 5; i++){
            if (columnCounters[i] < 5){
                if (gamePieces[i+1][columnCounters[i]+1] == 1){
                    if (gamePieces[i+2][columnCounters[i]+2] == 1){
                        if (gamePieces[i+3][columnCounters[i]+3] == 1)
                            return new Point(i, columnCounters[i]);
                    }
                }
            }
        }
        return new Point(8, 8);
    }

    public int[] getColumnCounters(){
        return columnCounters;
    }

    public static void main(String[] args){
        new GameLogic();
    }

}
