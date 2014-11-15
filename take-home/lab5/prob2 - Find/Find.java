/**
 *	name	  : Yong Shan Xian
 *	matric no.: A0132763H
 */

import java.util.*;

class HashNode
{
	private String key;

	public HashNode(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}
}

class HashMap
{
	private final static int hashSize = 7109;
	private HashNode[] storage = new HashNode[hashSize];

	public void insert(String key)
	{
		int intKey = convert(key);
		int homeAddress = h1(intKey);
		if (storage[homeAddress] != null) {
			// woah, someone is already sitting in this place.
			// let's find a new address
			for (int i = 1; i < hashSize; ++i)
			{
				int probeAddress = (homeAddress + i * h2(intKey)) % hashSize;
				if (storage[probeAddress] == null)
				{
					storage[probeAddress] = new HashNode(key);
					break;
				}
			}
		} else {
			storage[homeAddress] = new HashNode(key);
		}
	}

	public boolean contains(String key)
	{
		int intKey = convert(key);
		int homeAddress = h1(intKey);
		for (int i = 0; i < hashSize; ++i) {
			int probeAddress = (homeAddress + i * h2(intKey)) % hashSize;
			if (storage[probeAddress] == null) {
				break;
			} else if (storage[probeAddress].getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	private static int h1(int key)
	{
		return key % hashSize;
	}

	private static int h2(int key)
	{
		return 4567 - (key % 4567);
	}

	private static int convert(String key)
	{
		int result = 0;

		for (int i = 0; i < key.length(); ++i) {
			result = result * (i + 1) + (int)key.charAt(i);
		}
		return result;
	}
}

class Result
{

    // declare the member field
	private String firstString;
	private String secondString;

	private HashMap firstSubstrings = new HashMap();
	private HashMap secondSubstrings = new HashMap();

    // declare the constructor
	public Result(String first, String second, int subStringLength)
	{
		firstString = first;
		secondString = second;
		generate(firstSubstrings, firstString, subStringLength);
		generate(secondSubstrings, secondString, subStringLength);
	}

	private void generate(HashMap hash, String dna, int length)
	{
		generate(hash, dna, length, 0);
	}

	/**
	 *	generate		: use this method to generate/pre-process the substrings of length K from string-idx,
	 *					  either first string or second string
	 *	Pre-condition	:
	 *	Post-condition	:
	 */
	private void generate(HashMap hash, String dna, int length, int idx) {
		if (idx + length <= dna.length()) {
			String substring = dna.substring(idx, idx + length);
			hash.insert(substring);
			generate(hash, dna, length, idx + 1);
		}
	}

	/**
	 *	find			: use this method to check whether a particular substring exists in string-idx,
	 *				  	  either first string, or second string
	 *	Pre-condition	:
	 *	Post-condition	:
	 */
	private boolean find(HashMap hash, String substring) {
		return hash.contains(substring);
	}

	public int check(String substring)
	{
		boolean firstCheck = find(firstSubstrings, substring);
		boolean secondCheck = find(secondSubstrings, substring);

		if (firstCheck && secondCheck) {
			return 3;
		} else if (secondCheck) {
			return 2;
		} else if (firstCheck) {
			return 1;
		}
		return 0;
	}
}

class Find {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int length = sc.nextInt();
		int sublength = sc.nextInt();

		String firstString = sc.next();
		String secondString = sc.next();

		Result result = new Result(firstString, secondString, sublength);
		int cases = sc.nextInt();
		for (int i = 0; i < cases; ++i) {
			System.out.println(result.check(sc.next()));
		}
	}
}
