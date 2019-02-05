package s4.B183318; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
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

	/*
--------------- int subByteFrequency(int start, int end) ------------------
	*/

	System.out.println("\n------------------------------------");	
	System.out.println("int frequency()\n");

    /*
    CASE1 :
    check if frequency() returns correct number
    when the all arguments are correctly given
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 1\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("I am a student.".getBytes());
	    myObject.setTarget("a".getBytes());
	    freq = myObject.frequency();
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE2 :
    check if frequency() returns correct number 
    when the SPACE repeats the same letters
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 2\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("ssss".getBytes());
	    myObject.setTarget("ss".getBytes());
	    freq = myObject.frequency();
	    if(3 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 3:
    check if frequency() returns -1 when 
    TARGET is not set
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 3\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("I am a student.".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 4:
    check if frequency() returns -1 when 
    TARGET's length is zero
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 4\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("I am a student.".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 5:
    check if frequency() returns 0 when 
    SPACE is not set
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 5\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setTarget("A".getBytes());
	    freq = myObject.frequency();
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 6:
    check if frequency() returns 0 when 
    Space's length is zero
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 6\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("A".getBytes());
	    freq = myObject.frequency();
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 7:
    check if frequency() returns -1 when 
    Space's length is zero and Target's length is zero
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 7\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 8:
    check if frequency() returns -1 when 
    Space's length is zero and Target is not set
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 8\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 9:
    check if frequency() returns -1 when 
    Space is not set and Target's length is not set
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 9\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 10:
    check if frequency() returns -1 when 
    Both Space and Target is not set
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 10\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("".getBytes());
	    freq = myObject.frequency();
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}


	/*
--------------- int subByteFrequency(int start, int end) ------------------
	*/

	System.out.println("\n------------------------------------");	
	System.out.println("int subByteFrequency(int start, int end)\n");

    /*
    CASE1 :
    check if subByteFrequency() returns correct number
    when the all arguments are correctly given
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 1\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("I am a student.".getBytes());
	    myObject.setTarget("a".getBytes());
	    freq = myObject.subByteFrequency(0,1);
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE2 :
    check if subByteFrequency() returns correct number
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 2\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("ssssss".getBytes());
	    myObject.setTarget("s".getBytes());
	    freq = myObject.subByteFrequency(0,1);
 		if(6 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE3 :
    check if the programer realised the mistake of the argument name 
    (int start, int end) [not (int start,int length)]
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 3\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("0123456789".getBytes());
	    myObject.setTarget("34".getBytes());

	    freq = myObject.subByteFrequency(0,2);
 		if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG: Check the instruction again."); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}


	/*
--------------- int subByteFrequency(int start, int end) ------------------
	*/

	System.out.println("\n------------------------------------");	
	System.out.println("double estimation()\n");

    /*
    CASE 1:
    check if estimation() returns 0 when 
    every each letter and the tartget are the same 
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 1\t: ");
	    myObject = new s4.B183318.InformationEstimator();
	    myObject.setSpace("000000".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 2:
    check if estimation() returns Double.MAX_VALUE when 
    none of the letters are the same with the target
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 2\t: ");
	    myObject = new s4.B183318.InformationEstimator();
	    myObject.setSpace("000000".getBytes());
	    myObject.setTarget("1".getBytes());
	    value = myObject.estimation();
	    if(Double.MAX_VALUE == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 3:
    check if estimation() returns Double.MAX_VALUE when 
    SPACE is not set
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 3\t: ");
	    myObject = new s4.B183318.InformationEstimator();
	    myObject.setTarget("1".getBytes());
	    value = myObject.estimation();
	    if(Double.MAX_VALUE == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 4:
    check if estimation() returns Double.MAX_VALUE when 
    SPACE's length is zero
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 4\t: ");
	    myObject = new s4.B183318.InformationEstimator();
		myObject.setSpace("".getBytes());
	    myObject.setTarget("1".getBytes());
	    value = myObject.estimation();
	    if(Double.MAX_VALUE == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 5:
    check if estimation() returns Double.MAX_VALUE when 
    target's length is zero
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 5\t: ");
	    myObject = new s4.B183318.InformationEstimator();
		myObject.setSpace("123456789".getBytes());
	    myObject.setTarget("".getBytes());
	    value = myObject.estimation();
	    if(0 == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    /*
    CASE 6:
    check if estimation() returns Double.MAX_VALUE when 
    target is not set
    */
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.print("case 6\t: ");
	    myObject = new s4.B183318.InformationEstimator();
		myObject.setSpace("123456789".getBytes());
	    value = myObject.estimation();
	    if(0 == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	/*
--------------- ホワイトボックステスト ------------------
	*/

	System.out.println("\n------------------------------------");	
	System.out.println("ホワイトボックステスト\n");


    /*
    CASE1 :
    スペースの最後の文字とターゲットの最初の字が部分的に一致すると，配列をオーバーする．
    */
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.print("case 1\t: ");
	    myObject = new s4.B183318.Frequencer();
	    myObject.setSpace("aaaaa".getBytes());
	    myObject.setTarget("aa".getBytes());
	    freq = myObject.frequency();
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}


    }
}	    
	    
