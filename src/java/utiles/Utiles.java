package utiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Utiles {

    public static final String BD_EMPRESAS = "CEPI";
    public static final int REGISTROS_PAGINA = 7;

    public static String quitarGuiones(String texto){
        return texto.replace("--", "");
    }
    
    public static String md5(String palabra) {
        String palabraMD5="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(palabra.getBytes());
            int size = b.length;
            StringBuffer sb = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    sb.append("0" + Integer.toHexString(u));
                } else {
                    sb.append(Integer.toHexString(u));
                }
            }
            palabraMD5 = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return palabraMD5;
    }
     
    public static java.sql.Date utilToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.util.Date sqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.util.Date stringToUtilDate(String fecha2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        try {
            utilDate = sdf.parse(fecha2);
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilDate;
    }
    
    public static java.sql.Date stringToSqlDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = null;
        try {
            utilDate = sdf.parse(fecha);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }
    
    public static java.sql.Date stringToSqlDate2(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = null;
        try {
            utilDate = sdf.parse(fecha);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }

    public static String sqlDateToString(java.sql.Date fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        string = sdf.format(fecha);
        return string;
    }

    public static Timestamp stringToUtilTimestamp(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        java.util.Date today = new java.util.Date();
        try {
            today = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Timestamp ts1 = new java.sql.Timestamp(today.getTime());
        SimpleDateFormat otroformato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        otroformato.format(ts1);
        return ts1;
    }

    public static String sqlTimestampToString(Timestamp fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        string = sdf.format(fecha);
        return string;
    }

    public static String espacios(int longitud) {
        longitud = longitud * 3;
        String valor = "";
        for (int i = 1; i <= longitud; i++) {
            valor += "&nbsp;";
        }
        return valor;
    }

    public static String darFormato(float numero) {
        DecimalFormat formateador = new DecimalFormat("###,###,###.##");
        formateador.setMaximumFractionDigits(2);
        return formateador.format(numero);
    }
    
    
    public static String getSN(String letra){
        String valor = "";
        if(letra.equals("S")){
            valor = "Si";
        }else if(letra.equals("N")){
            valor = "No";
        }
        return valor;
    }
    
    public static String getSNA(String letra){
        String valor = "";
        if(letra.equals("S")){
            valor = "Tiene Auxiliar";
        }else if(letra.equals("N")){
            valor = "No Tiene Auxiliar";
        }else if(letra.equals("A")){
            valor = "Es Auxiliar";
        }
        return valor;
    }
    
}
