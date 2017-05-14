import java.io.IOException;
import java.util.Scanner;

public class Test {
	public static void main(String args[]) throws IOException, InterruptedException {
		Scanner in = new Scanner(System.in);
		CPIO p7 = new CPIO("XIO-P7", "of"); //initializes pin with invalid argument, defaults to in
		
		p7.dir("out"); //changes pin to write
		p7.write(1); //pin on
		System.out.print("Pin on...");
		
		in.nextLine(); //waits for user input to continue
		
		p7.write(0); //pin off
		System.out.print("Pin off...");
		
		in.nextLine(); //waits
		System.out.println("Reading pin. Type anything to quit.");
		
		p7.dir("in"); //changes pin to in
		
		String trigger = "";
		while (trigger.equals("")) { //writes value when user presses 'enter', any text breaks loop
			System.out.println(p7.read());
			trigger = in.nextLine();
		}
		
		p7.del(); //unexports pin from system
	}
}
