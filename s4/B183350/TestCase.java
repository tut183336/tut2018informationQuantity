package s4.B183350; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

public class TestCase {
    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking s4.B183350.Frequencer");
	    myObject = new s4.B183350.Frequencer();

		myObject.setSpace("".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"\" appears "+freq+" times. ");
	    if(freq == 0) { System.out.println("OK"); } else {System.out.println("WRONG"); }

		myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(freq == -1) { System.out.println("OK"); } else {System.out.println("WRONG"); }

		myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }

		myObject.setSpace("H H H H H".getBytes());
	    myObject.setTarget("H H".getBytes());
	    freq = myObject.frequency();
	    System.out.print("\"H H\" in \"H H H H H\" appears "+freq+" times. ");
	    if(freq == 4) { System.out.println("OK"); } else {System.out.println("WRONG"); }

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
		e.printStackTrace();
	}

	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking s4.B183350.InformationEstimator");
	    myObject = new s4.B183350.InformationEstimator();
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
		myObject.setTarget("".getBytes());
	    value = myObject.estimation();
		System.out.print("noTarget: ");
	    if(value == 0.0){
			System.out.println("OK");
			}
		else{
			System.out.println("WRONG");
		}
		
		myObject.setSpace("".getBytes());
		myObject.setTarget("0".getBytes());
		value = myObject.estimation();
		System.out.print("noSpace: ");
		if(value == Double.MAX_VALUE){
			System.out.println("OK");
		}else{
			System.out.println("WRONG");
		}

		myObject.setSpace("".getBytes());
		myObject.setTarget("".getBytes());
		System.out.println("noSpace, noTarget: " + value);

	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}	    
	    
