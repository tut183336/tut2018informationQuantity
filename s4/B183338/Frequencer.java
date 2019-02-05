package s4.B183338;
import java.lang.*;
import s4.specification.*;


/*package s4.specification;
public interface FrequencerInterface {	   // This interface provides the design for frequency counter.
	void setTarget(byte	 target[]); // set the data to search.
	void setSpace(byte	space[]);  // set the data to be searched target from.
	int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
					//Otherwise, it return 0, when SPACE is not set or SPACE's length is zero
					//Otherwise, get the frequency of TAGET in SPACE
	int subByteFrequency(int start, int end);
	// get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
	// For the incorrect value of START or END, the behavior is undefined.
}
*/



public class Frequencer implements FrequencerInterface{
	// Code to start with: This code is not working, but good start point to work.
	byte [] myTarget;
	byte [] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;

	int []	suffixArray;

	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a integer, which is the starting position in mySpace.
	// The following is the code to print the variable
	private void printSuffixArray() {
	if(spaceReady) {
		for(int i=0; i< mySpace.length; i++) {
		int s = suffixArray[i];
		for(int j=s;j<mySpace.length;j++) {
			System.out.write(mySpace[j]);
		}
		System.out.write('\n');
		}
	}
	}

	private int suffixCompare(int i, int j) {
	// comparing two suffixes by dictionary order.
	// i and j denoetes suffix_i, and suffix_j
	// if suffix_i > suffix_j, it returns 1
	// if suffix_i < suffix_j, it returns -1
	// if suffix_i = suffix_j, it returns 0;
	// It is not implemented yet, 
	// It should be used to create suffix array.
	// Example of dictionary order
	// "i"		<  "o"		  : compare by code
	// "Hi"		<  "Ho"		  ; if head is same, compare the next element
	// "Ho"		<  "Ho "	  ; if the prefix is identical, longer string is big
	//
	// ****	 Please write code here... ***
	//
	int i2 = i;
	int j2 = j;
	for(int k = 0; k < mySpace.length-Math.max(i2, j2); k++){
	if(mySpace[i] > mySpace[j]){
		return 1;
	}
	else if(mySpace[i] < mySpace[j]){
		return -1;
	}
	else if(mySpace[i] == mySpace[j]){
		i++;
		j++;
		continue;
	}
	else{
	}		
	//return 1; // This line should be modified.
	}
	if(i > j){
		return -1;
	}
	else if(i < j){
		return 1;
	}
	else if(i == j){
		return 0;
	}
	else{
	}
	return 0;
	}

	public void setSpace(byte []space) { 
	mySpace = space; if(mySpace.length>0) spaceReady = true; 
	suffixArray = new int[space.length];
	// put all suffixes	 in suffixArray. Each suffix is expressed by one integer.
	for(int i = 0; i< space.length; i++) {
		suffixArray[i] = i;
	}
	// Sorting is not implmented yet.
	//
	//
	// ****	 Please write code here... ***
	//
	for (int i = 0; i < suffixArray.length - 1; i++) {
	for (int j = suffixArray.length - 1; j > i; j--) {
		int tmpNum = suffixArray[j-1];
		int sufComp = suffixCompare(suffixArray[j-1], suffixArray[j]);
		if(sufComp == 1){
			tmpNum = suffixArray[j-1];
			suffixArray[j-1] = suffixArray[j];
			suffixArray[j] = tmpNum;
		}
	}
	}
	}

	private int targetCompare(int i, int j, int end) {
	// comparing suffix_i and target_j_end by dictonary order with limitation of length;
	// if the beginning of suffix_i matches target_j, and suffix is longer than target	it returns 0;
	// if suffix_i > target_j_end it return 1;
	// if suffix_i < target_j_end it return -1
	// It is not implemented yet.
	// It should be used to search the apropriate index of some suffix.
	// Example of search
	// suffix		   target
	// "o"		 >	   "i"
	// "o"		 <	   "z"
	// "o"		 =	   "o"
	// "o"		 <	   "oo"
	// "Ho"		 >	   "Hi"
	// "Ho"		 <	   "Hz"
	// "Ho"		 =	   "Ho"
	// "Ho"		 <	   "Ho "   : "Ho " is not in the head of suffix "Ho"
	// "Ho"		 =	   "H"	   : "H" is in the head of suffix "Ho"
	//
	// ****	 Please write code here... ***
	//
	if(mySpace[i] > myTarget[j]){
		return 1;
	}else if(mySpace[i] < myTarget[j]){
		return -1;
	}else if(mySpace[i] == myTarget[j]){
		if(mySpace.length-i < end-j){
			return 1;
		}
		for(j++, i++; j < end; j++, i++){
			if(mySpace[i] > myTarget[j]){
				return 1;
			}else if(mySpace[i] < myTarget[j]){
				return -1;
			}
		}
	}
	return 0;
	}

	private int subByteStartIndex(int start, int end) {
	// It returns the index of the first suffix which is equal or greater than subBytes;
	// not implemented yet;
	// For "Ho", it will return 5  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
	//
	// ****	 Please write code here... ***
	//
	for(int i = 0; i < suffixArray.length ; i++){
		if(targetCompare(suffixArray[i], start, end) == 0){
			return i;
		}
	}
	return suffixArray.length; // This line should be modified.
	}

