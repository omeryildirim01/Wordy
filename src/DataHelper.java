import java.util.List;

/**
 * @author omeryildirim
 *
 */
public interface DataHelper {
	
	public void setSentenceCount(int size);

	public Integer getSentenceCount();

	public void setAvarageWordCount(List<String> sentenceList);

	public Integer getAvarageWordCount();

	public void setWordValue(String givenWord);
	
	public void setThreadPoolItem(String threadName);

	public void printDataList();

	public void printThreadPoolList();

	public Integer getThreadCount();
}
