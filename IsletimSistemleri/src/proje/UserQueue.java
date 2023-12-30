package proje;

import java.util.List;
import java.util.ArrayList;

public class UserQueue implements Queue {
	private List<Process> priority1_queue; //1.öncelikli kuyruk
	private List<Process> priority2_queue; //2.öncelikli kuyruk
	private List<Process> priority3_queue; //3.öncelikli kuyruk
	
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
		if (priority == 1)
			priority1_queue.add(process);
		else if (priority == 2)
			priority2_queue.add(process);
		else if (priority == 3)
			priority3_queue.add(process);
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
	
	//Kuyruktaki ilk değeri elde etme
	public Process getFirst()
	{
		if (!priority1_queue.isEmpty())
			return priority1_queue.get(0);
		else if (!priority2_queue.isEmpty())
			return priority2_queue.get(0);
		else if (!priority3_queue.isEmpty())
			return priority3_queue.get(0);
		
		return null;
	}
	
	//Kuyruk Boş mu Kontrol için
	public boolean isEmpty()
	{
		if (!priority1_queue.isEmpty())
			return priority1_queue.isEmpty();
		else if (!priority2_queue.isEmpty())
			return priority2_queue.isEmpty();
		else if (!priority3_queue.isEmpty())
			return priority3_queue.isEmpty();
		
		return true;
	}
	
	//Öncelik Seviyesine göre kuyruğu elde etme
	public List<Process> getPriQueue(int priority)
	{
		if (priority == 1)
			return priority1_queue;
		else if (priority == 2)
			return priority2_queue;
		else if (priority == 3)
			return priority3_queue;
		
		return null;
	}
	
	//RR için özel ekleme fonksiyonu
	public void EnqueueRR(Process process)
	{
		priority3_queue.add(process);
	}
	
	//RR için çalışma ortamı uygun mu kontrol eder
	public boolean check4RunRR()
	{
		if (priority1_queue.isEmpty() && priority2_queue.isEmpty())
			return true;
		
		return false;
	}
	
	//Proses zaman aşımına uğradı mı kontrol eder ve gerekli işlemleri yapar
	public void CheckTimeOut(int timer)
	{
		//1.Öncelikli Kuyruktaki prosesleri dolaşıp şu anki zaman ile prosesin varış zamanı kontrol edilir.Aradaki fark 20'den büyükse proses silinir.
		for (int i = 0; i < priority1_queue.size(); i++)
		{
			if (timer - priority1_queue.get(i).getArrivalTime() > 20)
			{
				priority1_queue.get(i).setStatus(ProcessStatus.TIMEOUT);
				priority1_queue.get(i).PrintProcessError();
				priority1_queue.remove(i);
			}	
		}
		//2.Öncelikli kuyruktaki prosesleri dolaşıp şu anki zaman ile prosesin varış zamanı kontrol edilir.Aradaki fark 20'den büyükse proses silinir.
		for (int i = 0; i < priority2_queue.size(); i++)
		{
			if (timer - priority2_queue.get(i).getArrivalTime() > 20)
			{
				priority2_queue.get(i).setStatus(ProcessStatus.TIMEOUT);
				priority2_queue.get(i).PrintProcessError();
				priority2_queue.remove(i);
			}	
		}
		//3.Öncelikli kuyruktaki prosesleri dolaşıp şu anki zaman ile prosesin varış zamanı kontrol edilir.Aradaki fark 20'den büyükse proses silinir.
		for (int i = 0; i < priority3_queue.size(); i++)
		{
			if (timer - priority3_queue.get(i).getArrivalTime() > 20)
			{
				priority3_queue.get(i).setStatus(ProcessStatus.TIMEOUT);
				priority3_queue.get(i).PrintProcessError();
				priority3_queue.remove(i);
			}	
		}
	}
}
