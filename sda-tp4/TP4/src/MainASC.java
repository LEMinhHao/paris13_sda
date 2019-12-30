
import java.util.Random;
public class MainASC {
	public static void main(String[] args) {
		AVLTree avltree = new AVLTree();

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;
		
		for (int i = 0; i < 10000; i++) {
			before = System.nanoTime();
			avltree.insert(i);
			after = System.nanoTime();

			time_analysis.append(after - before);

			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(avltree.getUnusedMemory());
		}

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/avltree_time_java.plot");
		memory_analysis.save_values("../plots/avltree_memory_java.plot");
	}
}
