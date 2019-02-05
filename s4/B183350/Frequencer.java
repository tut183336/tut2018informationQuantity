package s4.B183350;

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

public class Frequencer implements FrequencerInterface {
    // Code to start with: This code is not working, but good start point to work.
    byte[] myTarget;
    byte[] mySpace;
    boolean targetReady = false;
    boolean spaceReady = false;

    int[] suffixArray;

    // The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
    // Each suffix is expressed by a integer, which is the starting position in
    // mySpace.
    // The following is the code to print the variable
    private void printSuffixArray() {
        if (spaceReady) {
            for (int i = 0; i < mySpace.length; i++) {
                int s = suffixArray[i];
                for (int j = s; j < mySpace.length; j++) {
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
        // "i" < "o" : compare by code
        // "Hi" < "Ho" ; if head is same, compare the next element
        // "Ho" < "Ho " ; if the prefix is identical, longer string is big
        //
        // **** Please write code here... ***
        String str = new String(mySpace);
        String str_i = str.substring(suffixArray[i], mySpace.length);
        String str_j = str.substring(suffixArray[j], mySpace.length);
        if (str_i.compareTo(str_j) > 0) {
            return 1;
        } else if (str_i.compareTo(str_j) < 0) {
            return -1;
        } else {
            return 0;
        }
        //
        // return 0; // This line should be modified.
    }

    public void setSpace(byte[] space) {
        mySpace = space;
        if (mySpace.length > 0)
            spaceReady = true;
        suffixArray = new int[space.length];
        // put all suffixes in suffixArray. Each suffix is expressed by one integer.
        for (int i = 0; i < space.length; i++) {
            suffixArray[i] = i;
        }
        // Sorting is not implmented yet.
        //
        //
        // **** Please write code here... ***
        int temp;
        for (int i = 0; i < suffixArray.length - 1; i++) {
            for (int j = suffixArray.length - 1; j > i; j--) {
                if (suffixCompare(j - 1, j) == 1) {
                    temp = suffixArray[j - 1];
                    suffixArray[j - 1] = suffixArray[j];
                    suffixArray[j] = temp;
                }
            }
        }
    }

    private int targetCompare(int i, int j, int end) {
        // comparing suffix_i and target_j_end by dictonary order with limitation of
        // length;
        // if the beginning of suffix_i matches target_j_end, and suffix is longer than
        // target it returns 0;
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
        try {
            if (end - j > myTarget.length) {
                throw new IllegalArgumentException("myTargetよりendが大きいです。");
            }
            if (i < 0 || i > suffixArray.length || j < 0 || j > suffixArray.length) {
                throw new IllegalArgumentException("iかjの値が不正です。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = new String(mySpace);
        String str_i = str.substring(suffixArray[i], str.length());
        String str_j = str.substring(suffixArray[j], end);
        String tar = new String(myTarget);
        tar = tar.substring(j, j + 1);
        if (str_i.startsWith(tar)) {
            return 0;
        }
        if (str_i.compareTo(str_j) > 0) {
            return 1;
        } else if (str_i.compareTo(str_j) < 0) {
            return -1;
        } else {
            return 0;
        }
        //
        // return 0; // This line should be modified.
    }

    private int subByteStartIndex(int start, int end) {
        // It returns the index of the first suffix which is equal or greater than
        // subBytes;
        // not implemented yet;
        // For "Ho", it will return 5 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 6 for "Hi Ho Hi Ho".
        //
        // **** Please write code here... ***
        if (start > end) {
            // startよりendが大きかった場合、startとendを入れ替え
            int temp = start;
            start = end;
            end = temp;
        }
        if (end > myTarget.length || start < 0) {
            // endが大きすぎるか、startが小さすぎる
            return -1;
        }
        String str = new String(mySpace);
        String targetStr = new String(myTarget);
        targetStr = targetStr.substring(start, end);
        for (int i = 0; i < suffixArray.length; i++) {
            if (str.substring(suffixArray[i], mySpace.length).startsWith(targetStr)) {
                return i;
            }
        }
        return -1;
        //
        // return suffixArray.length; // This line should be modified.
    }

    private int subByteEndIndex(int start, int end) {
        // It returns the next index of the first suffix which is greater than subBytes;
        // not implemented yet
        // For "Ho", it will return 7 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 7 for "Hi Ho Hi Ho".
        //
        // **** Please write code here... ***
        if (start > end) {
            // startよりendが大きかった場合、startとendを入れ替え
            int temp = start;
            start = end;
            end = temp;
        }
        if (end > mySpace.length || start < 0) {
            // endが大きすぎるか、startが小さすぎる
            return -1;
        }
        String str = new String(mySpace);
        String targetStr = new String(myTarget);
        targetStr = targetStr.substring(start, end);
        boolean startB; // startBoolean
        boolean subBF = false; // subByteFlag
        for (int i = 0; i < suffixArray.length; i++) {
            startB = str.substring(suffixArray[i], mySpace.length).startsWith(targetStr);
            if (startB == true) {
                subBF = true;
            }
            if (subBF == true && startB == false) {
                return i;
            }
        }
        return -1;
        //
        // return suffixArray.length; // This line should be modified.
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
        Frequencer frequencerObject;
        try {
            frequencerObject = new Frequencer();
            frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
            frequencerObject.printSuffixArray(); // you may use this line for DEBUG
            /*
             * Example from "Hi Ho Hi Ho" 0: Hi Ho 1: Ho 2: Ho Hi Ho 3:Hi Ho 4:Hi Ho Hi Ho
             * 5:Ho 6:Ho Hi Ho 7:i Ho 8:i Ho Hi Ho 9:o A:o Hi Ho
             */

            frequencerObject.setTarget("H".getBytes());
            //
            // **** Please write code to check subByteStartIndex, and subByteEndIndex
            System.out.println(frequencerObject.subByteStartIndex(0, 1));
            System.out.println(frequencerObject.subByteStartIndex(3, 30));
            System.out.println(frequencerObject.subByteEndIndex(0, 1));
            System.out.println(frequencerObject.subByteEndIndex(3, 30));
            //

            int result = frequencerObject.frequency();
            System.out.print("Freq = " + result + " ");
            if (4 == result) {
                System.out.println("OK");
            } else {
                System.out.println("WRONG");
            }
        } catch (Exception e) {
            System.out.println("STOP");
            e.printStackTrace();
        }
    }
}
