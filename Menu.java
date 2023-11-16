
import java.util.ArrayList;

class Menu {// kelas Menu yang akan digunakan 
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
}
