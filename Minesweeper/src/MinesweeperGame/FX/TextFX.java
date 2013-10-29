package MinesweeperGame.FX;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import MinesweeperGame.Minesweeper;

public class TextFX {
	public static Color front = Color.BLACK;
	public static Color back = Color.GRAY;
	public static Font mainFont = new Font("Arial", 32, 0);
	
	public static int getSW(String s){
		Minesweeper.canvas.setFont(mainFont);
		
		FontMetrics FM = Minesweeper.canvas.getFontMetrics(Minesweeper.canvas.getFont());
		return FM.stringWidth(s);
	}
	
	public static void drawCString(String m, int y){
		Minesweeper.canvas.setColor(front);
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.drawString(m, Minesweeper.WIDTH / 2 - getSW(m) / 2, y - mainFont.getSize() / 2);
	}
	
	public static void drawCString(String m){
		Minesweeper.canvas.setColor(front);
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.drawString(m, Minesweeper.WIDTH / 2 - getSW(m) / 2, Minesweeper.HEIGHT / 2 - mainFont.getSize() / 2);
	}
	
	public static void drawCString(String m, int x, int y){
		Minesweeper.canvas.setColor(front);
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.drawString(m, Minesweeper.WIDTH / 2 - getSW(m) / 2 + x, y - mainFont.getSize() / 2);
	}
	
	public static void drawC3DString(String m, int y, int sep){
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.setColor(back);
		drawCString(m, 2, y + sep - mainFont.getSize() / 2);
		Minesweeper.canvas.setColor(front);
		drawCString(m, y - mainFont.getSize() / 2);
	}

	public static void draw3DString(String m, int x, int y, int sep){
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.setColor(back);
		Minesweeper.canvas.drawString(m, x + sep, y + sep - mainFont.getSize() / 2);
		Minesweeper.canvas.setColor(front);
		Minesweeper.canvas.drawString(m, x, y - mainFont.getSize() / 2);
	}
	
	public static void drawString(String m, int x, int y){
		Minesweeper.canvas.setColor(front);
		Minesweeper.canvas.setFont(mainFont);
		Minesweeper.canvas.drawString(m, x, y);
	}
	
}
