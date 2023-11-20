
import java.io.Serializable;

abstract class MenuItem { //kelas abstrak MenuItem 
    //yang akan menjadi kelas dasar 
    //untuk semua menu item dalam restoran
//kelas ini tidak bisa diakses di luar kelas

    protected String name;
    protected double price;
    protected String category;
    protected int qty; //Penambahan Attribut qty 
    //untuk perhitungan total biaya
//agar dapat diakses maka dibuat turunannya dengan encapsulation
    //pembatasan public

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.qty = 1; // Default qty adalah 1
    }
//Di bawah ini merupakan metode setter dan getter 
    //yang diperlukan dalam class2 berikutnya

    public String getCategory() {
        return category; //kalau setelah public, tipe data maka
        //ada return atau nilai kembalian
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {//jika metode void
        //tidak ada return dan nilai kembalian
        this.qty = qty;
    }

    public String getQtyText() {
        return "(" + getQty() + ")";
    }

    //metode abstrak tampil menu
    //disini belum didefinisikan keseluruhan perintah tampil menu
    //karena masih dibungkus pada kelas abstract menuItem
    public abstract void tampilMenu();

    public abstract MenuItem copy(); //Saya menambahkan metode menuItemcopy
    //untuk digunakan pada kelas pesanan.java

}

class Makanan extends MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;

//Selanjutnya kelas utama menuItem
    //menurunkan atribut dan sifat atributnya
    //ke dalam kelas lain menggunakan extends

    private String jenisMakanan; //subkelas memiliki attribut tambahan jenisMakanan

    public Makanan() {
        super("", 0.0, "");
        //ketika inisiasi kelas makanan
        //parameter yang digunakan nama harga kategori
        //super menandakan bahwa eksekusinya mengikuti kelas
        //super yaitu menuItem
        this.jenisMakanan = "";
    }
// Konstruktor dengan parameter

    public Makanan(String name, double price, String category, String jenisMakanan) {
        super(name, price, category);
        this.jenisMakanan = jenisMakanan;
    }

    @Override//disini metode dioverride atau diberikan impplementasi
    //ulang thdp metode yang sudah ada pada kelas induk
    public void tampilMenu() {

        System.out.println(name + " " + price + " " + getQtyText() + " " + category);

    }

    @Override
    public MenuItem copy() {
        return new Makanan(name, price, category, jenisMakanan);
    }
}

class Minuman extends MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;


    private String jenisMinuman;

    public Minuman() {
        super("", 0.0, "");
        this.jenisMinuman = "";
    }

    public Minuman(String name, double price, String category, String jenisMinuman) {
        super(name, price, category);
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {

        System.out.println(name + " " + price + " " + getQtyText() + " " + category);

    }

    @Override
    public MenuItem copy() {
        return new Minuman(name, price, category, jenisMinuman);
    }
}

class Discount extends MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;


    private double discount;

    public Discount(String name, double price, double discount) {
        super(name, price, "Diskon");
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void tampilMenu() {
        System.out.println(getName() + " " + getPrice() + " Disc: " + discount + "%" + getQtyText());
    }

    @Override
    public MenuItem copy() {
        return new Discount(getName(), getPrice(), discount);
    }
}
