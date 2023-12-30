package proje;



public class FCFS {	
	//Gerçek Zamanlı kuyrukların hepsi bitecek
	public int runFCFS(RealTimeQueue rt_queue,UserQueue user_queue, ProcessList process_list, ResourceManager resource_manager, 
					   MemoryManager memory_manager, int timer, int rt_process_count)
	{
		
		while (rt_process_count != 0)
		{
			rt_queue.CheckTimeOut(timer);
			user_queue.CheckTimeOut(timer);
			if (!rt_queue.isEmpty())
			{
				Process process = rt_queue.getFirst();
				int process_burst_time = process.getBurstTime();
				while (process_burst_time != 0)
				{
					if (process.getStatus() == null)
						process.setStatus(ProcessStatus.STARTED);
					else if (process.getBurstTime() > 0 && process.getStatus() != ProcessStatus.RUNNING)
						process.setStatus(ProcessStatus.RUNNING);
					
					process.PrintProcess();
					process_burst_time--;
					process.setBurstTime(process_burst_time);
					rt_queue.CheckTimeOut(timer);
					user_queue.CheckTimeOut(timer);
					addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
					timer++;
				}
			
				process.setStatus(ProcessStatus.COMPLETED);
				process.PrintProcess();
				rt_queue.Dequeue();
				rt_process_count--;
				addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
				timer++;
			}
			else
			{
				addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
				timer++;
				System.out.print(rt_process_count);
			}
		}			
		
		return timer;
	}
	
	private void addProcess2Queues(RealTimeQueue rt_queue, UserQueue user_queue,  ProcessList process_list, 
								   ResourceManager resource_manager, MemoryManager memory_manager, int timer, int rt_process_count)
	{
		if (!process_list.isEmpty())
		{
			while (!process_list.isEmpty() && process_list.getFirst().getArrivalTime() == timer)
			{
				Process process = process_list.getFirst();
				int priority = process.getPriority();
				if (!memory_manager.isValid(process))
				{
					if (priority == 0)
					{
						process.setStatus(ProcessStatus.REJECTEDREALMEM);
						rt_process_count--;
					}
					else 
						process.setStatus(ProcessStatus.REJECTEDUSERMEM);
					process.PrintProcessError();
				}
				else if (!resource_manager.isValid(process))
				{
					if (priority == 0)
					{
						process.setStatus(ProcessStatus.REJECTEDREALRESOURCE);
						rt_process_count--;
					}
					else
						process.setStatus(ProcessStatus.REJECTEDUSERRESOURCE);
					process.PrintProcessError();
				}
				else
				{
					if (priority == 0) 
						rt_queue.Enqueue(process);
					else 
						user_queue.Enqueue(process);
				}
			
				process_list.getProcessList().remove(0);
			}
		}
	}
	
	
}
