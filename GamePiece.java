package aaron;

import java.awt.*;
import javax.swing.*;

public class GamePiece extends JPanel{
	private int team;
	private int xPoint, yPoint;
	private final int OVAL_SIZE = 75;
	
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
		
	public int getXCoordinate(){
		return xPoint;
	}
	
	public int getYCoordinate(){
		return yPoint;
	}
	
	public void setTeam(int newTeam){
		team = newTeam;
	}
	
	public int getTeam(){
		return team;
	}
}
