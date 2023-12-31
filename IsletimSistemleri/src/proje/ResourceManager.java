package proje;

public class ResourceManager
{
	private Resource resources;
	
	//Kurucu
	public ResourceManager()
	{
		resources = new Resource();
	}
	//Kaynak Tahsisi İşlemi
	public void AllocateResources(Process process)
	{
		UpdateResources(process, 1);
	}
	
	//Tahsis Edilen Kaynakları Geri Alma İşlemi
	public void DeallocateResources(Process process)
	{
		UpdateResources(process, -1); 
	}
	
	//Kaynakları Güncelleme İşlemi
	//Multiplier parametresi 1 olursa kaynak tahsisi yapar -1 olursa kaynakları serbest bırakır.
	private void UpdateResources(Process process, int multiplier)
	{
		int	available_printers = resources.getAvailablePrinters() - (multiplier * process.getPrinters());
		int available_scanners = resources.getAvailableScanners() - (multiplier * process.getScanners());
		int available_modems   = resources.getAvailableModems()   - (multiplier * process.getModems());
		int available_cds      = resources.getAvailableCDs()      - (multiplier * process.getCDs());
		
		resources.setAvailablePrinters(available_printers);
		resources.setAvailableScanners(available_scanners);
		resources.setAvailableModems(available_modems);
		resources.setAvailableCDs(available_cds);
	}
	
	//Kaynak Tahsisi Yapabilmek İçin Prosesin talep ettiği kaynakları karşılıyor mu
	public boolean areResourcesEnough(Process process)
	{
		if (resources.getAvailablePrinters() < process.getPrinters() &&
			resources.getAvailableScanners() < process.getScanners() &&
			resources.getAvailableModems()   < process.getModems()   &&
			resources.getAvailableCDs()      < process.getCDs())
			return false;
			
		return true;
	}
	
	//Prosesin Talep Ettiği Kaynak Sayısı Geçerli Mi Kontrol Eder
	public boolean isValid(Process process)
	{
		//Gerçek Zamanlı Proses için
		if(process.getPriority() == 0)
		{
			if (process.getPrinters() > 0 || process.getScanners() > 0 || process.getModems() > 0 || process.getCDs() > 0)
				return false;
		}
		//Kullanıcı Prosesleri İçin	
		else
		{
			if (process.getPrinters() > 2 || process.getScanners() > 1 || process.getModems() > 1 || process.getCDs() > 2)
				return false;
		}
		
		return true;
	}
}
