package proje;

public class Resource {
	private int _available_printers; //Kullanılabilir Yazıcılar
	private int _available_scanners; //Kullanılabilir Tarayıcılar
	private int _available_modems;	 //Kullanılabilir Modemler
	private int _available_cds;		 //Kullanılabilir CDler
	
	//Kurucu
	public Resource()
	{
		_available_printers = 2;
		_available_scanners = 1;
		_available_modems = 1;
		_available_cds = 2;
	}
	
	//Diğer Sınıflarda kullanılabilmeleri için getter ve setter fonksiyonları
	
    public int getAvailablePrinters() { return _available_printers; }
    
    public void setAvailablePrinters(int availablePrinters) { _available_printers = availablePrinters; }

    public int getAvailableScanners() { return _available_scanners; }

    public void setAvailableScanners(int available_scanners) { _available_scanners = available_scanners; }

    public int getAvailableModems() { return _available_modems; }

    public void setAvailableModems(int available_modems) { _available_modems = available_modems; }

    public int getAvailableCDs() { return _available_cds; }

    public void setAvailableCDs(int available_cds) { _available_cds = available_cds; }
}


