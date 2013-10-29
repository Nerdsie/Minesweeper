package MinesweeperGame;

import java.awt.Color;

public class Selector {
	int x = 0;
	int y = 0;
	
	boolean on = false;
	
	boolean lock = false;
	
	public void left(){
		x--;
		
		if(x<0){
			x = Minesweeper.level.h - 1;
		}
		
		moved();
	}
	
	public void right(){
		x++;
		
		if(x>=Minesweeper.level.h){
			x = 0;
		}
		
		moved();
	}
	
	public void up(){
		y--;
		
		if(y < 0){
			y = Minesweeper.level.w - 1;
		}
		
		moved();
	}
	
	public void down(){
		y++;
		
		if(y >= Minesweeper.level.w){
			y = 0;
		}
		
		moved();
	}
	
	public void moved(){
		if(lock){
			show();
		}
	}
	
	public void togglelock(){
		lock = !lock;
	}
	
	public void onoff(){
		on = !on;
	}
	
	public void toggle(){
		Minesweeper.getTile(x, y).toggleState();
	}
	
	public void show(){
		Minesweeper.getTile(x, y).show();
	}
	
	public void render(){
		if(!on){
			return;
		}

		Minesweeper.canvas.setColor(Color.BLACK);
		
		if(lock){
			Minesweeper.canvas.setColor(Color.BLUE);
		}
		
		int w = Minesweeper.SIZE / 10;
		int h = Minesweeper.SIZE - 2;

		Minesweeper.canvas.fillRect(x * Minesweeper.SIZE + w - 1, y * Minesweeper.SIZE + 1, w, h);
		Minesweeper.canvas.fillRect((x+1) * Minesweeper.SIZE - w, y * Minesweeper.SIZE + 1, w, h);

		w = Minesweeper.SIZE - 2;
		h = Minesweeper.SIZE / 10;

		Minesweeper.canvas.fillRect(x * Minesweeper.SIZE + 1, y * Minesweeper.SIZE + h -1 , w, h);
		Minesweeper.canvas.fillRect(x * Minesweeper.SIZE + 1, (1+y) * Minesweeper.SIZE - h, w, h);
	}
}
