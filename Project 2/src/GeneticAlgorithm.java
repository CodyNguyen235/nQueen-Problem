
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm
{
	RandomBoard randBoard = new RandomBoard();
	Random rand = new Random();
	int initialPopulationSize = 50, populationSize, generations = 10000, maxFitness, boardSize = 21; // different variables to adjust the way genetic
																										// algorithm is run.
	double mutation = .1;
	int[] temp, array1, array2, solution;

	int generationsReq;
	long start, end;

	/**
	 * This both generated a random board, as well as runs the genetic algorithm on
	 * it in order to attempt to solve it.
	 * 
	 * @return true if the board is solved, false otherwise
	 */
	public boolean solve()
	{
		start = System.currentTimeMillis();
		generationsReq = 0;
		populationSize = initialPopulationSize - (initialPopulationSize % 2);

		// generate a population to start from
		int[][] population = new int[populationSize][];
		for (int i = 0; i < populationSize; i++)
		{
			population[i] = randBoard.randomBoard(boardSize);
		}

		// beginning of the genetic algorithm
		for (int a = 0; a < generations; a++)
		{

			Arrays.sort(population, Comparator.comparingInt(this::calcCost)); // sort based on the cost of each individual

			population = crossovers(population); // perform crossovers, also known as reproduction

			generationsReq++;
			// System.out.print("New Generation!");

			// check each of the individuals in the population to see if the solution exists
			// in one
			for (int b = 0; b < populationSize; b++)
			{
				temp = population[b];
				// System.out.print(calcCost(temp) + " ");

				if (calcCost(temp) == 0)
				{
					end = System.currentTimeMillis();
					solution = temp;
					return true;
				}

				temp = mutate(temp, mutation); // attempt to mutate each one based on the mutation probability

				if (calcCost(temp) == 0)
				{
					end = System.currentTimeMillis();
					solution = temp;
					return true;
				}

			}
			// System.out.println();

		}
		end = System.currentTimeMillis();
		return false;

	}

	/**
	 * This performs the crossovers of the boards, also known as reproduction
	 * between two boards. It is inputted the whole population and then takes the
	 * top 5 percent, and then uses that to create the next generation.
	 * 
	 * @param population
	 * @return population after crossovers
	 */
	private int[][] crossovers(int[][] population)
	{
		int[][] tempPopulation = population;
		for (int a = 0; a < population.length * .05; a += 2)
		{
			int temp = 0;

			for (int x = 0; x < 20; x++)
			{
				int crossoverPos = (int) (rand.nextDouble() * boardSize);
				for (int b = 0; b < crossoverPos; b++)
				{
					int cross = population[a][b];
					tempPopulation[temp][b] = population[a + 1][b];
					tempPopulation[temp + 1][b] = cross;
				}
				temp++;
			}

		}
		return tempPopulation;
	}

	/**
	 * mutates a board if a randomly generated number is below the mutation
	 * probability number.
	 * 
	 * @param temp, an individual in the population
	 * @param mutationProbability, the probability in which a mutation will occur.
	 *        between 0 and 1
	 * @return the individual, whether or not it is mutated
	 */
	private int[] mutate(int[] temp, double mutationProbability)
	{
		if (mutationProbability >= Math.random())
		{
			temp[(int) (rand.nextDouble() * temp.length)] = (int) (rand.nextDouble() * temp.length);
		}

		return temp;
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
	 * @return the time it took for the genetic algorithm to run in milliseconds
	 */
	public long getTime()
	{
		return end - start;
	}

	/**
	 * @return the amount of generations required to solve the board
	 */
	public int getGenerationsReq()
	{
		return generationsReq;
	}

	/**
	 * @return a board in which the state is a solution to nQueen
	 */
	public int[] getSolution()
	{
		return solution;
	}
}
