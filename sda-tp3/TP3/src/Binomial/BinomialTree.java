package Binomial;

import java.util.LinkedList;

public class BinomialTree {

	public BinomialTreeNode root;
	public int height;

	public BinomialTree(BinomialTreeNode root) {
		super();
		this.root = root;
		this.height = root.height;
	}

	public BinomialTree(int data) {
		this.height = 0;
		this.root = new BinomialTreeNode(data);
	}

	public int getDataRoot() {
		return root.data;
	}

	public LinkedList<BinomialTree> extractRootAndSplit() {
		LinkedList<BinomialTree> trees = new LinkedList<BinomialTree>();
		BinomialTreeNode child = root.child;

		while (child != null) {
			child.parent = null;
			BinomialTree childTree = new BinomialTree(child);

			// sort height asc
			trees.addFirst(childTree);
			child = child.sibling;
		}
		return trees;
	}
	
	public int getUnusedMemory() {
		if (root == null) {
			return 0;
		} else {
			return root.getUnusedMemory();
		}
	}
}
