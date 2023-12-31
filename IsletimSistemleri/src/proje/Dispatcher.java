package proje;

public class Dispatcher 
{
	private ResourceManager resource_manager; //Kaynak Yöneticisi
	private MemoryManager memory_manager;	  //Bellek Yöneticisi
	private UserQueue user_queue;			  //Kullanıcı Proses Kuyruğu
	private RealTimeQueue rt_queue;			  //Gerçek Zamanlı Proses Kuyruğu
	private ProcessList process_list;		  //Proses Listesi
	private GBG gbg;						  //GBG Algoritması
	private FCFS fcfs;						  //FCFS Algoritması
	private RR rr;							  //Round Robin Algoritması
	int timer;								  //Zamanlayıcı
	int q;									  //Quantum Zamanı
	
	//Kurucu
	public Dispatcher()
	{
		resource_manager = new ResourceManager();
		memory_manager = new MemoryManager();
		user_queue = new UserQueue();
		rt_queue = new RealTimeQueue();
		process_list = new ProcessList();
		timer = 0;
		q = 1;	//Quantum zamanı 1 atanıyor
		gbg = new GBG();
		fcfs = new FCFS();
		rr = new RR();
	}
	
	//Programı Çalıştıran Ana Fonksiyon
	public void Execute(String FileName)
	{
		//Girdi verilen dosyayı okuma kısmı dosya okunabiliyorsa proses listesine aktararak programa devam eder.
		//Okunamadıysa hata döndürüp programı bitirir.
		try 
		{
			process_list.ReadFile(FileName);
		} 
		catch (Exception e) 
		{
			System.out.println("Dosya Okunamadı: " + e.getMessage());
		}
		
		//Proses listesindeki gerçek zamanlı proses sayısını tutar.FCFS'nin ne zaman biteceğinin belirlenmesi için gerekli.
		int rt_process_count = process_list.getRTProcessCount();
		//Gerçek Zamanlı Kuyruk, Kullanıcı Kuyrukları ve Proses listesi boş olduğunda program sonlanır.
		while (!user_queue.isEmpty() || !rt_queue.isEmpty() || !process_list.isEmpty()) 
		{
			//Proses listesi boş değilse proses listesinde bulunulan timer zamanındaki prosesler ilgili kuyruklara aktarılır.
			if (!process_list.isEmpty())
			{
				while (!process_list.isEmpty() && process_list.getFirst().getArrivalTime() == timer)
				{
					Process process = process_list.getFirst();
					int priority = process.getPriority();
					//Eğer sistemin karşıladığı bellek boyutundan fazla bellek isteme olursa o prosesi reddeder.
					if (!memory_manager.isValid(process))
					{
						if (priority == 0)
						{
							process.setStatus(ProcessStatus.REJECTED_REAL_MEM);
							rt_process_count--;
						}
						else 
							process.setStatus(ProcessStatus.REJECTED_USER_MEM);
						process.PrintProcessError();
					}
					//Eğer sistemin karşıladığı kaynaklardan fazla kaynak isteme olursa o prosesi reddeder.
					else if (!resource_manager.isValid(process))
					{
						if (priority == 0)
						{
							process.setStatus(ProcessStatus.REJECTED_REAL_RESOURCE);
							rt_process_count--;
						}
						else
							process.setStatus(ProcessStatus.REJECTED_USER_RESOURCE);
						process.PrintProcessError();
					}
					//Bellek ve Kaynak isteme geçerli ise proses ilgili kuyruğa aktarılır.
					else
					{
						if (priority == 0) 
							rt_queue.Enqueue(process);
						else 
							user_queue.Enqueue(process);
					}
					//Proses, proses listesinden çıkarılır.
					process_list.getProcessList().remove(0);
				}
			}
			
			//İki kuyrukta boşsa zamanlayıcı 1 arttırılır.Proseslerin reddedilmesi durumu için yazıldı.
			if (user_queue.isEmpty() && rt_queue.isEmpty())
				timer++;
			//Herhangi bir kuyruk doluysa yapılacaklar
			else
			{
				//Uygunsa GBG Algoritmasını Çağırır
				if (!user_queue.isEmpty())
					timer = gbg.runGBG(user_queue, rt_queue, resource_manager, memory_manager, timer, q);
				//Uygunsa FCFS Algoritmasını Çağırır.
				if (!rt_queue.isEmpty())
					timer = fcfs.runFCFS(rt_queue, user_queue, process_list, resource_manager, memory_manager, timer, rt_process_count);
				//Uygunsa Round Robin Algoritmasını Çağırır.
				if (process_list.isEmpty() && user_queue.check4RunRR())
					timer = rr.runRR(user_queue, resource_manager, memory_manager, timer, q);
			}
	    }
		//Programın sonlanmasını bildirir.
	    System.out.println("Program Sonlandı!");
	}	
}
