import java.io.FileWriter;
import java.io.IOException;

public class CariPrima {
    public static void main() throws IOException {
        // Buat berkas untuk menulis hasil. Pakai WRITER karena yang ditulis 
        // berkas text.
        FileWriter berkas = new FileWriter(NAMA_BERKAS);
        
        // Buat array dari thread
        BenarPrima[] benarPrima = new BenarPrima[JUMLAH_THREAD];
        // Mulai hitung dari angka 2, karena 1 otomatis bukan prima
        int angka = 2;
        Thread [] benang = new Thread[JUMLAH_THREAD];
        // Loop sampai batas atas yang diminta
        while (angka<=ANGKA_TERBESAR) {
            //Dimasukin dulu semua angka awal ke 10 buah benang
              for(int x=0; x<JUMLAH_THREAD; ++x){
                  benarPrima[x] = new BenarPrima(angka);
                  benang[x] = new Thread(benarPrima[x]);
                  ++angka;
                }
            //10 benang di jalankan
              for(int y=0; y<JUMLAH_THREAD; ++y){
                  benang[y].start();
              }
                          
        // Tunggu sampai semua thread selesai
        for (int counterThread=0; counterThread<JUMLAH_THREAD; ++counterThread)
            while (benarPrima[counterThread].selesai() == false) {}        
        
        for(int z=0; z<JUMLAH_THREAD; ++z){
            if(benarPrima[z].selesai()){
                if(benarPrima[z].prima()){
                    synchronized(berkas){
                        try{
                            berkas.write(benarPrima[z].angka()+"\t");
                        }
                        catch (IOException kesalahan) {
                          System.out.printf("Terjadi kesalahan: %s", kesalahan);
                        }
                    }  
                }
            }   
        }
       } 
                 
       // Tutup berkas untuk menulis hasil
        berkas.close();
    }
    
    private final static String NAMA_BERKAS = "prima.log";
    private final static int JUMLAH_THREAD = 10;
    private final static int ANGKA_TERBESAR = 100000;
}