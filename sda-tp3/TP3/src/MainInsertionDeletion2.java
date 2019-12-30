import java.util.Arrays;
import java.util.Random;

public class MainInsertionDeletion2 {

	public static void main(String[] args) {
		// construction
		BinaryHeap2 heap = new BinaryHeap2();

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();
		// Analyse du nombre de copies faites par les opérations.
		Analyzer copy_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;
		boolean isAllocated = false;
		
		// 70% add, 30% extract
		double P = 0.7;
		int e;
		Random random = new Random();
		
		for (int i = 10000; i > 0; i--) {
			if (random.nextDouble() < P) {
				e = random.nextInt(100000);
				before = System.nanoTime();
				isAllocated = heap.add(i);
				after = System.nanoTime();
			} else {
				before = System.nanoTime();
				if (heap.size() > 0) {
					heap.extractMinimum();
					isAllocated = heap.isReduced;
				}
				after = System.nanoTime();
			}

			// Enregistrement du temps pris par l'opération
			time_analysis.append(after - before);

			// Enregistrement du nombre de copies efféctuées par l'opération.
			// S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
			if (isAllocated) {
				copy_analysis.append(heap.countSwap * 2 + heap.size());
			} else {
				copy_analysis.append(1 + heap.countSwap * 2);
			}
			

			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(heap.getUnusedMemory());
		}

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/binary_heap_time_java_InsDel2.plot");
		copy_analysis.save_values("../plots/binary_heap_copy_java_InsDel2.plot");
		memory_analysis.save_values("../plots/binary_heap_memory_java_InsDel2.plot");
		System.out.print(heap.data.toString());
	}
}
