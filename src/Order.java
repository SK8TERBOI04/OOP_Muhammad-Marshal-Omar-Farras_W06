public class Order {
    private int id;
    private Barang barang;
    private int jumlah;
    private int total;

    public Order(int id, Barang barang, int jumlah) {
        this.id = id;
        this.barang = barang;
        this.jumlah = jumlah;

        if(barang instanceof Voucher) {
            this.total = ((Voucher) barang).getHargaDenganPajak() * jumlah;
        } else {
            this.total = barang.getHarga() * jumlah;
        }
    }

    public int getId() {
        return id;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getTotal() {
        return total;
    }
}
