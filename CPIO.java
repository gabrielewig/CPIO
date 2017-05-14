import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The CPIO class aims to make GPIO easily accessible with Java on the Next Thing Co CHIP computer
 * The full repository along with README are available at https://github.com/Sudo-Tech/CPIO
 * @author SudoTech (https://www.youtube.com/sudotech)
 * @version 0.2
 */

public class CPIO {
	private int pin;
	
	/**
	 * Constructor for new CPIO object.
	 * @param pinName Pin to initialize (XIO-P0 - XIO-P7)
	 * @param state Initial state of read (in) or write (out)
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public CPIO(String pinName, String state) throws IOException, InterruptedException {
		if (!(state.equals("in") || state.equals("out"))) {
			System.out.println("New CPIO requires valid state of in or out. Defaulting to in.");
			state = "in";
		}
		this.pin = pin(pinName);
		exec(Arrays.asList("sudo", "sh", "-c", "echo " + pin + " > /sys/class/gpio/export"));
		if (state.equals("out"))
		    dir("out");
	}
	
	/**
	 * Reads current pin direction as read (in) or write (out).
	 * @return current direction (in / out)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void dir(String dir) throws IOException, InterruptedException {
		exec(Arrays.asList("sudo", "sh", "-c", "echo " + dir + " > /sys/class/gpio/gpio" + pin + "/direction"));
	}
	
	/**
	 * Reads value of pin as powered/neutral (1) or ground (0).
	 * @return Value of pin (0 / 1)
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int read() throws NumberFormatException, IOException, InterruptedException {
		return Integer.valueOf(exec(Arrays.asList("cat", "/sys/class/gpio/gpio" + pin + "/value")).get(0));
	}
	
	/**
	 * Writes value to pin as powered (1) or off (0). Returns set value or -1 if pin is set to read or not initialized.
	 * @param val Value to  set (0 / 1)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void write(int val) throws IOException, InterruptedException {
		System.out.println(exec(Arrays.asList("sudo", "sh", "-c", "echo " + val + " > /sys/class/gpio/gpio" + pin + "/value")).get(0));
	}
	
	/**
	 * Tells system to unexport the pin.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void del() throws IOException, InterruptedException {
		exec(Arrays.asList("sudo", "sh", "-c", "echo " + pin + " > /sys/class/gpio/unexport'"));
	}
	
	/**
	 * Calculates system pin number from friendly string
	 * @param pin String that refers to pin (XIO-P0 - XIO-P7)
	 * @return Int that refers to pin for system
	 */
	private int pin(String pin) {
		return 1013 + Integer.valueOf(pin.substring(pin.length() - 1));
	}
	
	/**
	 * Executes command in System terminal.
	 * @param command Command as ArrayList
	 * @return Output of command by line in List<String> from .getInputStream followed by .getErrorStream.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<String> exec(List<String> command) throws IOException, InterruptedException {
		ProcessBuilder cmd = new ProcessBuilder(command);
		Process proc = cmd.start();
		proc.waitFor();
		
		List<String> out = new ArrayList<String>();
		Scanner inStream = new Scanner(proc.getInputStream());
		try {
			while (true)
				out.add(inStream.nextLine());
		} catch (NoSuchElementException e) {
			inStream.close();
			Scanner errStream = new Scanner(proc.getErrorStream());
			try {
				while (true)
					out.add(errStream.nextLine());
			} catch (NoSuchElementException e1) {
				errStream.close();
				out.add("");
				return out;
			}
		}
	}
}