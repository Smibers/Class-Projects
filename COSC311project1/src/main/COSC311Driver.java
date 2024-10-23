//Smith   Benjamin
//COSC-311 fall2024
//Project 1


package main;

import java.util.*;

public class COSC311Driver {

	public static void main(String[] args) {
		/*
		 * The following declaration declares a data structure that will change from one
		 * assignment to the next. For example, you will need to implement the following
		 * as a doubly linked list, as well as a tree.
		 */

		DataBase d = new DataBase();
		int response;
		Scanner keyboard = new Scanner(System.in);

		/*
		 * Read the data into the database from the external disk file here IMPORTANT:
		 * duplicate ID numbers should not be added. Disregard the entire record for
		 * duplicate IDs
		 */

		do {
			
			System.out.println(" 1 Add a new student");
			System.out.println(" 2 Delete a student");
			System.out.println(" 3 Find a student by ID");
			System.out.println(" 4 List students by ID increasing");
			System.out.println(" 5 List students by first name increasing");
			System.out.println(" 6 List students by last name increasing");
			System.out.println(" 7 List students by ID decreasing");
			System.out.println(" 8 List students by first name decreasing");
			System.out.println(" 9 List students by last name decreasing");
			System.out.println(" ");
			System.out.println(" 0 End");

			response = keyboard.nextInt();

			switch (response) {
			case 1:
				d.addIt(); // Note: if the user enters an ID already in use, issue a warning and return to
							// the menu
				break;
			case 2:
				d.deleteIt(); // Note: output either "Deleted" or "ID not Found" and return to menu
				break;
			case 3:
				d.findIt(); // Note: output the entire record or the message "ID not Found" and return to
							// menu
				break;
			case 4:
				d.ListByIDAscending();
				break;
			case 5:
				d.ListByFirstAscending();
				break;
			case 6:
				d.ListByLastAscending();
				break;
			case 7:
				d.ListByIDDescending();
				break;
			case 8:
				d.ListByFirstDescending();
				break;
			case 9:
				d.ListByLastDescending();
				break;
			default:
			}

		} while (response != 0);
	}
}
