package proje;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class UserQueue implements Queue 
{
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
		//1.Öncelikli Kuyruğa Ekleme İşlemi
		if (priority == 1)
			priority1_queue.add(process);
		//2.Öncelikli Kuyruğa Ekleme İşlemi
		else if (priority == 2)
			priority2_queue.add(process);
		//3.Öncelikli Kuyruğa Ekleme İşlemi
		else if (priority == 3)
			priority3_queue.add(process);
	}
	
	//Kuyruktan Çıkarma İşlemi
	public void Dequeue()
	{
		//1.Öncelikli Kuyruktan Çıkarma İşlemi
		if (!priority1_queue.isEmpty())
			priority1_queue.remove(0);
		//2.Öncelikli Kuyruktan Çıkarma İşlemi
		else if (!priority2_queue.isEmpty())
			priority2_queue.remove(0);
		//3.Öncelikli Kuyruktan Çıkarma İşlemi
		else if (!priority3_queue.isEmpty())
			priority3_queue.remove(0);
	}
	
	//Kuyruktaki ilk değeri elde etme
	public Process getFirst()
	{
		//1.Öncelikli Kuyruktaki İlk Değeri Elde Etme İşlemi
		if (!priority1_queue.isEmpty())
			return priority1_queue.get(0);
		//2.Öncelikli Kuyruktaki İlk Değeri Elde Etme İşlemi
		else if (!priority2_queue.isEmpty())
			return priority2_queue.get(0);
		//3.Öncelikli Kuyruktaki İlk Değeri Elde Etme İşlemi
		else if (!priority3_queue.isEmpty())
			return priority3_queue.get(0);
		
		return null;
	}
	
	//Kuyruk Boş mu Kontrol için
	public boolean isEmpty()
	{
		//1.Öncelikli Kuyruk Boş Mu Kontrol Etme İşlemi
		if (!priority1_queue.isEmpty())
			return priority1_queue.isEmpty();
		//2.Öncelikli Kuyruk Boş Mu Kontrol Etme İşlemi
		else if (!priority2_queue.isEmpty())
			return priority2_queue.isEmpty();
		//3.Öncelikli Kuyruk Boş Mu Kontrol Etme İşlemi
		else if (!priority3_queue.isEmpty())
			return priority3_queue.isEmpty();
		
		return true;
	}
	
	//Öncelik Seviyesine göre kuyruğu elde etme
	public List<Process> getPriQueue(int priority)
	{
		//1.Öncelikli Kuyruğu Elde Etme İşlemi
		if (priority == 1)
			return priority1_queue;
		//2.Öncelikli Kuyruğu Elde Etme İşlemi
		else if (priority == 2)
			return priority2_queue;
		//3.Öncelikli Kuyruğu Elde Etme İşlemi
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
	    // 1. Öncelikli Kuyruktaki prosesleri kontrol et
	    CheckTimeoutForQueue(priority1_queue, timer);
	    // 2. Öncelikli Kuyruktaki prosesleri kontrol et
	    CheckTimeoutForQueue(priority2_queue, timer);
	    // 3. Öncelikli Kuyruktaki prosesleri kontrol et
	    CheckTimeoutForQueue(priority3_queue, timer);
	}
	
	//Parametre olarak verilen kuyrukta bulunan prosesler zaman aşımına uğramış mı  kontrol eder
	private void CheckTimeoutForQueue(List<Process> queue, int timer)
	{
	    Iterator<Process> iterator = queue.iterator();
	    while (iterator.hasNext()) 
	    {
	        Process process = iterator.next();
	        if (timer - process.getArrivalTime() > 20) 
	        {
	            process.setStatus(ProcessStatus.TIMEOUT);
	            process.PrintProcessError();
	            iterator.remove();
	        }
	    }
	}
}
