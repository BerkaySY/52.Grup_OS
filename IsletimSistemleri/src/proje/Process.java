package proje;

public class Process {
	private int _id;
	private int _arrival_time;
	private int _priority;
	private int _burst_time;
	private int _memory_size;
	private int _printers;
	private int _scanners;
	private int _modems;
	private int _cds;
	private Color _color;
	private ProcessStatus _status;
	private boolean _allocated; 
	
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
	
	public void setBurstTime(int burst_time) { _burst_time = burst_time; }
	
	public void setStatus(ProcessStatus status) { _status = status; }
	
	public void setPriority(int priority) { _priority = priority; }
	
	public void setAllocated(boolean allocated) { _allocated = allocated; }
	
	public void PrintProcess()
	{
		String text = "";
		switch(_status)
		{
			case STARTED:
				text += "Proses Başlatıldı ";
				break;
			case RUNNING: 
				text += "Proses Yürütülüyor ";
				break;
			case SUSPENDED:
				text += "Proses Askıya Alındı ";
				break;
			case RESUMED:
				text += "Prosese Devam Ediliyor ";
				break;
			case COMPLETED:
				text += "Proses Tamamlandı ";
				break;
		}
		
		text += (" Proses ID: "+ _id + " Varış Zamanı: " + _arrival_time + " Öncelik: " + _priority +
				 " Kalan Süre: " + _burst_time + " Bellek Boyutu: " + _memory_size +
				 " Yazıcı: " + _printers + " Tarayıcı: " + _scanners + " Modem: " + _modems +
				 " CD: " + _cds);
		System.out.println("\033[38;2;" + _color.getColor() + "m" + text + "\033[0m");
	}
	
	public void PrintProcessError()
	{
		String text = "Proses ID: " + _id;
		switch(_status)
		{
			case REJECTEDUSERRESOURCE:
				text += " HATA! Proses çok sayıda kaynak talep ediyor - Proses Silindi";
				break;
			case REJECTEDREALRESOURCE:
				text += " HATA! Gerçek zamanlı proses çok sayıda kaynak talep ediyor - Proses Silindi";
				break;
			case REJECTEDUSERMEM:
				text += " HATA! Proses 960 MB'tan daha fazla bellek talep ediyor – Proses Silindi ";
				break;
			case REJECTEDREALMEM:
				text += " HATA! Gerçek zamanlı proses 64MB'tan daha fazla bellek talep ediyor - Proses Silindi";
				break;
			case TIMEOUT:
				text += " HATA! Proses zaman aşımı (20 sn de tamamlanamadı)";
				break;
		}
		System.out.println("\033[38;2;" + _color.getColor() + "m" + text + "\033[0m");
	}
}


