package MinesweeperGame.Listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import MinesweeperGame.Minesweeper;

public class FListener implements FocusListener{
	public Minesweeper game;
	
	public FListener(Minesweeper g){
		game = g;
	}
	
	public void focusGained(FocusEvent e) {
		game.focus = true;
	}

	public void focusLost(FocusEvent e) {
		game.focus = false;
	}

}
