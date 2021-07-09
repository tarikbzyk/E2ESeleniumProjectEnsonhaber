package dao;

import Atelier.detaySayfa;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatabaseConnection {

    public Connection con ;


    public  void listele() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensonhaber", "root", "30031993");
        Statement s=con.createStatement();
        ResultSet rs = s.executeQuery("select * from haber");


        while (rs.next()){
            System.out.println(rs.getString("haber_title"));
        }
        con.close();

    }


    public Statement baglantı() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensonhaber", "root", "30031993");
        Statement s=con.createStatement();
        //con.close();
        return s;

    }

    public void ekle(String q) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensonhaber", "root", "30031993");
        Statement s=con.createStatement();
        s.executeUpdate(q);
        con.close();
    }

    public void ekle(detaySayfa eleman) throws SQLException, ParseException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensonhaber", "root", "30031993");
        Statement s=con.createStatement();
        String title;
        if(eleman.getTitle().contains("'")){
            title=eleman.getTitle().replace("'","\\'");
        }
        else
            title=eleman.getTitle();
        String q = "INSERT INTO haber (haber_source, haber_category, haber_title, haber_date)\n" +
                "VALUES ('"+eleman.getSource()+"', '"+eleman.getCategory()+"','"+title+"','"+convertDate(eleman.getDate())+"')";
       try {
           s.executeUpdate(q);
       }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Haber kaydı mevcut : '"+eleman.getTitle()+"'"+ "");
        }

        con.close();
    }

    public void ekle (List<detaySayfa> elemanListe) throws SQLException, ParseException {

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensonhaber", "root", "30031993");
        Statement s=con.createStatement();
        String title;
        String q;
        String cıktı;
        int a = elemanListe.size();


        for (int i=0;i<elemanListe.size();i++) {
            if (elemanListe.get(i).getTitle().contains("'")) {
                title = elemanListe.get(i).getTitle().replace("'", "\\'");
            } else
                title = elemanListe.get(i).getTitle();
            q = "INSERT INTO haber (haber_source, haber_category, haber_title, haber_date)\n" +
                    "VALUES ('" + elemanListe.get(i).getSource() + "', '" + elemanListe.get(i).getCategory() + "','" + title + "','" + convertDate(elemanListe.get(i).getDate()) + "')";
            cıktı = "Haber kaydı olusturuldu : '" + elemanListe.get(i).getTitle() + "'" + "";
            try {
                s.executeUpdate(q);
            } catch (SQLIntegrityConstraintViolationException e) {
                //cıktı = "Haber kaydı mevcut : '" + elemanListe.get(i).getTitle() + "'" + "";
                a--;
            }
            //System.out.println(cıktı);

        }
        if(a>1) {
            System.out.println(a + " yeni kayıt eklendi");
        }
        else
            System.out.println("Yeni haber bulunmamaktadır.Mevcut haberlerin zaten kaydı var.");
        con.close();

    }

    private static String convertDate (String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
        Date date=sdf.parse(dateStr);
        SimpleDateFormat sdf1 = new SimpleDateFormat("y-MM-dd hh:mm:ss");
        return sdf1.format(date);
    }

}
