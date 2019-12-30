import java.util.Arrays;

public class MainInsertionASC {

	public static void main(String[] args) throws InterruptedException {
		// construction
		BinaryHeap heap = new BinaryHeap(10000);

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();
		// Analyse du nombre de copies faites par les opérations.
		Analyzer copy_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;


		// insert number increased
		for (int i = 0; i < 10000; i++) {
			before = System.nanoTime();
			heap.add(i);
			after = System.nanoTime();

			// Enregistrement du temps pris par l'opération
			time_analysis.append(after - before);
			// Enregistrement du nombre de copies efféctuées par l'opération.
			// S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
			copy_analysis.append(1 + heap.countSwap * 2); 

			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(heap.capacity - heap.size);
		}

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/binary_heap_time_java_ASC.plot");
		copy_analysis.save_values("../plots/binary_heap_copy_java_ASC.plot");
		memory_analysis.save_values("../plots/binary_heap_memory_java_ASC.plot");
		System.out.print(Arrays.toString(heap.data));

	}
}
