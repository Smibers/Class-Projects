//Smith   Benjamin

//COSC-311 fall2024
//Project 1


package main;

public class IndexArray 
{
//ordered array

	// Fields
	public String ID;
	public int iterator;
	private IndexRecord[] indexRecord;
	public int elements;

	// initialize new array with specific size
	public IndexArray(int size) 
	{
		this.indexRecord = new IndexRecord[size]; // Create an array with a specified size
		this.iterator = 0; // Initialize iterator to 0
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
		iterator = indexRecord.length - 1;
	}

	// Method to get the record at a specific index using linear search
	public int find(String key) 
	{

		for (int i = 0; i < elements; i++) 
		{
			if (indexRecord[i].getKey().equalsIgnoreCase(key)) 
			{ // Use 'key' parameter
				return indexRecord[i].getWhere(); // Return the index of the record
			}
		}
		return -1; // ID not found

	}

	public boolean hasNext() 
	{
		if (iterator <= indexRecord.length - 1)
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}

	// Return true if iterator > 0; false otherwise:
	public boolean hasPrevious() 
	{
		// check if the iterator > 0:
		if (iterator > 0) 
		{
			return true;
		}
		return false;
	}

	// Return the 'where' field from IndexRecord; then increment the iterator:
	public int getNext() 
	{
		if (!hasNext()) 
		{
			return -1; // Return a special value indicating no more elements
		}

		// Loop to skip over null entries while ensuring iterator stays within bounds
		while (iterator < indexRecord.length && indexRecord[iterator] == null) 
		{
			iterator++; // Move to the next element if the current one is null
		}

		// Final bounds check to prevent ArrayIndexOutOfBoundsException
		if (iterator >= indexRecord.length) 
		{
			return -1; // Return special value instead of null
		}

		// If the current element is valid, proceed to get 'where' value
		int where = indexRecord[iterator].getWhere();
		iterator++;
		return where;
	}

	public int getPrevious()
	{
		// Ensure that there are valid elements to iterate over
		if (!hasPrevious()) 
		{
			throw new IndexOutOfBoundsException("No more elements.");
		}

		// Loop to skip over null entries
		while (iterator >= 0 && indexRecord[iterator] == null) 
		{
			iterator--; // Move to the previous element if the current one is null
		}

		// Check if the iterator has gone out of bounds after skipping nulls
		if (iterator < 0 || indexRecord[iterator] == null) 
		{
			throw new IndexOutOfBoundsException("No more valid elements.");
		}

		// Retrieve the 'where' field from the non-null IndexRecord
		int where = indexRecord[iterator].getWhere();
		iterator--; // Move the iterator to the previous element for the next call
		return where;
	}

	public void insert(IndexRecord record)
	{

		// Find the correct position to insert (to keep the array ordered)
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

	public int remove(String key) 
	{

		int index = find(key);
		// Find the index of the record to delete
		int indexToRemove = -1;

		// find the location and shifts the elements
		if (index != -1) 
		{
			indexToRemove = indexRecord[index].getWhere();
			for (int i = index; i < elements - 1; i++)
			{
				indexRecord[i] = indexRecord[i + 1];
			}
			elements--;
		}
		return indexToRemove;

	}

}
