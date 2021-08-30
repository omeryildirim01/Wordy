/**
 * @author omeryildirim
 *
 */
public class SubTask implements Runnable {

	private String sentence;

	public SubTask(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public void run() {
		processGivenSentence();
		Application.helper.setThreadPoolItem(Thread.currentThread().getName());
	}

	private void processGivenSentence() {
		String[] wordArray = sentence.split(" ");
		for (int i = 0; i < wordArray.length; i++) {
			Application.helper.setWordValue(wordArray[i]);
		}
	}

}