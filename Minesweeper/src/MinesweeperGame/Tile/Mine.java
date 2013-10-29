package MinesweeperGame.Tile;

public class Mine extends Tile{

	public Mine(int xx, int yy) {
		super(xx, yy);
		
		type = TileType.MINE;
	}

}
