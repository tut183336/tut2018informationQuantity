package s4.B183325; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
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

	System.out.println("checking s4.B183325.Frequencer");

	// It returns -1 when TARGET is not set.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("It returns -1 when TARGET is not set.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.frequency();
	    System.out.println("null in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

	// It returns -1 when TARGET's length is zero.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("It returns -1 when TARGET's length is zero.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.println("\"\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

	 // It returns 0 when SPACE is not set.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("It returns 0 when SPACE is not set.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("\"H\" in null appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

	// It returns 0 when SPACE's length is zero.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("It returns 0 when SPACE's length is zero.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("\"H\" in \"\" appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

        // Returns the frequency of TAGET in SPACE.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("Returns the frequency of TAGET in SPACE.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

	// Return 1 when START is 3 snd END is 4.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("Return 1 when START is 3 snd END is 4.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.subByteFrequency(0, 1);
	    System.out.println("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");

	// return 4 when START is 0 snd END is 11.
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("creturn 4 when START is 0 snd END is 11.");
	    myObject = new s4.B183325.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.subByteFrequency(0, 11);
	    System.out.println("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	System.out.println("");
	
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B183325.InformationEstimator");
	    myObject = new s4.B183325.InformationEstimator();
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

    }
}
