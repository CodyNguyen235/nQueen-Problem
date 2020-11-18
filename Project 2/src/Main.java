
public class Main
{

	public static void main(String[] args)
	{
		int iterations = 1000, solutionCounter = 0; // change iterations to change the amount of times each algorithm is run.
		double simSolved = 0, simTotalCost = 0, genSolved = 0, genTotalCost = 0, simTotalTime = 0, genTotalTime = 0;
		int[] simSol1 = null, simSol2 = null, simSol3 = null, genSol1 = null, genSol2 = null, genSol3 = null;
		boolean temp;

		// Start of the Simulated Annealing Algorithm
		SimulatedAnnealing sim = new SimulatedAnnealing();
		System.out.println("Start of Program: Simulated Annealing Run " + iterations + " Times");
		for (int i = 0; i < iterations; i++)
		{
			System.out.print("Current Iteration: " + (i + 1));
			temp = sim.solve();

			if (temp)
			{
				simSolved++;
				System.out.println(" Solved! " + "Search Cost: " + sim.getMovesReq() + " Time: " + sim.getTime() + " ms");
				simTotalCost += sim.getMovesReq();
				simTotalTime += sim.getTime();

				// Save the first 3 solutions that come up in order to display at the end.
				if (solutionCounter == 0)
					simSol1 = sim.getSolution();
				if (solutionCounter == 1)
					simSol2 = sim.getSolution();
				if (solutionCounter == 2)
					simSol3 = sim.getSolution();
				solutionCounter++;

			} else
			{
				System.out.println(" Failed! " + "Search Cost: " + sim.getMovesReq() + " Time: " + sim.getTime() + " ms");
			}
		}

		solutionCounter = 0; // reset the solution counter

		// Start of the Genetic Algorithm
		GeneticAlgorithm gen = new GeneticAlgorithm();
		System.out.println("Start of Program: Genetic Algorithm Run " + iterations + " Times");
		for (int i = 0; i < iterations; i++)
		{
			System.out.print("Current Iteration: " + (i + 1));
			temp = gen.solve();

			if (temp)
			{
				genSolved++;
				System.out.println(" Solved! " + "Search Cost: " + gen.getGenerationsReq() + " Generations  Time: " + gen.getTime() + " ms");
				genTotalCost += gen.getGenerationsReq();
				genTotalTime += gen.getTime();

				// Save the first 3 solutions that come up in order to display at the end.
				if (solutionCounter == 0)
					genSol1 = gen.getSolution();
				if (solutionCounter == 1)
					genSol2 = gen.getSolution();
				if (solutionCounter == 2)
					genSol3 = gen.getSolution();
				solutionCounter++;
			} else
			{
				System.out.println(" Failed! " + "Search Cost: " + gen.getGenerationsReq() + " Generations  Time: " + gen.getTime() + " ms");
			}
		}

		System.out.println();

		System.out.println("Simulated Annealing Summary:");
		System.out.println("Percentage Solved: " + ((simSolved / iterations) * 100) + "%");
		System.out.println("Average Search Cost of Successes: " + simTotalCost / iterations + " Moves Required");
		System.out.println("Average Time of Successes: " + simTotalTime / iterations + " ms");

		System.out.println();

		System.out.println("Genetic Algorithm Summary:");
		System.out.println("Percentage Solved: " + ((genSolved / iterations) * 100) + "%");
		System.out.println("Average Search Cost of Successes: " + genTotalCost / iterations + " Generations");
		System.out.println("Average Time of Successes: " + genTotalTime / iterations + " ms");

		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println();

		System.out.println("Sample Solutions ");
		System.out.println();
		System.out.println("Simulated Annealing Solution 1");
		System.out.println();

		printSolution(simSol1);

		System.out.println("Simulated Annealing Solution 2");
		System.out.println();

		printSolution(simSol2);

		System.out.println("Simulated Annealing Solution 3");
		System.out.println();

		printSolution(simSol3);

		System.out.println("Genetic Algorithm Solution 1");
		System.out.println();

		printSolution(genSol1);

		System.out.println("Genetic Algorithm Solution 2");
		System.out.println();

		printSolution(genSol2);

		System.out.println("Genetic Algorithm Solution 3");
		System.out.println();

		printSolution(genSol3);

	}

	/**
	 * Prints the inputted board into the console
	 * 
	 * @param temp
	 */
	private static void printSolution(int[] temp)
	{
		for (int col = 0; col < 21; col++)
		{
			for (int row = 0; row < 21; row++)
			{
				if (temp[col] == row)
					System.out.print(" Q ");
				else
					System.out.print(" . ");
			}
			System.out.println();
		}
	}

}
