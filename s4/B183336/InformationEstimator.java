package s4.B183336; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

import java.util.ArrayList;

import s4.specification.FrequencerInterface;
import s4.specification.InformationEstimatorInterface;

/* What is imported from s4.specification
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

public class InformationEstimator implements InformationEstimatorInterface {
	// Code to tet, *warning: This code condtains intentional problem*
	byte[] myTarget; // data to compute its information quantity
	byte[] mySpace; // Sample space to compute the probability
	FrequencerInterface myFrequencer; // Object for counting frequency

	byte[] subBytes(byte[] x, int start, int end) {
		// corresponding to substring of String for byte[] ,
		// It is not implement in class library because internal structure of byte[]
		// requires copy.
		byte[] result = new byte[end - start];
		for (int i = 0; i < end - start; i++) {
			result[i] = x[start + i];
		}
		;
		return result;
	}

	// IQ: information quantity for a count, -log2(count/sizeof(space))
	double iq(int freq) {
		return -Math.log10((double) freq / (double) mySpace.length) / Math.log10((double) 2.0);
	}

	public void setTarget(byte[] target) {
		myTarget = target;
	}

	public void setSpace(byte[] space) {
		myFrequencer = new Frequencer();
		mySpace = space;
		myFrequencer.setSpace(space);
	}

	public double estimation() {
		ArrayList<Double> storeIq = new ArrayList<Double>();
		int start = 0;
		int end = 0;


		double value = Double.MAX_VALUE; // value = mininimum of each "value1".
		double valuetemp;

		for(int i = 0; i < myTarget.length; i++){
			end = i + 1;
			if(i == 0){
				myFrequencer.setTarget(subBytes(myTarget, start, end));
				storeIq.add(i,iq(myFrequencer.frequency()));//add以外でもいける？
			}else{
				for(int j = 0; j < end; j++){
					start = j;
					myFrequencer.setTarget(subBytes(myTarget, start, end));
					valuetemp = iq(myFrequencer.frequency());
					if(start != 0){
						valuetemp = storeIq.get(j-1) + valuetemp;
					}
					if(value > valuetemp){
						value = valuetemp;
					}
				}
				storeIq.add(i, value);
			}

		}

		return storeIq.get(myTarget.length-1);

	}

	public static void main(String[] args) {
		InformationEstimator myObject;
		double value;
		myObject = new InformationEstimator();




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


		// Hi Ho        [0]
    	// Ho			[1]
    	// Ho Hi Ho		[2]
    	//Hi Ho			[3]
    	//Hi Ho Hi Ho	[4]
    	//Ho			[5]
    	//Ho Hi Ho		[6]
    	//i Ho			[7]
    	//i Ho Hi Ho	[8]
    	//o				[9]
    	//o Hi Ho		[10]
		/*
		myObject.setTarget(" Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println("> Hi Ho = " + value);
		myObject.setTarget(" Ho".getBytes());
		value = myObject.estimation();
		System.out.println("> Ho = " + value);
		myObject.setTarget(" Ho Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println("> Ho Hi Ho = " + value);

		myObject.setTarget("Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">Hi Ho = " + value);

		myObject.setTarget("Hi Ho Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">Hi Ho Hi Ho = " + value);

		myObject.setTarget("Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">Ho = " + value);

		myObject.setTarget("Ho Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">Ho Hi Ho = " + value);

		myObject.setTarget("i Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">i Ho = " + value);

		myObject.setTarget("i Ho Hi Ho".getBytes());
		value = myObject.estimation();
		System.out.println(">i Ho Hi Ho = " + value);

		myObject.setTarget("o".getBytes());
		value = myObject.estimation();
		System.out.println(">o = " + value);

		myObject.setTarget("o".getBytes());
		value = myObject.estimation();
		System.out.println(">o Hi Ho = " + value);
		*/

	}
}




