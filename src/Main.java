import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Barang> barangList = new ArrayList<>();
    static ArrayList<Order> orderList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int orderCounter = 1;

    public static void main(String[] args) {
        // Data awal
        barangList.add(new Handphone(1, "Samsung Galaxy", 5000000, 5, "Hitam"));
        barangList.add(new Handphone(2, "iPhone 14", 12000000, 3, "Putih"));
        barangList.add(new Voucher(3, "Voucher Pulsa 100k", 100000, 10));

        boolean jalan = true;
        while (jalan) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int menu = sc.nextInt();

            if (menu == 1) {
                pesanBarang();
            } else if (menu == 2) {
                lihatPesanan();
            } else if (menu == 3) {
                tambahBarangBaru();
            } else if (menu == 0) {
                System.out.println("Terima kasih!");
                jalan = false;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void pesanBarang() {
        System.out.println("\n=== Daftar Barang ===");
        for (Barang b : barangList) {
            if (b instanceof Handphone) {
                Handphone h = (Handphone) b;
                System.out.println(h.getId() + ". " + h.getNama() +
                        " - Harga: " + h.getHarga() +
                        " - Stock: " + h.getStock() +
                        " - Warna: " + h.getWarna());
            } else {
                System.out.println(b.getId() + ". " + b.getNama() +
                        " - Harga: " + b.getHarga() +
                        " - Stock: " + b.getStock());
            }
        }

        System.out.print("Pilih ID barang (0 untuk batal): ");
        int id = sc.nextInt();
        if (id == 0) return;

        Barang barangDipilih = null;
        for (Barang b : barangList) {
            if (b.getId() == id) {
                barangDipilih = b;
                break;
            }
        }

        if (barangDipilih == null) {
            System.out.println("Barang tidak tersedia!");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int qty = sc.nextInt();

        if (qty <= 0 || qty > barangDipilih.getStock()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        int hargaBayar;
        if (barangDipilih instanceof Voucher) {
            hargaBayar = ((Voucher) barangDipilih).getHargaDenganPajak() * qty;
        } else {
            hargaBayar = barangDipilih.getHarga() * qty;
        }

        System.out.print("Masukkan uang sesuai harga (" + hargaBayar + "): ");
        int bayar = sc.nextInt();
        if (bayar < hargaBayar) {
            System.out.println("Jumlah uang tidak mencukupi!");
            return;
        }

        barangDipilih.kurangiStock(qty);
        orderList.add(new Order(orderCounter++, barangDipilih, qty));
        System.out.println("Pesanan berhasil dibuat!");
    }

    static void lihatPesanan() {
        System.out.println("\n=== Daftar Pesanan ===");
        if (orderList.isEmpty()) {
            System.out.println("Belum ada pesanan.");
        } else {
            for (Order o : orderList) {
                if (o.getBarang() instanceof Handphone) {
                    Handphone h = (Handphone) o.getBarang();
                    System.out.println("Order " + o.getId() + " - " +
                            h.getNama() + " (" + h.getWarna() + ")" +
                            " x " + o.getJumlah() +
                            " = Rp" + o.getTotal());
                } else {
                    System.out.println("Order " + o.getId() + " - " +
                            o.getBarang().getNama() +
                            " x " + o.getJumlah() +
                            " = Rp" + o.getTotal());
                }
            }
        }
    }

    static void tambahBarangBaru() {
        System.out.println("\n=== Tambah Barang Baru ===");
        System.out.print("1. Handphone\n2. Voucher\nPilih jenis: ");
        int jenis = sc.nextInt();
        sc.nextLine(); // buang newline

        System.out.print("Masukkan nama: ");
        String nama = sc.nextLine();
        System.out.print("Masukkan harga: ");
        int harga = sc.nextInt();
        System.out.print("Masukkan stok: ");
        int stock = sc.nextInt();
        sc.nextLine(); // buang newline

        int idBaru = barangList.size() + 1;
        if (jenis == 1) {
            System.out.print("Masukkan warna: ");
            String warna = sc.nextLine();
            barangList.add(new Handphone(idBaru, nama, harga, stock, warna));
        } else if (jenis == 2) {
            barangList.add(new Voucher(idBaru, nama, harga, stock));
        } else {
            System.out.println("Jenis tidak valid!");
        }
    }
}
