import java.util.Arrays;

public class BinaryHeap {

	public int[] data;
	public int size;
	public int capacity;
	public int countSwap;

	public BinaryHeap(int size) {
		data = new int[size];
		capacity = size;
		size = 0;
	}

	public void add(int e) {
		// append to last index
		data[size] = e;
		size++;
		if (size == 1)
			return;
		// compare with parent and swap if it less parent
		// find parent
		int currentIndex = size - 1;
		int parentIndex = findParent(currentIndex);
		int parentValue = data[parentIndex];

		// reset swap counting
		countSwap = 0;
		// while new element less than parent
		while (data[currentIndex] < parentValue && currentIndex != 0) {
			// swap
			swap(currentIndex, parentIndex);

			// continue loop
			currentIndex = parentIndex;
			parentIndex = findParent(currentIndex);
			parentValue = data[parentIndex];
		}
	}

	public int extractMinimum() {

		int rightindex;
		int leftindex;
		int minindex;
		int minValue = data[0];

		// reset swap counting
		countSwap = 0;
		if (size == 0)
			throw new RuntimeException("heap is empty");
		else {
			size--;
			if (size == 0) {
				return data[0]; 
			}
			data[0] = data[size];
			
			int currentIndex = 0;
			// find minimum children
			rightindex = currentIndex * 2 + 2;
			leftindex = currentIndex * 2 + 1;
			// if rightChildren and leftChildren are not null
			if (leftindex < size && rightindex < size) {
				// minimum value of children
				minindex = data[leftindex] < data[rightindex] ? leftindex : rightindex;
			} else if (leftindex < size) {
				// if rightIndex is null
				minindex = leftindex;
			} else {
				return minValue;
			}

			// while it greater than minimum children -> swap
			while (data[currentIndex] > data[minindex] && minindex < size) {
				// swap
				swap(currentIndex, minindex);

				// continue loop
				currentIndex = minindex;

				// find minimum children
				rightindex = currentIndex * 2 + 2;
				leftindex = currentIndex * 2 + 1;
				// if rightChildren and leftChildren are not null
				if (leftindex < size && rightindex < size) {
					// minimum value of children
					minindex = data[leftindex] < data[rightindex] ? leftindex : rightindex;
				} else if (leftindex < size) {
					// if rightIndex is null
					minindex = leftindex;
				} else {
					break;
				}
			}

		}
		return minValue;

	}

	private int findParent(int index) {
		return (index - 1) / 2;
	}

	private void swap(int index1, int index2) {
		countSwap++;
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	public static void main(String[] args) {
		BinaryHeap heap = new BinaryHeap(10);
		heap.add(6);
		heap.add(5);
		heap.add(7);
		heap.add(3);
		heap.add(1);
		System.out.println(Arrays.toString(heap.data));

		heap.extractMinimum();
		System.out.println(Arrays.toString(heap.data));

		heap.extractMinimum();
		System.out.println(Arrays.toString(heap.data));
	}
}
