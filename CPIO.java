/**
 * The CPIO class aims to make GPIO easily accessible with Java on the Next Thing Co CHIP computer
 * The full repository along with README are available at https://github.com/Sudo-Tech/CPIO
 * @author SudoTech (https://www.youtube.com/sudotech)
 * @version 0.2
 */

public class CPIO {
	
	private ProcessBuilder sys;
	private int pin;
	private String command;
	
	public CPIO(String pin, String state) {
		pin = pin(pin);
		command = 
		sys = new ProcessBuilder(command);
		Process p = pb.start();
		p.waitFor();
	}
	/**
	 * Reads current pin direction as read (in) or write (out).
	 * @return current direction (in / out)
	 */
	public String dir() {
		return "out";
	}
	/**
	 * Sets pin direction to read (in) or write (out).
	 * @param dir Direction to set (in / out)
	 */
	public void dir(String dir) {
		
	}
	/**
	 * Reads value of pin as powered/neutral (1) or ground (0). If pin is set to write (0) or not initialized, returns -1.
	 * @return Value of pin (-1 / 0 / 1)
	 */
	public int read() {
		return -1;
	}
	/**
	 * Writes value to pin as powered (1) or off (0). Returns set value or -1 if pin is set to read or not initialized.
	 * @param val Value to  set (0 / 1)
	 * @return Set value (-1 / 0 / 1)
	 */
	public int write(int val) {
		return -1;
	}
	/**
	 * Tells system to unexport the pin and deletes object.
	 */
	public void del() {
		
	}
	/**
	 * Calculates system pin number from friendly string
	 * @param pin String that refers to pin (XIO-P0 - XIO-P7)
	 * @return Int that refers to pin for system
	 */
	private int pin(String pin) {
		return 1013 + pin.substring(pin.length - 1);
	}
}
