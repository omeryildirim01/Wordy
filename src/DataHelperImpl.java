import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author omeryildirim
 *
 */
public class DataHelperImpl implements DataHelper {

	private Integer threadCount;
	private Data localData;
	private static final Object locker = new Object();

	public DataHelperImpl(Integer defaultThreadCount) {
		localData = new Data();
		this.threadCount = defaultThreadCount;
	}

	public DataHelperImpl() {
		localData = new Data();
		this.threadCount = 5;
	}

	/**
	 * set sentence count
	 */
	@Override
	public void setSentenceCount(int size) {
		synchronized (locker) {
			localData.setSentenceCount(size);
		}
	}

	/**
	 * get sentence count
	 */
	@Override
	public Integer getSentenceCount() {
		synchronized (locker) {
			return localData.getSentenceCount();
		}
	}

	/**
	 * set avarage word count
	 */
	@Override
	public void setAvarageWordCount(List<String> sentenceList) {
		synchronized (locker) {
			if (sentenceList != null && !sentenceList.isEmpty()) {
				List<Integer> wordCountList = new ArrayList<Integer>();
				sentenceList.stream().forEach((sentence) -> {
					wordCountList.add(sentence.split(" ").length);
				});
				Integer totalCount = 0;
				for (int c : wordCountList) {
					totalCount = totalCount + c;
				}
				localData.setAvarageWordCount(totalCount / sentenceList.size());
			}
		}
	}

	/**
	 * get avarage word count
	 */
	@Override
	public synchronized Integer getAvarageWordCount() {
		return localData.getAvarageWordCount();
	}

	/**
	 * set Word values
	 */
	@Override
	public synchronized void setWordValue(String givenWord) {
		localData.setWord(givenWord);
	}

	/**
	 * set thread pool task item
	 */
	@Override
	public synchronized void setThreadPoolItem(String threadName) {
		localData.setThreadPoolItem(threadName);
	}

	/**
	 * print data list
	 */
	@Override
	public void printDataList() {
		Map<String, Integer> data = localData.getData();
		data.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));

	}

	/**
	 * print thread pool items
	 */
	@Override
	public void printThreadPoolList() {
		Map<String, Integer> data = localData.getThreadPoolData();
		System.out.println("Thread counts :");
		data.entrySet().stream().forEachOrdered(
				entry -> System.out.println("\t ThreadId= " + entry.getKey() + ", Count=" + entry.getValue()));

	}

	/**
	 * get thread count
	 */
	@Override
	public Integer getThreadCount() {
		return threadCount;
	}
}
