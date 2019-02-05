package s4.B183367; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

public class Frequencer implements FrequencerInterface{
    // Code to Test, *warning: This code  contains intentional problem*
    byte [] myTarget;
	byte [] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;
	
	int [] suffixArray;
	int [] A;
	int [] a;
	int count = 0;

	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a interger, which is the starting position in mySpace.
	// The following is the code to print the variable
	private void printSuffixArray() {
		System.out.println("----- SuffixArray -----");
		if(spaceReady) {
			for(int i=0; i< mySpace.length; i++) {
			int s = suffixArray[i];
			System.out.printf("%02d:",i);		
			for(int j=s;j<mySpace.length;j++) {
				System.out.write(mySpace[j]);
			}
			System.out.write('\n');
			}
		}
	}

	private int suffixCompare_(int i, int j) {
		// comparing two suffixes by dictionary order.
		// i and j denoetes suffix_i, and suffix_j
		// if suffix_i > suffix_j, it returns 1
		// if suffix_i < suffix_j, it returns -1
		// if suffix_i = suffix_j, it returns 0;
		// It is not implemented yet,
		// It should be used to create suffix array.
		// Example of dictionary order
		// "i" < "o" : compare by code
		// "Hi" < "Ho" ; if head is same, compare the next element
		// "Ho" < "Ho " ; if the prefix is identical, longer string is big
		int si = i;
		int sj = j;
		int s = 0;
		if(si > s) s = si;
		if(sj > s) s = sj;
		int n = mySpace.length - s;
		for(int k=0;k<n;k++) {
			if(mySpace[si+k] > mySpace[sj+k]) return 1;
			if(mySpace[si+k] < mySpace[sj+k]) return -1;
		}
		if(si < sj) return 1;
		if(si > sj) return -1;
		return 0;
	}

	public void setSpace(byte []space) {
		mySpace = space; if(mySpace.length>0) spaceReady = true;
		suffixArray = new int[space.length];
		a = suffixArray;

		// put all suffixes in suffixArray. Each suffix is expressed by one interger.
		for(int i = 0; i< space.length; i++) {
			a[i] = suffixArray[i] = i;
		}
		printSuffixArray();
		mergeSort(suffixArray);
		//Copy the result to the suffixArray
		//suffixArray = a;

		/* Example from "Hi Ho Hi Ho"
		0:Hi Ho Hi Ho
		1:i Ho Hi Ho
		2: Ho Hi Ho
		3:Ho Hi Ho
		4:o Hi Ho
		5: Hi Ho
		6:Hi Ho
		7:i Ho
		8: Ho
		9:Ho
		A:o
		*/
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

		printSuffixArray();
	}

	private void merge(int [] a1, int [] a2, int [] a) {
		int i = 0, j = 0;
		while(i < a1.length || j < a2.length) {
			if(j >= a2.length || (i < a1.length && suffixCompare_(a1[i],a2[j]) != 1)) {				
				a[i+j] = a1[i];
				i++;
			} else {				
				a[i+j] = a2[j];
				j++;
			}
		}
	}

	private void mergeSort(int [] a) {
		if(a.length > 1) {
			int m = a.length/2;
			int n = a.length - m;
			int [] a1 = new int[m];
			int [] a2 = new int[n];
			for(int i = 0; i < m; i++) { a1[i] = a[i]; }
			for(int i = 0; i < n; i++) { a2[i] = a[m+i]; }
			mergeSort(a1);
			mergeSort(a2);
			merge(a1,a2,a);
		}
	}

	private int targetCompare(int i, int start, int end) {
		// comparing suffix_i and target_start_end by dictonary order with limitation of length;
		// if the beginning of suffix_i matches target_i_end, and suffix is longer than target it returns 0;
		// if suffix_i > target_j_end it return 1;
		// if suffix_i < target_j_end it return -1;
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

		if (i < 0) {
			 /* System.out.println("minus index"); */ 
			 return -1; 
		} else if (i > mySpace.length - 1) {
			 /* System.out.println("plus index"); */ 
			 return 1; 
		}

		int si = suffixArray[i];
		int s = 0;
		if(si > s) s = si;
		int n = mySpace.length - s;

		//suffix_i > myTarget のとき
		if( n > end - start ) n = end - start;

		for(int k=0; k < n;k++) {
			if(mySpace[si+k] > myTarget[start+k]) return 1;
			if(mySpace[si+k] < myTarget[start+k]) return -1;
		}

		if( n < (end - start))  return -1;

		return 0;

	}

