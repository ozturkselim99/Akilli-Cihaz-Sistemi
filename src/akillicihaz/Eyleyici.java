package akillicihaz;

import java.util.Random;

public class Eyleyici implements IObservable {

    private boolean sogutucudurumu;
    private IObserver kullanici;
    private static Eyleyici single_instance;
    private final Random rnd;
    private int sogutucukapamakontrol;
    private int sogutucuacmakontrol;

    private Eyleyici() {
        sogutucudurumu = false;
        kullanici = null;
        rnd = new Random();
    }

    public static Eyleyici GetInstance() {
        if (single_instance == null) {
            single_instance = new Eyleyici();
        }
        return single_instance;
    }

    public boolean sogutucuAc() throws InterruptedException {
        if (this.sogutucudurumu == true) {
            System.out.println("Sogutucu zaten ACIK !!!");
            return false;
        } else {
            if (rnd.nextBoolean()) {
                sogutucuacmakontrol = 0;
                this.sogutucudurumu = true;
                System.out.println("Sogutucu aciliyor...");
                Thread.sleep(500);
                notifyObserver();
                return true;

            } else {
                sogutucuacmakontrol++;
                if (sogutucuacmakontrol == 3) {
                    System.out.println("Sogutucu acilamadi !!! Sogutucunuz bozulmus olabilir !!! Cihazini en yakin teknik servise kontrol ettirin !!!");
                    return false;
                } else {
                    System.out.println("Sogutucu acilamadi tekrar deneyiniz ve ag baglantinizi kontrol ettirin !!! ");
                    return false;
                }

            }
        }
    }

    public void sogutucuKapat() throws InterruptedException {

        if (this.sogutucudurumu == false) {
            System.out.println("Sogutucu zaten KAPALI !!!");
        } else {
            if (rnd.nextBoolean()) {
                sogutucukapamakontrol = 0;
                this.sogutucudurumu = false;
                System.out.println("Sogutucu kapaniyor...");
                Thread.sleep(500);
                notifyObserver();
            } else {
                sogutucukapamakontrol++;
                if (sogutucukapamakontrol == 3) {
                    System.out.println("Sogutucu kapatilamadi !!! Sogutucunuz bozulmuş olabilir !!! Cihazini en yakin teknik servise kontrol ettirin !!!");
                } else {
                    System.out.println("Sogutucu kapatilamadi tekrar deneyiniz ve ag baglantinizi kontrol ediniz !!! ");

                }

            }

        }

    }

    @Override
    public void notifyObserver() {
        kullanici.notify(sogutucudurumu);
    }

    @Override
    public void addObserver(IObserver observer) {
        this.kullanici = observer;
    }

}
