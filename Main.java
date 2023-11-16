
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu restaurantMenu = new Menu();
        Pesanan customerOrder = new Pesanan();

        int choice;//masukkan pilihan
        do {//program akan menjalankan/eksekusi perintah
            //dibawah hingga pelanggan memasukkan 5 atau exit
            System.out.println("\n----Manajemen Menu Restoran Zoldyck----");
            System.out.println("1. Tambah Menu Restoran");
            System.out.println("2. Tampilkan Semua Menu");
            System.out.println("3. Terima Pesanan");
            System.out.println("4. Tampilkan Struk");
            System.out.println("5. Exit");
            System.out.print("Masukkan Pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {//mempergunakan switch case 
                case 1 ->//jika pilihan 1 
                    addItemToMenu(scanner, restaurantMenu);
                case 2 ->
                    restaurantMenu.tampilMenu();
                case 3 ->
                    placeOrder(scanner, restaurantMenu, customerOrder);
                case 4 ->
                    customerOrder.displayOrderReceipt();
                case 5 ->
                    System.out.println("Keluar Program");
                default ->//apabila memasukkan nomor yang tidak ada
                    System.out.println("Pilihan Tidak Valid. Masukkan Kembali Pilihan Anda");
            }
        } while (choice != 5); //tutup dari do, ketika bukan 5 maka program kembali
        //ekseskusi do
    }

    private static void addItemToMenu(Scanner scanner, Menu menu) {
        boolean tambahMenu = false;//menambahkan kondisi tambahmenu = false
        //tidak tambah menu
        do { //jalankan eksekusi di bawah
            System.out.println("\n-----Tambah Menu Restoran-----\n");
            System.out.print("Masukkan Nama Menu: ");
            String name = scanner.next();
            if (menu.isMenuExists(name)) {//kondisi pengecekan dimana 
                //apabila menu yang dimasukkan sudah ada
                //maka sistem akan continue
                System.out.print("Menu Sudah Ada\n");
                continue;//kembali menjalankan perintah do
            }
           
            System.out.print("Harga: ");
            scanner.nextLine();
            double price = scanner.nextDouble();
            System.out.print("Masukkan Kategori\n");
            System.out.print("(Minuman/Makanan/Discount): ");
            String category = scanner.next();

            MenuItem newItem;
            if (category.equalsIgnoreCase("Makanan")) {
                System.out.print("Masukkan Jenis Makanan");
                System.out.print("(Main/Appetizer): ");
                String jenisMakanan = scanner.next();
                newItem = new Makanan(name, price, jenisMakanan);
            } else if (category.equalsIgnoreCase("Minuman")) {
                System.out.print("Masukkan Jenis Minuman: ");
                System.out.print("(Cold/Hot): ");
                String jenisMinuman = scanner.next();
                newItem = new Minuman(name, price, jenisMinuman);
            } else if (category.equalsIgnoreCase("Discount")) {
                System.out.print("Masukkan Persentase Diskon: ");
                scanner.nextLine();
                double discount = scanner.nextDouble();
                newItem = new Discount(name, price, discount);
            } else {
                System.out.println("Kategori Invalid. Masukkan Kembali");
                continue;//apabila pilihan invalid
                //kembali ke perintah do
            }

            menu.addMenuItem(newItem);
            System.out.println("Menu Berhasil Ditambahkan");
            System.out.print("Tambah Menu Lagi? (Y/N): ");
            tambahMenu = scanner.next().equalsIgnoreCase("Y");
        } while (tambahMenu);//jika pengguna memasukkan tambah menu lagi
    }

    private static void placeOrder(Scanner scanner, Menu menu, Pesanan order) {
        System.out.println("\n-----------Terima Pesanan-----------\n");
        menu.tampilMenu();

        do {//lakukan eksekusi ini ketika pengguna
            //ingin menambahkan pesanan kembali
            System.out.print("Masukkan Nama Menu Orderan Anda: ");
            String itemName = scanner.next();

            try {//penanganan eksepsi apabila
                //menu yang dimasukkan tidak ada pada daftar menu

                MenuItem selectedItem = findItemByName(itemName, menu);
                order.addItemToOrder(selectedItem);
                System.out.println("Orderan Berhasil Ditambahkan");
                //jika berhasil maka ada output berhasil
            } catch (ItemNotFoundException e) {//jika tidak ada maka
                //program akan catch(menangkap) sebagai sebuah error
                System.out.println("Error: " + e.getMessage());//Program
                //mencetak pesan error
            }
            System.out.print("Tambah Pesanan (Y/N): ");
        } while (scanner.next().equalsIgnoreCase("Y"));//dilakukan do selama
        //pilihan Y

    }//tutup static void

    private static MenuItem findItemByName(String itemName, Menu menu) throws ItemNotFoundException {
        for (MenuItem item : menu.menuItems) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        throw new ItemNotFoundException("Item '" + itemName + "' Tidak Ditemukan Pada Menu.");
        //penanganan pesan eksepsi yang dilakukan adalah
        //dengan throw (melempar) pesan sebagai berikut
    }
}//tutup class main

class ItemNotFoundException extends Exception {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
