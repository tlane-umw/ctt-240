import java.util.ArrayList;


public class World{

	private ArrayList<GameBoard> world = new ArrayList<GameBoard>();


	public World (){
		GameBoard gameBoard0 = new GameBoard(0);
		world.add(gameBoard0);

		GameBoard gameBoard1 = new GameBoard(1);
		world.add(gameBoard1);

		GameBoard gameBoard2 = new GameBoard(2);
		world.add(gameBoard2);
	
	}
	public char[][] getCurrentBoard(int num){
		if (num == 1){
			return world.get(1).getGameBoard();
		}
		return world.get(0).getGameBoard();
	}

}

