package proje;

public class FCFS 
{	
	public int runFCFS(RealTimeQueue rt_queue,UserQueue user_queue, ProcessList process_list, ResourceManager resource_manager, 
					   MemoryManager memory_manager, int timer, int rt_process_count)
	{
		//FCFS başlatıldı mı bütün gerçek zamanlı prosesler bitecek bu yüzden dosyadan gelen gerçek zamanlı proses sayısına göre döngüye girdirildi.
		while (rt_process_count != 0)
		{
			//Gerçek Zamanlı Proses Kuyruğu boş değilse
			if (!rt_queue.isEmpty())
			{
				Process process = rt_queue.getFirst();
				//Proses bellek tahsisine hazırsa bellek tahsisi yapılır
				if (memory_manager.isReady(process))
				{
					memory_manager.AllocateMemory(process);
					process.setAllocated(true);
				}
				//Prosese bellek tahsis edilmişse yapılacaklar
				if (process.getAllocated())
				{
					int process_burst_time = process.getBurstTime();
					//Proses patlayana kadar devam et
					while (process_burst_time != 0)
					{
						//Prosesin Durumunu değiştirir.
						if (process.getStatus() == null)
							process.setStatus(ProcessStatus.STARTED);
						else if (process.getStatus() != ProcessStatus.RUNNING)
							process.setStatus(ProcessStatus.RUNNING);
					
						process.PrintProcess();
						process_burst_time--;
						process.setBurstTime(process_burst_time);
						//Timer değişeceğinden o timer zamanında prosesler varsa proses listesinden çekmek için
						addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
						timer++;
					}
					//Proses tamamlandıysa yapılacaklar
					memory_manager.DeallocateMemory(process);
					process.setAllocated(false);
					process.setStatus(ProcessStatus.COMPLETED);
					process.PrintProcess();
					rt_queue.Dequeue();
					rt_process_count--;
					//Timer değişeceğinden o timer zamanında prosesler varsa proses listesinden çekmek için
					addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
					//Proses tamamlandıktan sonra zaman aşımı yaşanan prosesleri yazdırma
					rt_queue.CheckTimeOut(timer);
					user_queue.CheckTimeOut(timer);
					timer++;
				}
				//Bellek Tahsisi yapılamadıysa timer arttırılır
				else
					timer++;
			}
			//Kuyruk boşsa yapılacaklar
			else
			{
				addProcess2Queues(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
				timer++;
			}
		}
		return timer;
	}
	//Dispatcher sınıfındaki proses listesinden prosesleri çekme işlemiyle bire bir aynıdır
	private void addProcess2Queues(RealTimeQueue rt_queue, UserQueue user_queue,  ProcessList process_list, 
								   ResourceManager resource_manager, MemoryManager memory_manager, int timer, int rt_process_count)
	{
		if (!process_list.isEmpty())
		{
			while (!process_list.isEmpty() && process_list.getFirst().getArrivalTime() == timer)
			{
				Process process = process_list.getFirst();
				int priority = process.getPriority();
				//Prosesin istediği bellek geçerli mi kontrol eder
				if (!memory_manager.isValid(process))
				{
					//Gerçek zamanlı için
					if (priority == 0)
					{
						process.setStatus(ProcessStatus.REJECTED_REAL_MEM);
						rt_process_count--;
					}
					//Kullanıcı prosesi için
					else 
						process.setStatus(ProcessStatus.REJECTED_USER_MEM);
					process.PrintProcessError();
				}
				//Prosesin istediği kaynaklar geçerli mi kontrol eder
				else if (!resource_manager.isValid(process))
				{
					//Gerçek Zamanlı İçin
					if (priority == 0)
					{
						process.setStatus(ProcessStatus.REJECTED_REAL_RESOURCE);
						rt_process_count--;
					}
					//Kullanıcı prosesi için
					else
						process.setStatus(ProcessStatus.REJECTED_USER_RESOURCE);
					process.PrintProcessError();
				}
				//Kaynak ve Bellek istemeleri geçerli ise ilgili kuyruğa ekleme yapar
				else
				{
					if (priority == 0) 
						rt_queue.Enqueue(process);
					else 
						user_queue.Enqueue(process);
				}
				//Proses listesinden çıkartır.
				process_list.getProcessList().remove(0);
			}
		}
	}
}
