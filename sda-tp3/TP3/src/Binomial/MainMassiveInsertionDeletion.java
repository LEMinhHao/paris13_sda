package Binomial;

import java.util.ArrayList;
import java.util.Random;

public class MainMassiveInsertionDeletion {
	public static void main(String[] args) {
		// construction
		ArrayList<BinomialHeap> heaps = new ArrayList<BinomialHeap>(6);
		BinomialHeap heap1 = new BinomialHeap();
		BinomialHeap heap2 = new BinomialHeap();
		BinomialHeap heap3 = new BinomialHeap();
		BinomialHeap heap4 = new BinomialHeap();
		BinomialHeap heap5 = new BinomialHeap();
		BinomialHeap heap6 = new BinomialHeap();

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();
		// Analyse du nombre de copies faites par les opérations.
		Analyzer copy_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;
		Random randomGen = new Random();
		int element;
		boolean isAllocated = false;
		for (int i = 0; i < 100000; i++) {
			element = randomGen.nextInt();
			before = System.nanoTime();
			heap1.add(element);
			heap2.add(element);
			heap3.add(element);
			heap4.add(element);
			heap5.add(element);
			isAllocated = heap6.add(element);
			after = System.nanoTime();
			time_analysis.append(after - before);

			// Enregistrement du nombre de copies efféctuées par l'opération.
			// S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
			if (isAllocated) {
				copy_analysis.append((heap1.size() + heap1.countUnion * 5) * 6);
			} else {
				copy_analysis.append((1 + heap1.countUnion * 5) * 6);
			}
			//
			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(heap1.getUnusedMemory() * 6);
		}

		for (int i = 0; i < 80000; i++) {
			before = System.nanoTime();
			heap1.extractMin();
			heap2.extractMin();
			heap3.extractMin();
			heap4.extractMin();
			heap5.extractMin();
			heap6.extractMin();
			after = System.nanoTime();
			isAllocated = heap1.isReduced;
			time_analysis.append(after - before);

			// Enregistrement du nombre de copies efféctuées par l'opération.
			// S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
			if (isAllocated) {
				copy_analysis.append((heap1.size() + heap1.countUnion * 5) * 6);
			} else {
				copy_analysis.append((1 + heap1.countUnion * 5) * 6);
			}
			//
			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(heap1.getUnusedMemory() * 6);
		}

		// TODO: merge 2 bonimial

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/binomial_heap_time_java_massive.plot");
		copy_analysis.save_values("../plots/binomial_heap_copy_java_massive.plot");
		memory_analysis.save_values("../plots/binomial_heap_memory_java_massive.plot");
	}
}
