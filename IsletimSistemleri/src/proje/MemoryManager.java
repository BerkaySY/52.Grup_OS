package proje;

import java.util.List;
import java.util.ArrayList;

public class MemoryManager 
{
	private List<MemoryBlock> rt_memory_blocks; 	//Gerçek Zamanlı Proseslerin Bellek Bloklarını Tutar.
	private List<MemoryBlock> user_memory_blocks;	//Kullanıcı Proseslerinin Bellek Bloklarını Tutar.
	private int rt_memory_size;						//Gerçek Zamanlı Proseslere Ayrılan Bellek Boyutu
	private int user_memory_size;					//Kullanıcı Proseslerine Ayrılan Bellek Boyutu
	private int remaining_rt_mem;					//Kullanılabilecek Gerçek Zamanlı Bellek Boyutu
	private int remaining_user_mem;					//Kullanılabilecek Kullanıcı Bellek Boyutu
		
	//Kurucu
	public MemoryManager()
	{
		rt_memory_blocks = new ArrayList<>();
		user_memory_blocks = new ArrayList<>();
		rt_memory_size = 64;	//Gerçek Zamanlılar için 64 MB bellek ayrıldı
		user_memory_size = 960;	//Kullanıcı Prosesleri için 960 MB bellek ayrıldı
		remaining_rt_mem = rt_memory_size;
		remaining_user_mem = user_memory_size;
	}
	
	//Prosese Bellek Tahsisi Yapar
	public void AllocateMemory(Process process)
	{
		//Proses Gerçek Zamanlıysa First Fit algoritmasına göre bellek tahsis eder.
		if (process.getPriority() == 0)
			FirstFit(process, rt_memory_blocks, remaining_rt_mem);
		//Kullanıcı Prosesiyse First Fit algoritmasına bellek tahsis eder.
		else 
			FirstFit(process, user_memory_blocks, remaining_user_mem);
	}
	//Tahsis Edilen Belleği Geri Alma İşlemi
	public void DeallocateMemory(Process process)
	{
		//Proses Gerçek Zamanlıysa Bellek Bloğunu bulur yok eder ve tahsis edilen belleği geri alır.
		if (process.getPriority() == 0)
		{
			for (MemoryBlock block : rt_memory_blocks)
		    {
		        if (block.getProcessId() == process.getId())
		        {
		            rt_memory_blocks.remove(block);
		            remaining_rt_mem += process.getMemorySize();
		            break;
		        }
		    }
		}
		//Kullanıcı Prosesiyse Bellek Bloğunu bulur yok eder ve tahsis edilen belleği geri alır.
		else
		{
			for (MemoryBlock block : user_memory_blocks)
		    {
		        if (block.getProcessId() == process.getId())
		        {
		            user_memory_blocks.remove(block);
		            remaining_user_mem += process.getMemorySize();
		            break;
		        }
		    }
		}
	}
	
	//First Fit Algoritmasına göre bellek tahsisi
	private void FirstFit(Process process, List<MemoryBlock> mem_blocks, int remaining_mem)
	{
		MemoryBlock newBlock = new MemoryBlock(process.getMemorySize(), process.getId());
		mem_blocks.add(newBlock);
		remaining_mem -= process.getMemorySize();
	}
	
	//Prosesin İstediği Belleği Verebilir Mi Kontrol Eder
	public boolean isReady(Process process)
	{
		//Gerçek Zamanlı Proses için
		if (process.getPriority() == 0)
			return process.getMemorySize() <= remaining_rt_mem;
		//Kullanıcı Prosesleri için
		else
			return process.getMemorySize() <= remaining_user_mem;
	}
	
	//Proses Geçerli bir bellek talep ediyor mu kontrol eder
	public boolean isValid(Process process)
	{
		//Gerçek Zamanlı Proses için
		if (process.getPriority() == 0)
			return process.getMemorySize() <= rt_memory_size;
		//Kullanıcı Prosesleri için
		else
			return process.getMemorySize() <= user_memory_size;
	}
	
	
}
