package directaddressing;

import java.io.IOException;

/**
 * <b>Purpose:</b> User interface for Electronic Voting Machine. It has 3
 * methods namely add, find and checkCount which are implemented by
 * electionCount class.
 * 
 * @author amit.goel
 * @version 1.0
 * @since 27-04-2018
 */
public interface ElectronicVotingMachine {
	/**
	 * <b>Purpose:</b> Adds data from a file in an array using direct addressing.
	 * 
	 * @param fileName
	 *            Absolute path for file name as input
	 * @throws IOException
	 *             Throws IO Exception while handling file
	 */
	public void add(String fileName) throws IOException;

	/**
	 * <b>Purpose:</b> Accepts voter id as input and finds out candidate for which
	 * vote was casted.
	 * 
	 * @param voterId
	 *            Voter Id
	 */
	public void find(int voterId);

	/**
	 * <b>Purpose:</b> Accepts candidate id as input and count total number of votes
	 * received by given candidate.
	 * 
	 * @param candidateId
	 *            Candidate Id
	 */
	public void checkCount(int candidateId);
}
