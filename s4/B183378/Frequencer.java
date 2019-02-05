package s4.B183378;
import java.lang.*;
import s4.specification.*;

/*
package s4.specification;

public interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte  target[]); // set the data to search.
    void setSpace(byte  space[]);  // set the data to be searched target from.
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

    int []  suffixArray;

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
	// "i"      <  "o"        : compare by code
	// "Hi"     <  "Ho"       ; if head is same, compare the next element
	// "Ho"     <  "Ho "      ; if the prefix is identical, longer string is big
	//
	// ****  Please write code here... ***
        i = suffixArray[i];
        j = suffixArray[j];
        while(i<mySpace.length && j<mySpace.length){
            if(mySpace[i] > mySpace[j])
                return 1;
            else if(mySpace[i]==mySpace[j]){
                i++;
                j++;
            }
            else{
                return -1;
            }
        }
        if(i<mySpace.length && j>=mySpace.length)
            return 1;
        else if(i>=mySpace.length && j<mySpace.length)
            return -1;
        return 0; // This line should be modified.
    }

    public void setSpace(byte []space) { 
	mySpace = space; if(mySpace.length>0) spaceReady = true; 
	suffixArray = new int[space.length];
	// put all suffixes  in suffixArray. Each suffix is expressed by one integer.
	for(int i = 0; i< space.length; i++) {
	    suffixArray[i] = i;
	}
	// Sorting is not implmented yet
	// ****  Please write code here... ***
        for(int i=0;i<mySpace.length-1;i++){
            for(int j=mySpace.length-1;j>i;j--){
                int flag = suffixCompare(j,j-1);
                if(flag == -1){
                    int tmpNum = suffixArray[j-1];
                    suffixArray[j-1] = suffixArray[j];
                    suffixArray[j] = tmpNum;
                }
            }
        }
    }

    private int targetCompare(int i, int j, int end) {
	// comparing suffix_i and target_j_end by dictonary order with limitation of length;
	// if the beginning of suffix_i matches target_i_end, and suffix is longer than target  it returns 0;
	// if suffix_i > target_i_end it return 1;
	// if suffix_i < target_i_end it return -1
	// It is not implemented yet.
	// It should be used to search the apropriate index of some suffix.
	// Example of search
	// suffix          target
        // "o"       >     "i"
        // "o"       <     "z"
	// "o"       =     "o"
        // "o"       <     "oo"
	// "Ho"      >     "Hi"
	// "Ho"      <     "Hz"
	// "Ho"      =     "Ho"
        // "Ho"      <     "Ho "   : "Ho " is not in the head of suffix "Ho"
	// "Ho"      =     "H"     : "H" is in the head of suffix "Ho"
	//
	// ****  Please write code here... ***
	//
        int num = suffixArray[i];
        int last = mySpace.length - num;
        if(last>end-j){
            last = end-j;
        }
        for(int a=0;a<last;a++){
            if(mySpace[num+a]>myTarget[j+a]){
                return 1;
            }
            else if(mySpace[num+a]<myTarget[j+a]){
                return -1;
            }
        }
        if(last<end-j){
            return -1;
        }
        return 0; // This line should be modified.
    }

    private int subByteStartIndex(int start, int end) {
	// It returns the index of the first suffix which is equal or greater than subBytes;
	// not implemented yet;
	// For "Ho", it will return 5  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
	//
	// ****  Please write code here... ***
	//
        int min = 0;
        int mid = 0;
        int max = mySpace.length;
        int result = 0;
        while(max-min>1){
            mid = (max+min)/2;
            result = targetCompare(mid,start,end);
            if(result>=0){
                max = mid;
            }
            else{
                min = mid;
            }
        }
        return max; // This line should be modified.
    }

    private int subByteEndIndex(int start, int end) {
	// It returns the next index of the first suffix which is greater than subBytes;
	// not implemented yet
	// For "Ho", it will return 7  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
	//
	// ****  Please write code here... ***
	//
        int min = 0;
        int mid = 0;
        int max = mySpace.length;
        int result = 0;
        while(max-min>1){
            mid = (max+min)/2;
            result = targetCompare(mid,start,end);
            if(result<=0){
                min = mid;
            }
            else{
                max = mid;
            }
        }
        return max; // This line should be modified.
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

    public static void main(String[] args) {
	Frequencer frequencerObject;
	try {
	    frequencerObject = new Frequencer();
	    frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
	    frequencerObject.printSuffixArray(); // you may use this line for DEBUG
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

	    frequencerObject.setTarget("Ho".getBytes());
	    //
	    // ****  Please write code to check subByteStartIndex, and subByteEndIndex
	    //check subByteStartIndex
        int result_start = frequencerObject.subByteStartIndex(0,"Ho".getBytes().length);
        System.out.print("subByteStartIndex =" + result_start + " ");
        if(5 == result_start) {
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        
        frequencerObject.setTarget("Ho ".getBytes());
        result_start = frequencerObject.subByteStartIndex(0, "Ho ".getBytes().length);
        System.out.print("subByteStartIndex = " + result_start + " ");
        if(6 == result_start){
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        
        //check subByteEndIndex
        int result_End = frequencerObject.subByteEndIndex(0, "Ho".getBytes().length);
        System.out.print("subByteEndIndex = " + result_End + " ");
        if(7 == result_End){
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        
        frequencerObject.setTarget("Ho ".getBytes());
        result_End = frequencerObject.subByteEndIndex(0, "Ho ".getBytes().length);
        System.out.print("subByteEndIndex = " + result_End + " ");
        if(7 == result_End){
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        
        frequencerObject.setTarget("H".getBytes());
	    int result = frequencerObject.frequency();
	    System.out.print("Freq = "+ result+" ");
	    if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("STOP");
	}
    }
}	    
	    
