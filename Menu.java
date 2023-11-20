
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
// kelas Menu yang akan digunakan 
    //untuk mengelola semua item menu dalam restoran. 
    //Kelas ini harus memiliki atribut berupa sebuah 
    //ArrayList untuk menyimpan semua item menu.

    ArrayList<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void tampilMenu() {//metode tampil menu
        //disini kita definisikan lagi
        System.out.print("Daftar Menu Makanan dan Minuman\n");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.print((i + 1) + "." + item.getName() + " " + item.getPrice() + "(" + item.getCategory() + ")" + "\n");
        }
    }

    boolean isMenuExists(String name) { //pada kelas menu
        //diberikan kondisi yang menyatakan apakah
        //menu yang akan diinput kemudian sudah ada atau tidak
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true; // Menu sudah ada
            }
        }
        return false; // Menu belum ada
    }

//Menyimpan menu ke dalam file
    // Metode untuk menulis menu ke file teks
    public void simpanMenuKeFile(String namaBerkas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaBerkas, true))) {
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                writer.write((i + 1) + ". " + item.getName() + " " + item.getPrice() + "(" + item.getCategory() + ")");
                writer.newLine();
            }

            System.out.println("Berhasil Menulis Menu ke  "+namaBerkas);
        } catch (IOException e) {
            System.out.println("Gagal Menulis ke File");
            e.printStackTrace();
        }
    }

    public static void buatFile(String namaFile) {
        File file = new File(namaFile);

        try {
            if (file.createNewFile()) {
                System.out.println("File " + namaFile + " berhasil dibuat");
            } else {
                System.out.println("File " + namaFile + " sudah ada");
            }
        } catch (IOException e) {
            System.err.println("Gagal membuat file " + namaFile);
            e.printStackTrace();
        }
    }

    //Membaca menu dari file
    public void bacaMenuDariFile(String namaBerkas) {
        File file = new File(namaBerkas);

        if (!file.exists()) {
            System.err.println("File " + namaBerkas + " tidak ditemukan.");
            return;
        }

        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String baris;
            menuItems = new ArrayList<>();
            System.out.print("Daftar Menu Makanan dan Minuman\n");
            while ((baris = bufferedReader.readLine()) != null) {
                
                System.out.println(" " + baris);
                
                

            }

            System.out.println("Berhasil Membaca Menu dari File");
            
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.print((i + 1) + "." + item.getName() + " " + item.getPrice() + "(" + item.getCategory() + ")" + "\n");
        }
        } catch (IOException e) {
            System.out.println("Gagal Baca File");
            e.printStackTrace();
        }
    }
}
