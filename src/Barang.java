public class Barang {
    protected int id;
    protected String nama;
    protected int harga;
    protected int stock;

    public Barang(int id, String nama, int harga, int stock) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStock() {
        return stock;
    }

    public void kurangiStock(int qty) {
        if(qty <= stock) {
            stock -= qty;
        }
    }
}
