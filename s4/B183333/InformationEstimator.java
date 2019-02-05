package s4.B183333; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

public class InformationEstimator implements InformationEstimatorInterface{
    byte [] myTarget; // 情報量計算用
    byte [] mySpace;  // 確率計算用の標本空間
    FrequencerInterface myFrequencer;  // 頻度をカウントする用のオブジェクト
    double [] value;    // 情報量の最小値を格納する用

    byte [] subBytes(byte [] x, int start, int end) {
    	// byte []のStringの部分文字列に対応します。
        //byte[]の内部構造にはコピーが必要なため、クラスライブラリには実装されていません。
        byte [] result = new byte[end - start];
    	for( int i = 0 ; i < end - start ; i++ ) {
            result[i] = x[start + i];
        };
    	return result;
    }

    //ip : 情報量をカウントするよう -log2(count/sizeof(space))
    double iq(int freq) {
	       return -Math.log10((double) freq / (double) mySpace.length) / Math.log10((double) 2.0);
    }

    // 情報量を計算するためのデータを設定する
    // Targetの設定
    public void setTarget(byte [] target) {
        myTarget = target;
        myFrequencer.setTarget(target);
    }

    // サンプル空間のデータを確率に設定する
    // 元の文字列の設定(?)
    public void setSpace(byte []space) {
        myFrequencer = new Frequencer();
        mySpace = space;
        myFrequencer.setSpace(space);
    }

    // 情報量の最小値を返す
    // iq_min：information quantity minimum
    double iq_min(double [] min){
        double iq_min = min[0];
        for( int i = 1 ; i < min.length ; i++ ){
            if( iq_min > min[i] ){
                iq_min = min[i];
            }
        }
        return iq_min;
    }

    // 情報量の最小値を格納する用のメソッド
    double iqk(int num){
        // 文字列の長さが0以下の時0.0を返す
        if( num <= 0 ){
            return 0.0;
        }
        // 文字列の長さが1の時情報量を計算し返す
        if( num == 1 ){
            value[num-1] = iq(myFrequencer.subByteFrequency(0, 1));
            return value[num-1];
        }
        // 文字列の長さが0以上の時そのまま返す
        if( value[num-1] >= 0 ){
            return value[num-1];
        }

        double [] tmp = new double[num]; // 最小値の場所を格納する用

        for( int i = 0 ; i < tmp.length ; i++ ){
            tmp[i] = iqk(i) + iq(myFrequencer.subByteFrequency(i, num));
        }
        value[num-1] = iq_min(tmp); // 情報量の最小値の格納
        return value[num-1];
    }

    //
    public double estimation(){
        // 作ったやつ
        value = new double[myTarget.length];
        for( int i = 0 ; i < myTarget.length ; i++ ){
            value[i] = -1.0;
        }
        return iqk(myTarget.length);
    }

    public static void main(String[] args){
    	InformationEstimator myObject;
    	double value;
    	myObject = new InformationEstimator();
    	myObject.setSpace("3210321001230123".getBytes());

    	myObject.setTarget("0".getBytes());
    	value = myObject.estimation();
    	System.out.println(">0 "+value);
        System.out.println();

    	myObject.setTarget("01".getBytes());
    	value = myObject.estimation();
    	System.out.println(">01 "+value);
        System.out.println();

    	myObject.setTarget("0123".getBytes());
    	value = myObject.estimation();
    	System.out.println(">0123 "+value);
        System.out.println();

    	myObject.setTarget("00".getBytes());
    	value = myObject.estimation();
    	System.out.println(">00 "+value);
    }
}
