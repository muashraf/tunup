package org.tunup.modules.kmeans.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.tunup.modules.kmeans.configuration.KMeansConfiguration;
import org.tunup.modules.kmeans.configuration.KMeansConfigurationWithCentroids;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

/**
 * Crossovering operator for KMeans.
 * 
 * @author Gianmario Spacagna (gmspacagna@gmail.com)
 */
public class KMeansCrossover extends AbstractCrossover<KMeansConfiguration> {

	public KMeansCrossover(int crossoverPoints, Probability crossoverProbability) {
		super(crossoverPoints, crossoverProbability);
	}

	@Override
	protected List<KMeansConfiguration> mate(KMeansConfiguration parent1,
	    KMeansConfiguration parent2,
	    int numberOfCrossoverPoints, Random rng) {
		KMeansConfiguration[] parents = new KMeansConfiguration[] { parent1, parent2 };
		int[][] parameters = new int[3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				parameters[i][j] = parents[j].getParam(i);
			}
		}
		// swap the second parameter always
		swap(parameters, 1, 0, 1, 1);
		for (int i = 0; i < numberOfCrossoverPoints; i++) {

			for (int j = 0; j < 3; j++) {
				if (rng.nextBoolean()) {
					swap(parameters, 2, 0, 2, 1);
				}
			}
		}
		KMeansConfiguration offspring1 = new KMeansConfiguration(parameters[0][0],
		    parameters[1][0], parameters[2][0]);
		KMeansConfiguration offspring2 = new KMeansConfiguration(parameters[0][1],
		    parameters[1][1], parameters[2][1]);
		List<KMeansConfiguration> offsprings = new ArrayList<KMeansConfiguration>(2);
		offsprings.add(offspring1);
		offsprings.add(offspring2);
		return offsprings;
	}

	private void swap(int[][] parameters, int r1, int c1, int r2, int c2) {
		int tmp = parameters[r1][c1];
		parameters[r1][c1] = parameters[r2][c2];
		parameters[r2][c2] = tmp;
	}
}
