package s4.B183385; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specificaion.*;

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
	    System.out.println("checking s4.B183385.Frequencer");
	    myObject = new s4.B183385.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
		freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

		myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
		freq = myObject.frequency();
	    System.out.print("\"x\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

		myObject.setSpace("xxxxxxx".getBytes());
	    myObject.setTarget("H".getBytes());
		freq = myObject.frequency();
	    System.out.print("\"H\" in \"xxxxxx\" appears "+freq+" times. ");
		if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
		
		//When TARGET's length is zero
		System.out.println("\nWhen TARGET's length is zero");
		myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
			
		//When SPACE's length is zero
		System.out.println("\nWhen SPACE's length is zero");
		myObject.setSpace("".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"\" appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    FrequencerInterface  myObject;
		int freq;
		System.out.println("\nWhen TARGET is not set");
	    //System.out.println("checking s4.B183385.Frequencer");
	    myObject = new s4.B183385.Frequencer();

		//When TARGET is not set
		myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"(TARGET is not set)\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    FrequencerInterface  myObject;
		int freq;
		System.out.println("\nWhen SPACE is not set");
	    //System.out.println("checking s4.B183385.Frequencer");
	    myObject = new s4.B183385.Frequencer();
		//When SPACE is not set
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"(SPACE is not set)\" appears "+freq+" times. ");
		if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
		
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}



	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("\nchecking s4.B183385.InformationEstimator ");
	    myObject = new s4.B183385.InformationEstimator();
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


	try {
	    InformationEstimatorInterface myObject;
		double value;    
		myObject = new s4.B183385.InformationEstimator();
		//It returns 0.0 when the TARGET is not set or TARGET's length is zero;
		System.out.println("\nWhen TARGET is not set or TARGET's length is zero (should return 0)");
		myObject.setSpace("3210321001230123".getBytes());
		myObject.setTarget("xx".getBytes());
	    value = myObject.estimation();
		System.out.println(">xx "+value);
		
	    myObject.setTarget("".getBytes());
	    value = myObject.estimation();
		System.out.println("> "+value);

		//It returns Double.MAX_VALUE when the true value is infinite, or SPACE is not set.		
		System.out.println("\nWhen SPACE is not set (should return Double.MAX_VALUE)");
		myObject.setSpace("".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(">0 "+value);

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}	    
	    
