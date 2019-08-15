package directaddressing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * <b>Purpose:</b> Implements array as dictionary ADT using direct addressing.
 * It stores data in an array using bucket/bin sort and implement various
 * methods to perform various operations.
 * 
 * @author amit.goel
 * @version 1.0
 * @since 27-04-2018
 */
public class electionCount implements ElectronicVotingMachine {
	private int[] directAddressing;
	private int minValue;
	private int rangeOfValues;

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
	 * <b>Purpose:</b> Accepts integer array as input and finds out maximum value in
	 * the array and return it.
	 * 
	 * @param numbers
	 *            Array of integers
	 * @return Integer - maximum value in the input integer array
	 */
	public static int getMaxValue(int[] numbers) {
		int maxValue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > maxValue) {
				maxValue = numbers[i];
			}
		}
		return maxValue;
	}

	/**
	 * <b>Purpose:</b> Accepts integer array as input and finds out minimum value in
	 * the array and return it.
	 * 
	 * @param numbers
	 *            Array of integers
	 * @return Integer - minimum value in the input integer array
	 */
	public static int getMinValue(int[] numbers) {
		int minValue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < minValue) {
				minValue = numbers[i];
			}
		}
		return minValue;
	}

	/**
	 * <b>Purpose:</b> Adds data from a file in an array using direct addressing.
	 * <p>
	 * It performs following:
	 * <p>
	 * 1. Accepts a file name as input and count number of lines in it.
	 * <p>
	 * 2. Based on number of lines, initializes arrays to hold data from the file.
	 * <p>
	 * 3. Parses the file and stores data in arrays and finds out minimum and
	 * maximum keys to know the range.
	 * <p>
	 * 4. Once range is known, initialize array for bucket/bin sorting and assign
	 * values in buckets from the arrays holding data using direct addressing
	 * approach.
	 * 
	 * @param fileName
	 *            Accepts absolute path for file name as input
	 * @throws IOException
	 *             Throws IO Exception while handling file
	 */
	public void add(String fileName) throws IOException {
		int maxValue;
		int[] keyVoterId;
		int[] valueCandidateId;
		int cntLines = 0;
		cntLines = countLines(fileName);
		if (cntLines == -1) {
			System.out.println("Exception raised, aborting.");
			System.exit(0);
		} else if (cntLines > 0) {
			keyVoterId = new int[cntLines];
			valueCandidateId = new int[cntLines];
			try (Scanner sc = new Scanner(new File(fileName))) {
				int i = 0;
				while (sc.hasNextLine()) {
					String[] str = sc.nextLine().split("\\s+");
					keyVoterId[i] = Integer.parseInt(str[0]);
					valueCandidateId[i] = Integer.parseInt(str[1]);
					i++;
				}
			} catch (IOException e) {
				System.out.println(e);
				System.out.println("Exception raised, aborting.");
				System.exit(0);
			}
			minValue = getMinValue(keyVoterId);
			maxValue = getMaxValue(keyVoterId);
			rangeOfValues = maxValue - minValue + 1;
			directAddressing = new int[rangeOfValues];
			for (int j = 0; j < rangeOfValues; j++) {
				directAddressing[keyVoterId[j] - minValue] = valueCandidateId[j];
			}
			System.out.println("Data has been added from file: \"" + fileName + "\"");
		} else {
			System.out.println("File is empty.");
		}
	}

	/**
	 * <b>Purpose:</b> Accepts voter id as input and finds out candidate for which
	 * vote was casted.
	 * <p>
	 * It finds out candidate id using (given voter id - minimum voter id) as index
	 * location in the array. If location is present in array, displays candidate id
	 * for which vote was casted.
	 * 
	 * @param voterId
	 *            Voter Id
	 */
	public void find(int voterId) {
		if (directAddressing != null) {
			int index = voterId - minValue;
			if (index >= 0 && index < rangeOfValues) {
				int candidateId = directAddressing[index];
				System.out.println("Voter Id: " + voterId + ", casted vote for Candidate Id => " + candidateId);
			} else {
				System.out.println("No information available for given Voter Id: " + voterId);
			}
		} else {
			System.out.println("Data from file has not been added yet.");
		}
	}

	/**
	 * <b>Purpose:</b> Accepts candidate id as input and count total number of votes
	 * received by given candidate.
	 * <p>
	 * It searches for given candidate id in the array. Increments counter upon
	 * finding the matching value. Once iterated through whole array and search is
	 * finished, displays total count.
	 * 
	 * @param candidateId
	 *            Candidate Id
	 */
	public void checkCount(int candidateId) {
		if (directAddressing != null) {
			int cnt = 0;
			for (int i = 0; i < rangeOfValues; i++) {
				if (directAddressing[i] == candidateId) {
					cnt++;
				}
			}
			if (cnt > 0) {
				System.out.println("Total number of Vote(s) received by Candidate Id: " + candidateId + " => " + cnt);
			} else {
				System.out.println("No information available for given Candidate Id: " + candidateId);
			}
		} else {
			System.out.println("Data from file has not been added yet.");
		}
	}
}
