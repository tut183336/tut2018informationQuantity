package s4.B183306; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import	java.lang.*;
import	s4.specification.*;
/*package	s4.specification;
public	interface	FrequencerInterface	{	//	This	interface	provides	the	design	for
frequency	counter.
			void	setTarget(byte		target[]);	//	set	the	data	to	search.
				void	setSpace(byte		space[]);		//	set	the	data	to	be	searched	target	from.
				int	frequency();	//It	return	-1,	when	TARGET	is	not	set	or	TARGET's	length	is	zero
																				//Otherwise,	it	return	0,	when	SPACE	is	not	set	or	SPACE's	length
is	zero
																				//Otherwise,	get	the	frequency	of	TAGET	in	SPACE
				int	subByteFrequency(int	start,	int	end);
				//	get	the	frequency	of	subByte	of	taget,	i.e target[start],	taget[start+1],	...	,
target[end-1].
				//	For	the	incorrect	value	of	START	or	END,	the	behavior	is	undefined.
}
*/
public	class	Frequencer	implements	FrequencerInterface{
//	Code	to	start	with:	This	code	is	not	working,	but	good	start	point	to	work.
		byte	[]	myTarget;
  	byte	[]	mySpace;
  	boolean	targetReady	=	false;
		boolean	spaceReady	=	false;
		int	[]	suffixArray;
		//	The	variable,	"suffixArray"	is	the	sorted	array	of	all	suffixes	of	mySpace.
		//	Each	suffix	is	expressed	by	a	integer,	which	is	the	starting	position	in	mySpace.
		//	The	following	is	the	code	to	print	the	variable
		private	void	printSuffixArray()	{
      if(spaceReady)	{
				for(int	i=0;	i<	mySpace.length;	i++)	{
					int	s	=	suffixArray[i];
						for(int	j=s;j<mySpace.length;j++)	{
							System.out.write(mySpace[j]);
            }
						System.out.write('\n');
						}
					}
				}

		private	int	suffixCompare(int	i,	int	j)	{
		//	comparing	two	suffixes	by	dictionary	order.
		//	i	and	j	denoetes	suffix_i,	and	suffix_j
		//	if	suffix_i	>	suffix_j,	it	returns	1
    //	if	suffix_i	<	suffix_j,	it	returns	-1
    //	if	suffix_i	=	suffix_j,	it	returns	0;
    //	It	is	not	implemented	yet,
    //	It	should	be	used	to	create	suffix	array.
    //	Example	of	dictionary	order
    //	"i"						<		"o"								:	compare	by	code
    //	"Hi"					<		"Ho"							;	if	head	is	same,	compare	the	next	element
    //	"Ho"					<		"Ho	"						;	if	the	prefix	is	identical,	longer	string	is	big
    //
    //	****		Please	write	code	here...	***
    //
            int flag = 1;
            if(i > j){
                int tmp = i;
                i = j;
                j= tmp;
                flag = -1;
            }
            if(i==j){
                return 0;
            }
            while(true){
                if(mySpace[i] > mySpace[j]){
                    return 1 * flag;
                }
                else if(mySpace[i] < mySpace[j]){
                    //System.out.println("-1deta");
                    return -1 * flag;
                }
                else if(mySpace[i] == mySpace[j]){
                    if(j==mySpace.length-1){
                        return 1 * flag;
                    }
                    i++;
                    j++;
                }
            }
		}

    public	void	setSpace(byte	[]space)	{
      mySpace	=	space;
			if(mySpace.length>0)	spaceReady	=	true;
      suffixArray	=	new	int[space.length];
      //	put	all	suffixes		in	suffixArray.	Each	suffix	is	expressed	by	one	integer.
      for(int	i	=	0; i<	space.length;	i++)	{
				suffixArray[i]	=	i;
      }
			//	Sorting	is	not	implmented	yet.
      //
      //
      //	****		Please	write	code	here...	***
      //

        printSuffixArray();
        System.out.println();
        //int test = suffixCompare(suffixArray[1],suffixArray[2]);
        //System.out.println(test);
        for(int i = 0; i < mySpace.length; i++){
            for(int j = mySpace.length - 1; j > i; j--){
                //System.out.println(j);
                int x=suffixCompare(suffixArray[j-1],suffixArray[j]);
				//System.out.println(x);
				
                if(x==1){
                    int y = suffixArray[j-1];
                    suffixArray[j-1]=suffixArray[j];
                    suffixArray[j]=y;
                }
                else if(x==0){
                    System.out.println("ERROR");
                }
                //System.out.println();
                //printSuffixArray();

            }
			/*
			printSuffixArray();
			System.out.println();
			*/
        }
	}




