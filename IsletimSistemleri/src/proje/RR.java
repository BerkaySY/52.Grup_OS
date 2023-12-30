package proje;

public class RR {
	public int runRR(UserQueue user_queue, ResourceManager resource_manager, MemoryManager memory_manager, int timer, int q)
    {
		Process process;
        while (!user_queue.getPriQueue(3).isEmpty())
        {
            process = user_queue.getFirst();
            int process_burst_time = process.getBurstTime();
            if (process_burst_time != 0)
            {
            	if (process.getStatus() == null)
            		process.setStatus(ProcessStatus.STARTED);
            	else if (process.getStatus() == ProcessStatus.SUSPENDED)
            		process.setStatus(ProcessStatus.RESUMED);
            	else if (process.getStatus() == ProcessStatus.STARTED || process.getStatus() == ProcessStatus.RESUMED)
            		process.setStatus(ProcessStatus.RUNNING);
            	
            	process.PrintProcess();
            	process_burst_time -= q;
            	process.setBurstTime(process_burst_time);
            	timer++;
            	if (process_burst_time == 0)
            	{
            		process.setStatus(ProcessStatus.COMPLETED);
            		process.PrintProcess();
            		user_queue.Dequeue();
            	}
            	else
            	{
            		process.setStatus(ProcessStatus.SUSPENDED);
            		process.PrintProcess();
            		user_queue.Dequeue();
            		user_queue.EnqueueRR(process);
            	}
            }  
        }

        return timer;
    }
}
