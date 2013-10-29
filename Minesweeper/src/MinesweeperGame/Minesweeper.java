package MinesweeperGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import MinesweeperGame.FX.TextFX;
import MinesweeperGame.Listener.FListener;
import MinesweeperGame.Listener.KListener;
import MinesweeperGame.Listener.MListener;
import MinesweeperGame.Tile.Mine;
import MinesweeperGame.Tile.Tile;
import MinesweeperGame.Tile.TileType;

public class Minesweeper extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	public boolean running = false;
	
	int ticks = 0;
	
	public Image backBuffer;
	public static Graphics canvas;
	
	public boolean focus = false;
	public int yFocOff = 0, yFocDir = 1;

	//Hard = 78 x 36
	//Medium = 30 x 30
	//Easy = 10 x 10
	public static Level level =  Level.MEDIUM;
	public static final int SIZE = 20;
	public static final int PADDING = 30;
	
	public ArrayList<String> highscores = new ArrayList<String>();
	
	public static String msg = "";
	
	public static boolean gameOver = false;
	
	public static Selector sel = new Selector();
	
	public void tick(){
		checkKeys();
		ticks++;
		
		if(!focus){
			updateFocus();
			return;
		}
	}
	
	public void render(){
		canvas.setColor(Color.gray);
		canvas.fillRect(0, 0, getW(), getH());
		
		for(int x = 0; x < level.w; x++){
			for(int y = 0; y < level.h; y++){
				Tile.map[x][y].render();
			}
		}
		
		sel.render();
		
		TextFX.mainFont = new Font("Arial", 1, 20);
		TextFX.drawString(msg, 3, getH() - 8);
		
		if(!focus){
			renderFocus();
		}
	}
	
	public void checkKeys(){
		
	}
	
	public void resetMap(){
		for(int x = 0; x < level.w; x++){
			for(int y = 0; y < level.h; y++){
				Tile.map[x][y] = new Tile(x, y);
			}
		}
		
		for(int i = 0; i < level.getMines(); i++){
			genMine();
		}
	}
	
	public void genMine(){
		int x = new Random().nextInt(level.w);
		int y = new Random().nextInt(level.h);
			
		if(!isMine(x, y)){
			Tile.map[x][y] = new Mine(x, y);
		}else{
			genMine();
		}
	}
	
	public static Tile getPTile(int x, int y){
		int evenX = x - x % SIZE;
		int evenY = y - y % SIZE;
		
		evenX /= SIZE;
		evenY /= SIZE;
		
		if(evenX >= 0 && evenX < level.w){
			if(evenY >= 0 && evenY < level.h){
				return Tile.map[evenX][evenY];
			}
		}
		
		return null;
	}
	
	public static Tile getTile(int x, int y){
		return Tile.map[x][y];
	}
	
	public static void lose(){
		msg = "You Lose! Press 'R'";
		gameOver = true;
	}
	
	public static void win(){
		msg = "You Win! Press 'R'";
		gameOver = true;
		showAll(false);
	}
	
	public static void checkWin(){
		for(int x = 0; x < level.w; x++){
			for(int y = 0; y < level.h; y++){
				Tile check = Tile.map[x][y];
				
				if(check.type == TileType.MINE){
					if(!check.state){
						return;
					}
				}
			}
		}
		
		win();
	}
	
	public static void showAll(boolean justMines){
		for(int x = 0; x < level.w; x++){
			for(int y = 0; y < level.h; y++){
				if(!justMines || isMine(x, y))
					Tile.map[x][y].isShowing = true;
			}
		}
	}
	
	public static boolean isMine(int x, int y){
		if(x < 0 || y < 0 || x >= level.w || y >= level.h)
			return false;
			
		if(Tile.map[x][y].type == TileType.MINE)
			return true;
		
		return false;
	}
	
	public void run() {
		reset();
		
		while(running){
			tick();
			render();
			repaint();
			
			try{
				Thread.sleep(17);
			}catch(Exception e){}
		}
	}

	public void init(){
		
	}
	
	public void start(){
		this.setSize( getW(), getH() );
		
		running = true;
		
		backBuffer = createImage(getW(), getH());
		canvas = backBuffer.getGraphics();

		this.addKeyListener(new KListener(this));
		this.addFocusListener(new FListener(this));
		this.addMouseListener(new MListener(this));
		
		new Thread(this).start();

		this.requestFocus();
	}
	
	public void reset(){
		resetMap();
		msg = "";
		gameOver = false;
	}
	
	public void stop(){
		running = false;
	}
	
	public void updateFocus(){
		yFocOff+=yFocDir;
		
		if(yFocOff > 20){
			yFocDir = -yFocDir;
		}
		if(yFocOff < 0){
			yFocDir = -yFocDir;
		}
	}
	
	public void renderFocus(){
		canvas.setColor(new Color(0, 0, 0, 210));
		canvas.fillRect(0, 0, level.w * SIZE, level.h * SIZE + PADDING);
		
		int xOff = -135, yOff = yFocOff - 10;
		
		TextFX.mainFont = new Font("Arial", 1, 30);
		TextFX.back = Color.gray;
		TextFX.front = Color.white;
		TextFX.draw3DString("Click Here to Play!", getW() / 2 + 2 + xOff, getH() / 2 + 2 + yOff, 2);
		
		repaint();
	}
	
	public void paint(Graphics g){
		g.drawImage(backBuffer, 0, 0, null);
	}
	
	public void update(Graphics g){	
		paint(g);
	}
	
	public int getW(){
		return level.w * SIZE + 1;
	}
	
	public int getH(){
		return level.h * SIZE + PADDING + 1;
	}
}
