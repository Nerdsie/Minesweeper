package MinesweeperGame.Tile;

import java.awt.Color;
import java.awt.Font;

import MinesweeperGame.Minesweeper;
import MinesweeperGame.FX.TextFX;

public class Tile {
	public static Tile[][] map = new Tile[Minesweeper.level.w][Minesweeper.level.h];

	public int xLoc, yLoc;
	public TileType type = TileType.INFO;
	
	public boolean isShowing = false;
	
	public boolean state = false;
	
	public Tile(int xx, int yy){
		xLoc = xx;
		yLoc = yy;
	}
	
	public void toggleState(){
		if(isShowing)
			return;
		
		state = !state;
		
		Minesweeper.checkWin();
	}
	
	public int getPX(){
		return xLoc * Minesweeper.SIZE;
	}

	public int getPY(){
		return yLoc * Minesweeper.SIZE;
	}
	
	public void render(){
		if(isShowing){
			Minesweeper.canvas.setColor(new Color(183, 65, 14));
		}else{
			Minesweeper.canvas.setColor(new Color(150, 75, 0));
		}
		
		Minesweeper.canvas.fillRect(xLoc * Minesweeper.SIZE, yLoc * Minesweeper.SIZE, Minesweeper.SIZE, Minesweeper.SIZE);
		
		if(state){
			Minesweeper.canvas.setColor(new Color(255, 255, 0, 100));
			Minesweeper.canvas.fillRect(xLoc * Minesweeper.SIZE, yLoc * Minesweeper.SIZE, Minesweeper.SIZE, Minesweeper.SIZE);
		}
		
		if(type == TileType.INFO && isShowing && countMinesNear() != 0){
			TextFX.front = Color.black;
			TextFX.mainFont = new Font("Arial", 0, 14);
			TextFX.drawCString(countMinesNear() + "", getPX() + Minesweeper.SIZE / 2, getPY() + Minesweeper.SIZE + 3);
		}
		
		if(type == TileType.MINE && isShowing){
			TextFX.front = Color.black;
			TextFX.mainFont = new Font("Arial", 0, 14);
			TextFX.drawCString("X", getPX() + Minesweeper.SIZE / 2, getPY() + Minesweeper.SIZE + 3);
			
			Minesweeper.canvas.setColor(new Color(255, 0, 0, 100));
			Minesweeper.canvas.fillRect(xLoc * Minesweeper.SIZE, yLoc * Minesweeper.SIZE, Minesweeper.SIZE, Minesweeper.SIZE);
		}
		
		Minesweeper.canvas.setColor(Color.black);
		Minesweeper.canvas.drawRect(xLoc * Minesweeper.SIZE, yLoc * Minesweeper.SIZE, Minesweeper.SIZE, Minesweeper.SIZE);
	}

	public void show(){
		if(state)
			return;
					
		isShowing = true;
		
		if(type == TileType.MINE){
			Minesweeper.showAll(true);
			Minesweeper.lose();
		}else{
			if(countMinesNear() == 0){
				try{
					removeNearZeros();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void removeNearZeros(){
		for(int x = xLoc -1; x <= xLoc + 1; x++){
			for(int y = yLoc -1; y <= yLoc + 1; y++){
				if(x >= 0 && y >= 0 && y < Minesweeper.level.h && x < Minesweeper.level.w){
					Tile check = Tile.map[x][y];
					
					if(!check.isShowing && check.countMinesNear() == 0){
						try{
							check.show();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public int countMinesNear(){
		int count = 0;
		
		for(int x = xLoc -1; x <= xLoc + 1; x++){
			for(int y = yLoc -1; y <= yLoc + 1; y++){
				if(x >= 0 && y >= 0 && y < Minesweeper.level.h && x < Minesweeper.level.w){
					if(Minesweeper.isMine(x, y)){
						count++;
					}
				}
			}
		}
		
		return count;
	}
}
