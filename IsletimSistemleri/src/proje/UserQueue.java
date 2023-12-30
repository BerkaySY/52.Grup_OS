package proje;

import java.util.List;
import java.util.ArrayList;

public class UserQueue implements Queue {
	private List<Process> priority1_queue;
	private List<Process> priority2_queue;
	private List<Process> priority3_queue;
	
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
	
	public void EnqueueRR(Process process)
	{
		priority3_queue.add(process);
	}
	
	public boolean check4RunRR()
	{
		if (priority1_queue.isEmpty() && priority2_queue.isEmpty())
			return true;
		
		return false;
	}
	
	public void CheckTimeOut(int timer)
	{
		for (int i = 0; i < priority1_queue.size(); i++)
		{
			if (timer - priority1_queue.get(i).getArrivalTime() > 20)
			{
				priority1_queue.get(i).setStatus(ProcessStatus.TIMEOUT);
				priority1_queue.get(i).PrintProcessError();
				priority1_queue.remove(i);
			}	
		}
	}
}
