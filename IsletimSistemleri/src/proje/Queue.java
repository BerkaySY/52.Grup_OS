package proje;

//Gerçek zamanlı ve kullanıcı kuyruklarında kullanmak için arayüz tanımlandı
interface Queue {
	public void Enqueue(Process process); 
	public void Dequeue();
	public Process getFirst();
	public boolean isEmpty();
	public void CheckTimeOut(int timer);
}
