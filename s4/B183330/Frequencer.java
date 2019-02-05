package s4.B183330;
import java.lang.*;
import s4.specification.*;
/*package s4.specification;
public interface FrequencerInterface { frequency counter.
// This interface provides the design for
void setTarget(byte target[]); // set the data to search.
void setSpace(byte space[]); // set the data to be searched target from.
int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
//Otherwise, it return 0, when SPACE is not set or SPACE's length
is zero
//Otherwise, get the frequency of TAGET in SPACE int subByteFrequency(int start, int end);
// get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
// For the incorrect value of START or END, the behavior is undefined. }
*/
public class Frequencer implements FrequencerInterface{
  // Code to start with: This code is not working, but good start point to work.
  byte [] myTarget;
  byte [] mySpace;
  boolean targetReady = false;
  boolean spaceReady = false;
  int [] suffixArray;
// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
// Each suffix is expressed by a integer, which is the starting position in mySpace. // The following is the code to print the variable
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

  for(;i<mySpace.length && j<mySpace.length;i++,j++){
      if(mySpace[i] < mySpace[j]){
        return -1;
      }else if(mySpace[i] > mySpace[j]){
        return 1;
      }
  }
  if(i > j){
    return -1;
  }else if(i < j){
    return 1;
  }
  return 0; // This line should be modified.
  }

public void setSpace(byte []space) {
  mySpace = space; if(mySpace.length>0) spaceReady = true;
  suffixArray = new int[space.length];
  int s= 0,temp = 0;
  //putallsuffixes insuffixArray.Eachsuffixisexpressedbyoneinteger.
  for(int i = 0; i< space.length; i++) {
    suffixArray[i] = i;
  }
// Sorting is not implmented yet. //
//
  for(int i = 0;i<suffixArray.length-1;i++){
    for(int j = suffixArray.length-1;j>i;j--){
      s = suffixCompare(suffixArray[j-1],suffixArray[j]);
      if(s == 1){
        temp = suffixArray[j];
        suffixArray[j] = suffixArray[j-1];
        suffixArray[j-1] = temp;
      }
    }
  }
// **** Please write code here... *** //
}
private int targetCompare(int i, int start, int end) {
  int s = suffixArray[i];
  int t = 0;
  for(t = start;t<end;t++){
    if(myTarget[t] < mySpace[s]){
      return 1;
    }else if(myTarget[t] > mySpace[s]){
      return -1;
    }
    if(s != suffixArray.length-1){
      s++;
    }
  }
  return 0; // This line should be modified.
}

private int subByteStartIndex(int start, int end) {
// It returns the index of the first suffix which is equal or greater than subBytes;
// not implemented yet;
// For "Ho", it will return 5 for "Hi Ho Hi Ho".
// For "Ho ", it will return 6 for "Hi Ho Hi Ho". //

  for(int i = start;i<suffixArray.length;i++){
    int target = targetCompare(i,start,end);
    if(target == 0){
      return i;
    }
  }

// **** Please write code here... **
return suffixArray.length; // This line should be modified.
}
private int subByteEndIndex(int start, int end) {
// It returns the next index of the first suffix which is greater than subBytes;
// not implemented yet
// For "Ho", it will return 7 for "Hi Ho Hi Ho".
// For "Ho ", it will return 7 for "Hi Ho Hi Ho". //
  for(int i = subByteStartIndex(start,end);i<suffixArray.length;i++){
    int target = targetCompare(i,start,end);
    if(target == 1){
      return i;
    }
  }
// **** Please write code here... ***
//
return suffixArray.length; // This line should be modified.
}
public int subByteFrequency(int start, int end) {
  /* This method be work as follows, but
  int spaceLength = mySpace.length;
  int count = 0;
  for(int offset = 0; offset< spaceLength - (end - start); offset++) {
   boolean abort = false;
   for(int i = 0; i< (end - start); i++) {
   if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; } }
   if(abort == false) { count++; }
  }
*/
  int first = subByteStartIndex(start, end);
  int last1 = subByteEndIndex(start, end);
  System.out.println("first:"+first);
  System.out.println("last1:"+last1);

  return last1 - first;
}
  public void setTarget(byte [] target) {
    myTarget = target; if(myTarget.length>0) targetReady = true;
  }
  public int frequency() {
    if(targetReady == false) return -1; if(spaceReady == false) return 0;
    return subByteFrequency(0, myTarget.length);
  }
  public static void main(String[] args) {
    Frequencer frequencerObject;
    try {
      frequencerObject = new Frequencer();
      frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
      frequencerObject.printSuffixArray(); // you may use this line forDEBUG
       /* Example from "Hi Ho Hi Ho"
0: Hi Ho
1: Ho
2: Ho Hi Ho 3:Hi Ho
4:Hi Ho Hi Ho 5:Ho
6:Ho Hi Ho 7:i Ho
8:i Ho Hi Ho 9:o
A:o Hi Ho
*/
    frequencerObject.setTarget("H".getBytes());
    //Test of targetCompare
int end = frequencerObject.myTarget.length;
//
//**** PleasewritecodetochecksubByteStartIndex,andsubByteEndIndex //
  int result = frequencerObject.frequency();
  System.out.print("Freq = "+ result+" ");
  if(4 == result) {
     System.out.println("OK");
  } else{
    System.out.println("WRONG");
  }
 }
 catch(Exception e) {
   System.out.println("STOP");
  }
}
}
