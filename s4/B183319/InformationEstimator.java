package s4.B183319; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
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

public class InformationEstimator implements InformationEstimatorInterface{
  // Code to tet, *warning: This code condtains intentional problem*
  byte [] myTarget; // data to compute its information quantity
  byte [] mySpace;  // Sample space to compute the probability
  FrequencerInterface myFrequencer;  // Object for counting frequency

  byte [] subBytes(byte [] x, int start, int end) {
    // corresponding to substring of String for  byte[] ,
    // It is not implement in class library because internal structure of byte[] requires copy.
    byte [] result = new byte[end - start];
    for(int i = 0; i<end - start; i++) { result[i] = x[start + i]; };
    return result;
  }

  // IQ: information quantity for a count,  -log2(count/sizeof(space))
  double iq(int freq) {
    return  - Math.log10((double) freq / (double) mySpace.length)/ Math.log10((double) 2.0);
  }

  public void setTarget(byte [] target) { myTarget = target;}
  public void setSpace(byte []space) {
    myFrequencer = new Frequencer();
    mySpace = space; myFrequencer.setSpace(space);
  }

  public double estimation(){
    //boolean [] partition = new boolean[myTarget.length+1];
    //int np;
    //np = 1<<(myTarget.length-1);
    // System.out.println("np="+np+" length="+myTarget.length);
    double value = Double.MAX_VALUE; // value = mininimum of each "value1".

    double [] tempIQ = new double[myTarget.length];
    //if target is "abcd"
    //tempIQ[0]=Iq(a),tempIQ[1]=Iq(ab),tempIQ[2]=Iq(abc),tempIQ[3]=Iq(abcd)

    //this is O(an)(a is constant)
    //but the run time hardly changes
    for(int i = 0 ;i < myTarget.length ;i++) {
      myFrequencer.setTarget(subBytes(myTarget ,0 ,i + 1));
      //if subByte is "ab"
      //Iq(ab) = iq(frequency() of ab)
      tempIQ[i] = iq(myFrequencer.frequency());
      value = 0.0;
      for(int j = 0 ;j < i ;j++) {
        //if subByte is "ab"
        //compute Iq = tempIQ[0] + iq(frequency() of b)
        //tempIQ[0] = iq(frequency() of a)
        myFrequencer.setTarget(subBytes(myTarget , j + 1, myTarget.length));
        value = tempIQ[j] + iq(myFrequencer.frequency());
        if(value < tempIQ[i]) {
          //tempIQ[i] sets minimam value
          tempIQ[i] = value;
        }
      }
      //value = minimam iq
      value = tempIQ[i];
    }
    /*
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
      int end = 0;
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
        //value1 += iq(myFrequencer.frequency());
        //System.out.println("value1=" + value1);
        start = end;
      }
      // System.out.println(" "+ value1);

      // Get the minimal value in "value"
      if(value1 < value) value = value1;
    }
    */
    return value;
  }

  public static void main(String[] args) {
    InformationEstimator myObject;
    double value;
    long start = System.currentTimeMillis();
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
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
