package proje;

public class MemoryBlock {
	private int _pid;  //Proses ID
	private int _size; //Bellek Boyutu
	
	//Kurucu
	public MemoryBlock(int size, int pid)
	{
		_pid = pid;
		_size = size;
	}
	
	//Diğer Sınıflarda Kullanabilmek için getter fonksiyonları
	public int getSize() { return _size; }
	
	public int getProcessId() { return _pid; }
}
