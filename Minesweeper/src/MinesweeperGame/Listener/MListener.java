package MinesweeperGame.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import MinesweeperGame.Minesweeper;

public class MListener implements MouseListener{
	Minesweeper game;
	
	public MListener(Minesweeper minesweeper) {
		game = minesweeper;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Minesweeper.gameOver)
			return;
		
		if(e.getButton() == 1){
			try{
				Minesweeper.getPTile(e.getX(), e.getY()).show();
			}catch(Exception ex){
			}
		}
		
		if(e.getButton() == 3){
			try{
				Minesweeper.getPTile(e.getX(), e.getY()).toggleState();
			}catch(Exception ex){
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
