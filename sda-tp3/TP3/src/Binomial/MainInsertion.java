package Binomial;

import java.util.Random;

public class MainInsertion {

	public static void main(String[] args) {
		BinomialHeap binomialHeap = new BinomialHeap();

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();
		// Analyse du nombre de copies faites par les opérations.
		Analyzer copy_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;
		boolean isAllocated;
		Random randomGenerator = new Random();
		int e;
		
		for (int i = 0; i < 100000; i++) {
			e = randomGenerator.nextInt();
			before = System.nanoTime();
			isAllocated = binomialHeap.add(e);
			after = System.nanoTime();

			time_analysis.append(after - before);

			// Enregistrement du nombre de copies efféctuées par l'opération.
			// S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
			if (isAllocated) {
				copy_analysis.append(binomialHeap.size() + binomialHeap.countUnion * 5);
			} else {
				copy_analysis.append(1 + binomialHeap.countUnion * 5);
			}

			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(binomialHeap.getUnusedMemory());
		}

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/binomial_heap_time_java.plot");
		copy_analysis.save_values("../plots/binomial_heap_copy_java.plot");
		memory_analysis.save_values("../plots/binomial_heap_memory_java.plot");
	}
}
