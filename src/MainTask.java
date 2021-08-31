import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author omeryildirim
 *
 */
public class MainTask extends Thread {

	private String filePath;
	private int threadCount;

	private MainTask(int threadCount, String filePath) {
		this.threadCount = threadCount;
		this.filePath = filePath;
	}

	public static MainTask newInstance(int threadCount, String filePath) {
		return new MainTask(threadCount, filePath);
	}

	@Override
	public void run() {
		try {
			List<String> dataList = readFile(filePath);
			if (dataList != null && !dataList.isEmpty()) {
				Application.helper.setSentenceCount(dataList.size());
				Application.helper.setAvarageWordCount(dataList);
				executeTask(dataList);
				printResult();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// to start sub tasks
	private void executeTask(List<String> dataList) {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
			dataList.forEach((item)-> {
				executorService.submit(new SubTask(item));
			});
			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * to read given file
	 */
	private List<String> readFile(String filePath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
		return getSentences(content);
	}

	/**
	 * to get sentence list
	 */
	private List<String> getSentences(String sentences) {
		List<String> sentenceList = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(sentences, "\".:;?![]()'");
		while (tokenizer.hasMoreTokens()) {
			sentenceList.add(tokenizer.nextToken().replace(",", " ").replace("\n", " ").trim());
		}
		return sentenceList;
	}

	/**
	 * to print result
	 */
	private void printResult() {
		System.out.println(" Sentence Count :" + Application.helper.getSentenceCount());
		System.out.println(" Avg. Word Count :" + Application.helper.getAvarageWordCount());
		Application.helper.printThreadPoolList();
		Application.helper.printDataList();
	}
}
