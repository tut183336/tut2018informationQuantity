package s4; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import s4.specification.FrequencerInterface;
import s4.specification.InformationEstimatorInterface;

/*
interface FrequencerInterface {     // このインタフェースは、周波数カウンタの設計を提供します。
    void setTarget(byte[] target); // サーチするデータをセットする。
    void setSpace(byte[] space);  // 検索対象のスペースをセットする。
    int frequency(); //ターゲットがセットされていないか、ターゲットの長さが0の時、-1を返す。
                    //スペースがセットされていない、スペースの長さが0の時、0を返す。
                    //それ以外の時、frequencyを返す。
    int subByteFrequency(int start, int end);
    // ターゲットのサブバイトの頻度を取得する。i.e target[start], target[start+1], ... , target[end-1].
    // STARTまたはENDの値が正しくない場合の動作は未定義です。
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // 情報量を算出するためのデータを設定する
    void setSpace(byte space[]); //サンプル空間のデータをコンピュータ確率に設定する
    double estimation(); // ターゲットがないか、ターゲットの長さ0の時、0を返す。
// 真値が無限大の場合、またはスペースが設定されていない場合はDouble.MAX_VALUEを返します。
// 真値が有限でDouble.MAX_VALUEより大きい場合の動作は未定義です。
// これは、スペースが不当に大きい場合にのみ発生することに注意してください。 とにかく他の問題に遭遇します。
// そうでなければ、情報量の推定だけを行う。
}
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface myObject;
	    int freq;
	    System.out.println("checking s4.B183336.Frequencer");
	    myObject = new s4.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
    //ここから追加分

    //ターゲットがセットされていないか、ターゲットの長さが0の時、-1を返す。
	try {
	    FrequencerInterface myObject;
	    int freq;
	    System.out.println("checking s4.B183336.Frequencer");
	    myObject = new s4.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    if(freq == -1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try{
	    FrequencerInterface myObject;
	    int freq;
	    System.out.println("checking s4.B183336.Frequencer");
	    myObject = new s4.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.frequency();
	    if(freq == -1) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}


	//スペースがセットされていない、スペースの長さが0の時、0を返す。
	try{
	    FrequencerInterface myObject = new s4.Frequencer();
	    int freq;
	    System.out.println("checking s4.B183336.Frequencer");
		myObject.setTarget("H".getBytes());
	    myObject.setSpace("".getBytes());
	    freq = myObject.frequency();
	    if(freq == 0) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}


	try{
	    FrequencerInterface myObject = new s4.Frequencer();
	    int freq;
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    if(freq == 0) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	//12/18追加分

	System.out.println("12/18追加分");

    //28行目 for文の見る範囲は、ターゲットの長さを引いてあげる必要がある。例えば、ターゲットの長さが2の時、最後の1文字を見る必要はない。
	//OutOfBoundsが起こる可能性がある。
	try {
	    FrequencerInterface myObject = new s4.Frequencer();
	    int freq;
	    System.out.println("checking s4.B183353.Frequencer");
	    myObject.setSpace("AAAAAA".getBytes());
	    myObject.setTarget("AA".getBytes());
	    freq = myObject.frequency();
	    if(freq == 5) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	    System.err.println(e);
	}

    //SpaceよりTarget配列の方が大きい場合
	//outofbounds

	try{
	    FrequencerInterface myObject = new s4.Frequencer();
	    int freq;
	    System.out.println("checking s4.B183353.Frequencer");
	    myObject.setSpace("ho".getBytes());
	    myObject.setTarget("hogehoge".getBytes());
	    freq = myObject.frequency();
	    if(freq == 0){ System.out.println("OK"); } else {System.out.println("WRONG"); }

	}
	catch(Exception e) {
		System.out.println("Exception occurred: STOP");
		System.err.println(e);
	}

	//






	System.out.println("以上");





	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B183336.InformationEstimator");
	    myObject = new s4.InformationEstimator();
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

	    //ここから追加分



	    //ターゲットがセットされていないなら、0.0を返す。
	    InformationEstimatorInterface myObject2 = new s4.InformationEstimator();
	    myObject2.setSpace("3210321001230123".getBytes());
	    value = myObject2.estimation();

	    if(value == 0.0) { System.out.println("OK"); } else {System.out.println("WRONG");}

	    //もしくはターゲットの長さが0の時は0.0
	    myObject.setTarget("".getBytes());
	    value = myObject.estimation();

	    if(value == 0.0) { System.out.println("OK"); } else {System.out.println("WRONG");}

	    // スペースが設定されていないときには、Double.MAX_VALUEを返す。
	    InformationEstimatorInterface myObject3 = new s4.InformationEstimator();
	    myObject3.setTarget("0".getBytes());
	    value = myObject3.estimation();

	    if(value == Double.MAX_VALUE) { System.out.println("OK"); } else {System.out.println("WRONG");}

	    //真値が無限大である、Double.MAX_VALUEを返す。間違っている？

	    myObject.setSpace("".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();

	    if(value == Double.MAX_VALUE) { System.out.println("OK"); } else {System.out.println("WRONG");}
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}

