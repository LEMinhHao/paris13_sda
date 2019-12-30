package Binomial;

public class BinomialTreeNode {
	public int data;
	public int height;
	public BinomialTreeNode child;
	public BinomialTreeNode sibling;
	public BinomialTreeNode parent;

	public BinomialTreeNode(int data, int height, BinomialTreeNode child, BinomialTreeNode sibling,
			BinomialTreeNode parent) {
		super();
		this.data = data;
		this.height = height;
		this.child = child;
		this.sibling = sibling;
		this.parent = parent;
	}

	public BinomialTreeNode(int data) {
		super();
		this.data = data;
		this.height = 0;
	}

	// recursive pour compter l'espace mémoire inutilisé 
	public int getUnusedMemory() {
		// Noeud n'est pas feuille 
		if (height != 0) {
			if (sibling != null && child != null) {
				return sibling.getUnusedMemory() + child.getUnusedMemory();
			}

			if (sibling != null || child != null) {
				return (sibling != null) ? sibling.getUnusedMemory() + 1 : child.getUnusedMemory() + 1;
			}

			return 2;
		} else {
			// Noeud est un feuille
			if (sibling == null && child == null) {
				return 2;
			}

			if (sibling == null || child == null) {
				return 1;
			}

			return 0;
		}

	}

}
