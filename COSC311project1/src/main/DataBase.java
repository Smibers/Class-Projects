package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataBase {
	int sz = 100;
	private Scanner file;
	int move = 0;
	private DataBaseArray myDB;
	private IndexArray ID, First, Last;

	public DataBase() {

		myDB = new DataBaseArray(sz);
		ID = new IndexArray(sz);
		First = new IndexArray(sz);
		Last = new IndexArray(sz);

		// actual file Path if needed = "/COSC311project1/src/main/data.txt";
		try {
			file = new Scanner(new FileInputStream("data.txt"));
			while (file.hasNextLine()) {
				String last = file.next();
				String first = file.next();
				String id = file.next();
				loadData(id, last, first);
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not found: " + e.getMessage(), e);
		} finally {

			file.close();
		}
	}

	public DataBaseRecord[] output() {

		return myDB.getRecords();
	}

	private void addRecord(String id, String firstName, String lastName) {

		// Check if the ID already exists before creating a new record
		if (ID.find(id) != -1) {
			System.out.println("ID already in use.");
			return;
		}

		// Create the new record
		DataBaseRecord newRecord = new DataBaseRecord(id, lastName, firstName);
		myDB.insert(newRecord);

		int index = myDB.elems - 1; // This is safe since we just added the record

		// Insert index records for ID, First Name, and Last Name
		ID.insert(new IndexRecord(id, index));
		First.insert(new IndexRecord(firstName, index));
		Last.insert(new IndexRecord(lastName, index));
	}

	// Method to load data from the file
	public void loadData(String id, String last, String first) {
		myDB.insert(new DataBaseRecord(id, last, first));
		int index = myDB.elems - 1;
		ID.insert(new IndexRecord(id, index));
		Last.insert(new IndexRecord(last, index));
		First.insert(new IndexRecord(first, index));
	}

	public int currentIndex() {
		int index;
		index = move++;
		return index;
	}

	public void addIt() {
		// add a student object
		// Note: if the user enters an ID already in use, issue a warning and return to
		// the menu
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter ID: ");
		String id = sc.nextLine();
		if (ID.find(id) != -1) {
			System.out.println("ID already in use");
		} else if (!id.matches("\\d+")) {
			System.out.println("ID must contain only numeric characters.");
			return;
		} else {

			System.out.print("Enter First Name: ");
			String first = sc.nextLine();
			System.out.print("Enter Last Name: ");
			String last = sc.nextLine();
			if (!Character.isUpperCase(first.charAt(0)) || !Character.isUpperCase(last.charAt(0))) {
				System.out.println("First, and Last names must start with an uppercase letter.");
				return; // Return if any of the fields do not start with an uppercase letter
			}
			if (first.isEmpty() || id.isEmpty() || last.isEmpty()) {
				System.out.println("ID and First and Last names cannot be empty.");
				return; // Return if names and id are empty
			}
			addRecord(id, first, last);
			System.out.println("Added Record ID: " + id);
		}
	}

	public void deleteIt() {
		// delete a student object
		// Note: output either "Deleted" or "ID not Found" and return to menu
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		String id = sc.nextLine();

		// find the index of the record to delete
		int indexToDelete = ID.find(id); // This would now return -1 if the ID was not found
		if (indexToDelete == -1) {
			System.out.println("ID not found. Cannot delete.");
			return;
		}

		// Delete the record from myDB
		myDB.remove(id); // Remove from DataBaseArray
		ID.remove(id); // Remove from ID index
		First.remove(id); // Remove from First Name index
		Last.remove(id); // Remove from Last Name index
		System.out.println("Deleted ID: " + id);

	}

	public void findIt() {
		// Find a student by ID");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter ID to find: ");
		String id = sc.nextLine();

		int index = ID.find(id);
		if (index != -1) {
			// Now safe to retrieve the record
			DataBaseRecord record = myDB.retrieve(index);

			// Check if the record is null ( deleted or not available)
			if (record != null) {
				System.out.println(record); // Print the valid record
			} else {
				System.out.println("Record not found."); // Handle the case where the record is null
			}
		} else {
			System.out.println("ID not found."); // Handle the case where the ID is not found
		}
	}

	public void ListByIDAscending() {
		// List students by ID increasing
		ID.iteratorInitFront();
		while (ID.hasNext()) {
			Integer temp = ID.getNext();
			if (temp != null) {
				System.out.println(myDB.retrieve(temp));
			}
		}
	}

	public void ListByFirstAscending() {
		// List students by first name increasing
		First.iteratorInitFront();
		while (First.hasNext()) {
			int temp = First.getNext();
			System.out.println(myDB.retrieve(temp));
		}
	}

	public void ListByLastAscending() {
		// List students by last name increasing
		Last.iteratorInitFront();
		while (Last.hasNext()) {
			int temp = Last.getNext();
			System.out.println(myDB.retrieve(temp));
		}
	}

	public void ListByIDDescending() {
		// List students by ID decreasing
		ID.iteratorInitBack();
		while (ID.hasPrevious()) {
			int temp = ID.getPrevious();
			System.out.println(myDB.retrieve(temp));
		}
	}

	public void ListByFirstDescending() {
		// List students by first name decreasing
		First.iteratorInitBack();
		while (First.hasPrevious()) {
			int temp = First.getPrevious();
			System.out.println(myDB.retrieve(temp));
		}

	}

	public void ListByLastDescending() {
		Last.iteratorInitBack(); // Initialize iterator from the back
		while (Last.hasPrevious()) {
			int temp = Last.getPrevious();
			// Retrieve and print only non-null records
			DataBaseRecord record = myDB.retrieve(temp);
			if (record != null) {
				System.out.println(record);
			}
		}
	}

}
