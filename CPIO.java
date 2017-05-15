import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * The CPIO class aims to make GPIO easily accessible with Java on the Next Thing Co CHIP computer
 * The full repository along with README are available at https://github.com/Sudo-Tech/CPIO
 * @author SudoTech (https://www.youtube.com/sudotech)
 * @version 1.0
 */

public class CPIO {
	private Path path;
	
	/**
	 * Constructor for new CPIO object.
	 * @param pinName Pin to initialize (XIO-P0 - XIO-P7)
	 * @param state Initial state of read (in) or write (out)
	 * @throws IOException
	 */
	public CPIO(String pinName, String state) throws IOException, NumberFormatException {
		if (!(state.equals("in") || state.equals("out"))) {
			System.out.println("New CPIO requires valid state of in or out. Defaulting to in.");
			state = "in";
		}
		int pin = CPIO.pin(pinName);
		CPIO.writeToSysFile("/sys/class/gpio/export", "" + pin);
		this.path = Paths.get("/sys/class/gpio/gpio" + pin)
		if (state.equals("out"))
		    dir("out");
	}
	
	/**
	 * Reads current pin direction as read (in) or write (out).
	 * @return current direction (in / out)
	 * @throws IOException 
	 */
	public void dir(String dir) throws IOException {
		CPIO.writeToSysFile(this.path.resolve("direction"), dir);
	}
	
	/**
	 * Reads value of pin as powered/neutral (1) or ground (0).
	 * @return Value of pin (0 / 1)
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int read() throws NumberFormatException, IOException {
		return Integer.valueOf(CPIO.readFromSysFile(this.path.resolve("value")));
	}
	
	/**
	 * Writes value to pin as powered (1) or off (0). Returns set value or -1 if pin is set to read or not initialized.
	 * @param val Value to  set (0 / 1)
	 * @throws IOException 
	 */
	public void write(int val) throws IOException {
		CPIO.writeToSysFile(this.path.resolve("value"), "" + val);
	}
	
	/**
	 * Tells system to unexport the pin.
	 * @throws IOException 
	 */
	public void del() throws IOException {
		CPIO.writeToSysFile("/sys/class/gpio/unexport", "" + this.path.getFileName().substring(4));
		this.path = null;
	}
	
	/**
	 * Calculates system pin number from friendly string
	 * @param pin String that refers to pin (XIO-P0 - XIO-P7)
	 * @return Int that refers to pin for system
	 */
	private static int pin(String pin), NumberFormatException {
		return 1013 + Integer.valueOf(pin.substring(pin.length() - 1));
	}
	
	/**
	 * Writes to a system file
	 * @param file Path to file
	 * @param text Text to write into the file
	 * @throws IOException
	 */
	private static void writeToSysFile(Path file, String text) throws IOException {
		if(text == null)
			text = "";
		// Always write \n at end of text
		if(!text.endsWith("\n"))
			text = text + "\n";
		Files.write(file, text.getBytes(), StandardOpenOption.WRITE | StandardOpenOption.DSYNC)
	}
	
	/**
	 * Writes to a system file
	 * @param file Path to file
	 * @param text Text to write into the file
	 * @throws IOException
	 */
	private static void writeToSysFile(String file, String text) throws IOException {
		CPIO.writeToSysFile(Paths.get(file), text);
	}
	
	/**
	 * Reads from a system file
	 * @param file Path to file
	 * @return Content of file
	 * @throws IOException
	 */
	private static String readFromSysFile(Path file) throws IOException {
		byte[] data = Files.readAllBytes(file);
		return data == null ? null : new String(data);
	}
	
	/**
	 * Reads from a system file
	 * @param file Path to file
	 * @return Content of file
	 * @throws IOException
	 */
	private static String readFromSysFile(String file) throws IOException {
		return CPIO.readFromSysFile(Paths.get(file));
	}
}