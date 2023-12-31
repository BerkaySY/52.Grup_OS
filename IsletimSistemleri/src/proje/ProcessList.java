package proje;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Tamam
public class ProcessList 
{
	private List<Process> _process_list; //Prosesleri tutan liste
	int rt_process_count;	//Gerçek zamanlı proses sayısı
	
	//Kurucu
	public ProcessList()
	{
		_process_list = new ArrayList<>();
		rt_process_count = 0;
	}
	
	//txt içindeki verileri okuyup proses nesnelerini oluşturan fonksiyon
	public void ReadFile(String file) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int id = 0;
		//txt dosyasındaki verileri satır satır okuyarak Proses sınıfında gerekli yerlere atama yapar
		while ((line = br.readLine()) != null)
		{
			String[] tokens = line.split(",");
			
			int arrival_time   = Integer.parseInt(tokens[0].trim());
			int priority       = Integer.parseInt(tokens[1].trim());
			int execution_time = Integer.parseInt(tokens[2].trim());
			int memory_size    = Integer.parseInt(tokens[3].trim());
			int printers       = Integer.parseInt(tokens[4].trim());
			int scanners       = Integer.parseInt(tokens[5].trim());
			int modems         = Integer.parseInt(tokens[6].trim());
			int cds            = Integer.parseInt(tokens[7].trim());
			
			Process process = new Process(id, arrival_time, priority, execution_time, memory_size,
										  printers, scanners, modems, cds);
			//Prosesi proses listesine ekler.
			_process_list.add(process);
			//Gerçek zamanlı proses sayısını arttırır
			if (process.getPriority() == 0)
				rt_process_count++;
			
			id++;
		}
		
		br.close();
	}
	
	//Diğer sınıflarda kullanabilmek için getter fonksiyon
	public List<Process> getProcessList() { return _process_list; }
	
	//Proses listesi boş mu kontrol için
	public boolean isEmpty()
	{
		return _process_list.isEmpty();
	}
	
	//Proses listesinin en başındaki prosesi döndürür
	public Process getFirst()
	{
		return _process_list.get(0);
	}
	
	//Gerçek zamanlı proses sayısını döndüren getter fonksiyon
	public int getRTProcessCount()
	{
		return rt_process_count;
	}
	
	
}
