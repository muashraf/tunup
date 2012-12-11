package org.tunup.modules.kmeans.watchmaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.tunup.modules.kmeans.javaml.KMeansParameters;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import org.uncommons.watchmaker.framework.operators.IntArrayCrossover;

/**
 * Crossover operator for KMeans as an Array of Integers.
 * 
 * @author Gianmario Spacagna (gmspacagna@gmail.com)
 */
public class KMeansCrossover extends AbstractCrossover<KMeansParameters> {

	public KMeansCrossover(int crossoverPoints, Probability crossoverProbability) {
		super(crossoverPoints, crossoverProbability);
	}

	@Override
  protected List<KMeansParameters> mate(KMeansParameters parent1, KMeansParameters parent2,
      int numberOfCrossoverPoints, Random rng) {
		KMeansParameters[] parents = new KMeansParameters[]{parent1, parent2};
		int[][] parameters = new int[3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				parameters[i][j] = parents[j].getParam(i);
			}
		}
		for (int i = 0 ; i < numberOfCrossoverPoints; i++) {
			int index = rng.nextInt(4);
			for (int j = index; j < 3; j++) {
				// crossover all the parameters at the right of index (included)
				swap(parameters[j][0], parameters[j][1]);
			}
		}
		KMeansParameters offspring1 = new KMeansParameters(parameters[0][0], 
				parameters[1][0], parameters[2][0]);
		KMeansParameters offspring2 = new KMeansParameters(parameters[0][1], 
				parameters[1][1], parameters[2][1]);
		List<KMeansParameters> offsprings = new ArrayList<KMeansParameters>(2);
		offsprings.add(offspring1);
		offsprings.add(offspring2);
		return offsprings;
	}
	
	private void swap(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}
}