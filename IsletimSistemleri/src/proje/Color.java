package proje;

import java.util.Random;

public class Color {
	private String color;
	
	public Color()
	{
		color = hex2RGB(getHexColor());
	}
	
	public String getColor()
	{
		return color;
	}

    private String getHexColor() {
        StringBuilder hexColor = new StringBuilder("#");

        // Altı adet rastgele hex sembolü oluştur.
        for (int i = 0; i < 6; i++) {
            hexColor.append(getRandomHex());
        }

        return hexColor.toString();
    }
    private char getRandomHex() {
        // 0-15 arasında rastgele bir sayı alır.
        Random random = new Random();
        int random_num = random.nextInt(16);
        
        if (random_num < 10)
           return (char) ('0' + random_num); // 0-9 arasında sayı döndürür. 
        else
          return (char) ('A' + (random_num - 10)); // 10-15 arasında harf (A-F) döndürür.
        
    }

    private String hex2RGB(String hex_color) {
        // Hex rengini RGB formatına çevirme.
        int r = Integer.parseInt(hex_color.substring(1, 3), 16);
        int g = Integer.parseInt(hex_color.substring(3, 5), 16);
        int b = Integer.parseInt(hex_color.substring(5, 7), 16);

        return r + ";" + g + ";" + b;
    }
}