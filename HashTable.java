package assignment5;

import java.io.IOException;

public class HashTable {
	private DataItem[] hashArray;

	private int arraySize;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
	}

	public int hashFunction(int key) {
		return key % arraySize;
	}

	public int doubleFun(int key) {
		int r = arraySize - 2;
		return r + key % r;
	}

	public void insert(DataItem item) {
		int key = item.getKey();
		int hashVal = hashFunction(key); // hash the key
		int i = 0;
		while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
			//++hashVal; // linear
			hashVal = hashVal + i*i; // quadratic
			//hashVal = (hashVal + i * doubleFun(key)); // double
			i++;
			hashVal %= arraySize; // wrap around if necessary
		}
		hashArray[hashVal] = item; // insert item
	}

	public void displayTable() {
		System.out.print("Hash table: ");
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null) {
				System.out.print(hashArray[j].getKey() + " ");
			} else
				System.out.print("# ");
		}
	}

	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, hashSize, keySize, keysPerCell;
		double loadFac;
		
		//test
		hashSize = 50000000;
		keySize = (int) (50000000*0.6);
		keysPerCell = 10;
		HashTable theHashTable = new HashTable(hashSize);

		long startTime = System.currentTimeMillis();
		for (int j = 0; j < keySize; j++) {
			aKey = (int) (Math.random() * keysPerCell * hashSize);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Cost time: " + (endTime - startTime) + "ms");
		loadFac = (double) keySize / (double) hashSize;
		System.out.println("Load factor=" + loadFac);
		// theHashTable.displayTable();

	}
}