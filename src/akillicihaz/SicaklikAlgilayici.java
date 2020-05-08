package akillicihaz;

import java.util.*;

public class SicaklikAlgilayici {

    private int sicaklikdegeri;
    private final Random rnd;

    private SicaklikAlgilayici() {
        rnd = new Random();
        sicaklikdegeri = rnd.nextInt(20) + 10;
    }
    private static SicaklikAlgilayici single_instance;

    public static SicaklikAlgilayici GetInstance() {
        if (single_instance == null) {
            single_instance = new SicaklikAlgilayici();
        }
        return single_instance;
    }

    public int getSicaklikdegeri() {
        return sicaklikdegeri;
    }

    public void setSicaklikdegeri(int sicaklikdegeri) {
        this.sicaklikdegeri = sicaklikdegeri;
    }

    public boolean sicaklikOku() {
        System.out.println("Sicaklik ölçülüyor...");
        return rnd.nextBoolean();
    }

    public int sicaklikGonder() throws InterruptedException {
        Thread.sleep(500);
        return sicaklikdegeri;
    }
}
