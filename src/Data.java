import java.util.HashMap;
import java.util.Map;

/**
 * @author omeryildirim
 *
 */
public class Data implements DataModel {

	private int sentenceCount = 0;
	private int avarageWordCount = 0;
	private Map<String, Integer> dataMap = new HashMap<String, Integer>();
	private Map<String, Integer> threadPool = new HashMap<String, Integer>();

	@Override
	public void setSentenceCount(int sentenceCount) {
		this.sentenceCount = sentenceCount;
	}

	@Override
	public Integer getSentenceCount() {
		return sentenceCount;
	}

	@Override
	public void setAvarageWordCount(int avarageWordCount) {
		this.avarageWordCount = avarageWordCount;
	}

	@Override
	public Integer getAvarageWordCount() {
		return avarageWordCount;
	}

	@Override
	public void setWord(String wordItem) {
		if (wordItem != null) {
			if (dataMap.containsKey(wordItem)) {
				int defaultCount = dataMap.get(wordItem);
				dataMap.replace(wordItem, defaultCount + 1);

			} else {
				dataMap.put(wordItem, 1);
			}
		}
	}

	@Override
	public Map<String, Integer> getData() {
		return dataMap;
	}

	@Override
	public Map<String, Integer> getThreadPoolData() {
		return threadPool;
	}

	@Override
	public void setThreadPoolItem(String threadName) {
		if (threadName != null) {
			if (threadPool.containsKey(threadName)) {
				int defaultCount = threadPool.get(threadName);
				threadPool.replace(threadName, defaultCount + 1);
			} else {
				threadPool.put(threadName, 1);
			}
		}
	}
}
