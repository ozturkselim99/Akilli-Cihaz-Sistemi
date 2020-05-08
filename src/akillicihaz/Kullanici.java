package akillicihaz;

public class Kullanici implements IObserver {

    private final String kullaniciadi;
    private final String sifre;

    public Kullanici(String kullaniciadi, String sifre) {
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
    }

    @Override
    public String toString() {
        return kullaniciadi;

    }

    @Override
    public void notify(boolean durum) {
        if (durum == true) {
            System.out.println("Bildirim!!! Sogutucu acildi " + this.kullaniciadi);
        } else {
            System.out.println("Bildirim!!! Sogutucu kapandi " + this.kullaniciadi);
        }
    }

}
