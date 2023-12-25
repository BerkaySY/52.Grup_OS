package proje;

import java.util.List;
import java.util.ArrayList;

public class MemoryManager {
	private List<MemoryBlock> _rt_memory_blocks;
	private List<MemoryBlock> _user_memory_blocks;
	private int _rt_memory_size;
	private int _user_memory_size;
	private int _remaining_rt_mem;
	private int _remaining_user_mem;
	
	public MemoryManager()
	{
		_rt_memory_blocks = new ArrayList<>();
		_user_memory_blocks = new ArrayList<>();
		_rt_memory_size = 64;
		_user_memory_size = 960;
		_remaining_rt_mem = _rt_memory_size;
		_remaining_user_mem = _user_memory_size;
	}
	
	public void AllocateMemory(Process process)
	{
		if (process.getPriority() == 0)
		{
			FirstFit(process, _rt_memory_blocks, _remaining_rt_mem);
		}
		else 
		{
			FirstFit(process, _user_memory_blocks, _remaining_user_mem);
		}
	}
	
	public void DeallocateMemory(Process process)
	{
		if (process.getPriority() == 0)
		{
			for (MemoryBlock block : _rt_memory_blocks)
		    {
		        if (block.getProcessId() == process.getId())
		        {
		            _rt_memory_blocks.remove(block);
		            _remaining_rt_mem += process.getMemorySize();
		            break;
		        }
		    }
		}
		else
		{
			for (MemoryBlock block : _user_memory_blocks)
		    {
		        if (block.getProcessId() == process.getId())
		        {
		            _user_memory_blocks.remove(block);
		            _remaining_user_mem += process.getMemorySize();
		            break;
		        }
		    }
		}
	}
	
	public void FirstFit(Process process, List<MemoryBlock> mem_blocks, int remaining_mem)
	{
		if (remaining_mem >= process.getMemorySize())
		{
			MemoryBlock newBlock = new MemoryBlock(process.getMemorySize(), process.getId());
			mem_blocks.add(newBlock);
			remaining_mem -= process.getMemorySize();
		}
	}
	
	
}
