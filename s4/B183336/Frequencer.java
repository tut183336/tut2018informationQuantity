package s4.B183336; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
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

	public String printTarget(){
		String a = new String(myTarget);
		return a;
	}

	private int suffixCompare(int i, int j) {
		// comparing two suffixes by dictionary order.
		// i and j denoetes suffix_i, and suffix_j
		// if suffix_i > suffix_j, it returns 1
		// if suffix_i < suffix_j, it returns -1
		// if suffix_i = suffix_j, it returns 0;
		// It is not implemented yet,
		// It should be used to create suffix array.


		//1/23更新 Stringでは無くて、byte型で実行できるようにする。

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






		int mySpaceLength = mySpace.length;


		//byte[] suffix_id = Arrays.copyOfRange(mySpace,i,mySpace.length+1);
		//byte[] suffix_jd = Arrays.copyOfRange(mySpace,j,mySpace.length+1);

		//String test1 = new String(suffix_id);
		//String test2 = new String(suffix_jd);

		byte[] suffix_id = new byte[mySpace.length-i];
		byte[] suffix_jd = new byte[mySpace.length-j];


		int ki = 0;
		int kj = 0;

		//System.out.println("↓");
		for(int k = 0;k<mySpace.length;k++){
			if(k>=i){
				suffix_id[ki] = mySpace[k];
				ki++;
			}
			if(k>=j){
				suffix_jd[kj] = mySpace[k];
				kj++;
			}
			//System.out.println(mySpace[k]);
		}

		//String test1b = new String(test_suffix_i);
		//String test2b = new String(test_suffix_j);


		//System.out.println("bi = "+test1b);
		//System.out.println("bj = "+test2b);

		//System.out.println("di = "+test1);
		//System.out.println("dj = "+test2);


		//int suffix_id_length = suffix_id.length-1;//仕様で-1しよう
		//int suffix_jd_length = suffix_jd.length-1;//仕様で-1しよう


		int suffix_id_length = suffix_id.length;
		int suffix_jd_length = suffix_jd.length;

		//System.out.println("length"+test_i);
		//System.out.println("length"+test_j);
		//System.out.println("length"+suffix_id_length);
		//System.out.println("length"+suffix_jd_length);


		for(int k = 0;k < mySpaceLength;k++){

			byte targeti =0;
			byte targetj =0;

			boolean emptyi = true;
			boolean emptyj = true;

			if(k != suffix_id_length){
				targeti = suffix_id[k];
			}else{
				emptyi = false;
			}

			if(k != suffix_jd_length){
				targetj = suffix_jd[k];
			}else{
				emptyj = false;
			}


			//どちらもない場合
			if(emptyi == false && emptyj==false){
				return 0;
			}
			//i がある。jが無い
			if(emptyi == true && emptyj==false){
				return 1;
			}
			//j がある。iが無い
			if(emptyi == false && emptyj ==true){
				return -1;
			}

			int judge = targeti-targetj;

			if(judge < 0){
				return -1;
			}else if(judge > 0){
				return 1;
			}
		}

		return 0;




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

		//マージソート
		this.sort(suffixArray,0, suffixArray.length-1);
	}

	//マージソートのメソッド
	public void sort(int[] array,int low,int high){
		if(low < high){
			int middle = (low + high) >>> 1;
		this.sort(array, low, middle);
		this.sort(array, middle+1, high);
		this.merge(array, low, middle,high);
		}
	}

	//マージソートのメソッド
	public void merge(int [] array,int low,int middle, int high){
		int[] temp = new int[array.length];

		for(int i = low; i <= high; i++){
			temp[i] = array[i];
		}
		int tempLeft = low;
		int tempRight = middle + 1;
		int current = low;

		while(tempLeft <= middle && tempRight <= high){
			if(suffixCompare(temp[tempLeft],temp[tempRight]) == -1){
				array[current] = temp[tempLeft];
				tempLeft++;
			}else{
				array[current] = temp[tempRight];
				tempRight++;
			}
			current ++;
		}
		int remaining = middle - tempLeft;
		for(int i = 0; i <= remaining; i++){
			array[current + i] = temp[tempLeft+i];
		}
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

		byte[] suffix_id = new byte[mySpace.length-suffixArray[i]];
		byte[] target_jd = new byte[end-j];


		int ki = 0;
		int lj = 0;

		//System.out.println("↓");
		for(int k = 0;k<mySpace.length;k++){
			if(k>=suffixArray[i]){
				suffix_id[ki] = mySpace[k];
				ki++;
			}
			//System.out.println(mySpace[k]);
		}

		for(int l = 0;l<myTarget.length;l++){
			if((l >= j)&&(l <= end-1)){
				target_jd[lj] = myTarget[l];
				lj++;
			}
		}


		int suffix_id_length = suffix_id.length;
		int target_d_length = target_jd.length;




		//System.out.println("length =" + suffix_id_length);
		//System.out.println("length =" + target_d_length);



		String test1 = new String(suffix_id);
		String test2 = new String(target_jd);

		//System.out.println("di = "+test1);
		//System.out.println("dj = "+test2);



		for(int k = 0;k < target_d_length;k++){


			byte targeti =0;
			byte targetj =0;

			boolean emptyi = true;
			boolean emptyj = true;

			if(k != suffix_id_length){
				targeti = suffix_id[k];
			}else{
				targeti = 0;
				emptyi = false;
			}

			if(k != target_d_length){
				targetj = target_jd[k];
			}else{
				targetj = 0;
				emptyj = false;
			}

			//どちらもない場合
			if(emptyi == false && emptyj==false){
				return 0;
			}
			//i があって jがない
			if(emptyi == true && emptyj==false){
				return 1;
			}
			//j があって iがない
			if(emptyi == false && emptyj ==true){
				return -1;
			}

			int judge = targeti-targetj;

			if(judge < 0){
				return -1;

			}else if(judge > 0){
				return 1;
			}
		}

		return 0;
	}

	private int subByteStartIndex(int start, int end) {

		// It returns the index of the first suffix which is equal or greater than subBytes;
		// not implemented yet;
		//suffixArrayで、一番最初に出てくるArrayの位置(番地)を返す。
		// For "Ho", it will return 5  for "Hi Ho Hi Ho".
		// For "Ho ", it will return 6 for "Hi Ho Hi Ho".

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

		//2/5 二分探索
		//int n = 0;


		int high = mySpace.length-1;

		int mid = 0;

		int low = 0;

		while(true){
			if(high < low){
				return -1;
			}

			mid = low + (high - low) /2;


			int result = targetCompare(mid,start,end);

			if(result == -1){
				low = mid+1;
			}
			if(result == 0){
				//一つ上を確かめる
				while(true){
					//今のindexが最初だった場合
					if(mid == 0){
						return mid;
					}
					int resultM = targetCompare(mid-1,start,end);//resultM...result minus 1
					//いっこ上がまだ0の場合
					if(resultM == 0){
						//mid = mid -1;
						high = mid-1;
						break;
					}else if(resultM == -1){//いっこ上が境目だった場合
						return mid ;
					}
				}


			}
			if(result == 1){
				high = mid-1;
			}
		}

	}
	private int subByteEndIndex(int start, int end) {
		// It returns the next index of the first suffix which is greater than subBytes;
		// not implemented yet
		//suffixArrayで、Hoが終わる番地を返す。
		// For "Ho", it will return 7  for "Hi Ho Hi Ho".
		// For "Ho ", it will return 7 for "Hi Ho Hi Ho".





		/*
        for( int k=mySpace.length-1 ;k>=0; k-- ){
            //System.out.println("end k =" + k);
            if( targetCompare(k,start,end) == 0 ){ //下から見ていって一致した所の一つ下がendIndex
                return k+1;
            }
        }

		 */


		int high = mySpace.length-1;

		int mid = 0;

		int low = 0;

		while(true){

			mid = low + (high - low) /2;

			if(high < low){
				return -1;
			}


			int result = targetCompare(mid,start,end);

			if(result == -1){
				low = mid+1;
			}
			if(result == 0){
				while(true){
					//一つ上を確かめる
					//indexが最後を指していた場合
					if(mid == mySpace.length-1){
						return mid + 1;//endだからいっこ下を参照
					}
					int resultP = targetCompare(mid+1,start,end); //resultP ... result plus 1

					if(resultP == 0){
						//mid = mid +1;
						low = mid + 1;
						break;
					}else if(resultP == 1){
						//endだからいっこ下を出すよ。
						return mid + 1;
					}
				}
			}
			if(result == 1){
				high = mid-1;
			}
		}
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

	    frequencerObject = new Frequencer();
	    frequencerObject.setSpace("AAAAB".getBytes());
	    //frequencerObject.printSuffixArray(); // you may use this line for DEBUG
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

	    frequencerObject.setTarget("AAAAB".getBytes());

	    //int freq = frequencerObject.subByteFrequency(0,1);

	    //
	    // ****  Please write code to check subByteStartIndex, and subByteEndIndex
	    //

	    int result = frequencerObject.frequency();
	    System.out.print("Freq = "+ result+" ");
	    if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	}

}


