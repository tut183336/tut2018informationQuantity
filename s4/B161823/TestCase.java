package s4.B161823; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
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


public class TestCase{
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.B161823.Frequencer");
	    myObject = new s4.B161823.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");

	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

        int freq2;
        freq2 = myObject.subByteFrequency( 0, 1);
        System.out.print("\"H\" in \"H\" appears "+ freq2 + " times. ");
        if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

        FrequencerInterface new1Object = new s4.B161823.Frequencer();
        new1Object.setSpace("Hi Ho Hi Ho".getBytes());
        new1Object.setTarget("".getBytes());
        int freq3 = new1Object.frequency();
        System.out.print("When you don't set target and do int freq3 = newObject.frequency() it returns "+freq3+".");
        if(-1 == freq3) { System.out.println("OK"); } else {System.out.println("WRONG"); }

        FrequencerInterface new2Object = new s4.B161823.Frequencer();
        new2Object.setSpace("".getBytes());
        new2Object.setTarget("H".getBytes());
        int freq4 = new2Object.frequency();
        System.out.print("When you don't set SPCE, it return"+freq4+".");
        if(0 == freq4) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B161823.InformationEstimator");
	    myObject = new s4.B161823.InformationEstimator();
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

	    InformationEstimatorInterface TestOb1 = new s4.B161823.InformationEstimator();
	    TestOb1.setSpace("3210321001230123".getBytes());
        TestOb1.setTarget("".getBytes());
        value = TestOb1.estimation();
	    System.out.print("No Target> "+value);
        if(0.0 == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }

        InformationEstimatorInterface TestOb2 = new s4.B161823.InformationEstimator();
	    TestOb2.setSpace("".getBytes());
        TestOb2.setTarget("01".getBytes());
        value = TestOb2.estimation();
	    System.out.print("No Space>01 "+value);
        if(Double.MAX_VALUE == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }

        InformationEstimatorInterface TestOb3 = new s4.B161823.InformationEstimator();
	    TestOb3.setSpace("98998180374087090505060981048971897483798560195981603080574980079834789589278389759798708937489980010270398".getBytes());
        TestOb3.setTarget("01".getBytes());
        value = TestOb3.estimation();
	    System.out.print("Space unreasonably large>01 "+value);

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}

