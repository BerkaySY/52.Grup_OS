package proje;

public class Process {
	private int _id;
	private int _arrival_time;
	private int _priority;
	private int _execution_time;
	private int _memory_size;
	private int _printers;
	private int _scanners;
	private int _modems;
	private int _cds;
	
	public Process(int id, int arrival_time, int priority, int execution_time, int memory_size,
			   	   int printers, int scanners, int modems, int cds)
	{
		_id = id;
		_arrival_time = arrival_time;
		_priority = priority;
		_execution_time = execution_time;
		_memory_size = memory_size;
		_printers = printers;
		_scanners = scanners;
		_modems = modems;
		_cds = cds;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public int getArrivalTime()
	{
		return _arrival_time;
	}
	
	public int getPriority()
	{
		return _priority;
	}
	
	public int getExecutionTime()
	{
		return _execution_time;
	}
	
	public int getMemorySize()
	{
		return _memory_size;
	}
	
	public int getPrinters()
	{
		return _printers;
	}
	
	public int getScanners()
	{
		return _scanners;
	}
	
	public int getModems()
	{
		return _modems;
	}
	
	public int getCDs()
	{
		return _cds;
	}
	
}


