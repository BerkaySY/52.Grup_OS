package proje;

import java.util.Random;

public class Color {
	private String color;
	
	//Kurucu
	public Color()
	{
		color = hex2RGB(getHexColor());
	}
	
	//Rengi elde etmek için getter fonksiyonu
	public String getColor()
	{
		return color;
	}
	
	//Renk değerini hex olarak elde etmek için kullanılan fonksiyon
    private String getHexColor() {
        String hex_color = "#";
        // Altı adet rastgele hex sembolü oluşturur.
        for (int i = 0; i < 6; i++)
        	hex_color += getRandomHex(); 
        return hex_color;
    }
    
    //getHexColor'da kullanmak için rastgele hex karakteri döndürür 
    private char getRandomHex() {
        // 0-15 arasında rastgele bir sayı alır.
        Random random = new Random();
        int random_num = random.nextInt(16);
        
        if (random_num < 10)
           return (char) ('0' + random_num); // 0-9 arasında sayı döndürür. 
        else
          return (char) ('A' + (random_num - 10)); // 10-15 arasında harf (A-F) döndürür.
        
    }
    
    //hex değerini rgb formatına çevirip döndüren fonksiyon
    private String hex2RGB(String hex_color) {
        // Hex rengini RGB formatına çevirme.
        int r = Integer.parseInt(hex_color.substring(1, 3), 16);
        int g = Integer.parseInt(hex_color.substring(3, 5), 16);
        int b = Integer.parseInt(hex_color.substring(5, 7), 16);

        return r + ";" + g + ";" + b;
    }
}