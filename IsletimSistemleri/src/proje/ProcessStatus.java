package proje;

//Prosesin durumlarını gösterebilmek için gerekli enum sınıfı
public enum ProcessStatus 
{
	STARTED, 
	RUNNING, 
	SUSPENDED, 
	RESUMED, 
	COMPLETED, 
	REJECTEDUSERRESOURCE,
	REJECTEDREALRESOURCE,
	REJECTEDUSERMEM,
	REJECTEDREALMEM,
	TIMEOUT
}
