package akillicihaz;

import java.sql.*;

public class KullaniciDogrulama {

    private static KullaniciDogrulama single_instance;

    private KullaniciDogrulama() {
    }

    public static KullaniciDogrulama GetInstance() {
        if (single_instance == null) {
            single_instance = new KullaniciDogrulama();
        }
        return single_instance;
    }

    private Connection baglan() throws InterruptedException {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AkilliCihaz",
                    "postgres", "123456789");
            if (conn != null) {
                System.out.println("Yukleniyor...");
                Thread.sleep(500);
            } else {
                System.out.println("Sistemde teknik bir hata oluştu. Lütfen odev sahibi ile irtibata geçiniz");
            }
        } catch (SQLException e) {
        }
        return conn;
    }

    public Kullanici ara(String kullaniciAdi, String sifre) throws InterruptedException {
        Kullanici kullanici = null;

        String sql = "SELECT *  FROM \"deneme\" WHERE kullaniciadi='" + kullaniciAdi + "' and sifre='" + sifre + "'";

        Connection conn = this.baglan();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            conn.close();

            String ad;
            String parola;

            while (rs.next()) {
                ad = rs.getString("kullaniciadi");
                parola = rs.getString("sifre");

                kullanici = new Kullanici(ad, parola);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
        }

        return kullanici;
    }

}
