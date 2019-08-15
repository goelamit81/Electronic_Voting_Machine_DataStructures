package hashing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <b>Purpose:</b> Implements hash table as dictionary ADT. It stores data in a
 * hash table and implement various methods to perform various operations.
 * 
 * @author amit.goel
 * @version 1.0
 * @since 27-04-2018
 */
public class electionCount implements ElectronicVotingMachine {
	private Map<Integer, Integer> hashing;

	/**
	 * <b>Purpose:</b> Accepts file name as input and count number of lines in the
	 * file and return it.
	 * 
	 * @param filename
	 *            Absolute path for file name as input
	 * @return Integer - Number of lines in the file
	 * @throws IOException
	 *             Throws IO Exception while handling file
	 */
	public static int countLines(String filename) throws IOException {
		try (LineNumberReader reader = new LineNumberReader(new FileReader(filename))) {
			while (reader.readLine() != null) {
				;
			}
			return reader.getLineNumber();
		} catch (IOException e) {
			System.out.println(e);
			return -1;
		}
	}

	/**
	 * <b>Purpose:</b> Adds data from a file in a hash table.
	 * <p>
	 * It performs following:
	 * <p>
	 * 1. Accepts a file name as input and and count number of lines in it.
	 * <p>
	 * 2. Based on number of lines, initializes hash table to hold data from the
	 * file.
	 * <p>
	 * 3. Parses the file and populates hash table with the key/value
	 * (voterId/candidateId) pairs.
	 * 
	 * @param fileName
	 *            Accepts absolute path for file name as input
	 * @throws IOException
	 *             Throws IO Exception while handling file
	 */
	public void add(String fileName) throws IOException {
		int size = 0;
		int cntLines = 0;
		cntLines = countLines(fileName);
		if (cntLines == -1) {
			System.out.println("Exception raised, aborting.");
			System.exit(0);
		}
		size = cntLines * 2;
		hashing = new HashMap<Integer, Integer>(size);
		if (cntLines > 0) {
			try (Scanner sc = new Scanner(new File(fileName))) {
				while (sc.hasNextLine()) {
					String[] str = sc.nextLine().split("\\s+");
					hashing.put(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
				}
			} catch (IOException e) {
				System.out.println(e);
				System.out.println("Exception raised, aborting.");
				System.exit(0);
			}
		}
		if (hashing.size() > 0) {
			System.out.println("Data has been added from file: \"" + fileName + "\"");
		} else {
			System.out.println("File is empty.");
		}
	}

	/**
	 * <b>Purpose:</b> Accepts voter id as input and finds out candidate for which
	 * vote was casted.
	 * <p>
	 * It checks for voter id if present in hash table. If found, retrieves
	 * candidate id for which vote was casted and displays it.
	 * 
	 * @param voterId
	 *            Voter Id
	 */
	public void find(int voterId) {
		try {
			if (!hashing.isEmpty()) {
				if (hashing.containsKey(voterId)) {
					System.out.println(
							"Voter Id: " + voterId + ", casted vote for Candidate Id => " + hashing.get(voterId));
				} else {
					System.out.println("No information available for given Voter Id: " + voterId);
				}
			} else {
				System.out.println("File was empty so no data was added. First add data from a suitable file.");
			}
		} catch (NullPointerException e) {
			System.out.println("Data from file has not been added yet.");
		} catch (Exception e) {
			System.out.println("Exception raised: " + e);
		}
	}

	/**
	 * <b>Purpose:</b> Accepts candidate id as input and count total number of votes
	 * received by given candidate.
	 * <p>
	 * It searches for given candidate id in hash table, increments counter upon
	 * finding the matching value. Once iterated through all values and search is
	 * finished, displays total count.
	 * 
	 * @param candidateId
	 *            Candidate Id
	 */
	public void checkCount(int candidateId) {
		try {
			if (!hashing.isEmpty()) {
				int cnt = 0;
				Collection<Integer> val = hashing.values();
				for (int v : val) {
					if (v == candidateId) {
						cnt++;
					}
				}
				if (cnt > 0) {
					System.out
							.println("Total number of Vote(s) received by Candidate Id: " + candidateId + " => " + cnt);
				} else {
					System.out.println("No information available for given Candidate Id: " + candidateId);
				}
			} else {
				System.out.println("File was empty so no data was added. First add data from a suitable file.");
			}
		} catch (NullPointerException e) {
			System.out.println("Data from file has not been added yet.");
		} catch (Exception e) {
			System.out.println("Exception raised: " + e);
		}
	}
}
