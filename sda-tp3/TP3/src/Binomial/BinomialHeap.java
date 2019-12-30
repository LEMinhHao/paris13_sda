package Binomial;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class BinomialHeap {

	// public List<BinomialTree> list;
	public HashMap<Integer, BinomialTree> nodes;
	int capacity;
	public int countUnion;
	public float loadFactor = 0.75f;
	public float reduceFactor = 0.25f;
	public boolean isReduced;

	// Java document: Constructs an empty HashMap with the default initial capacity
	// (16)
	// and the default load factor (0.75).
	public BinomialHeap() {
		nodes = new HashMap<Integer, BinomialTree>(4, loadFactor);
		capacity = 4;
	}

	public void mergeBinomialHeap(BinomialHeap heap) {
		for (BinomialTree tree : heap.nodes.values()) {
			this.union(tree);
		}
	}

	// min-heap
	public boolean add(int data) {
		// count number of union operation
		countUnion = 0;
		BinomialTree tree = new BinomialTree(data);
		boolean memoryAllocation = doWeEnlarge();
		union(tree);
		return memoryAllocation;
	}

	public int size() {
		return nodes.size();
	}

	public int getMin() {
		if (nodes.size() == 0) {
			throw new RuntimeException("heap is empty");
		}

		int minimum = Integer.MAX_VALUE;

		for (BinomialTree tree : nodes.values()) {
			if (tree.getDataRoot() < minimum) {
				minimum = tree.getDataRoot();
			}
		}

		return minimum;
	}

	public int extractMin() {
		if (nodes.size() == 0) {
			throw new RuntimeException("heap is empty");
		}
		// check will we reduce capacity of map
		isReduced = doWeReduce();

		int minimum = Integer.MAX_VALUE;
		BinomialTree minTree = null;
		for (BinomialTree tree : nodes.values()) {
			if (tree.getDataRoot() < minimum) {
				minimum = tree.getDataRoot();
				minTree = tree;
			}
		}

		// remove minimum tree
		nodes.remove(minTree.height);

		// union sub-trees of minimum tree
		if (minTree.height > 0) {
			LinkedList<BinomialTree> splitedTrees = minTree.extractRootAndSplit();
			for (BinomialTree subTree : splitedTrees) {
				union(subTree);
			}
		}
		// return minimum value
		return minimum;
	}

	private void union(BinomialTree tree) {
		// same height
		BinomialTree newTree = tree;
		while (nodes.containsKey(newTree.height)) {
			// countUnion + 1
			countUnion++;
			BinomialTree subTree = nodes.get(newTree.height);
			if (subTree.getDataRoot() < newTree.getDataRoot()) {
				// let newTree be a children of subTree
				if (subTree.root.child != null) {
					subTree.root.child.parent = null;
				}
				newTree.root.sibling = subTree.root.child;
				subTree.root.child = newTree.root;
				newTree.root.parent = subTree.root;

				// re-assign newTree
				newTree = subTree;
			} else {
				// let subTree be a children of newTree
				if (newTree.root.child != null) {
					newTree.root.child.parent = null;
				}
				subTree.root.sibling = newTree.root.child;
				newTree.root.child = subTree.root;
				subTree.root.parent = newTree.root;
			}

			// remove old subTree which has k height
			nodes.remove(newTree.height);

			// increase height of newTree
			newTree.height++;
			newTree.root.height = newTree.height;
		}

		// after all, it has not any BinomialTree K height in Binomial Heap.
		//
		nodes.put(newTree.height, newTree);
	}

	public boolean doWeEnlarge() {
		if (nodes.size() / capacity >= loadFactor) {
			capacity *= 2;
			return true;
		}
		return false;
	}

	public boolean doWeReduce() {
//		if (nodes.size() > 4 && nodes.size() / capacity <= reduceFactor) {
//			capacity = capacity / 2;
//			HashMap<Integer, BinomialTree> newMap = new HashMap<Integer, BinomialTree>(capacity);
//			for (Entry<Integer, BinomialTree> entry : nodes.entrySet()) {
//				newMap.put(entry.getKey(), entry.getValue());
//			}
//			nodes = newMap;
//			return true;
//		}
		return false;
	}

	public int getUnusedMemory() {
		return capacity - nodes.size();
	}

	public static void main(String[] args) {
		//
		BinomialHeap heap = new BinomialHeap();
		for(int i = 0; i < 10; i++) {
			heap.add(i);
		}
		for(int i = 0; i < 10; i++) {
			heap.extractMin();
		}
		
	}

}