	private int subByteStartIndex(int start, int end) {
		// It returns the index of the first suffix which is equal or greater than subBytes;
		// subBytes以上の最初の接尾辞のインデックスを返します。
		// not implemented yet;

		// For "Ho", it will return 5 for "Hi Ho Hi Ho".
		// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
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


		int pLeft = 0;
		int pRight = mySpace.length - 1;
		//System.out.println("pLeft: "+pLeft+" pRight:"+pRight);			
		

		do {
			int center = (pLeft + pRight) / 2;
			//System.out.println("Center > "+center);
			
			if (targetCompare(center,start,end) == 0 && targetCompare(center-1,start,end) == -1) {
				//System.out.println("Index > "+center);				
				return center;

			} else if (targetCompare(center,start,end) == -1) {
				pLeft = center + 1; //真ん中の一つ右側を左端とする
			} else {
				pRight = center - 1;
			}
			//System.out.println("pLeft: "+pLeft+" pRight:"+pRight);			
		
		} while (pLeft <= pRight);

		return suffixArray.length;
	}

	private int subByteEndIndex(int start, int end) {
		// It returns the next index of the first suffix which is greater than subBytes;
		// For "Ho", it will return 7 for "Hi Ho Hi Ho".
		// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
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

		int pLeft = 0;
		int pRight = mySpace.length - 1;		
		
		do {
			int center = (pLeft + pRight) / 2;
			//System.out.println("Center > "+center);
			if (targetCompare(center,start,end) == 0 && targetCompare(center+1,start,end) == 1 ) {
				//System.out.println("Index > "+center);
				return center+1;

			} else if (targetCompare(center,start,end) == 1) {
				pRight = center - 1;
				
			} else {
				pLeft = center + 1; //真ん中の一つ右側を左端とする
			}		
			
		} while (pLeft <= pRight);

		return suffixArray.length;
		
	}

	public int subByteFrequency(int start, int end) {
		//This method could be defined as follows though it is slow.
		/* int spaceLength = mySpace.length;
		int count = 0;
		for(int offset = 0; offset < spaceLength - (end - start); offset++) {
			boolean abort = false;
			for(int i = 0; i< (end - start); i++) {
				if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
			}
			if(abort == false) { count++; }
		} */

		//System.out.println("\n< binary serch >\n");
		
		int first = subByteStartIndex(start,end);
		int last1 = subByteEndIndex(start, end);

		//inspection code
		// for(int k=start;k<end;k++) { System.out.write(myTarget[k]); }
		// System.out.printf(": first=%d last1=%d\n", first, last1);
		
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
		long startTime, endTime;
		Frequencer frequencerObject ;

		// Hi Ho Hi Ho -> set as H -> should return 4
		try {
			frequencerObject = new Frequencer();

			startTime = System.nanoTime();
				frequencerObject.setSpace("Hi Ho Hi Ho Hi Ho Hi Ho".getBytes());
				frequencerObject.setTarget("H".getBytes());
				int result = frequencerObject.frequency();
				System.out.print("Freq = "+ result+" ");
			endTime = System.nanoTime();

			System.out.printf("%ntask is processing... %ntime: %d ms.%n", (endTime - startTime) / 1000);

			if(4 == result) { System.out.println("OK"); }
				else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("STOP");
		}

		// Die De Die De -> set as D ->  should return 4
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Die Do Die Do".getBytes());
			frequencerObject.setTarget("Di".getBytes());
			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");

			if(2 == result) { System.out.println("OK"); }
				else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println("STOP");
		}

		// Die De Die De -> set as "" -> should return -1
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Die Do Die Do".getBytes());
			frequencerObject.setTarget("".getBytes());
			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");

			if(-1 == result) { System.out.println("OK"); }
				else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println("STOP");
		}

		// AAAAAAAAA -> set as A -> should return 9
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("AAAAAAAAA".getBytes());
			frequencerObject.setTarget("A".getBytes());
			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");

			if(9 == result) { System.out.println("OK"); }
				else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println("STOP");
		}
	}
}
	    
