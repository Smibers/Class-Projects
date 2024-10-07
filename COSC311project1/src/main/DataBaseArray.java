//Smith   Benjamin

//COSC-311 fall2024
//Project 1


package main;

import java.util.Arrays;

public class DataBaseArray 
{
	public int elems = 0;
	private DataBaseRecord[] records;

	// Constructor to initialize the array with a specified capacity
	public DataBaseArray(int capacity) 
	{
		records = new DataBaseRecord[capacity];
	}

	public void insert(DataBaseRecord record) 
	{
		records[elems++] = record;
	}

	// Method to get the record at a specific index
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

	public void remove(String id) 
	{
		int indexToRemove = -1;

		for (int i = 0; i < elems; i++) 
		{
			if (records[i].getID().equalsIgnoreCase(id)) 
			{
				indexToRemove = i;
				break; // Exit loop once found
			}
		}

		if (indexToRemove == -1) 
		{
			System.out.println("ID not found. Cannot delete.");
			return; // Early exit if not found
		}

		// Shifting logic
		for (int i = indexToRemove; i < elems - 1; i++) 
		{
			records[i] = records[i + 1]; // Shift left
		}
		records[--elems] = null; // Clear the last element
	}
}
