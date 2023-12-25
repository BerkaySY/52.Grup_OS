package proje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//  PROSESTEN NE GELECEGİNİ HATIRLAMAK İCİN USTTE BUNU TUTTUM.
class Proses {
    int arrivalTime;
    int priority;
    int processingTime;
    int mBayt;
    int printers;
    int scanners;
    int modems;
    int CDs;

    public Proses(int arrivalTime, int priority, int processingTime, int mBayt, int printers, int scanners, int modems, int CDs) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.processingTime = processingTime;
        this.mBayt = mBayt;
        this.printers = printers;
        this.scanners = scanners;
        this.modems = modems;
        this.CDs = CDs;
    }
}

public class FCFS {
    
	private static List<Proses> processes = new ArrayList<>();

    public static void main(String[] args) {
        dosyadanOku("giris.txt");
        //FCFS yaklasiminin kodlari ve classi yazilacak. Return etmesine dikkat et!
        List<Proses> sortedProcesses = sortProcessesByFCFS(processes);

        // Sıralanmış işlemleri yazdırma BUNU RENKLİ YAPMALIYIZZ!!!!
        for (Proses process : sortedProcesses) {
            System.out.println("Arrival Time: " + process.arrivalTime + ", Priority: " + process.priority
                    + ", Processing Time: " + process.processingTime + ", Printers: " + process.printers
                    + ", Scanners: " + process.scanners + ", Modems: " + process.modems + ", CDs: " + process.CDs);
        }
    }
    // Dosyadan okuma işlemi gerçekleştiriliyor
    private static void dosyadanOku(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(", ");

                int arrivalTime = Integer.parseInt(values[0]);
                int priority = Integer.parseInt(values[1]);
                int processingTime = Integer.parseInt(values[2]);
                int mBayt = Integer.parseInt(values[3]);
                int printers = Integer.parseInt(values[4]);
                int scanners = Integer.parseInt(values[5]);
                int modems = Integer.parseInt(values[6]);
                int CDs = Integer.parseInt(values[7]);

                processes.add(new Proses(arrivalTime, priority, processingTime, mBayt, printers, scanners, modems, CDs));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }
    }
    
    public static List<Proses> sortProcessesByFCFS(List<Proses> processes) {
        // FCFS algoritması ile işlemleri sıralama
        processes.sort(Comparator.comparingInt(p -> p.priority));
        return processes;
    }
}