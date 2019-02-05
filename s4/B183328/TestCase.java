package s4.B183328; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;


// interface FrequencerInterface {     // This interface provides the design for frequency counter.
//     void setTarget(byte[]  target); // set the data to search.
//     void setSpace(byte[]  space);  // set the data to be searched target from.
//     int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
//                     //Otherwise, it return 0, when SPACE is not set or Space's length is zero
//                     //Otherwise, get the frequency of TAGET in SPACE
//     int subByteFrequency(int start, int end);
//     // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
//     // For the incorrect value of START or END, the behavior is undefined.
// }
//
//
//
// package s4.specification;
// public interface InformationEstimatorInterface{
//     void setTarget(byte target[]); // set the data for computing the information quantities
//     void setSpace(byte space[]); // set data for sample space to computer probability
//     double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// // It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// // The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// // Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// // Otherwise, estimation of information quantity,
// }


//テストケース
public class TestCase {
    public static void main(String[] args) {

      //case 1 正しい入力の時正しい出力を返す
    	try {
          System.out.print("CASE 1 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "H";             //探索文字
          String space = "Hello world";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 2　targetが空
      try {
          System.out.print("CASE 2 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "";             //探索文字
          String space = "Hello world";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 3　spaceが空
      try {
          System.out.print("CASE 3 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "H";             //探索文字
          String space = "";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 4　targetがセットされていない
      try {
          System.out.print("CASE 4 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "";             //探索文字
          String space = "Hello world";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    // myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 5　spaceがセットされていない
      try {
          System.out.print("CASE 5 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "H";             //探索文字
          String space = "";  //探索文字列

    	    // myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(Double.MAX_VALUE == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 6 targetの方が文字数が多い時
      try {
          System.out.print("CASE 6 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "12345";             //探索文字
          String space = "12";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq) ; }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //ホワイトケース
      //case 7 target が同じ文字で2文字以上(aaなど)の時
      //      かつ，spaceの末尾にtargetが出現するとき
      try {
          System.out.print("CASE 7 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "11";        //探索文字
          String space = "123113311";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq) ; }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 8 target が同じ文字で2文字以上(aaなど)の時
      //      かつ，spaceに2文字以上で同じ文字列が出現するとき(aaaなど)
      try {
          System.out.print("CASE 8 : ");
    	    FrequencerInterface  myObject;
    	    int freq;
    	    // System.out.println("checking s4.B183328.Frequencer");
    	    myObject = new s4.B183328.Frequencer();

          String target = "11";             //探索文字
          String space = "1111111122";  //探索文字列

    	    myObject.setSpace(space.getBytes());
    	    myObject.setTarget(target.getBytes());
    	    freq = myObject.frequency();

    	    if(7 == freq) { System.out.println("OK"); } else {System.out.println("WRONG:freq="+freq) ; }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}





//++++ InformationEstimatorInterface +++++++++++++++++++++++++++++++++++++++++++++++++++
      System.out.println("========");
    	try {
        System.out.println("CASE 1 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
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

      //case 2 targetが空
      try {
          System.out.print("CASE 2 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
    	    myObject.setSpace("3210321001230123".getBytes());
    	    myObject.setTarget("".getBytes());
    	    value = myObject.estimation();
    	    if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG:value="+value ); }
    	}catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 3 spaceが空
      try {
          System.out.print("CASE 3 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
    	    myObject.setSpace("".getBytes());
    	    myObject.setTarget("0".getBytes());
    	    value = myObject.estimation();
    	    if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG:value="+value ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 4 targetがセットされていない
      try {
          System.out.print("CASE 4 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
    	    myObject.setSpace("3210321001230123".getBytes());
    	    // myObject.setTarget("0".getBytes());
    	    value = myObject.estimation();
    	    if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG:value="+value ); }
    	}catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 5 spaceがセットされていない
      try {
          System.out.print("CASE 5 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
    	    // myObject.setSpace("3210321001230123".getBytes());
    	    myObject.setTarget("0".getBytes());
    	    value = myObject.estimation();
    	    if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG:value="+value ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

      //case 6 spaceがセットされていない
      try {
          System.out.print("CASE 6 : ");
    	    InformationEstimatorInterface myObject;
    	    double value;
    	    // System.out.println("checking s4.B183328.InformationEstimator");
    	    myObject = new s4.B183328.InformationEstimator();
          //文字列セット
    	    myObject.setSpace("00000000".getBytes());
    	    myObject.setTarget("1".getBytes());
    	    value = myObject.estimation();
    	    if(Double.MAX_VALUE == value) { System.out.println("OK"); } else {System.out.println("WRONG:value="+value ); }
    	}
    	catch(Exception e) {
    	    System.out.println("Exception occurred: STOP");
    	}

    }
}
