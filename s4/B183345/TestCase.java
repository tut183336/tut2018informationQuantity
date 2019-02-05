package s4.B183345; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 

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

    public static void testFrequency(byte[] Target, byte[] Space){
	FrequencerInterface myObject;
	int freq;
	
	myObject = new s4.B183345.Frequencer();
	if(Target != null){
	    myObject.setTarget(Target);
	    System.out.print("Target:" + (new String(Target)));
	}else{
	    System.out.print("Target: null");
	}
	System.out.println("");

	if(Space != null){
	    myObject.setSpace(Space);
	    System.out.print("Space:" + (new String(Space)));
	}else{
	    System.out.print("Space: null");
	}
	System.out.println("");

	try{
	    freq = myObject.frequency();
	    System.out.println("freq:" + freq);
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}
    }

    public static void testEstimator(byte[] Target, byte[] Space){
	InformationEstimatorInterface myObject;
	double value;
	
	myObject = new s4.B183345.InformationEstimator();
	if(Target != null){
	    myObject.setTarget(Target);
	    System.out.print("Target:" + (new String(Target)));
	}else{
	    System.out.print("Target: null");
	}
	System.out.println("");

	if(Space != null){
	    myObject.setSpace(Space);
	    System.out.print("Space:" + (new String(Space)));
	}else{
	    System.out.print("Space: null");
	}
	System.out.println("");

	try{
	    value = myObject.estimation();
	    System.out.println("value:" + value);
	}catch(Exception e){
	    System.out.println("Exception occurred: STOP");
	}
    }


    public static void main(String[] args) {
        try {
            FrequencerInterface myObject;
            int freq;
            System.out.println("checking s4.B183345.Frequencer");
            myObject = new s4.B183345.Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
            System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
            if (4 == freq) {
                System.out.println("OK");
            } else {
                System.out.println("WRONG");
            }

	    //Black Box Test//
	    TestCase.testFrequency(null, "H".getBytes());// target is not set. So output is -1.0.
	    TestCase.testFrequency("".getBytes(), "H".getBytes());// target's length is zero. So output is -1.0.
	    TestCase.testFrequency("Hi Ho Hi Ho".getBytes(), null);// space is not set. So output is 0.0.
	    TestCase.testFrequency("Hi Ho Hi Ho".getBytes(), "".getBytes());// space's length is zero. So output is 0.0.
	    TestCase.testFrequency("Hi Ho Hi Ho".getBytes(), "Hi".getBytes());

        } catch (Exception e) {
            System.out.println("Exception occurred: STOP");
        }

        try {
            InformationEstimatorInterface myObject;
            double value;
            System.out.println("checking s4.B183345.InformationEstimator");
            myObject = new s4.B183345.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            System.out.println(">0 " + value);
            myObject.setTarget("01".getBytes());
            value = myObject.estimation();
            System.out.println(">01 " + value);
            myObject.setTarget("0123".getBytes());
            value = myObject.estimation();
            System.out.println(">0123 " + value);
            myObject.setTarget("00".getBytes());
            value = myObject.estimation();
            System.out.println(">00 " + value);

	    //Black Box Test//
	    TestCase.testEstimator(null, "3210321001230123".getBytes());// target is not set. So output is 0.0.
	    TestCase.testEstimator("".getBytes(), "3210321001230123".getBytes());// target's length is zero. So output is 0.0.
	    TestCase.testEstimator("1111111111".getBytes(), "2".getBytes());//true value is infinite. So output is Double.MAX_VALUE.
	    TestCase.testEstimator("0".getBytes(), null);// space is not set. So output is Double.MAX_VALUE.


        } catch (Exception e) {
            System.out.println("Exception occurred: STOP");
        }

    }
}
