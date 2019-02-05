package s4.B161823;
import java.lang.*;

public class Hello {
    public static void main(String[] args) {
	System.out.println("hello..(buggy)");
        for(int i = 0;i < 10;i++){
            int x = 10 - i;
    System.out.println(x);
        }
        System.out.println("BOOOOOOOOOOOOOM!!!!!!!!!!!!");
    }
}
