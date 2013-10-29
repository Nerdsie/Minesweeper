package MinesweeperGame;

public class Level {

	public static Level EASY = new Level(10, 10, true, 25);
	public static Level MEDIUM = new Level(30, 30, true, 20);
	public static Level HARD = new Level(78, 36, true, 15);
	
	// non-static variables //
	
	public int w = 10;
	public int h = 10;
	
	public boolean perc = true;
	
	public int mines = 10;
	
	public Level(int w, int h, boolean perc, int mines){
		this.w = w;
		this.h = h;
		this.perc = perc;
		this.mines = mines;
	}
	
	public int getMines(){
		if(!perc){
			return mines;
		}
		
		double am =  (w*h) * (mines / 100.0);
		
		return (int) am;
	}
}
