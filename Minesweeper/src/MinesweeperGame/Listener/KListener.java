package MinesweeperGame.Listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import MinesweeperGame.Minesweeper;

public class KListener implements KeyListener{
	public Minesweeper game;
	public static boolean[] keys = new boolean[256];
	
	public KListener(Minesweeper g){
		game = g;
	}
		
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		if(keys[KeyEvent.VK_R]){
			game.reset();
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			Minesweeper.sel.show();
		}

		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			Minesweeper.sel.onoff();
		}

		if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_ALT){
			Minesweeper.sel.togglelock();
		}

		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			Minesweeper.sel.toggle();
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			Minesweeper.sel.right();
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			Minesweeper.sel.left();
		}

		if(e.getKeyCode() == KeyEvent.VK_UP){
			Minesweeper.sel.up();
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			Minesweeper.sel.down();			
		}
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
