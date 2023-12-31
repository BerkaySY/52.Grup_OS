package proje;

public class Program 
{

	public static void main(String[] args) {
		//Dispatcher nesnesi oluşturulur ve programa verilen txt dosyasına göre program çalıştırılır.
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.Execute(args[0]);
	}

}
