//Smith   Benjamin
//COSC-311 fall2024
//Project 1


package main;

import java.util.Arrays;

public class DataBaseArray 
{
	public int elems = 0;
	private DataBaseRecord[] records;

	public DataBaseArray(int capacity) 
	{
		records = new DataBaseRecord[capacity];
	}

	public void insert(DataBaseRecord record) 
	{
		records[elems++] = record;
	}

	public DataBaseRecord retrieve(int index) 
	{
		if (index >= 0 && index < elems && records[index] != null) 
		{
			return records[index]; // Only return non-null students
		}
		return null;

	}

	public DataBaseRecord[] getRecords()
	{
		return records;
	}

	public void remove(String id, IndexArray idIndex, IndexArray firstIndex, IndexArray lastIndex) {
	    int indexToRemove = -1;

	    for (int i = 0; i < elems; i++) {
	        if (records[i].getID().equalsIgnoreCase(id)) {
	            indexToRemove = i;
	            break; // Remove and exit loop once found
	        }
	    }

	    if (indexToRemove == -1) {
	        System.out.println("ID not found. Cannot delete.");
	        return; // Early exit if not found
	    }

	    // Remove the record from the IndexArrays
	    idIndex.remove(records[indexToRemove].getID());
	    firstIndex.remove(records[indexToRemove].getFirst());
	    lastIndex.remove(records[indexToRemove].getLast());

	    // Shifting logic
	    for (int i = indexToRemove; i < elems - 1; i++) {
	        records[i] = records[i + 1]; // Shift left
	    }
	    records[--elems] = null; // Clear the last element

	    // Update the 'where' fields in IndexRecords in all IndexArrays
	    idIndex.updateIndicesAfterRemoval(indexToRemove);
	    firstIndex.updateIndicesAfterRemoval(indexToRemove);
	    lastIndex.updateIndicesAfterRemoval(indexToRemove);
	}


}