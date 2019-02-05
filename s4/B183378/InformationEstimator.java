package s4.B183378; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

import java.lang.*;
import s4.specification.*;

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
    double[] values;
    
    byte[] subBytes(byte[] x, int start, int end) {
        // corresponding to substring of String for byte[] ,
        // It is not implement in class library because internal structure of byte[]
        // requires copy.
        byte[] result = new byte[end - start];
        for (int i = 0; i < end - start; i++) {
            result[i] = x[start + i];
        }
        return result;
    }
    
    // IQ: information quantity for a count, -log2(count/sizeof(space))
    double iq(int freq) {
        return -Math.log10((double) freq / (double) mySpace.length) / Math.log10((double) 2.0);
    }
    
    double min(double[] value_list) {// 最小値返すだけ
        double min = value_list[0];
        for (int i = 1; i < value_list.length; i++) {
            if (min > value_list[i]) {
                min = value_list[i];
            }
        }
        return min;
    }
    
    double myiq(int n) {
        // nはtargetの文字数である．
        if (n <= 0) {
            return 0.0;
        }
        if (n == 1) {
            return (values[n - 1] = iq(myFrequencer.subByteFrequency(0, 1)));
        }
        if (values[n - 1] >= 0) {
            return values[n - 1];
        }
        
        double[] tmp = new double[n];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = myiq(i) + iq(myFrequencer.subByteFrequency(i, n));
        }
        return (values[n - 1] = min(tmp));
    }
    
    public void setTarget(byte[] target) {
        myTarget = target;
        myFrequencer.setTarget(target);
    }
    
    public void setSpace(byte[] space) {
        myFrequencer = new Frequencer();
        mySpace = space;
        myFrequencer.setSpace(space);
    }
    
    public double estimation() {
        values = new double[myTarget.length];
        for (int i = 0; i < myTarget.length; i++) {
            values[i] = -1.0;
        }
        return (myiq(myTarget.length));
    }
    
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
    }
}
