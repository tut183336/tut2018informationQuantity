package s4.B183336; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

/*
interface FrequencerInterface {     // このインタフェースは、周波数カウンタの設計を提供します。
    void setTarget(byte[]  target); // サーチするデータをセットする。
    void setSpace(byte[]  space);  // 検索対象のスペースをセットする。
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
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.B183336.Frequencer");
	    myObject = new s4.B183336.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	    //ここから追加分
	    //ターゲットがセットされていないか、ターゲットの長さが0の時、-1を返す。
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    if(freq == -1) { System.out.println("OK"); } else {System.out.println("WRONG"); }


	    FrequencerInterface myObject2 = new s4.B183336.Frequencer();
	    myObject2.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject2.frequency();
	    if(freq == -1) { System.out.println("OK"); } else {System.out.println("WRONG"); }


	    //スペースがセットされていない、スペースの長さが0の時、0を返す。
	    myObject.setTarget("H".getBytes());
	    myObject.setSpace("".getBytes());
	    freq = myObject.frequency();
	    if(freq == 0) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	    FrequencerInterface myObject3 = new s4.B183336.Frequencer();
	    myObject3.setTarget("H".getBytes());
	    freq = myObject3.frequency();
	    if(freq == 0) { System.out.println("OK"); } else {System.out.println("WRONG"); }



	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B183336.InformationEstimator");
	    myObject = new s4.B183336.InformationEstimator();
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
	    InformationEstimatorInterface myObject2 = new s4.B183336.InformationEstimator();
	    myObject2.setSpace("3210321001230123".getBytes());
	    value = myObject2.estimation();

	    if(value == 0.0) { System.out.println("OK"); } else {System.out.println("WRONG");}

	    //もしくはターゲットの長さが0の時は0.0
	    myObject.setTarget("".getBytes());
	    value = myObject.estimation();

	    if(value == 0.0) { System.out.println("OK"); } else {System.out.println("WRONG");}

	    // スペースが設定されていないときには、Double.MAX_VALUEを返す。
	    InformationEstimatorInterface myObject3 = new s4.B183336.InformationEstimator();
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

