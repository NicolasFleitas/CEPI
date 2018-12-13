package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cuentas_cte;
import modelos.Inscripciones;
import utiles.Conexion;
import utiles.Utiles;

public class CuentasControlador {
    public static String agregarCuota(int id_inscripcion, int nro_cuotas) {
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                String sql = "select *, cv.monto_convocatoria as cuotamonto from inscripciones i "
                        + "left join convocatorias cv on "
                        + "i.id_convocatoria=cv.id_convocatoria "
                        + "where i.id_inscripcion=" + id_inscripcion
                        + " order by id_inscripcion";
                System.out.println("id----->" + id_inscripcion);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    BigDecimal total = BigDecimal.ZERO;
                    int cuota_cuota = 0;
                    String vence = "";
                    String estado;
                    estado = "PENDIENTE";
                    while (rs.next()) {
                        Calendar cuota_vencimiento = GregorianCalendar.getInstance();
                        cuota_vencimiento.setTime(rs.getDate("vencimientocuota_inscripcion"));
                        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String sqli = "insert into cuentas_cte "
                                + "(id_inscripcion,"
                                + "cuota_cuota,"
                                + "total_cuota,"
                                + "id_concepto,"
                                + "monto_cuota,"
                                + "vencimiento_cuota,"
                                + "estado) "
                                + "values (?,?,?,?,?,?,?)";

                        if (rs.getInt("monto_matricula") > 0) {
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, rs.getInt("id_inscripcion"));
                                psi.setInt(2, 1);
                                psi.setInt(3, 1);
                                psi.setInt(4, 1);
                                psi.setInt(5, rs.getInt("monto_matricula"));
                                psi.setDate(6, rs.getDate("fecha_inscripcion"));
                                psi.setString(7, estado);
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().setAutoCommit(false);
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }
                        }
                        // cuota definida
                        //nro_cuotas
                        //for (int i = 0; i < rs.getInt("cuotas_cursohabilitado"); i++) {
                            for (int i = 0; i < nro_cuotas; i++) {
                            cuota_cuota = cuota_cuota + 1;
                            vence = sdf.format(cuota_vencimiento.getTime());
                            java.sql.Date cuotavence = Utiles.stringToSqlDate(vence);
                            //cuotas 
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                                psi.setInt(1, rs.getInt("id_inscripcion"));
                                psi.setInt(2, cuota_cuota);
                                psi.setInt(3, rs.getInt("monto_convocatoria"));
                                psi.setInt(4, 2);
                                psi.setInt(5, rs.getInt("cuotamonto"));
                                psi.setDate(6, cuotavence);
                                psi.setString(7, estado);
                                psi.executeUpdate();
                                psi.close();
                                Conexion.getConn().commit();
                            } catch (SQLException ex) {
                                System.out.println("--> " + ex.getLocalizedMessage());
                                try {
                                    Conexion.getConn().rollback();
                                } catch (SQLException ex1) {
                                    System.out.println("--> " + ex1.getLocalizedMessage());
                                }
                            }
                            cuota_vencimiento.add(Calendar.DATE, 30);
                        }
                    }
                    ps.close();
                    System.out.println("--> " + valor);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

}
