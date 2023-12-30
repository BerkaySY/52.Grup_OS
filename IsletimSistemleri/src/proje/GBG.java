package proje;

public class GBG {
	public int runGBG(UserQueue user_queue, RealTimeQueue rt_queue, ResourceManager resource_manager, MemoryManager memory_manager, int timer, int q)
	{
		Process process = user_queue.getFirst();
		if (!rt_queue.isEmpty())
		{
			if (process.getStatus() != null && process.getStatus() != ProcessStatus.SUSPENDED)
			{
				process.setStatus(ProcessStatus.SUSPENDED);
				process.PrintProcess();
			}
			memory_manager.DeallocateMemory(process);
			resource_manager.DeallocateResources(process);
			process.setAllocated(false);
			return timer;
		}
		int process_burst_time = process.getBurstTime();
		
		if (!user_queue.getPriQueue(1).isEmpty())
			timer = DecreasePriority(resource_manager, memory_manager, process, user_queue, 1, process_burst_time, timer, q);
		else if (!user_queue.getPriQueue(2).isEmpty())
			timer = DecreasePriority(resource_manager, memory_manager, process,user_queue, 2, process_burst_time, timer, q);
		
		return timer;
	}
	
	private int DecreasePriority(ResourceManager resource_manager, MemoryManager memory_manager, Process process, UserQueue user_queue, 
								 int priority, int process_burst_time, int timer, int q)
	{
		if(resource_manager.areResourcesEnough(process) && memory_manager.isReady(process))
		{
			resource_manager.AllocateResources(process);
			memory_manager.AllocateMemory(process);
			process.setAllocated(true);
			if (process.getStatus() == null)
				process.setStatus(ProcessStatus.STARTED);
			else if (process.getStatus() != ProcessStatus.SUSPENDED)
				process.setStatus(ProcessStatus.RESUMED);
			else if (process.getStatus() != ProcessStatus.STARTED)
				process.setStatus(ProcessStatus.RUNNING);
			else if (process.getStatus() != ProcessStatus.RESUMED)
				process.setStatus(ProcessStatus.RUNNING);
		
			process.PrintProcess();
			process_burst_time -= q;
			process.setBurstTime(process_burst_time);
			timer++;
			resource_manager.DeallocateResources(process);
			memory_manager.DeallocateMemory(process);
			process.setAllocated(false);
			if (process_burst_time == 0)
			{
				process.setStatus(ProcessStatus.COMPLETED);
				user_queue.Dequeue();
			}
			else
			{
				process.setStatus(ProcessStatus.SUSPENDED);
				process.setPriority(process.getPriority() + 1);
				user_queue.getPriQueue(priority + 1).add(process);
				user_queue.Dequeue();
			}
			process.PrintProcess();
		}
		
		
		return timer;
	}
}

