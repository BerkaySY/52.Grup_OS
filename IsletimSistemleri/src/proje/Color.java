package proje;

import java.util.Random;

public class Color {
    public static String RastRenkGetir() {
        StringBuilder hexrenk = new StringBuilder("#");
        
        // Altı adet rastgele hex sembolü oluştur.
        for (int i = 0; i < 6; i++) {
        	hexrenk.append(RastHexOlustur());
        }
        return hexrenk.toString();
    }
    private static char RastHexOlustur() {
        // 0-15 arasında rastgele bir sayı alır.
        Random rast = new Random();
        int rastInt = rast.nextInt(16);

        // Sayıyı veya harfi döndür.
        if (rastInt < 10) {
            // 0-9 arasında sayı döndürür.
            return (char) ('0' + rastInt);
        } else {
            // 10-15 arasında harf (A-F) döndürür.
            return (char) ('A' + (rastInt - 10));
        }
    }

    private static void printColoredText(String text) {
        // ANSI renk kodları kullanarak konsolda renkli yazdırma.
        System.out.println("\033[38;2;" + hexToRgb(RastRenkGetir()) + "m" + text + "\033[0m");
    }

    private static String hexToRgb(String HexRenk) {
        // Hex rengini RGB formatına çevirme.
        int r = Integer.parseInt(HexRenk.substring(1, 3), 16);
        int g = Integer.parseInt(HexRenk.substring(3, 5), 16);
        int b = Integer.parseInt(HexRenk.substring(5, 7), 16);

        return r + ";" + g + ";" + b;
    }
}

