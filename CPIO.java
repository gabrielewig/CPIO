/*
 * The CPIO class aims to make GPIO easily accessible with Java on the Next Thing Co CHIP computer
 * The full repository along with README are available at https://github.com/Sudo-Tech/CPIO
 * @author SudoTech (https://www.youtube.com/sudotech)
 * @version 0.1
 */

public class CPIO {
	/**
	 * Tells system to initialize GPIO pin. Default direction is set to in (read).
	 * @param pin Pin to initialize
	 */
	public static void export(String pin) {
		
	}
	/**
	 * Sets pin direction to read (in) or write (out).
	 * @param pin Pin to set
	 * @param dir Direction to set (in / out)
	 */
	public static void set(String pin, String dir) {
		
	}
	/**
	 * Reads value of pin as powered/neutral (1) or ground (0). If pin is set to write (0) or not initialized, returns -1.
	 * @param pin Pin to read
	 * @return Value of pin (-1 / 0 / 1)
	 */
	public static int read(String pin) {
		return -1;
	}
	/**
	 * Writes value to pin as powered (1) or off (0). Returns set value or -1 if pin is set to read or not initialized.
	 * @param pin Pin to set
	 * @param val Value to  set (0 / 1)
	 * @return Set value (-1 / 0 / 1)
	 */
	public static int write(String pin, int val) {
		return -1;
	}
	/**
	 * Tells system to unexport the pin.
	 * @param pin Pin to unexport
	 */
	public static void unexport(String pin) {
		
	}
	/**
	 * Returns current state of read (in) or write (out). Returns none if pin not initialized.
	 * @param pin Pin to test
	 * @return Current state (in / out / none)
	 */
	public static String state(String pin) {
		return "none";
	}
}