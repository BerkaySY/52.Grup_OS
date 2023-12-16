package proje;

import java.util.List;
import java.util.ArrayList;

public class UserQueue {
	private List<Process> priority1_queue; //1.Öncelik
	private List<Process> priority2_queue; //2.Öncelik
	private List<Process> priority3_queue; //3.Öncelik
	
	//Kurucu
	public UserQueue() {
		priority1_queue = new ArrayList<>();
		priority2_queue = new ArrayList<>();
		priority3_queue = new ArrayList<>();
	}
	
	//Kuyruğa Ekleme İşlemi
	public void Enqueue(Process process)
	{
		int priority = process.getPriority();
		switch (priority)
		{
			case 1:
				priority1_queue.add(process);
				break;
			case 2:
				priority2_queue.add(process);
				break;
			case 3:
				priority3_queue.add(process);
				break;
			default:
				break;
		}
	}
	
	//Kuyruktan Çıkarma İşlemi
	public void Dequeue()
	{
		if (!priority1_queue.isEmpty())
			priority1_queue.remove(0);
		else if (!priority2_queue.isEmpty())
			priority2_queue.remove(0);
		else if (!priority3_queue.isEmpty())
			priority3_queue.remove(0);
	}
	
	//Diğer Sınıflarda Kullanılması için Boş mu kontrol eden fonksiyon
	public boolean isEmpty()
	{
		return priority1_queue.isEmpty() && priority2_queue.isEmpty()
			   && priority3_queue.isEmpty();
	}
	
}
