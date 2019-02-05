package s4.B183311; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 

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
		FrequencerInterface myObject;
		int freq;
		System.out.println("---- checking s4.B183311.Frequencer ----");

		try {
			// TARGETが設定されていない
			System.out.print("TARGET is not set:");
			myObject = new s4.B183311.Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			freq = myObject.frequency();
			if (freq == -1) {
				System.out.println("-1 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// TARGETの長さがゼロ
			System.out.print("TARGET's length is zero:");
			myObject = new s4.B183311.Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("".getBytes());
			freq = myObject.frequency();
			if (freq == -1) {
				System.out.println("-1 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// SPACEが設定されていない
			System.out.print("SPACE is not set:");
			myObject = new s4.B183311.Frequencer();
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			if (freq == 0) {
				System.out.println("0 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// SPACEの長さがゼロ
			System.out.print("SPACE's length is zero:");
			myObject = new s4.B183311.Frequencer();
			myObject.setSpace("".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			if (freq == 0) {
				System.out.println("0 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			myObject = new s4.B183311.Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
			if (4 == freq) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		InformationEstimatorInterface myObject2;
		double value;
		System.out.println("\n---- checking s4.B183311.InformationEstimator ----");

		try {
			// TARGETが設定されていない
			System.out.print("TARGET is not set:");
			myObject2 = new s4.B183311.InformationEstimator();
			myObject2.setSpace("3210321001230123".getBytes());
			value = myObject2.estimation();
			if (value == 0.0) {
				System.out.println("0.0 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// TARGETの長さがゼロ
			System.out.print("TARGET's lengsth is zero:");
			myObject2 = new s4.B183311.InformationEstimator();
			myObject2.setSpace("3210321001230123".getBytes());
			myObject2.setTarget("".getBytes());
			value = myObject2.estimation();
			if (value == 0.0) {
				System.out.println("0.0 OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// SPACEが設定されていない
			System.out.print("SPACE is not set:");
			myObject2 = new s4.B183311.InformationEstimator();
			myObject2.setTarget("00".getBytes());
			value = myObject2.estimation();
			if (value == Double.MAX_VALUE) {
				System.out.println("Double.MAX_VALUE OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			// SPACEの長さがゼロ
			System.out.print("SPACE's length is zero:");
			myObject2 = new s4.B183311.InformationEstimator();
			myObject2.setSpace("".getBytes());
			myObject2.setTarget("00".getBytes());
			value = myObject2.estimation();
			if (value == 0.0) {
				System.out.println("Double.MAX_VALUE OK!");
			} else {
				System.out.println("Failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

		try {
			myObject2 = new s4.B183311.InformationEstimator();
			myObject2.setSpace("3210321001230123".getBytes());
			myObject2.setTarget("0".getBytes());
			value = myObject2.estimation();
			System.out.println(">0 " + value);
			myObject2.setTarget("01".getBytes());
			value = myObject2.estimation();
			System.out.println(">01 " + value);
			myObject2.setTarget("0123".getBytes());
			value = myObject2.estimation();
			System.out.println(">0123 " + value);
			myObject2.setTarget("00".getBytes());
			value = myObject2.estimation();
			System.out.println(">00 " + value);
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}

	}
}
