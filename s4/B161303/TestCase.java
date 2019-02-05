package s4.B161303; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
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
	  CASE 1
	  チェック項目：Space内のTargetの頻度を返すかどうか
	*/

	
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 1:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	

	/*
	  CASE 2
	  チェック項目：Targetに何もセットされていないとき,frequency()が-1を返すかどうか 
	*/

	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 2:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	/*
	  CASE 3
	  チェック項目：Targetの長さが0の時,frequency()が-1を返すかどうか
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 3:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("a".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"a\" in \"\" appears "+freq+" times. ");
	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	/*
	  CASE 4
	  チェック項目：Spaceに何もセットされない時,0を返すかどうか
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 4:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
      	/*
	  CASE 5
	  チェック項目：Spaceの長さが0の時,0を返すかどうか
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 5:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	/*
	  CASE 6
	  チェック項目：TargetとSpaceに何もセットしない場合に0を返すか
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 6:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	/*
	  CASE 7
	  チェック項目：TargetとSpaceの長さが0の場合に0を返すか
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 7:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"\" in \"\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	/*
	  CASE 8
	  チェック項目：配列内に日本語を使用してもfrequency()が正しく機能するか(環境によってエラーが出る場合がある)
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("CASE 8:checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("はい ほ はい ほ".getBytes());
	    myObject.setTarget("は".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"は\" in \"はい ほ はい ほ\" appears "+freq+" times. ");
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	/*
	  Frequencer.javaの問題点:
	  Spaceの最後の文字とTargetの最初の文字が一致しており、Targetの文字数が複数の場合を考える.
	  この時,Targetの配列がSpaceの配列を超えてしまうためエラーが起きる.
	  例:
	  Space: aiueo
	  Target     oi ←iが超えている
	*/
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.B161303.Frequencer");
	    myObject = new s4.B161303.Frequencer();
	    myObject.setSpace("aiueo".getBytes());
	    myObject.setTarget("oi".getBytes());
	    freq = myObject.frequency();
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
	
	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B161303.InformationEstimator");
	    myObject = new s4.B161303.InformationEstimator();
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
	    
