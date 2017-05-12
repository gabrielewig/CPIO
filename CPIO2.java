import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io;

/**
 * The CPIO class aims to make GPIO easily accessible with Java on the Next Thing Co CHIP computer
 * The full repository along with README are available at https://github.com/Sudo-Tech/CPIO
 * @author SudoTech (https://www.youtube.com/sudotech)
 * @version 0.2
 */

public class CPIO {
	private int pin;
	
	/**
	 * Initializes new CPIO (pin) object.
	 * @param pin Pin to initialize (XIO-P0 - XIO-P7)
	 * @param state State to initialize pin as (in / out)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public CPIO(String pin, String state) throws IOException, InterruptedException {
		if (!(state.equals("in") || state.equals("out"))) {
			System.out.println("New CPIO requires valid state of in or out. Defaulting to in.");
			state = "in";
		}
		this.pin = pin(pin);
		exec("sh -c 'echo " + pin + " > /sys/class/gpio/export'");
		if (state.equals("out"))
		    dir("out");
	}
	/**
	 * Reads current pin direction as read (in) or write (out).
	 * @return current direction (in / out)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public String dir() throws IOException, InterruptedException {
		return exec("cat /sys/class/gpio/gpio" + pin + "/direction");
	}
	/**
	 * Sets pin direction to read (in) or write (out).
	 * @param dir Direction to set (in / out)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void dir(String dir) throws IOException, InterruptedException {
		exec("sh -c 'echo " + dir + " > /sys/class/gpio/gpio" + pin + "/direction'");
	}
	/**
	 * Reads value of pin as powered/neutral (1) or ground (0).
	 * @return Value of pin (0 / 1)
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int read() throws NumberFormatException, IOException, InterruptedException {
		return Integer.valueOf(exec("cat /sys/class/gpio/gpio" + pin + "/value"));
	}
	/**
	 * Writes value to pin as powered (1) or off (0). Returns set value or -1 if pin is set to read or not initialized.
	 * @param val Value to  set (0 / 1)
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void write(int val) throws IOException, InterruptedException {
		exec(" sudo sh -c 'echo " + val + " > /sys/class/gpio/gpio" + pin + "/value'");
	}
	/**
	 * Tells system to unexport the pin and deletes object.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void del() throws IOException, InterruptedException {
		exec("sh -c 'echo " + pin + " > /sys/class/gpio/unexport'");
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
	 * Executes given command and returns output.
	 * @param cmd Command to execute
	 * @return Standardoutput of command as String
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static InputStream exec(String command) throws IOException, InterruptedException {
		ProcessBuilder cmd = new ProcessBuilder(command);
		Process proc = cmd.start();
		proc.waitFor();
		System.out.println(proc.getInputStream());
		return IOUtils.toString(proc.getInputStream(), "UTF-8");
	}
}
