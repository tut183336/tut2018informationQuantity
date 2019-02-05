package s4.B183322;
import java.lang.*;
import s4.specification.*;


/*package s4.specification;
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
	//
  //ーーーーーーーーーーーーーー前回までのプログラムーーーーーーーーーーーーー
	// byte[] suffix_i = new byte[mySpace.length-i];
	// byte[] suffix_j = new byte[mySpace.length-j];
	// int i_length, j_length;
	// for(int k = 0; k < mySpace.length-i; k++){
	//   suffix_i[k] = mySpace[k+i];
	// }
  //
	// for(int k = 0; k < mySpace.length-j; k++){
	//   suffix_j[k] = mySpace[k+j];
	// }
  //
	// i_length = suffix_i.length;
	// j_length = suffix_j.length;
  //
	// if(i_length > j_length){
	//   for(int k = 0; k < j_length; k++){
	//     if(suffix_i[k] < suffix_j[k]){
	//       return -1;
	//     }else if(suffix_i[k] > suffix_j[k]){
	//       return 1;
	//     }
	//   }
  //   return 1;
	// }else if(i_length < j_length){
	//   for(int k = 0; k < i_length; k++){
	//     if(suffix_i[k] < suffix_j[k]){
	//       return -1;
	//     }else if(suffix_i[k] > suffix_j[k]){
	//       return 1;
	//     }
	//   }
  //   return -1;
	// }else{
  //   for(int k = 0; k < i_length; k++){
  //     if(suffix_i[k] < suffix_j[k]){
  //       return -1;
  //     }else if(suffix_i[k] > suffix_j[k]){
  //       return 1;
  //     }
  //   }
  //   return 0;
  // }

  //ーーーーーーーーーーーー改良版ーーーーーーーーーーーーーー
    if(i < j){
      for(int k=0; k < mySpace.length - j; k++){
        if(mySpace[i + k] > mySpace[j + k]) return 1;
        if(mySpace[i + k] < mySpace[j + k]) return -1;
      }
      return 1;
    }else if(i > j){
      for(int k=0; k < mySpace.length - i; k++){
        if(mySpace[i + k] > mySpace[j + k]) return 1;
        if(mySpace[i + k] < mySpace[j + k]) return -1;
      }
      return -1;
    } else {
      for(int k=0; k < mySpace.length - i; k++){
        if(mySpace[i + k] > mySpace[j + k]) return 1;
        if(mySpace[i + k] < mySpace[j + k]) return -1;
      }
      return 0;
    }
  }

  public void setSpace(byte []space) {
  	int temp;
  	mySpace = space; if(mySpace.length>0) spaceReady = true;
  	suffixArray = new int[space.length];
	// put all suffixes  in suffixArray. Each suffix is expressed by one integer.
  	for(int i = 0; i< space.length; i++) {
  	    suffixArray[i] = i;
  	}
  	for(int i = 0; i< (suffixArray.length-1); i++){
  	  for(int j = (suffixArray.length-1); j > i; j--){
  	    if(suffixCompare(suffixArray[j-1], suffixArray[j]) == 1){
  	      temp = suffixArray[j-1];
  	      suffixArray[j-1] = suffixArray[j];
  	      suffixArray[j] = temp;
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
  //ーーーーーーーーー前回までのプログラムーーーーーーーーー
  	// byte[] suffix = new byte[mySpace.length-i];
  	// byte[] target = new byte[end-j];
  	// for(int k=0; k<mySpace.length-i;k++){
  	// 	suffix[k] = mySpace[k+i];
  	// }
    //
  	// for(int k=j; k < end; k++){
  	// 	target[k-j] = myTarget[k];
  	// }
    //
  	// if(target.length <= suffix.length){
  	// 	for(int k=0; k<target.length; k++){
  	// 		if(suffix[k] > target[k])return 1;
  	// 		else if(suffix[k] < target[k]) return -1;
  	// 	}
  	// 	return 0;
  	// }else{
  	// 	for(int k=0; k<suffix.length; k++){
  	// 		if(suffix[k] > target[k]) return 1;
  	// 		else if(suffix[k] < target[k]) return -1;
  	// 	}
  	// 	return -1;
  	// }

    //ーーーーーーーーー改良版ーーーーーーーーーーーー
    int suffix_i = mySpace.length-i;
    int target_j_end = end-j;

    if(target_j_end <= suffix_i){
      for(int k=0; k<target_j_end; k++){
        if(mySpace[i+k] > myTarget[k]) return 1;
        if(mySpace[i+k] < myTarget[k]) return -1;
      }
      return 0;
    }else{
      for(int k=0; k<suffix_i; k++){
        if(mySpace[i+k] > myTarget[k]) return 1;
        if(mySpace[i+k] < myTarget[k]) return -1;
      }
      return -1;
    }

  }

  private int subByteStartIndex(int start, int end) {
	// It returns the index of the first suffix which is equal or greater than subBytes;
	// not implemented yet;
	// For "Ho", it will return 5  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
	//
	// ****  Please write code here... ***
	//
  	int result;
  	for(int i=0; i<suffixArray.length; i++){
  		result = targetCompare(suffixArray[i],start,end);
  		if(result == 0) return i;
  		if(result == 1){
  		       	break;
  		}
  	}
  	return suffixArray.length;
  }

  private int subByteEndIndex(int start, int end) {
	// It returns the next index of the first suffix which is greater than subBytes;
	// not implemented yet
	// For "Ho", it will return 7  for "Hi Ho Hi Ho".
	// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
	//
	// ****  Please write code here... ***
  	int result;
    //ーーーーーー前回までのプログラムーーーーーーー
  	// for(int i=suffixArray.length-1; i >= 0; i--){
  	// 	result = targetCompare(suffixArray[i],start,end);
  	// 	if(result == 0) return i+1;
  	// 	if(result == -1) break;
  	// }
    //ーーーーーー改良版ーーーーーーーーー
    for(int i=subByteStartIndex(start, end); i<suffixArray.length; i++){
      result = targetCompare(suffixArray[i],start,end);
      if(result == 1) return i;
      if(result == -1){
              break;
      }
    }
  	return suffixArray.length;
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
  	int last = subByteEndIndex(start, end);
  	return last - first;
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
        System.out.println("-------------------suffix array--------------------");
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
        System.out.println("-------------------探索--------------------");
  	    frequencerObject.setTarget("H".getBytes());
  	    //
  	    // ****  Please write code to check subByteStartIndex, and subByteEndIndex
        int first = frequencerObject.subByteStartIndex(0, frequencerObject.myTarget.length);
        int last = frequencerObject.subByteEndIndex(0, frequencerObject.myTarget.length);
        System.out.printf("start:%d/end:%d\n",first,last);
  	    //
  	    int result = frequencerObject.frequency();
  	    System.out.print("Freq = "+ result+" ");
  	    if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
  	}
  	catch(Exception e) {
  	    System.out.println("STOP");
  	}
  }
}
