package proje;

public class Process 
{
	private int _id;  				//Proses ID
	private int _arrival_time;		//Varış Zamanı
	private int _priority;			//Öncelik
	private int _burst_time;		//Patlama Zamanı
	private int _memory_size;		//Bellek Boyutu
	private int _printers;			//Kullandığı Yazıcı Sayısı
	private int _scanners;			//Kullandığı Tarayıcı Sayısı
	private int _modems;			//Kullandığı Modem Sayısı
	private int _cds;				//Kullandığı CD Sayısı
	private Color _color;			//Renk
	private ProcessStatus _status;	//Anlık Durumu
	private boolean _allocated; 	//Bellek ve Kaynak Tahsisi Yapıldı Mı
	
	//Kurucu
	public Process(int id, int arrival_time, int priority, int burst_time, int memory_size,
			   	   int printers, int scanners, int modems, int cds)
	{
		_id = id;
		_arrival_time = arrival_time;
		_priority = priority;
		_burst_time = burst_time;
		_memory_size = memory_size;
		_printers = printers;
		_scanners = scanners;
		_modems = modems;
		_cds = cds;
		_color = new Color();
		_status = null;
		_allocated = false;
	}
	
	//Diğer sınıflarda kullanılabilmeleri için getter fonksiyonları
	
	public int getId() { return _id; }
	
	public int getArrivalTime() { return _arrival_time; }
	
	public int getPriority() { return _priority; }
	
	public int getBurstTime() { return _burst_time; }
	
	public int getMemorySize() { return _memory_size; }
	
	public int getPrinters() { return _printers; }
	
	public int getScanners() { return _scanners; }
	
	public int getModems() { return _modems; }
	
	public int getCDs() { return _cds; }
	
	public ProcessStatus getStatus() { return _status; }
	
	public boolean getAllocated() { return _allocated; }
	
	//Diğer Sınıflarda Kullanılabilmeleri İçin Setter Fonksiyonları
	
	public void setBurstTime(int burst_time) { _burst_time = burst_time; }
	
	public void setStatus(ProcessStatus status) { _status = status; }
	
	public void setPriority(int priority) { _priority = priority; }
	
	public void setAllocated(boolean allocated) { _allocated = allocated; }
	
	//Prosesin Normal Durumlarında Yazdırılacaklar
	public void PrintProcess()
	{
		String text = "";
		switch(_status)
		{
			//Başlatıldı Durumu
			case STARTED:
				text += "Proses Başlatıldı ";
				break;
			//Yürütülüyor Durumu
			case RUNNING: 
				text += "Proses Yürütülüyor ";
				break;
			//Askıya Alınma Durumu
			case SUSPENDED:
				text += "Proses Askıya Alındı ";
				break;
			//Devam Edilme Durumu
			case RESUMED:
				text += "Prosese Devam Ediliyor ";
				break;
			//Tamamlanma Durumu
			case COMPLETED:
				text += "Proses Tamamlandı ";
				break;
		}
		
		//Prosesin anlık durumu ve bilgileriyle birlikte tuttuğu renk ile konsola yazdırılır.
		text += (" Proses ID: "+ _id + " Varış Zamanı: " + _arrival_time + " Öncelik: " + _priority +
				 " Kalan Süre: " + _burst_time + " Bellek Boyutu: " + _memory_size +
				 " Yazıcı: " + _printers + " Tarayıcı: " + _scanners + " Modem: " + _modems +
				 " CD: " + _cds);
		System.out.println("\033[38;2;" + _color.getColor() + "m" + text + "\033[0m");
	}
	
	//Prosesin Hatalı Durumları İçin Yazdırma Fonksiyonu
	public void PrintProcessError()
	{
		String text = "Proses ID: " + _id;
		switch(_status)
		{
			//Kullanıcı Prosesinin fazla kaynak istemesi durumu
			case REJECTED_USER_RESOURCE:
				text += " HATA! Proses çok sayıda kaynak talep ediyor - Proses Silindi";
				break;
			//Gerçek Zamanlı Prosesin fazla kaynak istemesi durumu
			case REJECTED_REAL_RESOURCE:
				text += " HATA! Gerçek zamanlı proses çok sayıda kaynak talep ediyor - Proses Silindi";
				break;
			//Kullanıcı Prosesinin fazla bellek talep etmesi durumu
			case REJECTED_USER_MEM:
				text += " HATA! Proses 960 MB'tan daha fazla bellek talep ediyor – Proses Silindi ";
				break;
			//Gerçek Zamanlı Prosesin fazla bellek talep etmesi durumu
			case REJECTED_REAL_MEM:
				text += " HATA! Gerçek zamanlı proses 64MB'tan daha fazla bellek talep ediyor - Proses Silindi";
				break;
			//Prosesin Zaman Aşımına Uğraması Durumu
			case TIMEOUT:
				text += " HATA! Proses zaman aşımı (20 sn de tamamlanamadı)";
				break;
		}
		//Prosesin tuttuğu renk ile hata yazdırılır.
		System.out.println("\033[38;2;" + _color.getColor() + "m" + text + "\033[0m");
	}
}


