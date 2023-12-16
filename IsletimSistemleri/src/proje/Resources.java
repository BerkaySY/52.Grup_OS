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

    public void setAvailableMemorySize(int availableMemorySize) {
        _available_memory_size = availableMemorySize;
    }

    public int getAvailablePrinters() {
        return _available_printers;
    }

    public void setAvailablePrinters(int availablePrinters) {
        _available_printers = availablePrinters;
    }

    public int getAvailableScanners() {
        return _available_scanners;
    }

    public void setAvailableScanners(int availableScanners) {
        _available_scanners = availableScanners;
    }

    public int getAvailableModems() {
        return _available_modems;
    }

    public void setAvailableModems(int availableModems) {
        _available_modems = availableModems;
    }

    public int getAvailableCDs() {
        return _available_cds;
    }

    public void setAvailableCDs(int availableCDs) {
        _available_cds = availableCDs;
    }
}


