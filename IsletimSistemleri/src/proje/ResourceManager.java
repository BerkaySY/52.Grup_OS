package proje;

public class ResourceManager {
	private Resource _resources;
	
	public ResourceManager()
	{
		_resources = new Resource();
	}
	public void AllocateResources(Process process)
	{
		if (areResourcesAvailable(process))
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
	
	private boolean areResourcesAvailable(Process process)
	{
		if (_resources.getAvailablePrinters() >= process.getPrinters() &&
			_resources.getAvailableScanners() >= process.getScanners() &&
			_resources.getAvailableModems()   >= process.getModems()   &&
			_resources.getAvailableCDs()      >= process.getCDs())
			return true;
		
		return false;
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
}
