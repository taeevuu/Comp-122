//
// Enter your name: 
// Enter your student ID: 
//
/*
 * TIME COMPLEXITY JUSTIFICATION:
 * * Task 2.1 (notExists): The time complexity is O(size1 * size2). 
 * This is because the algorithm uses nested loops: the outer loop iterates through 
 * every element of array1 (size1), and for each of those elements, the inner loop 
 * may traverse all elements of array2 (size2) to check for existence.
 * * Task 2.2 (count): The time complexity is O(size1 * size2). 
 * This also uses nested loops: the outer loop iterates through the database (size2), 
 * and for each entry, the inner loop iterates through the entire requests array (size1) 
 * to count occurrences.
 */
import java.util.*;
import java.io.*;

class COMP108W06 {

	// print the content of an array of size n [cite: 14]
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// Input: array1[] (requests) with size1 entries and array2[] (database) with size2 entries [cite: 14, 16]
	// print all entries of array1[] that does not exist in array2[] [cite: 14, 16]
	static void notExists(int array1[], int size1, int array2[], int size2) {
		// Task 2.1: Outer loop iterates through requests 
		for (int i = 0; i < size1; i++) {
			boolean found = false;
			// Inner loop iterates through database to see if request exists 
			for (int j = 0; j < size2; j++) {
				if (array1[i] == array2[j]) {
					found = true;
					break; // Exit inner loop early if found
				}
			}
			// If request was never found in the database, print it 
			if (!found) {
				System.out.print(array1[i] + " ");
			}
		}
		System.out.println();
	}
		
	// Input: array1[] (requests) with size1 entries and array2[] (database) with size2 entries [cite: 14, 16]
	// for each entry in array2[], count how many times it appears in array1[] [cite: 14, 16]
	static void count(int array1[], int size1, int array2[], int size2) {
		// Task 2.2: Outer loop iterates through the database 
		for (int i = 0; i < size2; i++) {
			int occurrenceCount = 0;
			// Inner loop iterates through requests to find matches 
			for (int j = 0; j < size1; j++) {
				if (array2[i] == array1[j]) {
					occurrenceCount++;
				}
			}
			// Report the count for the current database entry 
			System.out.print(occurrenceCount + " ");
		}
		System.out.println();
	}

}