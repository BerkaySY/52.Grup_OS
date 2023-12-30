package proje;

import java.util.ArrayList;
import java.util.List;

public class RealTimeQueue {
	private List<Process> realtime_queue;
	
	//Kurucu
	public RealTimeQueue()
	{
		realtime_queue = new ArrayList<>();
	}
	
	//Kuyruğa Ekleme İşlemi
	public void Enqueue(Process process)
	{
		realtime_queue.add(process);
	}
	
	//Kuyruktan Çıkarma İşlemi
	public void Dequeue()
	{
		if (!realtime_queue.isEmpty())
			realtime_queue.remove(0);
	}
	
	//Kuyruk boş mu işlemi
	public boolean isEmpty()
	{
		return realtime_queue.isEmpty();
	}
	
	//Kuyruğu elde etmek için getter fonksiyon
	public List<Process> getRTQueue()
	{
		return realtime_queue;
	}
	
	//Kuyruktaki ilk prosesi elde etmek için getter fonksiyon
	public Process getFirst()
	{
		return realtime_queue.get(0);
	}
	
	//Kuyruğun boyutunu elde etmek için getter fonksiyon
	public int getSize()
	{
		return realtime_queue.size();
	}
	
	//Proses zaman aşımına uğradı mı kontrol eder ve gerekli işlemleri yapar
	public void CheckTimeOut(int timer)
	{
		//Gerçek Zamanlı Kuyruktaki prosesleri dolaşıp şu anki zaman ile prosesin varış zamanı kontrol edilir.Aradaki fark 20'den büyükse proses silinir.
		for (int i = 0; i < realtime_queue.size(); i++)
		{
			if (timer - realtime_queue.get(i).getArrivalTime() > 20)
			{
				realtime_queue.get(i).setStatus(ProcessStatus.TIMEOUT);
				realtime_queue.get(i).PrintProcessError();
				realtime_queue.remove(i);
			}	
		}
	}
	
}
