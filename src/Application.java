
/**
 * @author omeryildirim
 *
 */
public class Application {
	
	// to put global data
	public static DataHelper helper = new DataHelperImpl(5);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "/Users/omeryildirim/eclipse-workspace/Wordy/src/readme.txt";
		MainTask.newInstance(helper.getThreadCount(), filePath).start();
	}
}
