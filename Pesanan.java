//ini adalah kelas untuk mengelola pesanan

import java.util.ArrayList;
import java.util.Scanner;

class Pesanan {

    private final ArrayList<MenuItem> orderedItems;

    public Pesanan() {
        this.orderedItems = new ArrayList<>();
    }

    public void addItemToOrder(MenuItem item) {//ketika metode ini digunakan
        //pada kelas main, maka program akan menjalankan perintah di bawah

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan QTY Pesanan Anda : ");
        int qty = scanner.nextInt();

        MenuItem orderedItem = item.copy();
        orderedItem.setQty(qty);
        orderedItems.add(orderedItem);

    }

    public double calculateTotal() {//digunakan untuk menghitung total
        double total = 0;
        for (MenuItem item : orderedItems) {
            total += item.price * item.getQty();
        }

        double discount = calculateDiscount();
        total -= discount;

        return total;
    }

    private double calculateDiscount() {//untuk menghitung diskon
        double discount = 0;
        for (MenuItem item : orderedItems) {
            if (item instanceof Discount) {
                // Jika item adalah objek Discount, tambahkan diskon ke total
                discount += item.getPrice() * item.getQty() * ((Discount) item).getDiscount() / 100.0;
            }
        }
        return discount;
    }

    public void displayOrderReceipt() {//metode untuk menampilkan struk belanja
        System.out.print("---------------------------------------");
        System.out.print("\n----Struk Belanja  Restoran Zoldyck----\n");
        int banyakPesanan = 1;//inisiasi pesanan pertama dengan nomor 1

        for (MenuItem item : orderedItems) {
            System.out.print(banyakPesanan + ".");
            item.tampilMenu();
            banyakPesanan++;//karena pesanan dikelola dengan arrayList, maka nomor
            //banyakPesanan akan terus bertambah ketika ada inputan pesanan
        }
        System.out.print("---------------------------------------");
        System.out.print("\n");
        System.out.print("Total: " + "\t" + calculateTotal());
        System.out.print("\nTerimakasih. Datang Kembali\n.");
        System.out.print("\n---------------------------------------\n");
    }
}
