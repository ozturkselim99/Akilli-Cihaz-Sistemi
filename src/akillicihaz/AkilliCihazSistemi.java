package akillicihaz;

import java.util.*;

public class AkilliCihazSistemi {

    public static void main(String[] args) throws InterruptedException {
        int ayarlanan_sicaklikdegeri;
        int sicaklikkontrol = 0;
        Scanner giris = new Scanner(System.in);
        SicaklikAlgilayici sicaklikAlgilayici = SicaklikAlgilayici.GetInstance();
        Eyleyici eyleyici = Eyleyici.GetInstance();
        Kullanici kullanici = null;
        String kullaniciadi;
        String sifre;
        int secim;
        do {
            KullaniciDogrulama kullaniciDogrulama = KullaniciDogrulama.GetInstance();
            System.out.print("Kullanici adinizi giriniz: ");
            kullaniciadi = giris.next();
            System.out.print("Sifrenizi giriniz: ");
            sifre = giris.next();
            kullanici = kullaniciDogrulama.ara(kullaniciadi, sifre);
            if (kullanici == null) {
                System.out.println("Kullanici adiniz veya sifreniz hatali tekrar deneyiniz");

            }
        } while (kullanici == null);
        eyleyici.addObserver(new Kullanici(kullaniciadi, sifre));
        System.out.println("Hosgeldiniz: " + kullanici);
        do {
            System.out.println("------------------------------------------");
            System.out.println("Hangi islemi gerceklestirmek istiyorsunuz ?");
            System.out.println("1- Sicaklik goruntule");
            System.out.println("2- Sogutucu ac");
            System.out.println("3- Sogutucu kapat");
            System.out.println("4- Herhangi bir işlem gerceklestirmek istemiyorum");
            System.out.println("------------------------------------------");
            secim = giris.nextInt();
            switch (secim) {
                case 1:
                    if (sicaklikAlgilayici.sicaklikOku()) {
                        sicaklikkontrol = 0;
                        System.out.println("Olculen sicaklik degeri:" + sicaklikAlgilayici.sicaklikGonder());
                    } else {
                        sicaklikkontrol++;
                        if (sicaklikkontrol == 3) {
                            System.out.println("Sicaklik olculemedi !!! Sıcaklık algılayıcınız  bozulmuş olabilir !!! Cihazini en yakin teknik servise kontrol ettirin");
                        } else {
                            System.out.println("Sicaklik olculemedi tekrar deneyiniz ve ag baglantinizi kontrol ediniz !!! ");

                        }

                    }
                    break;
                case 2:

                    if (eyleyici.sogutucuAc()) {
                        do {
                            System.out.print("Ayarlamak istediginiz sicaklik degerini giriniz: ");
                            ayarlanan_sicaklikdegeri = giris.nextInt();
                            if (ayarlanan_sicaklikdegeri > sicaklikAlgilayici.getSicaklikdegeri()) {
                                System.out.println("Ayarlamak istediginiz sicaklik degeri ortam sicakligindan büyük! Sicaklik degerini tekrar giriniz ");
                            } else {
                                System.out.println("Ortam sogutuluyor...");
                                Thread.sleep(500);
                                System.out.println("Ortam ayarlanan sicaklik degerine getirildi.");
                                sicaklikAlgilayici.setSicaklikdegeri(ayarlanan_sicaklikdegeri);
                                break;
                            }
                        } while (true);
                    }

                    break;
                case 3:
                    eyleyici.sogutucuKapat();
                    break;
                case 4:
                    System.out.println("Cikis yapildi");
                    break;
                default:
                    System.out.println("Hatali secim! 1, 2,3 ya da 4'e basiniz.");
                    break;
            }
        } while (secim != 4);
    }

}
