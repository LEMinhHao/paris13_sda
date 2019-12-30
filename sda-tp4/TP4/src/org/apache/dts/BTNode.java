package org.apache.dts;

/**
 * Class BTNode
 * 
 * @author tnguyen
 */
public class BTNode<K extends Comparable, V> {
	public final static int MIN_DEGREE = 5;
	public final static int LOWER_BOUND_KEYNUM = MIN_DEGREE - 1;
	public final static int UPPER_BOUND_KEYNUM = (MIN_DEGREE * 2) - 1;

	protected boolean mIsLeaf;
	protected int mCurrentKeyNum;
	protected BTKeyValue<K, V> mKeys[];
	protected BTNode<K, V> mChildren[];

	protected int degree;
	protected int lowerBound;
	protected int upperBound;

	public BTNode() {
		mIsLeaf = true;
		mCurrentKeyNum = 0;
		mKeys = new BTKeyValue[UPPER_BOUND_KEYNUM];
		mChildren = new BTNode[UPPER_BOUND_KEYNUM + 1];
		this.degree = MIN_DEGREE;// degree;
		this.lowerBound = this.degree - 1;
		this.upperBound = this.degree * 2 - 1;

	}

	public BTNode(int degree) {
		mIsLeaf = true;
		mCurrentKeyNum = 0;
		this.degree = degree;// degree;
		this.lowerBound = this.degree - 1;
		this.upperBound = this.degree * 2 - 1;
		mKeys = new BTKeyValue[this.upperBound];
		mChildren = new BTNode[this.upperBound + 1];

	}

	public int getUnusedMemory() {
		return 2 * upperBound - 2 * mCurrentKeyNum;
	}

	protected static BTNode getChildNodeAtIndex(BTNode btNode, int keyIdx, int nDirection) {
		if (btNode.mIsLeaf) {
			return null;
		}

		keyIdx += nDirection;
		if ((keyIdx < 0) || (keyIdx > btNode.mCurrentKeyNum)) {
			return null;
		}

		return btNode.mChildren[keyIdx];
	}

	protected static BTNode getLeftChildAtIndex(BTNode btNode, int keyIdx) {
		return getChildNodeAtIndex(btNode, keyIdx, 0);
	}

	protected static BTNode getRightChildAtIndex(BTNode btNode, int keyIdx) {
		return getChildNodeAtIndex(btNode, keyIdx, 1);
	}

	protected static BTNode getLeftSiblingAtIndex(BTNode parentNode, int keyIdx) {
		return getChildNodeAtIndex(parentNode, keyIdx, -1);
	}

	protected static BTNode getRightSiblingAtIndex(BTNode parentNode, int keyIdx) {
		return getChildNodeAtIndex(parentNode, keyIdx, 1);
	}
}
