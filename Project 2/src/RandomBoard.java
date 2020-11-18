import java.util.Random;

public class RandomBoard
{
	Random rand = new Random();

	/**
	 * generated a random board represented by an array of integers where the
	 * position is the columns and the integer is the rows.
	 * 
	 * @param temp, a board to be randomized
	 * @return a randomized board
	 */
	public int[] randomBoard(int[] temp)
	{

		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = (int) (rand.nextDouble() * temp.length);
		}
		return temp;
	}

	/**
	 * equivalent to the above but the input is now the size of the board instead of
	 * a board.
	 * 
	 * @param n, the size of the board
	 * @return a randomized board
	 */
	public int[] randomBoard(int n)
	{

		int[] temp = new int[n];

		for (int i = 0; i < n; i++)
			temp[i] = (int) (rand.nextDouble() * n);

		return temp;
	}

}
