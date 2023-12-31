package proje;

//Prosesin durumlarını gösterebilmek için gerekli enum sınıfı
public enum ProcessStatus 
{
	STARTED, 				//Başlatıldı
	RUNNING, 				//Yürütülüyor
	SUSPENDED, 				//Askıya Alındı
	RESUMED, 				//Devam Ediliyor
	COMPLETED, 				//Tamamlandı
	REJECTED_USER_RESOURCE, //Kullanıcı Prosesinde Fazla Kaynak İsteniyorsa Reddet
	REJECTED_REAL_RESOURCE, //Gerçek Zamanlı Proseste Fazla Kaynak İsteniyorsa Reddet
	REJECTED_USER_MEM,		//Kullanıcı Prosesinde Fazla Bellek İsteniyorsa Reddet
	REJECTED_REAL_MEM,		//Gerçek Zamanlı Proseste Fazla Bellek İsteniyorsa Reddet
	TIMEOUT					//Zaman Aşımı
}
