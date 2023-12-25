package proje;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Tamam
public class ProcessList {
	private List<Process> _process_list;
	int rt_process_count;
	
	public ProcessList()
	{
		_process_list = new ArrayList<>();
		rt_process_count = 0;
	}
	
	public void ReadFile(String file) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int id = 0;
		
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
			_process_list.add(process);
			if (process.getPriority() == 0)
				rt_process_count++;
			id++;
		}
		
		br.close();
	}
	
	public List<Process> getProcessList() { return _process_list; }
}