    		private	int	targetCompare(int	i,	int	j,	int	end)	{
								//	comparing	suffix_i	and	target_j_end	by	dictonary	order	with	limitation	of	length;
								//	if	the	beginning	of	suffix_i	matches	target_i_end,	and	suffix	is	longer	than target		it	returns	0;
								//	if	suffix_i	>	target_i_end	it	return	1;
								//	if	suffix_i	<	target_i_end	it	return	-1
								//	It	is	not	implemented	yet.
								//	It	should	be	used	to	search	the	apropriate	index	of	some	suffix.
								//	Example	of	search
								//	suffix										target
								//	"o"							>					"i"
								//	"o"							<					"z"
								//	"o"							=					"o"
								//	"o"							<					"oo"
								//	"Ho"						>					"Hi"
								//	"Ho"						<					"Hz"
								//	"Ho"						=					"Ho"
								// "Ho"						<					"Ho	"			:	"Ho	"	is	not	in	the	head	of	suffix	"Ho"
								//	"Ho"						=					"H"					:	"H"	is	in	the	head	of	suffix	"Ho"
								//
								//	****		Please	write	code	here...	***
								//
								return	0;	//	This	line	should	be	modified.
				}
				private int	subByteStartIndex(int	start,	int	end)	{
								//	It	returns	the	index	of	the	first	suffix	which	is	equal	or	greater	than	subBytes;
								//	not	implemented	yet;
								//	For	"Ho",	it	will	return	5		for	"Hi	Ho	Hi	Ho".
								//	For	"Ho	",	it	will	return	6	for	"Hi	Ho	Hi	Ho".
								//
								//	****		Please	write	code	here...	***
								//
								return	suffixArray.length;	//	This	line	should	be	modified.
				}
				private	int	subByteEndIndex(int	start,	int	end)	{
								//	It	returns	the	next	index	of	the	first	suffix	which	is	greater	than	subBytes;
								//	not	implemented	yet
								//	For	"Ho",	it	will	return	7		for	"Hi	Ho	Hi	Ho".
								//	For	"Ho	",	it	will	return	7	for	"Hi	Ho	Hi	Ho".
								//
								//	****		Please	write	code	here...	***
								//
								return	suffixArray.length;	//	This	line	should	be	modified.
				}
				public	int	subByteFrequency(int	start,	int	end)	{
								/*	This	method	be	work	as	follows,	but
								int	spaceLength	=	mySpace.length;
								int	count	=	0;
								for(int	offset	=	0;	offset<	spaceLength	- (end	- start);	offset++)	{
												boolean	abort	=	false;
												for(int	i	=	0;	i<	(end	- start);	i++)	{
																if(myTarget[start+i]	!=	mySpace[offset+i])	{	abort	=	true;	break;	}
												}
												if(abort	==	false)	{	count++;	}
								}
								*/
								int	first	=	subByteStartIndex(start,	end);
								int	last1	=	subByteEndIndex(start,	end);
								return	last1	- first;
				}
				public	void	setTarget(byte	[]	target)	{
								myTarget	=	target;	if(myTarget.length>0)	targetReady	=	true;
				}
				public	int	frequency()	{
								if(targetReady	==	false)	return	-1;
								if(spaceReady	==	false)	return	0;
								return	subByteFrequency(0,	myTarget.length);
				}
				public	static	void	main(String[]	args)	{
								Frequencer	frequencerObject;
								try	{
												frequencerObject	=	new	Frequencer();
												//frequencerObject.setSpace("cab".getBytes());
												frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
												frequencerObject.printSuffixArray();	//	you	may	use	this	line	for	DEBUG
												/*	Example	from	"Hi	Ho	Hi	Ho"
															0:	Hi	Ho
															1:	Ho
															2:	Ho	Hi	Ho
															3:Hi	Ho
															4:Hi	Ho	Hi	Ho
															5:Ho
															6:Ho	Hi	Ho
															7:i	Ho
															8:i	Ho	Hi	Ho
															9:o
															A:o	Hi	Ho
												*/


												frequencerObject.setTarget("H".getBytes());
												/*
												int x=0;
												for(int i=0;i<10;i++){
												x=frequencerObject.suffixCompare(i,i+1);
												System.out.println(x);
											}*/
												//
												//	****		Please	write	code	to	check	subByteStartIndex,	and	subByteEndIndex
												//
												int	result	=	frequencerObject.frequency();
												System.out.print("Freq	=	"+	result+"	");
												if(4	 ==	 result){	System.out.println("OK");	 }	else{System.out.println("WRONG");	}





								}
								catch(Exception	e)	{
												System.out.println("STOP");
												e.printStackTrace();
								}
				}
}
