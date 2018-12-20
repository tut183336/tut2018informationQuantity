package s4.B183336; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

/*

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

public class Frequencer implements FrequencerInterface{
    // Code to Test, *warning: This code  contains intentional problem*
    byte [] myTarget;
    byte [] mySpace;
    public void setTarget(byte [] target) { myTarget = target;}
    public void setSpace(byte []space) { mySpace = space; }
    public int frequency() {
		int targetLength = myTarget.length;
		int spaceLength = mySpace.length;
		int count = 0;
			for(int start = 0; start < spaceLength; start++) { // Is it OK?
	   			boolean abort = false;
	    		for(int i = 0; i<targetLength; i++) {
					if(myTarget[i] != mySpace[start+i]) { 
						abort = true;
						break;
					}
	    		}
	    		if(abort == false) {
					count++; 
				}
			}
		return count;
    }

    // I know that here is a potential problem in the declaration.
    public int subByteFrequency(int start, int length) {
	// Not yet, but it is not currently used by anyone.
	return -1;
    }

    public static void main(String[] args) {
	Frequencer myObject;
	int freq;
	try {
	    System.out.println("checking my Frequencer");
	    myObject = new Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
    }
}

