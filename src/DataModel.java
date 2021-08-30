import java.util.Map;

/**
 * @author omeryildirim
 *
 */
public interface DataModel {
	public void setSentenceCount(int sentenceCount);
	public Integer getSentenceCount();
	public void setAvarageWordCount(int avarageWordCount);
	public Integer getAvarageWordCount() ;
	public void setWord(String wordItem);
	public Map<String, Integer> getData();
	public Map<String, Integer> getThreadPoolData();
	public void setThreadPoolItem(String threadName);
}
