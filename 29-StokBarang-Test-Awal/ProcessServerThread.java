import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class ProcessServerThread implements Runnable 
{
    private Socket koneksi;
    private String baris;

    public ProcessServerThread(Socket koneksiKiriman, int angka) 
    {
        koneksi = koneksiKiriman;
        this.baris=""+baris;
    }

    public void run()
    {
        try{
            if (koneksi != null)
                prosesPermintaanClient();
        }catch(IOException err) {
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient() throws IOException
    {
        int i=0;
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        int jumlah=0;
        String pesanServer="";
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;

        for(jumlah=0; ;) 
        {
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan));
            String baris = masukanReader.readLine();
            System.out.println("Perintah Client : "+baris);
            /*
            if(baris.equalsIgnoreCase("TAMBAH"))
            jumlah++;
            else if(baris.equalsIgnoreCase("KURANG"))
            jumlah--;
            else if(baris.equalsIgnoreCase("JUMLAH"))
            System.out.println(jumlah);
             */
            // Kirim ke client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write(pesanServer);
            keluaranBuf.newLine();
            keluaranBuf.flush();
            
        if(baris.equalsIgnoreCase("TAMBAH"))
            jumlah++;
        else if (baris.equalsIgnoreCase("KURANG"))
            jumlah--;
        else if (baris.equalsIgnoreCase("JUMLAH"))
            System.out.println(+jumlah);
        else if (baris.equalsIgnoreCase("SELESAI"))
            break;
        if(pesanServer.equalsIgnoreCase(baris)){
            break;
        }
        }
    }     
}