	private int subByteEndIndex(int start, int end) {
	// It returns the next index of the first suffix which is greater than subBytes;
	// not implemented yet
	// For "Ho", it will return 7  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
	//
	// ****	 Please write code here... ***
	//
	for(int i = 0; i < suffixArray.length; i++){
		if(targetCompare(suffixArray[i], start, end) == 0){
			for(i++; i < suffixArray.length; i++){
				if(targetCompare(suffixArray[i], start, end) == 1){
					return i;
				}
			}

		}
	}
	return suffixArray.length; // This line should be modified.
	}

	public int subByteFrequency(int start, int end) {
	/* This method be work as follows, but
	int spaceLength = mySpace.length;
	int count = 0;
	for(int offset = 0; offset< spaceLength - (end - start); offset++) {
		boolean abort = false;
		for(int i = 0; i< (end - start); i++) {
		if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
		}
		if(abort == false) { count++; }
	}
	*/
	int first = subByteStartIndex(start, end);
	int last1 = subByteEndIndex(start, end);
	return last1 - first;
	}

	public void setTarget(byte [] target) { 
	myTarget = target; if(myTarget.length>0) targetReady = true; 
	}

	public int frequency() {
	if(targetReady == false) return -1;
	if(spaceReady == false) return 0;
	return subByteFrequency(0, myTarget.length);
	}

	private void printBar() {
	System.out.println("===============================================");
	}

	public static void main(String[] args) {
	Frequencer frequencerObject;
	try {
		frequencerObject = new Frequencer();
		frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
		//frequencerObject.printSuffixArray(); // you may use this line for DEBUG
		/* Example from "Hi Ho Hi Ho"
		   0: Hi Ho
		   1: Ho
		   2: Ho Hi Ho
		   3:Hi Ho
		   4:Hi Ho Hi Ho
		   5:Ho
		   6:Ho Hi Ho
		   7:i Ho
		   8:i Ho Hi Ho
		   9:o
		   A:o Hi Ho
		*/

		frequencerObject.setTarget("H".getBytes());
		//
		// ****	 Please write code to check subByteStartIndex, and subByteEndIndex
		//

		//  TestCase 1
		Frequencer testObj = new Frequencer();
		testObj.setSpace("Hi Ho Hi Ho".getBytes());
		testObj.setTarget("Ho".getBytes());

		System.out.println("TestCase 1: ");
		System.out.println("Space  : \"Hi Ho Hi Ho\"");
		System.out.println("Target : \"Ho\"");

		System.out.println("\n-- printSuffixArray()");
		testObj.printSuffixArray();
		System.out.println("\n-- targetCompare()");
		for(int i=0; i<testObj.suffixArray.length; i++){
			System.out.println("\ttargetCompare[suffix[" + i + "]] : " + testObj.targetCompare(testObj.suffixArray[i], 0, testObj.myTarget.length));
		}
		System.out.println("\n-- subByteStartIndex()");
		System.out.println("\tsubByteStartIndex : "+testObj.subByteStartIndex(0, testObj.myTarget.length));
		System.out.println("\n-- subByteEndIndex()");
		System.out.println("\tsubByteEndIndex : "+testObj.subByteEndIndex(0, testObj.myTarget.length));
		testObj.printBar();
		
		//  TestCase 2
		testObj.setSpace("Hi Ho".getBytes());
		testObj.setTarget("Hooooo".getBytes());
		System.out.println("TestCase 2: ");
		System.out.println("Space  : \"Hi Ho\"");
		System.out.println("Target : \"Hooooo\"");

		System.out.println("\n-- printSuffixArray()");
		testObj.printSuffixArray();
		System.out.println("\n-- targetCompare()");
		for(int i=0; i<testObj.suffixArray.length; i++){
			System.out.println("\ttargetCompare[suffix[" + i + "]] : " + testObj.targetCompare(testObj.suffixArray[i], 0, testObj.myTarget.length));
		}
		System.out.println("\n-- subByteStartIndex()");
		System.out.println("\tsubByteStartIndex : "+testObj.subByteStartIndex(0, testObj.myTarget.length));
		System.out.println("\n-- subByteEndIndex()");
		System.out.println("\tsubByteEndIndex : "+testObj.subByteEndIndex(0, testObj.myTarget.length));
		testObj.printBar();

		//  TestCase 3
		testObj.setSpace("FOOOO BAR FOOO".getBytes());
		testObj.setTarget("OO".getBytes());
		System.out.println("TestCase 3: ");
		System.out.println("Space  : \"FOOOO BAR FOOO\"");
		System.out.println("Target : \"OO\"");

		System.out.println("\n-- printSuffixArray()");
		testObj.printSuffixArray();
		System.out.println("\n-- targetCompare()");
		for(int i=0; i<testObj.suffixArray.length; i++){
			System.out.println("\ttargetCompare[suffix[" + i + "]] : " + testObj.targetCompare(testObj.suffixArray[i], 0, testObj.myTarget.length));
		}
		System.out.println("\n-- subByteStartIndex()");
		System.out.println("\tsubByteStartIndex : "+testObj.subByteStartIndex(0, testObj.myTarget.length));
		System.out.println("\n-- subByteEndIndex()");
		System.out.println("\tsubByteEndIndex : "+testObj.subByteEndIndex(0, testObj.myTarget.length));
		testObj.printBar();

		int result = frequencerObject.frequency();
		System.out.print("Freq = "+ result+" ");
		if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
		System.out.println("STOP");
	}
	}
}