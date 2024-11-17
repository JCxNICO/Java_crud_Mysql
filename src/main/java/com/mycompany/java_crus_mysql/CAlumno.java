package com.mycompany.java_crus_mysql;

import javax.swing.JTextField;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;



 
/**
 *
 * @author USUARIO
 */
public class CAlumno {

    private String DNI;
    private String nombreAlumnos;
    private String apellidosAlumnos;
    private String direccionAlumnos;
    private String edadAlumnos;
    private String telefonoAlumnos;

    // Getter and Setter para DNI
    public String getDNI() {
        return DNI;
    }

    // Setter para DNI
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    // Getter and Setter para nombreAlumnos
    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    // Getter and Setter para apellidosAlumnos
    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }

    // Getter and Setter para direccionAlumnos
    public String getDireccionAlumnos() {
        return direccionAlumnos;
    }

    public void setDireccionAlumnos(String direccionAlumnos) {
        this.direccionAlumnos = direccionAlumnos;
    }

    // Getter and Setter para edadAlumnos
    public String getEdadAlumnos() {
        return edadAlumnos;
    }

    public void setEdadAlumnos(String edadAlumnos) {
        this.edadAlumnos = edadAlumnos;
    }

    // Getter and Setter para telefonoAlumnos
    public String getTelefonoAlumnos() {
        return telefonoAlumnos;
    }

    public void setTelefonoAlumnos(String telefonoAlumnos) {
        this.telefonoAlumnos = telefonoAlumnos;
    }
    
    public void InsertarAlumno(JTextField paramDNI, JTextField paramNombre, JTextField paramApellidos, JTextField paramDireccion, JTextField paramEdad, JTextField paramTelefono){
       setDNI(paramDNI.getText());
       setNombreAlumnos(paramNombre.getText());
       setApellidosAlumnos(paramApellidos.getText());
       setDireccionAlumnos(paramDireccion.getText());
       setEdadAlumnos(paramEdad.getText());
       setTelefonoAlumnos(paramTelefono.getText());
       
       CConexion objetoConexion = new CConexion();
       
       String consulta =("insert into Alumnos(DNI,nombres,apellidos,direccion,edad,telefono) \n" + "values (?,?,?,?,?,?);");
       
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getDNI());
            cs.setString(2, getNombreAlumnos());
            cs.setString(3, getApellidosAlumnos());
            cs.setString(4, getDireccionAlumnos());
            cs.setString(5, getEdadAlumnos());
            cs.setString(6, getTelefonoAlumnos());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error:" + e.toString());

        }
    }
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
    
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("DNI");
        modelo.addColumn("nombre");
        modelo.addColumn("apellidos");
        modelo.addColumn("direccion");
        modelo.addColumn("edad");
        modelo.addColumn("telefono");
        
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        
        sql = "select * from Alumnos;";
        
        String[] datos = new String[6];
        Statement st;
        try {
            st= objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                
                modelo.addRow(datos);
                
            }
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se`pudo mostar los registros, error:"+e.toString());
            
        }
        
        
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumno, JTextField paramDNI, JTextField paramNombre, JTextField paramApellidos, JTextField paramDireccion, JTextField paramEdad, JTextField paramTelefono){
        
        try {
            
            int fila = paramTablaAlumno.getSelectedRow();
            
            if (fila >=0) {
                
                paramDNI.setText((paramTablaAlumno.getValueAt(fila, 0).toString()));
                paramNombre.setText((paramTablaAlumno.getValueAt(fila, 1).toString()));
                paramApellidos.setText((paramTablaAlumno.getValueAt(fila, 2).toString()));
                paramDireccion.setText((paramTablaAlumno.getValueAt(fila, 3).toString()));
                paramEdad.setText((paramTablaAlumno.getValueAt(fila, 4).toString()));
                paramTelefono.setText((paramTablaAlumno.getValueAt(fila, 5).toString()));       
        }
        
            else{
                 JOptionPane.showMessageDialog(null, "Fila no seleccionada, error:");
            }    
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de seleccion, error:" + e.toString());
            
        }
        
    }
    
    public void ModificarAlumnos( JTextField paramDNI, JTextField paramNombre, JTextField paramApellidos, JTextField paramDireccion, JTextField paramEdad, JTextField paramTelefono){
        
        setDNI(paramDNI.getText());
        setNombreAlumnos(paramNombre.getText());
        setApellidosAlumnos(paramApellidos.getText());
        setDireccionAlumnos(paramDireccion.getText());
        setEdadAlumnos(paramEdad.getText());
        setTelefonoAlumnos(paramTelefono.getText());
         
         CConexion objetoConexion = new CConexion();
         
         String consulta = "UPDATE Alumnos SET nombres = ?, apellidos = ?, direccion = ?, edad = ?, telefono = ? WHERE DNI = ?;";
        
         try {
            
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             
             cs.setString(1, getNombreAlumnos()); 
             cs.setString(2, getApellidosAlumnos()); 
             cs.setString(3, getDireccionAlumnos()); 
             cs.setString(4, getEdadAlumnos()); 
             cs.setString(5, getTelefonoAlumnos());
             cs.setString(6, getDNI());
             
             cs.execute();
             
             JOptionPane.showMessageDialog(null, "Modificación Exitosa");
             
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        }
    }
    
    public void EliminarAlumnos(JTextField paramDNI){
        
        setDNI(paramDNI.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos WHERE alumnos.DNI=?;";
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getDNI());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Alummno");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " +e.toString());
        }
    }
}
