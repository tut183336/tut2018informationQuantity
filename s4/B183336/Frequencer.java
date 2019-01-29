package s4; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.util.Arrays;

import s4.specification.FrequencerInterface;
/*

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
public class Frequencer implements FrequencerInterface{
    // Code to start with: This code is not working, but good start point to work.
    byte [] myTarget;
    byte [] mySpace;
    boolean targetReady = false;
    boolean spaceReady = false;
    int []  suffixArray;
    // The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
    // Each suffix is expressed by a integer, which is the starting position in mySpace.
    // The following is the code to print the variable

    public void printSuffixArray() {
    	if(spaceReady) {
            for(int i=0; i< mySpace.length; i++) {
                int s = suffixArray[i];
                for(int j=s;j<mySpace.length;j++) {
                    System.out.write(mySpace[j]);
                }
                System.out.write('\n');
            }
        }
    }
    private int suffixCompare(int i, int j) {
        // comparing two suffixes by dictionary order.
        // i and j denoetes suffix_i, and suffix_j
        // if suffix_i > suffix_j, it returns 1
    	// if suffix_i < suffix_j, it returns -1
    	// if suffix_i = suffix_j, it returns 0;
    	// It is not implemented yet,
    	// It should be used to create suffix array.
    	System.out.println("#############################################");
    	System.out.println("i = "+i);
    	System.out.println("j = "+j);

    	//1/23更新 Stringでは無くて、byte型で実行できるようにする。


    	String mySpaceS = new String(mySpace);



    	//myspace長さ取得
    	int mySpaceSLength = mySpaceS.length();

    	int mySpaceLength = mySpace.length;

		//System.out.println("myspaceSlength"+mySpaceSLength);
		//System.out.println("myspaceblength"+mySpaceLength);

    	//suffixのiと、jを作成。
    	String suffix_i = mySpaceS.substring(i);
    	String suffix_j = mySpaceS.substring(j);

    	byte[] suffix_id = Arrays.copyOfRange(mySpace,i,mySpace.length+1);
    	byte[] suffix_jd = Arrays.copyOfRange(mySpace,j,mySpace.length+1);

    	//System.out.println("suffix_i = "+suffix_i);
    	//System.out.println("suffix_j = "+suffix_j);

    	//String test_i = new String(suffix_id);
    	//String test_j = new String(suffix_jd);


    	//System.out.println("suffix_id = "+test_i);
    	//System.out.println("suffix_jd = "+test_j);


    	//byte[] suffix_i = mySpace.





    	//suffixのiと、jの長さを作成。
    	//int suffix_i_length = suffix_i.length();
    	//int suffix_j_length = suffix_j.length();

    	//byte
    	int suffix_id_length = suffix_id.length-1;
    	int suffix_jd_length = suffix_jd.length-1;





    	//System.out.println("suffix_id_length = "+ suffix_id.length);
    	//System.out.println("suffix_jd_length = "+ suffix_jd.length);



    	//byte
    	for(int k = 0;k < mySpaceLength;k++){
    		System.out.println("k = "+k);

    		byte targeti =0;
    		byte targetj =0;

    		boolean emptyi = true;
    		boolean emptyj = true;

    		if(k != suffix_id_length){
    			targeti = suffix_id[k];

    			char test = (char) targeti;//なんか他にやり方ありそう。
    			//System.out.println("target i = "+targeti + " moji ="+ test);
    		}else{
    			//System.out.println("iがないよ。byte");
    			emptyi = false;
    		}

    		if(k != suffix_jd_length){
    			targetj = suffix_jd[k];

    			char test = (char) targetj;//なんか他にやり方ありそう。
    			//System.out.println("target j = "+targetj + " moji ="+ test);
    			//System.out.println("test = "+test);
    		}else{
    			//System.out.println("jがないよ。byte");
    			emptyj = false;
    		}


    		//どちらもない場合
    		if(emptyi == false && emptyj==false){
    			return 0;
    		}
    		//i true ver
    		if(emptyi == true && emptyj==false){
    			return 1;
    		}
    		//j true ver
    		if(emptyi == false && emptyj ==true){
    			return -1;
    		}

        	int judge = targeti-targetj;


        	//compareToは、その文字の差を出すモノでaとcであれば2を返す。



        	System.out.println("judge =" +judge );

        	if(judge < 0){
        		return -1;
        	}else if(judge > 0){
        		return 1;
        	}

        	/*
    		char testa = 'a';
    		char testc = 'c';

    		byte btesta = (byte)testa;
    		byte btestc = (byte)testc;

    		System.out.println("btesta = "+ btesta);
    		System.out.println("btestc = "+ btestc);

    		int result = btesta - btestc;

    		System.out.println("result = "+ result);
    		*/
    	}

    	//string
    	/*
    	for(int k = 0;k < mySpaceSLength;k++){
    		//System.out.println("k ="+k);

    		//i,jのチェック
    		String targeti = null;
    		String targetj = null;


    		//emptyチェック
    		boolean emptyi = true;
    		boolean emptyj = true;



    		if(k != suffix_i_length){
    			targeti = suffix_i.substring(k, k+1);
    			//System.out.println("target i = "+targeti);
    		}else{
    			targeti = null;
    			//System.out.println("iないよ");
    			emptyi = false;
    		}

    		if(k != suffix_j_length){
    			targetj = suffix_j.substring(k, k+1);
    			//System.out.println("target j = "+targetj);
    		}else{
    			targetj = null;
    			//System.out.println("jないよ");
    			emptyj = false;
    		}

    		//どちらもない場合
    		if(emptyi == false && emptyj==false){
    			return 0;
    		}
    		//i true ver
    		if(emptyi == true && emptyj==false){
    			return 1;
    		}
    		//j true ver
    		if(emptyi == false && emptyj ==true){
    			return -1;
    		}


        	int judge = targeti.compareTo(targetj);

        	//compareToは、その文字の差を出すモノでaとcであれば2を返す。



        	//System.out.println("judge =" +judge );

        	if(judge < 0){
        		return -1;
        	}else if(judge > 0){
        		return 1;
        	}
    	}
    	*/

    	return 0;

    	//1文字目が一致していた場合、2文字目を比較、ここで比較できるなら、その比較結果による。

    	//p1 どっちも文字がある
    			//繰り返し
    	//p2 どっちもない
    			//おわり、
    	//p3 どっちかが終端記号 どっちかはある
    			//ある方が大きい。

    	// Example of dictionary order


    	// "i"      <  "o"        : compare by code
    	// "Hi"     <  "Ho"       ; if head is same, compare the next element
    	// "Ho"     <  "Ho "      ; if the prefix is identical, longer string is big
    	//
    	// ****  Please write code here... ***


    }
    public void setSpace(byte []space) {
    	mySpace = space;
    	if(mySpace.length>0) {
    		spaceReady = true;
    	}

    	suffixArray = new int[space.length];
    	// put all suffixes  in suffixArray. Each suffix is expressed by one integer.
    	for(int i = 0; i< space.length; i++) {
            suffixArray[i] = i;
    	}

        for (int i = suffixArray.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (suffixCompare(suffixArray[j],suffixArray[j+1]) == 1) {
                    int tmpNum = suffixArray[j];
                    suffixArray[j] = suffixArray[j+1];
                    suffixArray[j+1] = tmpNum;
                    //System.out.println("xxxxxxxxxxxxxxxxxxxx");
                    //printSuffixArray();

                }
            }
        }

        // Sorting is not implmented yet.
    	//
    	//
    	//
    }
    private int targetCompare(int i, int j, int end) {
        // comparing suffix_i and target_j_end by dictonary order with limitation of length;
        // if the beginning of suffix_i matches target_i_end, and suffix is longer than target it returns 0;
        //j～endまでの文字列をsuffixArrayと比較する
        //suffix_iはsuffixArrayのi番目の文字列
        // if suffix_i > target_j_end it return 1;
        // if suffix_i < target_j_end it return -1
        // It is not implemented yet.
        // It should be used to search the apropriate index of some suffix.
        // Example of search
        // suffix_i        target_j~end
        // "o"       >     "i"
        // "o"       <     "z"
        // "o"       =     "o"
        // "o"       <     "oo"
        // "Ho"      >     "Hi"
        // "Ho"      <     "Hz"
        // "Ho"      =     "Ho"
        // "Ho"      <     "Ho "   : "Ho " is not in the head of suffix "Ho"
        // "Ho"      =     "H"     : "H" is in the head of suffix "Ho"
        //
        // ****  Please write code here... ***
        //

        //String mySpaceS = new String(mySpace);  //byteからstringに変更
        //String myTargetS = new String(myTarget);



    	//System.out.println("*************************:targetCompare");
        //myspace長さ取得
        //int mySpaceSLength = mySpaceS.length();
    	//int mySpaceLength = mySpace.length;


        //suffixのiと、jを作成。
        //String suffix_i = mySpaceS.substring(suffixArray[i]);
        //String target_j = myTargetS.substring(j,end);

    	byte[] suffix_id = Arrays.copyOfRange(mySpace,suffixArray[i],mySpace.length+1);
    	byte[] target_jd = Arrays.copyOfRange(myTarget,j,end+1);




        //suffixのiと、jを作成
        //int suffix_i_length = suffix_i.length();
        //int target_length = target_j.length();

        //byte
    	int suffix_id_length = suffix_id.length-1;
    	int target_d_length = target_jd.length-1;

    	//System.out.println("suffix length = "+suffix_id_length);
    	//System.out.println("target length = "+target_d_length);


    	for(int k = 0;k < target_d_length;k++){
    		//System.out.println("k = "+k);

    		byte targeti =0;
    		byte targetj =0;



    		boolean emptyi = true;
    		boolean emptyj = true;

    		if(k != suffix_id_length){
    			targeti = suffix_id[k];

    			//char test = (char) targeti;//なんか他にやり方ありそう。
    			//System.out.println("target i = "+targeti);
    			//System.out.println("test = "+test);
    		}else{
    			targeti = 0;
    			//System.out.println("iがないよ。byte");
    			emptyi = false;
    		}

    		if(k != target_d_length){
    			targetj = target_jd[k];

    			//char test = (char) targetj;//なんか他にやり方ありそう。
    			//System.out.println("target j = "+targetj);
    			//System.out.println("test = "+test);
    		}else{
    			targetj = 0;
    			//System.out.println("jがないよ。byte");
    			emptyj = false;
    		}

    		char test = (char)targeti;
    		//System.out.println("c = "+test);


    		//どちらもない場合
    		if(emptyi == false && emptyj==false){
    			return 0;
    		}
    		//i true ver
    		if(emptyi == true && emptyj==false){
    			return 1;
    		}
    		//j true ver
    		if(emptyi == false && emptyj ==true){
    			return -1;
    		}

        	int judge = targeti-targetj;


        	//compareToは、その文字の差を出すモノでaとcであれば2を返す。



        	//System.out.println("judge =" +judge );

        	if(judge < 0){
        		return -1;
        	}else if(judge > 0){
        		return 1;
        	}
    	}





        /*
        for(int k = 0;k < target_length;k++){
            //System.out.println("k ="+k);
            //iのチェック
            String suffixChar = null;   //いま見たいやつ
            String targetChar = null;

            //emptyチェック
            boolean emptyi = true;
            boolean emptyj = true;

            if(k != suffix_i_length){
                suffixChar = suffix_i.substring(k, k+1);
                //System.out.println("target i = "+targeti);
            }
            else{
                suffixChar = null;
                //System.out.println("iないよ");
                emptyi = false;
            }

            if(k != target_length){
                targetChar = target_j.substring(k, k+1);
                //System.out.println("target j = "+targetj);
            }
            else{
                targetChar = null;
                //System.out.println("jないよ");
                emptyj = false;
            }

            //どちらもない場合
            if(emptyi == false && emptyj==false){
                return 0;
            }
            //i true ver
            if(emptyi == true && emptyj==false){
                return 1;
            }
            //j true ver
            if(emptyi == false && emptyj ==true){
                return -1;
            }


            int judge = suffixChar.compareTo(targetChar);

            //compareToは、その文字の差を出すモノでaとcであれば2を返す。

            //System.out.println("judge =" +judge );

            if(judge < 0){
                return -1;
            }else if(judge > 0){
                return 1;
            }
        }
        */

        return 0;


    }
    private int subByteStartIndex(int start, int end) {

        // It returns the index of the first suffix which is equal or greater than subBytes;
        // not implemented yet;
    	//suffixArrayで、一番最初に出てくるArrayの位置(番地)を返す。
        // For "Ho", it will return 5  for "Hi Ho Hi Ho".
        // For "Ho ", it will return 6 for "Hi Ho Hi Ho".
        //
        // ****  Please write code here... ***

    	//まず、start=0、end=2;
    	//targetが"Ho"ですよ。
    	//いま、辞書が
    	// Hi Ho        [0]
    	// Ho			[1]
    	// Ho Hi Ho		[2]
    	//Hi Ho			[3]
    	//Hi Ho Hi Ho	[4]
    	//Ho			[5]
    	//Ho Hi Ho		[6]
    	//i Ho			[7]
    	//i Ho Hi Ho	[8]
    	//o				[9]
    	//o Hi Ho		[10]

    	//で、Hoが最初にでてくる、[5]という、数字がほしい。

    	//targetCompare(int i, int j, int end) {
    	//i = Suffix_i
    	//j = target_start...myTarget[j]
    	//end = target_end...myTarget[end]


    	//int startIndex = -1;
    	//だから、for文回す。for(int k = 0;k<MyspaceLength-1;k++){
    	//						if(targetCompare(k,start,end) == 0){
    								//startIndex = k;
    								//この時のkは、StartIndexである。
    	//						}
    	//					 }
    	//

    	//



        for( int k=0 ; k < mySpace.length ; k++ ){
            if( targetCompare(k,start,end) == 0 ){ //最初に一致したところがstartIndex
                //System.out.println("start k =" + k);
                return k;
            }
        }

        System.out.println("dame");
        return -1; //




        //return startIndex;
    }
    private int subByteEndIndex(int start, int end) {
        // It returns the next index of the first suffix which is greater than subBytes;
        // not implemented yet
    	//suffixArrayで、Hoが終わる番地を返す。
        // For "Ho", it will return 7  for "Hi Ho Hi Ho".
        // For "Ho ", it will return 7 for "Hi Ho Hi Ho".
        //
        // ****  Please write code here... ***
        //
        for( int k=0 ; k < mySpace.length ; k++ ){
            if( targetCompare(k,start,end) == 1 ){ //最初に一致しなくなったところがendIndex
                //System.out.println("end k =" + k);
                return k;
            }
        }

        return -1; //
        // This line should be modified.
    }
    public int subByteFrequency(int start, int end) {
    	//spaceに同じ単語が何回でてくるか？みたいな？
        /*int spaceLength = mySpace.length;
        int count = 0;

        for(int offset = 0; offset< spaceLength - (end - start); offset++) {
            boolean abort = false;
            for(int i = 0; i< (end - start); i++) {
                if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
            }
            if(abort == false) { count++; }
        }
        */
        int first = subByteStartIndex(start, end);
        int last1 = subByteEndIndex(start, end);
        return last1 - first;
    }
    public void setTarget(byte [] target) {
        myTarget = target; if(myTarget.length>0) targetReady = true;
    }

    public int frequency() {
    	//まず確認
        if(targetReady == false) return -1;
        if(spaceReady == false) return 0;

        //System.out.println(myTarget.length);
        return subByteFrequency(0, myTarget.length);
    }

    public static void main(String[] args) {
        Frequencer frequencerObject;
        try {
            frequencerObject = new Frequencer();
            frequencerObject.setSpace("3210321001230123".getBytes());
            frequencerObject.printSuffixArray();// you may use this line for DEBUG
//
            /* Example from "Hi Ho Hi Ho"
               0: Hi Ho
               1: Ho
               2: Ho Hi Ho
               3:Hi Ho
               4:Hi Ho Hi Ho
               5:Ho
               6:Ho Hi Ho
               7:i Ho
               8:i Ho Hi Ho
               9:o
               A:o Hi Ho
            */

            frequencerObject.setTarget("0".getBytes());
            //
            // ****  Please write code to check subByteStartIndex, and subByteEndIndex
            //
            int result = frequencerObject.frequency();
            System.out.print("Freq = "+ result+" ");
            if(4  ==  result)  {  System.out.println("OK");}
            //}else{
            //	System.out.println("WRONG");
            //}



        //}
        } catch(Exception e) {
            System.out.println("STOP");
            System.err.println(e);
        }
    }
}