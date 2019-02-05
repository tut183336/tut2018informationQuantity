package s4.B183360; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
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
    	FrequencerInterface  myObject;
    	System.out.println("checking s4.B183360.Frequencer");

    try {	/*正常に動作する*/
		System.out.println("");
		System.out.println("正常に動作する");
	    int freq;
	    //int subFreq;
	    //System.out.println("checking s4.B183360.Frequencer");
	    myObject = new s4.B183360.Frequencer();

	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("＜ホワイトボックステスト＞");
	    System.out.print("s4.B183360.Frequencer.frequency()メソッドの戻り値:" +freq+ "\n");
	    System.out.println("＜ブラックボックステスト＞");
	    System.out.print("入力:文字列は”Hi Ho Hi Ho”にセットする, ターゲットは”H”にセットする\n");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	    /*
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	    subFreq = myObject.subByteFrequency(2,8);
      	System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+subFreq+" times from 2 to 8. ");
      	if(2 == subFreq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
      	*/
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {	/*TARGET's length is zero.*/
		System.out.println("");
		System.out.println("① This returns -1 when TARGET's length is zero.");
	    int freq;
	    myObject = new s4.B183360.Frequencer();

	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.println("＜ホワイトボックステスト＞");
	    System.out.print("s4.B183360.Frequencer.frequency()メソッドの戻り値:" +freq+ "\n");
	    System.out.println("＜ブラックボックステスト＞");
	    System.out.print("入力:文字列は”Hi Ho Hi Ho”にセットする, ターゲットの長さはゼロ\n");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {	/*TARGET is not set.*/
		System.out.println("");
		System.out.println("② This returns -1 when TARGET is not set.");
	    int freq;
	    myObject = new s4.B183360.Frequencer();

	    /*TARGET's length is zero.*/
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    //myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.println("＜ホワイトボックステスト＞");
	    System.out.print("s4.B183360.Frequencer.frequency()メソッドの戻り値:" +freq+ "\n");
	    System.out.println("＜ブラックボックステスト＞");
	    System.out.print("入力:文字列は”Hi Ho Hi Ho”にセットする, ターゲットはセットしない\n");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {	/*SPACE's length is zero.*/
		System.out.println("");
		System.out.println("③ This returns -1 when SPACE's length is zero.");
	    int freq;
	    myObject = new s4.B183360.Frequencer();

	    /*TARGET's length is zero.*/
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("＜ホワイトボックステスト＞");
	    System.out.print("s4.B183360.Frequencer.frequency()メソッドの戻り値:" +freq+ "\n");
	    System.out.println("＜ブラックボックステスト＞");
	    System.out.print("入力:文字列””にセットする, ターゲットは”H”にセットする\n");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {	/*SPACE is not set.*/
		System.out.println("");
		System.out.println("④ This returns 0 when SPACE is not set.");
	    int freq;
	    myObject = new s4.B183360.Frequencer();

	    /*TARGET's length is zero.*/
	    //myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("＜ホワイトボックステスト＞");
	    System.out.print("s4.B183360.Frequencer.frequency()メソッドの戻り値:" +freq+ "\n");
	    System.out.println("＜ブラックボックステスト＞");
	    System.out.print("入力:文字列はセットしない, ターゲットは”H”にセットする\n");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    InformationEstimatorInterface myObject2;
	    double value;
	    System.out.println("checking s4.B183360.InformationEstimator");
	    myObject2 = new s4.B183360.InformationEstimator();
	    myObject2.setSpace("3210321001230123".getBytes());
	    myObject2.setTarget("0".getBytes());
	    value = myObject2.estimation();
	    System.out.println(">0 "+value);
	    myObject2.setTarget("01".getBytes());
	    value = myObject2.estimation();
	    System.out.println(">01 "+value);
	    myObject2.setTarget("0123".getBytes());
	    value = myObject2.estimation();
	    System.out.println(">0123 "+value);
	    myObject2.setTarget("00".getBytes());
	    value = myObject2.estimation();
	    System.out.println(">00 "+value);
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}	    
	    
