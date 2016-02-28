package connect4;

/**
 * Object used to represent game pieces
 */

public class GamePiece{
	private int team;  //team piece is controlled by ( 1= human, 2= AI)
	private int xPoint, yPoint; //location of piece

	GamePiece(){
		team = 1;
		xPoint = 0;
		yPoint = 0;
	}
	
	GamePiece(int team, int xPoint, int yPoint){
		this.team = team;
		this.xPoint = xPoint;
		this.yPoint = yPoint;
	}

	/**
	 * Gets x coordinate of piece
 	 * @return X coordinate
     */
	public int getXCoordinate(){
		return xPoint;
	}

	/**
	 * Gets y coordinate of piece
	 * @return Y coordinate
     */
	public int getYCoordinate(){
		return yPoint;
	}

	/**
	 * Set team of piece
	 * @param newTeam The new team of piece
     */
	public void setTeam(int newTeam){
		team = newTeam;
	}

	/**
	 * Get team of piece
	 * @return The team the piece is controlled by
     */
	public int getTeam(){
		return team;
	}
}
