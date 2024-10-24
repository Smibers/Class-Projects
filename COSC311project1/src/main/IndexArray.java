//Smith   Benjamin
//COSC-311 fall2024
//Project 1


package main;

public class IndexArray 
{
//ordered array

	
	public String ID;
	public int iterator; // where the "pointer" is in the array
	private IndexRecord[] indexRecord;
	public int elements; 
	public IndexArray(int size) 
	{
		this.indexRecord = new IndexRecord[size]; 
		this.iterator = 0; 
		elements = 0;
	}

	// Sets the the iterator to 0:
	public void iteratorInitFront() 
	{
		iterator = 0;
	}

	// Sets the iterator to the back of the array:
	public void iteratorInitBack() 
	{
		iterator = elements - 1;
	}

	// Method to get the record using linear search
	public int find(String key) 
	{

		for (int i = 0; i < elements; i++) 
		{
			if (indexRecord[i].getKey().equalsIgnoreCase(key)) 
			{ // Use 'key' parameter
				return indexRecord[i].getWhere(); 
			}
		}
		return -1; 

	}

	public boolean hasNext() 
	{
		return iterator < elements;
	}

	
	public boolean hasPrevious() 
	{
		return iterator >= 0;
	}

	// Return the 'where' field from IndexRecord; then increment the iterator:
	public int getNext() 
	{
		if (!hasNext()) 
		{
			return -1; 
		}
		// get 'where' value
		int where = indexRecord[iterator].getWhere();
		iterator++;
		return where;
	}
	//make sure that the indexRecord reflects the new positions of all elements after the removal.
	public void updateIndicesAfterRemoval(int removedIndex) {
	    for (int i = 0; i < elements; i++) {
	        int where = indexRecord[i].getWhere();
	        if (where > removedIndex) {
	            indexRecord[i].setWhere(where - 1);
	        }
	    }
	}

	public int getPrevious() {
	    if (!hasPrevious()) {
	        return -1; // Indicate no more elements
	    }
	    int where = indexRecord[iterator].getWhere();
	    iterator--;
	    return where;
	}


	public void insert(IndexRecord record)
	{

		// Find the correct position to insert (keep the array ordered)
		int i;
		for (i = elements - 1; i >= 0; i--) 
		{
			if (record.getKey().compareTo(indexRecord[i].getKey()) > 0) 
			{
				break;
			}
			indexRecord[i + 1] = indexRecord[i];
		}
		indexRecord[i + 1] = record;
		elements++;
	}

	public int remove(String key) {
	    int index = -1;

	    for (int i = 0; i < elements; i++) {
	        if (indexRecord[i].getKey().equalsIgnoreCase(key)) {
	            index = i;
	            break;
	        }
	    }

	    if (index == -1) {
	        return -1; // Key not found
	    }

	    int indexToRemove = indexRecord[index].getWhere();

	    // Shift the elements
	    for (int i = index; i < elements - 1; i++) {
	        indexRecord[i] = indexRecord[i + 1];
	    }
	    indexRecord[--elements] = null; // Clear the last element
	    return indexToRemove;
	}


}