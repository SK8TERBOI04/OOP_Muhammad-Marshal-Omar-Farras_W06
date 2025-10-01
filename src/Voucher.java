public class Voucher extends Barang {
    private double pajak = 0.1; // 10% pajak

    public Voucher(int id, String nama, int harga, int stock) {
        super(id, nama, harga, stock);
    }

    public int getHargaDenganPajak() {
        return harga + (int)(harga * pajak);
    }
}
