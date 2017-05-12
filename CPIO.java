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
	 */
	public CPIO(String pin, String state) {
		if (!(state.equals("in") || state.equals("out"))) {
			System.out.println("New CPIO requires valid state of in or out. Defaulting to in.");
			state = "in";
		}
		this.pin = pin(pin);
		ProcessBuilder cmd = new ProcessBuilder("sh -c 'echo " + pin + " > /sys/class/gpio/export'");
		Process proc = cmd.start();
		proc.waitFor();
		if (state.equals("out")
		    dir("out");
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
		ProcessBuilder cmd = new ProcessBuilder("sh -c 'echo " + dir + " > /sys/class/gpio/gpio" + pin + "/direction'");
		Process proc = cmd.start();
		proc.waitFor();
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
	 */
	public void write(int val) {
		ProcessBuilder cmd = new ProcessBuilder(" sudo sh -c 'echo " + val + " > /sys/class/gpio/gpio" + pin + "/value'";;);
		Process proc = cmd.start();
		proc.waitFor();
	}
	/**
	 * Tells system to unexport the pin and deletes object.
	 */
	public void del() {
		ProcessBuilder cmd = new ProcessBuilder("sh -c 'echo " + pin + " > /sys/class/gpio/unexport'");
		Process proc = cmd.start();
		proc.waitFor();
		this = null;
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
