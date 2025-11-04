/******************************************************************************************
 * This class contains a notifier that is used when running the light model on 
 * multiple threads
*******************************************************************************************/

public class MLightNotifier
{
	private boolean notice[];
	
	public MLightNotifier(int threadCount)
	{
		notice = new boolean[threadCount];
	}
	
	public synchronized void setComplete(int index)
	{
		notice[index]=true;
	}
	
	public void reset()
	{
		for(int i=0; i<notice.length; ++i)
			notice[i] = false;
	}
	
	public synchronized boolean allComplete()
	{
		for(int i=0; i<notice.length; ++i)
		{
			if(!notice[i])
				return false;
		}
		return true;
	}
}