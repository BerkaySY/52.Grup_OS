package proje;

public class RR 
{
	public int runRR(UserQueue user_queue, ResourceManager resource_manager, MemoryManager memory_manager, int timer, int q)
    {
		Process process;
		//3.öncelikli kuyruk boş olmadığı sürece yapılacaklar
        while (!user_queue.getPriQueue(3).isEmpty())
        {
            process = user_queue.getFirst();
            int process_burst_time = process.getBurstTime();
            //Kaynak ve bellek tahsisi yapılır
            if (memory_manager.isReady(process) && resource_manager.areResourcesEnough(process))
            {
            	memory_manager.AllocateMemory(process);
            	resource_manager.AllocateResources(process);
            	process.setAllocated(true);
            }
            if (process_burst_time != 0 && process.getAllocated())
            {
            	//Prosesin Durumu Değiştirilir
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
            	//Proses patladıysa kaynak ve bellek serbest bırakılır prosesin tamamlandığı yazılır
            	if (process_burst_time == 0)
            	{
            		memory_manager.DeallocateMemory(process);
            		resource_manager.DeallocateResources(process);
            		process.setAllocated(false);
            		process.setStatus(ProcessStatus.COMPLETED);
            		process.PrintProcess();
            		user_queue.Dequeue();
            		user_queue.CheckTimeOut(timer);
            	}
            	//Diğer türlü Proses Askıya Alınır ve RR algoritmasına göre işlemleri yapılır
            	else
            	{
            		memory_manager.DeallocateMemory(process);
            		resource_manager.DeallocateResources(process);
            		process.setAllocated(false);
            		process.setStatus(ProcessStatus.SUSPENDED);
            		process.PrintProcess();
            		user_queue.Dequeue();
            		user_queue.EnqueueRR(process);
            		user_queue.CheckTimeOut(timer);
            	}
            }  
            //Kaynak ve bellek tahsisi yoksa
            else
            	timer++;
        }

        return timer;
    }
}
