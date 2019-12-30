import java.util.Arrays;

public class BinaryHeap2 {

	public ArrayList<Integer> data;
//	public int size;
//	public int capacity;
	public int countSwap;
	public boolean isReduced;

	public BinaryHeap2() {
		data = new ArrayList<Integer>();
	}

	public boolean add(int e) {
		// append to last index
		boolean memoryAllocation = data.append(e);
		if (data.size() == 1)
			return false;

		// compare with parent and swap if it less parent
		// find parent
		int currentIndex = data.size() - 1;
		int parentIndex = findParent(currentIndex);
		int parentValue = data.get(parentIndex);

		// reset swap counting
		countSwap = 0;
		// while new element less than parent
		while (data.get(currentIndex) < parentValue && currentIndex != 0) {
			// swap
			swap(currentIndex, parentIndex);

			// continue loop
			currentIndex = parentIndex;
			parentIndex = findParent(currentIndex);
			parentValue = data.get(parentIndex);
		}
		return memoryAllocation;
	}

	public int extractMinimum() {
		if (data.size() == 0)
			throw new RuntimeException("heap is empty");
		int rightindex;
		int leftindex;
		int minindex;
		int minValue = data.get(0);
		if (data.size() == 1) {
			isReduced = data.pop_back();
			return minValue;
		}
		// reset swap counting
		countSwap = 0;

//		data.set(0, data.get(data.size() - 1));
		data.set(0, data.get(data.size() - 1));
		isReduced = data.pop_back();

		int currentIndex = 0;
		// find minimum children
		rightindex = currentIndex * 2 + 2;
		leftindex = currentIndex * 2 + 1;

		// if rightChildren and leftChildren are not null
		if (leftindex < data.size() && rightindex < data.size()) {
			// minimum value of children
			minindex = data.get(leftindex) < data.get(rightindex) ? leftindex : rightindex;
		} else if (leftindex < data.size()) {
			// if rightIndex is null
			minindex = leftindex;
		} else {
			return minValue;
		}

		// while it greater than minimum children -> swap
		while (data.get(currentIndex) > data.get(minindex) && minindex < data.size()) {
			// swap
			swap(currentIndex, minindex);

			// continue loop
			currentIndex = minindex;

			// find minimum children
			rightindex = currentIndex * 2 + 2;
			leftindex = currentIndex * 2 + 1;
			// if rightChildren and leftChildren are not null
			if (leftindex < data.size() && rightindex < data.size()) {

				// minimum value of children
				minindex = data.get(leftindex) < data.get(rightindex) ? leftindex : rightindex;
			} else if (leftindex < data.size()) {
				// if rightIndex is null
				minindex = leftindex;
			} else {
				break;
			}
		}

		return minValue;

	}

	public int getUnusedMemory() {
		return data.capacity() - data.size();
	}

	public int size() {
		return data.size();
	}

	private int findParent(int index) {
		return (index - 1) / 2;
	}

	private void swap(int index1, int index2) {
		countSwap++;
		int temp = data.get(index1);
		data.set(index1, data.get(index2));
		data.set(index2, temp);
	}

	public static void main(String[] args) {
		BinaryHeap2 heap = new BinaryHeap2();
		heap.add(6);
		heap.add(5);
		heap.add(7);
		heap.add(3);
		heap.add(1);
		System.out.println(heap.data);

		heap.extractMinimum();
		System.out.println(heap.data);

		heap.extractMinimum();
		System.out.println(heap.data);
	}
}
