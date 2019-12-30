package org.apache.dts;

import java.util.Random;

public class MainASC {
	public static void main(String[] args) {
		BTree<Integer, Integer> btree = new BTree<Integer, Integer>();

		// Analyse du temps pris par les opérations.
		Analyzer time_analysis = new Analyzer();

		// Analyse de l'espace mémoire inutilisé.
		Analyzer memory_analysis = new Analyzer();
		long before, after;
		
		for (int i = 0; i < 10000; i++) {
			before = System.nanoTime();
			btree.insert(i, i);
			after = System.nanoTime();

			time_analysis.append(after - before);

			// Enregistrement de l'espace mémoire non-utilisé.
			memory_analysis.append(btree.getUnusedMemory());
		}

		// Affichage de quelques statistiques sur l'expérience.
		System.err.println("Total cost : " + time_analysis.get_total_cost());
		System.err.println("Average cost : " + time_analysis.get_average_cost());

		// Sauvegarde les données de l'expérience: temps et nombre de copies effectuées
		// par opération.
		time_analysis.save_values("../plots/btree_time_java.plot");
		memory_analysis.save_values("../plots/btree_memory_java.plot");
	}
}
