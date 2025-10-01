public class Handphone extends Barang {
    private String warna;

    public Handphone(int id, String nama, int harga, int stock, String warna) {
        super(id, nama, harga, stock);
        this.warna = warna;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
}
