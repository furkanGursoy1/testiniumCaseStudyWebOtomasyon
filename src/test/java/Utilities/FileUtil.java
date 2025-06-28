package Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void writeProductInfoToTxt(String urunAdi, String fiyat, String eskiFiyat) {
        String filePath = "urun_bilgisi.txt"; // Proje kök dizinine kaydeder

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Ürün Adı: " + urunAdi);
            writer.newLine();
            writer.write("Fiyat: " + fiyat);
            writer.newLine();
            writer.write("Eski Fiyat: " + eskiFiyat);
            writer.newLine();
            System.out.println("Ürün bilgisi 'urun_bilgisi.txt' dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosyaya yazılırken hata oluştu: " + e.getMessage());
        }
    }
}
