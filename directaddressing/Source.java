package directaddressing;

import java.io.IOException;
import java.util.Scanner;

/**
 * <b>Purpose:</b> Displays a menu to the user and then accepts option chosen by
 * user and perform actions accordingly. It displays 4 options to the user.
 * <p>
 * <b>Option 1 - ADD -</b> It will accept file name as input and will call add
 * method in electionCount class to add data from the file.
 * <p>
 * <b>Option 2 - FIND -</b> It will accept voter id as input and will call find
 * method in electionCount class to get the candidate for which vote was casted.
 * <p>
 * <b>Option 3 - COUNT -</b> It will accept candidate id as input and will call
 * checkCount method in electionCount class to count number of votes received by
 * the candidate.
 * <p>
 * <b>Option 4 - EXIT -</b> It will exit from menu and stop the program.
 * 
 * @author amit.goel
 * @version 1.0
 * @since 27-04-2018
 */
public class Source {
	/**
	 * <b>Starting point in the program</b>
	 * 
	 * @param args
	 *            It is not used
	 * @throws IOException
	 *             Throws IO Exception while taking input from console.
	 */
	public static void main(String args[]) throws IOException {
		ElectronicVotingMachine evm = new electionCount();
		System.out.println("Choose one of the following option numbers:");
		System.out.println("1. ADD - Add data from a file");
		System.out.println("2. FIND - Find out candidate id for a given voter id");
		System.out.println("3. COUNT - Count total number of votes received by a given candidate id");
		System.out.println("0. EXIT");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		while (n != 0) {
			switch (n) {
			case 1: {
				System.out.println("Enter absolute path for data file (For example : D:/data.txt):");
				scanner = new Scanner(System.in);
				String fileName = scanner.nextLine().replaceAll("(^\\s+)|(\\s+$)", "");
				evm.add(fileName);
				break;
			}
			case 2: {
				System.out.println("Enter Voter Id:");
				scanner = new Scanner(System.in);
				int voterId = scanner.nextInt();
				evm.find(voterId);
				break;
			}
			case 3: {
				System.out.println("Enter Candidate Id:");
				scanner = new Scanner(System.in);
				int candidateId = scanner.nextInt();
				evm.checkCount(candidateId);
				break;
			}
			default: {
				System.out.println("Wrong number entered.");
				break;
			}
			}
			System.out.println("Please enter suitable option number to proceed:");
			n = scanner.nextInt();
		}
		if (n == 0) {
			System.out.println("Thank you!!!");
		}
		scanner.close();
	}
}
