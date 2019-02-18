package s4.B183336; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

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



		Double[] storeIq = new Double[myTarget.length+1];
		int start = 0;
		int end = 0;

		double value = Double.MAX_VALUE;
		double value_t;


		if(myTarget.length==0){
			return 0.0;
		}




		for(int i = 0; i < myTarget.length; i++){
			end = i + 1;
			if(i == 0){
				value = Double.MAX_VALUE;
				myFrequencer.setTarget(subBytes(myTarget, start, end));
				//System.out.println("iq 0  = "+iq(0));
				//System.out.println("iq -1 = "+iq(-1));

				//double result = iq(myFrequencer.frequency());
				//System.out.println(myFrequencer.printTarget()+"は Iq = "+result+"です。"+"また、frequencyは"+myFrequencer.frequency());
				double result = myFrequencer.frequency();
				if(result == 0.0){
					//System.out.println("result = 0通ってます");
					result = Double.MAX_VALUE;
				}else if(result == -1){
					//System.out.println("result = -1通ってます");
					result = 0.0;
				}else{
					result = (iq((int)result));
				}




				storeIq[i] = result;
			}else{
				for(int j = 0; j < end; j++){
					start = j;
					myFrequencer.setTarget(subBytes(myTarget, start, end));
					value_t = myFrequencer.frequency();

					if(value_t == 0.0){
						//System.out.println("result = 0通ってます");
						value_t = Double.MAX_VALUE;
					}else if(value_t == -1){
						//System.out.println("result = -1通ってます");
						value_t =  (Double)0.0;
					}else{
						value_t = (iq((int)value_t));
					}

					if(start != 0){
						value_t = storeIq[j-1] + value_t;
					}
					if(value > value_t){
						value = value_t;
					}
				}
				storeIq[i] = value;
			}

		}

		return storeIq[myTarget.length-1];

	}



	/*

	boolean[] partition = new boolean[myTarget.length + 1];

	ArrayList<Double> st=new ArrayList<Double>();
	int np;
	np = 1 << (myTarget.length - 1);
	// System.out.println("np="+np+" length="+myTarget.length);
	double value = Double.MAX_VALUE; // value = mininimum of each "value1".
	for (int p = 0; p < np; p++) { // There are 2^(n-1) kinds of partitions.
		// binary representation of p forms partition.
		// for partition {"ab" "cde" "fg"}
		// a b c d e f g : myTarget
		// T F T F F T F T : partition:
		partition[0] = true; // I know that this is not needed, but..
		for (int i = 0; i < myTarget.length - 1; i++) {
			partition[i + 1] = (0 != ((1 << i) & p));
		}
		partition[myTarget.length] = true;
		// Compute Information Quantity for the partition, in "value1"
		// value1 = IQ(#"ab")+IQ(#"cde")+IQ(#"fg") for the above example
		double value1 = (double) 0.0;
		int end = 0;
		;
		int start = end;
		while (start < myTarget.length) {
			// System.out.write(myTarget[end]);
			end++;
			;
			while (partition[end] == false) {
				// System.out.write(myTarget[end]);
				end++;
			}
			// System.out.print("("+start+","+end+")");
			myFrequencer.setTarget(subBytes(myTarget, start, end));
			value1 = iq(myFrequencer.frequency());
			for (int i = 0; i < p; i++) {
				value1 += st.get(i);
			}
			start = end;
		}
		// System.out.println(" "+ value1);
		st.add(value1);		//store
		// Get the minimal value in "value"
		if (value1 < value)
			value = value1;
	}
	return value;

	}
	*/


	/*
	boolean [] partition = new boolean[myTarget.length+1];
	int np;
	np = 1<<(myTarget.length-1);
	// System.out.println("np="+np+" length="+myTarget.length);
	double value = Double.MAX_VALUE; // value = mininimum of each "value1".

	for(int p=0; p<np; p++) { // There are 2^(n-1) kinds of partitions.
	    // binary representation of p forms partition.
	    // for partition {"ab" "cde" "fg"}
	    // a b c d e f g   : myTarget
	    // T F T F F T F T : partition:
	    partition[0] = true; // I know that this is not needed, but..
	    for(int i=0; i<myTarget.length -1;i++) {
		partition[i+1] = (0 !=((1<<i) & p));
	    }
	    partition[myTarget.length] = true;

	    // Compute Information Quantity for the partition, in "value1"
	    // value1 = IQ(#"ab")+IQ(#"cde")+IQ(#"fg") for the above example
            double value1 = (double) 0.0;
	    int end = 0;;
	    int start = end;
	    while(start<myTarget.length) {
		// System.out.write(myTarget[end]);
		end++;;
		while(partition[end] == false) {
		    // System.out.write(myTarget[end]);
		    end++;
		}
		// System.out.print("("+start+","+end+")");
		myFrequencer.setTarget(subBytes(myTarget, start, end));
		value1 = value1 + iq(myFrequencer.frequency());
		start = end;
	    }
	    // System.out.println(" "+ value1);

	    // Get the minimal value in "value"
	    if(value1 < value) value = value1;
	}
	return value;

    }

	*/



	public static void main(String[] args) {
		InformationEstimator myObject;
		double value;
		myObject = new InformationEstimator();
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


		myObject.setTarget("9".getBytes());
		value = myObject.estimation();
		System.out.println(">9 " + value);

		myObject.setTarget("".getBytes());
		value = myObject.estimation();
		System.out.println(">" + value);


		myObject.setTarget("11".getBytes());
		value = myObject.estimation();
		System.out.println("11>" + value);


	}
}


