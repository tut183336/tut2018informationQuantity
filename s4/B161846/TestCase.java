package s4.B161846; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
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
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity,
}
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.B161846.Frequencer");
	    myObject = new s4.B161846.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

  //SPACE's length is zero
    try {
        FrequencerInterface myObject;
        int freq;

        System.out.println("Check s4.B161846.Frequencer.setSpace'length is zero");
        myObject = new s4.B161846.Frequencer();
        myObject.setSpace(" ".getBytes());
        myObject.setTarget("H".getBytes());
        freq = myObject.frequency();
        if (freq == 0) {
          System.out.println("OK");
        }
        else {
          System.out.println("WRONG");
        }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
    }
//SPACE is not set
    try {
        FrequencerInterface myObject;
        int freq;

        System.out.println("Check s4.B161846.Frequencer.setSpace is not set");
        myObject = new s4.B161846.Frequencer();
        myObject.setTarget("H".getBytes());
        freq = myObject.frequency();
        if (freq == 0) {
          System.out.println("OK");
        }
        else {
          System.out.println("WRONG");
        }
    }
    catch(Exception e) {
        System.out.println("Exception occurred: STOP");
    }

    //TARGET is not set
    try {
      FrequencerInterface myObject;
      int freq;

      System.out.println("Check s4.B161846.Frequencer.setTarget is not set");
      myObject = new s4.B161846.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      freq  = myObject.frequency();
      if(freq == -1) {
        System.out.println("OK");
      }
      else {
        System.out.println("WRONG");
      }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

//TARGET'length is zero
    try {
      FrequencerInterface myObject;
      int freq;

      System.out.println("Check s4.B161846.Frequencer.setTarget is Space");
      myObject = new s4.B161846.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget(" ".getBytes());
      freq  = myObject.frequency();
      if(freq == -1) {
        System.out.println("OK");
      }
      else {
        System.out.println("WRONG");
      }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    try {
      FrequencerInterface myObject;
      int freq;

      System.out.println("Check Space'length and Target'length is zero");
      myObject = new s4.B161846.Frequencer();
      myObject.setSpace("".getBytes());
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      if(freq == 0) {
        System.out.println("OK");
      }
      else {
        System.out.println("WRONG");
      }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

//Frequencerクラスの問題を明らかにする
//SPACEがすべて同じ文字で、TARGETがその文字を複数もつ
    try {
      FrequencerInterface myObject;
      int freq;
      System.out.println("Check SPACE is same character");
      myObject = new s4.B161846.Frequencer();
      myObject.setSpace("aaaaa".getBytes());
      myObject.setTarget("aa".getBytes());
      freq = myObject.frequency();
      if(freq == 2) {
        System.out.println("OK");
      }
      else {
        System.out.println("WRONG");
      }

    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }
    //Targetの最初の文字とSpaceの最後の文字が一致するとき
  try {
    FrequencerInterface myObject;
    int freq;

    System.out.println("Check when correspond last letter of Space and first letter of Target");
    myObject = new s4.B161846.Frequencer();
    myObject.setSpace("bbadfiokjb".getBytes());
    myObject.setTarget("bb".getBytes());
    freq = myObject.frequency();
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }


	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B161846.InformationEstimator");
	    myObject = new s4.B161846.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0 "+value);
	    myObject.setTarget("01".getBytes());
	    value = myObject.estimation();
	    System.out.println(">01 "+value);
	    myObject.setTarget("0123".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0123 "+value);
	    myObject.setTarget("00".getBytes());
	    value = myObject.estimation();
	    System.out.println(">00 "+value);
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

/*
 *問題その1
 *FrequencerのFrequencyメソッドにおいて、mySpaceの最後の文字と
 *myTargetの最初の文字が一致し、かつmyTargetが複数文字で構成されているとき、
 *mySpaceの配列サイズを超えた部分を参照している。
*/

    }
}
