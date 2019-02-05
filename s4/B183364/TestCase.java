package s4.B183364; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
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
 
  static void frequencerInterfaceFrequencerTest(String space, String target, int count) {
    try{
      FrequencerInterface myObject;
      int freq;
      myObject = new s4.B183364.Frequencer();
      if(space != null) myObject.setSpace(space.getBytes());
      if(target != null) myObject.setTarget(target.getBytes());
      freq = myObject.frequency();
      if(count == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e){
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }
  }

  static void frequencerInterfaceSubByteFrequencerTest(String space, String target, int start, int end, int count) {
    try{
      FrequencerInterface myObject;
      int subFreq;
      myObject = new s4.B183364.Frequencer();
      if(space != null) myObject.setSpace(space.getBytes());
      if(target != null) myObject.setTarget(target.getBytes());
      subFreq = myObject.subByteFrequency(start, end);
      if(count == subFreq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e){
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }
  }

  static void informationEstimatorBlackTest(String space, String target) {
    try{
      InformationEstimator myObject;
      double value;
      myObject = new s4.B183364.InformationEstimator();
      myObject.setSpace(space.getBytes());
      myObject.setTarget(target.getBytes());
      value = myObject.estimation();
      System.out.println(">" + target + " " + value);
    }
    catch(Exception e){
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }
  }

  static void informationEstimatorWhiteTest(String space, String target, double valid) {
    try{
      InformationEstimator myObject;
      double value;
      myObject = new s4.B183364.InformationEstimator();
      if(space != null) myObject.setSpace(space.getBytes());
      if(target != null) myObject.setTarget(target.getBytes());
      value = myObject.estimation();
      if(valid == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e){
      System.out.println("Exception occurred: STOP");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
	  try {
      System.out.println("checking s4.B183364.Frequencer");
      
	    System.out.println("\"H\" in \"Hi Ho Hi Ho\" appears 4 times. ");
	    frequencerInterfaceFrequencerTest("Hi Ho Hi Ho", "H", 4);

      System.out.println("When length of setSpace() is zero, return 0. ");
      frequencerInterfaceFrequencerTest("", "H", 0);

      System.out.println("When setSpace() is not doing, return 0. ");
      frequencerInterfaceFrequencerTest(null , "H", 0);

      System.out.println("When length of setTarget() is zero, return -1. ");
      frequencerInterfaceFrequencerTest("Hi Ho Hi Ho", "", -1);

      System.out.println("When setSpace() is not doing, return 0. ");
      frequencerInterfaceFrequencerTest("Hi Ho Hi Ho" , null, -1);

      System.out.println("\"H\" in \"Hi Ho Hi Ho\" appears 4 times from 2 to 8. ");
      frequencerInterfaceSubByteFrequencerTest("Hi Ho Hi Ho" , "H", 2, 8, 2);

      System.out.println(" ");
      frequencerInterfaceSubByteFrequencerTest("012345678" , "34", 3, 4, 1);

	  } catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	  }

	  try {
	    System.out.println("checking s4.B183364.InformationEstimator");

      informationEstimatorBlackTest("3210321001230123", "0");

      informationEstimatorBlackTest("3210321001230123", "01");

      informationEstimatorBlackTest("3210321001230123", "0123");

      informationEstimatorBlackTest("3210321001230123", "00");

      informationEstimatorBlackTest("3210321001230123", "00");

      informationEstimatorWhiteTest("3210321001230123", null, 0.0);

      informationEstimatorWhiteTest("3210321001230123", "", 0.0);

      informationEstimatorWhiteTest("", "0", Double.MAX_VALUE);
      
	  }
	  catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	  }
  }
}	    
	    
