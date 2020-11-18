import java.util.Random;

public class SimulatedAnnealing
{

	RandomBoard randBoard = new RandomBoard();
	Random rand = new Random();
	int[] board = new int[21];
	double randomness = 120;
	int currentCost, movesReq;
	long start, end;

	/**
	 * This both generated a random board, as well as runs the simulated Annealing
	 * algorithm on it in order to attempt to solve it.
	 * 
	 * @return true if the board is solved, false otherwise
	 */
	public boolean solve()
	{
		start = System.currentTimeMillis();
		board = randBoard.randomBoard(board);
		currentCost = calcCost(board);
		movesReq = 0;

		for (int i = 0; currentCost > 0 && i < 1000; i++)
		{
			board = moveQueen();
			movesReq++;
			currentCost = calcCost(board);
			randomness = Math.max(randomness * .95, 0.01); // .95 is the cooling factor, .01 is the minimum randomness
		}

		end = System.currentTimeMillis();
		if (currentCost == 0)
			return true;
		else
			return false;
	}

	/**
	 * This simulates moving a queen
	 * 
	 * @return a board with a queen moved
	 */
	private int[] moveQueen()
	{

		while (true)
		{
			int row = (int) (rand.nextDouble() * board.length);
			int col = (int) (rand.nextDouble() * board.length);

			int temp = board[col];
			board[col] = row;

			int cost = calcCost(board);
			int costDifference = currentCost - cost;
			double accept = Math.min(1, Math.exp(costDifference / randomness));

			if (cost < currentCost)
			{
				return board;
			}

			if (rand.nextDouble() < accept)
			{
				return board;
			}

			board[col] = temp;
		}

	}

	/**
	 * This calculated the cost, or the amount of moves required, to solve the
	 * board. also known as how many queens are able to capture another queen.
	 * 
	 * @param temp, an individual in the population
	 * @return the cost to solve the board
	 */
	public int calcCost(int[] temp)
	{
		int cost = 0;

		for (int a = 0; a < temp.length; a++)
		{
			for (int b = a + 1; b < temp.length; b++)
			{
				if (temp[a] == temp[b] || Math.abs(temp[a] - temp[b]) == b - a)
					cost++;
			}
		}

		return cost;
	}

	/**
	 * 
	 * @return The cost it took to solve the board, also known as the moves made by
	 *         the algorithm
	 */
	public int getCost()
	{
		return currentCost;
	}

	/**
	 * @return the time it took for the genetic algorithm to run in milliseconds
	 */
	public long getTime()
	{
		return end - start;
	}

	/**
	 * 
	 * @return The mount of moves it took for the algorithm to complete the board
	 */
	public int getMovesReq()
	{
		return movesReq;
	}

	/**
	 * @return a board in which the state is a solution to nQueen
	 */
	public int[] getSolution()
	{
		return board;
	}
}
