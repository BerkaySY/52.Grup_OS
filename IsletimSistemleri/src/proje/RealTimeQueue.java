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
	
	//Diğer sınıflarda kullanılabilmesi için kuyruk boş mu işlemi
	public boolean isEmpty()
	{
		return realtime_queue.isEmpty();
	}
	
	public List<Process> getRTQueue()
	{
		return realtime_queue;
	}
	
	public Process getFirst()
	{
		return realtime_queue.get(0);
	}
	
	public int getSize()
	{
		return realtime_queue.size();
	}
}
