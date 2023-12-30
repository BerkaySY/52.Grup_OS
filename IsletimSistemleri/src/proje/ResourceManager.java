package proje;

public class ResourceManager {
	private Resource _resources;
	
	public ResourceManager()
	{
		_resources = new Resource();
	}
	public void AllocateResources(Process process)
	{
		UpdateResources(process, 1);
	}
	
	public void DeallocateResources(Process process)
	{
		UpdateResources(process, -1); 
	}
	
	private void UpdateResources(Process process, int multiplier)
	{
		int	available_printers = _resources.getAvailablePrinters() - (multiplier * process.getPrinters());
		int available_scanners = _resources.getAvailableScanners() - (multiplier * process.getScanners());
		int available_modems   = _resources.getAvailableModems()   - (multiplier * process.getModems());
		int available_cds      = _resources.getAvailableCDs()      - (multiplier * process.getCDs());
		
		_resources.setAvailablePrinters(available_printers);
		_resources.setAvailableScanners(available_scanners);
		_resources.setAvailableModems(available_modems);
		_resources.setAvailableCDs(available_cds);
	}
	
	public boolean areResourcesEnough(Process process)
	{
		if (_resources.getAvailablePrinters() < process.getPrinters() &&
			_resources.getAvailableScanners() < process.getScanners() &&
			_resources.getAvailableModems()   < process.getModems()   &&
			_resources.getAvailableCDs()      < process.getCDs())
			return false;
			
		return true;
	}
	
	public boolean isValid(Process process)
	{
		if(process.getPriority() == 0)
		{
			if (process.getPrinters() > 0 || process.getScanners() > 0 || process.getModems() > 0 || process.getCDs() > 0)
				return false;
		}
			
		else
		{
			if (process.getPrinters() > 2 || process.getScanners() > 1 || process.getModems() > 1 || process.getCDs() > 2)
				return false;
		}
		
		return true;
	}
}
