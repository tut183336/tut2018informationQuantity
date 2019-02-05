package s4.B183360;
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
        // **** Please write code here... ***
        int suffix_i = i;
        int suffix_j = j;
        int suffix_i_length = mySpace.length - suffix_i;
        int suffix_j_length = mySpace.length - suffix_j;

        for(int k = 0; k < suffix_j_length; k++) {
//          System.out.println("Check1!");

            if((suffix_i + k >= mySpace.length) || (suffix_j + k >= mySpace.length)) {
                break;
            }

            if(mySpace[suffix_i + k] > mySpace[suffix_j + k]) {
//              System.out.println("1");
                return 1;
            }
            if(mySpace[suffix_i + k] < mySpace[suffix_j + k]) {
//              System.out.println("-1");
                return -1;
            }
        }

        if(suffix_i_length > suffix_j_length) {
            return 1;
        }

        return 0;

    }

    public void setSpace(byte []space) {
        mySpace = space;
        if(mySpace.length>0) spaceReady = true;
        suffixArray = new int[space.length];
        for(int i = 0; i< space.length; i++) {
            suffixArray[i] = i;
        }

        // **** Please write code here... *** //
        for(int i = 0; i < suffixArray.length-1; i++){
            for(int j = suffixArray.length - 1; j > i; j--){
//              System.out.println("###### i="+ i +" j="+ j +" ######");
                if(suffixCompare(suffixArray[j-1],suffixArray[j]) == 1){
                    int tmp;
                    tmp = suffixArray[j-1];
                    suffixArray[j-1] = suffixArray[j];
                    suffixArray[j] = tmp;
                }

//                for(int m = 0;m < suffixArray.length;m++)
//                  System.out.print(suffixArray[m]);
//                System.out.println("");
//                printSuffixArray();
//                System.out.println("#####################");
//                System.out.println("");
            }
        }
    }

    private int targetCompare(int i, int j, int end) {

      int suffix_i = i;
      //int suffix_j = j;
      int suffix_i_length = mySpace.length - suffix_i;
      //int suffix_j_length = mySpace.length - suffix_j;
      int target_j_end = end -j;

      for(int k = 0; k < suffix_i_length; k++) {
//          System.out.println("Check1!");

        if(mySpace[suffix_i + k] > myTarget[j + k]) {
//              System.out.println("1");
          return 1;
        }

        if(mySpace[suffix_i + k] < myTarget[j + k]) {
//              System.out.println("-1");
          return -1;
        }

        if(k+1 == target_j_end){
          return 0;
        }
      }

      if(suffix_i_length < target_j_end){
        return -1;
      }


        // comparing suffix_i and target_j_end by dictonary order with limitation of length;
        // if the beginning of suffix_i matches target_i_end, and suffix is longer than
        //"Ho" <
        // "Ho" = //
        // ****  Please write code here... ***
        //"Ho" :"Ho"isnotintheheadofsuffix"Ho"
        //"H" : "H" is in the head of suffix "Ho"
        return 0; // This line should be modified.

    }

    private int subByteStartIndex(int start, int end) {

      // try {
      //     if(start <= end) {
      //       System.out.println("check3: OK");
      //     }
      //     else {
      //       System.out.println("WRONG");
      //       System.out.println("case3 : checking s4.B183353.Frequencer");
      //       System.out.print("endよりstartの方が大きいです。");
      //     }
      // }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }

      // try {
      //     if(suffixArray.length != 0) { System.out.println("check4: OK"); }
      //       else {
      //         System.out.println("WRONG");
      //         System.out.println("case4 : checking s4.B183353.Frequencer");
      //         System.out.print("mySpaceに文字が指定されていません");
      //       }
      //     }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }

      // try {
      //     if(myTarget.length != 0) { System.out.println("check5: OK"); }
      //     else {
      //       System.out.println("WRONG");
      //       System.out.println("case5 : checking s4.B183353.Frequencer");
      //       System.out.print("Targetに文字が指定されていません");}
      // }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }


      for(int i = 0; i < suffixArray.length; i++){
        if(targetCompare(suffixArray[i],start,end) == 0){
          // System.out.println("start :" + i);
          return i;
        }
      }

        // It returns the index of the first suffix which is equal or greater than subBytes;
        // not implemented yet;
        // For "Ho", it will return 5 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 6 for "Hi Ho Hi Ho". //
        // **** Please write code here... *** //


        return suffixArray.length; // This line should be modified.
    }

    private int subByteEndIndex(int start, int end) {

      // try {
      //     if(start <= end) {
      //       System.out.println("check6: OK");
      //     }
      //     else {
      //       System.out.println("WRONG");
      //       System.out.println("case6 : checking s4.B183353.Frequencer");
      //       System.out.print("endよりstartの方が大きいです。");
      //     }
      // }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }

      // try {
      //     if(suffixArray.length != 0) { System.out.println("check7: OK"); }
      //       else {
      //         System.out.println("WRONG");
      //         System.out.println("case7 : checking s4.B183353.Frequencer");
      //         System.out.print("mySpaceに文字が指定されていません");
      //       }
      //     }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }

      // try {
      //     if(myTarget.length != 0) { System.out.println("check8: OK"); }
      //     else {
      //       System.out.println("WRONG");
      //       System.out.println("case8 : checking s4.B183353.Frequencer");
      //       System.out.print("Targetに文字が指定されていません");}
      // }
      // catch(Exception e) {
      //     System.out.println("Exception occurred: STOP");
      // }

      for(int i = suffixArray.length-1; i >= 0 ; i--){
        if(targetCompare(suffixArray[i],start,end) == 0){
          // System.out.print("end :");
          // System.out.println(i+1);
          return i+1;
        }
      }

        // It returns the next index of the first suffix which is greater than subBytes;
        // not implemented yet
        // For "Ho", it will return 7 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 7 for "Hi Ho Hi Ho". //
        // **** Please write code here... *** //


        return suffixArray.length; // This line should be modified.
    }

    public int subByteFrequency(int start, int end) {
        /* This method be work as follows, but
        int spaceLength = mySpace.length;
        int count = 0;
        for(int offset = 0; offset< spaceLength - (end - start); offset++) { boolean abort = false;
        for(int i = 0; i< (end - start); i++) {
        if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; } }
        if(abort == false) { count++; } }
        */
        int first = subByteStartIndex(start, end);
        int last1 = subByteEndIndex(start, end);

        return last1 - first;
    }

    public void setTarget(byte [] target) {
        myTarget = target;
        if(myTarget.length>0)
            targetReady = true;
    }

    public int frequency() {
        if(targetReady == false) return -1; if(spaceReady == false) return 0;
        return subByteFrequency(0, myTarget.length);
    }

    public static void main(String[] args) { Frequencer frequencerObject;

      String space = "Hi Ho Hi Ho";
      String target = "H";

        try {
            frequencerObject = new Frequencer();
            frequencerObject.setSpace(space.getBytes());
            frequencerObject.printSuffixArray(); // you may use this line for　DEBUG

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

            frequencerObject.setTarget(target.getBytes());
            //
            //**** Please write code to check subByteStartIndex, and subByteEndIndex //
            // try {
            //     if(space.length() != 0 )
            //     { System.out.println("check1: OK"); }
            //     else {
            //       System.out.println("WRONG");
            //       System.out.println("case1 : checking s4.B183353.Frequencer");
            //       System.out.print("mySpaceに文字が指定されていません");}
            // }
            // catch(Exception e) {
            //     System.out.println("Exception occurred: STOP");
            // }

            // try {
            //     if(target.length() != 0 )
            //     { System.out.println("check2: OK"); }
            //     else {
            //       System.out.println("WRONG");
            //       System.out.println("case2 : checking s4.B183353.Frequencer");
            //       System.out.print("Targetに文字が指定されていません");}
            // }
            // catch(Exception e) {
            //     System.out.println("Exception occurred: STOP");
            // }



            int result = frequencerObject.frequency();
            System.out.print("Freq = "+ result+" ");
            if(4 == result) {
                System.out.println("OK");
            } else {
                System.out.println("WRONG");
            }
        } catch(Exception e) {
            System.out.println("STOP");
            System.err.println(e);  //エラー内容を表示
        }
    }
}
