package MinesweeperGame.Files;

import java.util.ArrayList;

public class Leader {
	public static ArrayList<Leader> leaders = new ArrayList<Leader>();
	
	public int level = 1;
	public String name = "";
	public int seconds = 0;
	
	public String serialize(){
		String ret = "";
		
		return ret;		
	}
	
	public static Leader toItem(String s){
		Leader l = new Leader();
		
		return l;
	}
}
