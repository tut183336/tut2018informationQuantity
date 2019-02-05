package s4.B183331; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
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
	try {
	    FrequencerInterface  myObject;
	    int freq;
            System.out.println("--------------------------------------------");
	    System.out.println("checking s4.B183331.Frequencer");
            System.out.println("--------------------------------------------");
            System.out.println("++checking frequency()");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("   \"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

            myObject.setTarget("Hi".getBytes());
	    freq = myObject.frequency();
	    System.out.print("   \"Hi\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
           
            myObject.setTarget("Ha".getBytes());
	    freq = myObject.frequency();
	    System.out.print("   \"Ha\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
            
	    myObject.setSpace("".getBytes());
            freq = myObject.frequency();
            System.out.print("   Space's length is zero then frequency = "+freq+". ");
            if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
            
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("".getBytes());
            freq = myObject.frequency();
            System.out.print("   Target's length is zero then frequency = "+freq+". ");
            if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

            }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("   Space is not set then ");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.println("frequency = "+freq+".");
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("   Target is not set then ");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.frequency();
	    System.out.println("frequency = "+freq+".");
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("   \"aa\" in \"aaaaa\" appears ");
	    myObject = new s4.B183331.Frequencer();
            myObject.setSpace("aaaaa".getBytes());
	    myObject.setTarget("aa".getBytes());
	    freq = myObject.frequency();
	    System.out.println(freq+" times. ");
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

         try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("\n");
            System.out.println("++checking subByteFrequency(start,end)");
            System.out.println("(Set target is \"Hiiiiii\".)");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("Hiiiiii".getBytes());
	    freq = myObject.subByteFrequency(0,2);
            System.out.print("   \"Hi\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

            freq = myObject.subByteFrequency(0,4);
            System.out.print("   \"Hiii\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
            
	    freq = myObject.subByteFrequency(-1,2);
            System.out.println("   \"subByteFrequency(-1,2)\" When \"start\" is less than zero, frequency ="+freq+".");
            freq = myObject.subByteFrequency(0,"Hiiiiii".length()+5);
            System.out.println("   \"subByteFrequency(0,12)\" When \"end\" is more than length of \"Target\", frequency ="+freq+".");
            
            freq = myObject.subByteFrequency(3,2);
            System.out.println("   \"subByteFrequency(3,2)\" When start's value is more than end's value, frequency ="+freq+".");
            
            myObject.setSpace("".getBytes());
            freq = myObject.subByteFrequency(0,2);
            System.out.println("   When Space's length is zero, frequency ="+freq+".");
            
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("".getBytes());
            freq = myObject.subByteFrequency(0,2);
            System.out.println("   When Target's length is zero, frequency = "+freq+".");
            }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("   When Target is not set, ");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    freq = myObject.subByteFrequency(0,2);
	    System.out.println("frequency = "+freq+".");
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            FrequencerInterface  myObject;
	    int freq;
            System.out.print("   When Space is not set, ");
	    myObject = new s4.B183331.Frequencer();
	    myObject.setTarget("Hiiiiii".getBytes());
	    freq = myObject.subByteFrequency(0,2);
	    System.out.println("frequency = "+freq+".");
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
        
	try {
	    InformationEstimatorInterface myObject;
	    double value;
            System.out.print("\n");
            System.out.println("--------------------------------------------");
	    System.out.println("checking s4.B183331.InformationEstimator");
            System.out.println("--------------------------------------------");
            System.out.println("++checking estimation()");
	    myObject = new s4.B183331.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >0 "+value);
	    myObject.setTarget("01".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >01 "+value);
	    myObject.setTarget("0123".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >0123 "+value);
	    myObject.setTarget("00".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >00 "+value);
            myObject.setTarget("03".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >03 "+value);
            myObject.setTarget("12".getBytes());
	    value = myObject.estimation();
	    System.out.println("   >12 "+value);
            myObject.setTarget("14".getBytes());
	    value = myObject.estimation();
	    System.out.print("   >14 "+value);
            if(Double.MAX_VALUE == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }
            myObject.setTarget("abc".getBytes());
	    value = myObject.estimation();
	    System.out.print("   >abc "+value);
            if(Double.MAX_VALUE == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }
            myObject.setTarget("".getBytes());
	    value = myObject.estimation();
	    System.out.print("   >When Target's length is zero  "+value);
            if(0.0 == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }
            myObject.setSpace("".getBytes());
            myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.print("   >When Space's length is zero  "+value);
            if(Double.MAX_VALUE == value) { System.out.println(" OK"); } else {System.out.println(" WRONG"); }
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            InformationEstimatorInterface  myObject;
	    double value;
            System.out.print("   >When Target is not set ");
            myObject = new s4.B183331.InformationEstimator();
	    myObject.setSpace("3210321001230123".getBytes());
	    value = myObject.estimation();
	    System.out.println(value);
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
        
        try{
            InformationEstimatorInterface  myObject;
	    double value;
            System.out.print("   >When Space is not set ");
	    myObject = new s4.B183331.InformationEstimator();
	    myObject.setTarget("0".getBytes());
	    value = myObject.estimation();
	    System.out.println(value);
            if(Double.MAX_VALUE == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            InformationEstimatorInterface  myObject;
	    double value;
            System.out.print("   >02122000212200122012220002110120 ");
	    myObject = new s4.B183331.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("02122000212200122012220002110120".getBytes());
	    value = myObject.estimation();
            System.out.println(value);
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            InformationEstimatorInterface  myObject;
	    double value;
            System.out.print("   >1233 ");
	    myObject = new s4.B183331.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("1233".getBytes());
	    value = myObject.estimation();
            System.out.println(value);
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

        try{
            InformationEstimatorInterface  myObject;
	    double value;
            System.out.print("   >30 ");
	    myObject = new s4.B183331.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("30".getBytes());
	    value = myObject.estimation();
            System.out.println(value);
        }
        catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}	    
	    
