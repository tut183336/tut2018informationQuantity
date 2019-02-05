package s4.B183311; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 

import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
*/

public class Frequencer implements FrequencerInterface {
	// Code to Test, *warning: This code contains intentional problem*
	byte[] myTarget;
	byte[] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;

	int[] suffixArray;

	private void printSuffixArray() {
		if (spaceReady) {
			for (int i = 0; i < mySpace.length; i++) {
				int s = suffixArray[i];
				for (int j = s; j < mySpace.length; j++) {
					System.out.write(mySpace[j]);
				}
				System.out.println();
			}
		}
	}

	private int suffixCompare(int i, int j) {
		if (mySpace[i] > mySpace[j]) {
			return 1;
		} else if (mySpace[i] < mySpace[j]) {
			return -1;
		} else {
			// 文字列終端まで見た場合
			if (i + 1 >= mySpace.length && j + 1 >= mySpace.length) {
				return 0;
			} else if (i + 1 >= mySpace.length) {
				return -1;
			} else if (j + 1 >= mySpace.length) {
				return 1;
			}
			return suffixCompare(i + 1, j + 1); // This line should be modified.

			// It is not implemented yet,
			// It should be used to create suffix array.
			// Example of dictionary order
			// "i" < "o" : compare by code
			// "Hi" < "Ho" ; if head is same, compare the next element
			// "Ho" < "Ho " ; if the prefix is identical, longer string is big
			//
			// **** Please write code here... ***
			//
		}
	}

	public void setSpace(byte[] space) {
		mySpace = space;
		if (mySpace.length > 0)
			spaceReady = true;
		suffixArray = new int[space.length];
		byte[] foo = new byte[space.length];
		// put all suffixes in suffixArray.Each suffix is expressed by one integer.
		for (int i = 0; i < mySpace.length; i++) {
			suffixArray[i] = i;
		}

		// Sorting
		for (int i = 0; i < mySpace.length - 1; i++) {// 範囲を狭める
			for (int j = 0; j < space.length - i - 1; j++) {
				int r = suffixCompare(suffixArray[j], suffixArray[j + 1]);
				// suffix compare
				if (r == 1) { // suffix_j > suffix_j+1 の場合
					int tmp = suffixArray[j];
					suffixArray[j] = suffixArray[j + 1];
					suffixArray[j + 1] = tmp;
				}
			}
		}
	}

	private int targetCompare(int i, int j, int end) {
		// comparing suffix_i and target_j_end by dictonary order with limitation of
		// length;
		// if the beginning of suffix_i matches target_i_end, and suffix is longer than
		// target it returns 0;
		// suffix_i --> mySpace[i], mySpace[i+1], .... ,
		// mySpace[mySpace.length-1],mySpace[mySpace.length -1]
		// target_j_end -> myTarget[j], myTarget[j+1], .... ,
		// myTarget[end-2],myTarget[end-1]
		// if suffix_i > target_j_end it return 1;
		// if suffix_i < target_j_end it return -1
		// It is not implemented yet.
		// It should be used to search the apropriate index of some suffix.
		// Example of search
		// suffix target
		// "o" > "i"
		// "o" < "z"
		// "o" = "o"
		// "o" < "oo"
		// "Ho" > "Hi"
		// "Ho" < "Hz"
		// "Ho" = "Ho"
		// "Ho" < "Ho " : "Ho " is not in the head of suffix "Ho"
		// "Ho" = "H" : "H" is in the head of suffix "Ho"
		//
		// **** Please write code here... ***
		for (int k = suffixArray[i]; k < mySpace.length; k++) {
			if (mySpace[k] > myTarget[j]) {
				return 1;
			} else if (mySpace[k] < myTarget[j]) {
				return -1;
			}
			if (++j >= end) {
				return 0;
			}
		}
		return -1;
	}

	private int subByteStartIndex(int start, int end) {
		// It returns the index of the first suffix which is equal or greater than
		// subBytes;
		// not implemented yet;
		// If myTaget is "Hi Ho", start=0, end=2 means "Hi".
		// For "Ho", it will return 5 for "Hi Ho Hi Ho".
		// 5 means suffix_5,
		// Please note suffix_5 is "Ho" and "Ho" starts from here.
		// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
		// 6 means suffix_6,
		// Please note suffix_6 is "Ho Hi Ho", and "Ho " starts from here.
		//
		// **** Please write code here... ***
		for (int i = 0; i < suffixArray.length; i++) {
			int result = targetCompare(i, start, end);
			if (result == 0) {
				return i;
			}
		}
		return suffixArray.length; // This line should be modified.
	}

	private int subByteEndIndex(int start, int end) {
		// It returns the next index of the first suffix which is greater than subBytes;
		// not implemented yet
		// If myTaget is "Hi Ho", start=0, end=2 means "Hi".
		// For "Ho", it will return 7 for "Hi Ho Hi Ho".
		// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
		// 7 means suffix_7,
		// Please note suffix_7 is "i Ho Hi", which does not start with "Ho" nor "Ho ".
		// Whereas suffix_5 is "Ho Hi Ho", which starts "Ho" and "Ho ".
		//
		// **** Please write code here... ***
		for (int i = subByteStartIndex(start, end); i < suffixArray.length; i++) {
			int result = targetCompare(i, start, end);
			if (result != 0) {
				return i;
			}
		}
		return suffixArray.length; // This line should be modified.
	}

	public int subByteFrequency(int start, int end) {
		/*
		 * This method be work as follows, but int spaceLength = mySpace.length; int
		 * count = 0; for(int offset = 0; offset< spaceLength - (end - start); offset++)
		 * { boolean abort = false; for(int i = 0; i< (end - start); i++) {
		 * if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; } }
		 * if(abort == false) { count++; } }
		 */
		int first = subByteStartIndex(start, end);
		int last1 = subByteEndIndex(start, end);
		return last1 - first;
	}

	public void setTarget(byte[] target) {
		myTarget = target;
		if (myTarget.length > 0)
			targetReady = true;
	}

	public int frequency() {
		if (targetReady == false)
			return -1;
		if (spaceReady == false)
			return 0;
		return subByteFrequency(0, myTarget.length);
	}

	public static void main(String[] args) {
		Frequencer myObject;
		int freq;
		try {
			System.out.println("checking my Frequencer");
			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.printSuffixArray(); // you may use this line for DEBUG
			/*
			 * Example from "Hi Ho Hi Ho" 0: Hi Ho 1: Ho 2: Ho Hi Ho 3:Hi Ho 4:Hi Ho Hi Ho
			 * 5:Ho 6:Ho Hi Ho 7:i Ho 8:i Ho Hi Ho 9:o A:o Hi Ho
			 */
			myObject.setTarget("H".getBytes());

			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
			if (4 == freq) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
	}
}
