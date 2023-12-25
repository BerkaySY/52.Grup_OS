package proje;

public class Resources {
	private int _available_memory_size;
	private int _available_printers;
	private int _available_scanners;
	private int _available_modems;
	private int _available_cds;
	
	//Kurucu
	public Resources()
	{
		_available_memory_size = 1024;
		_available_printers = 2;
		_available_scanners = 1;
		_available_modems = 1;
		_available_cds = 2;
	}
	
	//Diğer Sınıflarda kullanılabilmeleri için getter ve setter fonksiyonları
	
	public int getAvailableMemorySize() {
        return _available_memory_size;
    }

    public void setAvailableMemorySize(int available_memory_size) {
        _available_memory_size = available_memory_size;
    }

    public int getAvailablePrinters() {
        return _available_printers;
    }

    public void setAvailablePrinters(int available_printers) {
        _available_printers = available_printers;
    }

    public int getAvailableScanners() {
        return _available_scanners;
    }

    public void setAvailableScanners(int available_scanners) {
        _available_scanners = available_scanners;
    }

    public int getAvailableModems() {
        return _available_modems;
    }

    public void setAvailableModems(int available_modems) {
        _available_modems = available_modems;
    }

    public int getAvailableCDs() {
        return _available_cds;
    }

    public void setAvailableCDs(int available_cds) {
        _available_cds = available_cds;
    }
}


