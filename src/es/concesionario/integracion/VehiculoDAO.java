package es.concesionario.integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.concesionario.modelo.Vehiculo;


public class VehiculoDAO {
	
	private Connection cx;
	   
    private void conectar() {
      try {
           Class.forName("com.mysql.jdbc.Driver");
           cx= DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario","root","root");
           cx.setAutoCommit(false);
       } catch (SQLException e) {
          
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }
    private void desconectar() {
        try {
           cx.close();
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    
    }

	public int darAlta(Vehiculo vehiculo) {
		
		int idRetornar=0;
		
		try {
		
		//1. conectar
        conectar();
        
        //2.Preparar la sql (query)
		PreparedStatement ps = cx.prepareStatement("INSERT INTO VEHICULO VALUES(?,?,?,?,?,?,?)");
		  // 2.1 setear los interrogantes...
		  ps.setInt(1, 0);
		  ps.setString(2, vehiculo.getMatricula());
		  ps.setString(3, vehiculo.getMarca());
		  ps.setString(4, vehiculo.getModelo());
		  ps.setString(5, vehiculo.getColor());
		  ps.setInt(6, vehiculo.getNumeroCaballos());
		  ps.setBoolean(7, vehiculo.isMarchas());
		
		//3. Ejecutar la consulta
        int filasAfectadas =ps.executeUpdate();
        
        //4.  hacer el commit....
        cx.commit();
        
        if(filasAfectadas>=1) { 
            idRetornar= ultimoId();
        }  
             
       
        //.5.cerrar la conexión
        desconectar();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		       
        return idRetornar;
	}
	
	
	
	public Vehiculo consultarUno(int id) {
		
		Vehiculo v= new Vehiculo();
		try {
		
	  //1. conectar
        conectar();
      //2. preparar la consulta
        PreparedStatement ps = cx.prepareStatement("SELECT * FROM VEHICULO WHERE id=?");
        // 2.1 setear los ?
            ps.setInt(1, id);
      // 3, ejecutar la consulta
            ResultSet rs =ps.executeQuery();  
       //4. llenar el objeto pais.. con los datos de respuesta de BBDD..
            //Nota: La respuesta viene en un objeto ResultSet
            
            if(rs.next()) {   
            	v.setId(rs.getInt("id"));
            	v.setMatricula(rs.getString("matricula"));
            	v.setMarca(rs.getString("marca"));
            	v.setModelo(rs.getString("modelo"));
            	v.setColor(rs.getString("color"));
            	v.setNumeroCaballos(rs.getInt("numeroCaballos"));
            	v.setMarchas(rs.getBoolean("marchas"));
                
             }
			
       //5.desconectar
        	 desconectar();
        	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	

	public ArrayList<Vehiculo> consultarTodos() {
		ArrayList<Vehiculo> vehiculos= new ArrayList<Vehiculo>();
		try {
            //1. conectar
            conectar();
            //2. preparar la sentencia
            PreparedStatement ps = cx.prepareStatement("SELECT * FROM VEHICULO");
            //3. ejecutar la consulta
            ResultSet consulta = ps.executeQuery();
            //4. bajar el resultado de la consulta y ponerlo en el arrayList
            while(consulta.next()) {
                Vehiculo v = new Vehiculo();
                v.setId(consulta.getInt("id"));
                v.setMatricula(consulta.getString("matricula"));
                v.setMarca(consulta.getString("marca"));
                v.setModelo(consulta.getString("modelo"));
                v.setColor(consulta.getString("color"));
                v.setNumeroCaballos(consulta.getInt("numeroCaballos"));
                v.setMarchas(consulta.getBoolean("marchas"));
                                
                vehiculos.add(v);
            }
            //5. desconectar
            desconectar();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return vehiculos;
		
				
	}
	public int ultimoId() {
		int  idM=90;
		try {
			//1. conectar
			conectar();
			//2. preparar la sentencia
			PreparedStatement ps = cx.prepareStatement("SELECT MAX(ID) AS ULTIMO FROM VEHICULO"); 
			//3. ejecutar la consulta
			ResultSet consulta = ps.executeQuery(); 
			//4. bajar el resultado de la consulta y ponerlo en el arrayList
			if(consulta.next()) { 
				idM=consulta.getInt("ULTIMO");
			}
			//5. desconectar
			desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return idM;
	
}
	public ArrayList<Vehiculo> consultarMatricula(String matricula) {
		ArrayList<Vehiculo> vehiculos= new ArrayList<Vehiculo>();
	       try {
	            //1. conectar
	            conectar();
	            //2. preparar la sentencia
	            PreparedStatement ps = cx.prepareStatement("SELECT * FROM VEHICULO WHERE MATRICULA LIKE ?");
	            // 2.1 setear el interrogante
	            //ps.setString(1, "\"" +nombre + "%" + "\"" );
	            ps.setString(1, "%" + matricula +  "%");
	            //3. ejecutar la consulta
	            ResultSet consulta = ps.executeQuery();
	            //4. bajar el resultado de la consulta y ponerlo en el arrayList
	            while(consulta.next()) {
	                Vehiculo v = new Vehiculo();
	                v.setId(consulta.getInt("id"));
	                v.setMatricula(consulta.getString("matricula"));
	                v.setMarca(consulta.getString("marca"));
	                v.setModelo(consulta.getString("modelo"));
	                v.setColor(consulta.getString("color"));
	                v.setNumeroCaballos(consulta.getInt("numeroCaballos"));
	                v.setMarchas(consulta.getBoolean("marchas"));
	                vehiculos.add(v);
	            }
	            //5. desconectar
	            desconectar();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return vehiculos;
		
	}
	public int borrar(int id) {
		int filasAfectadas=0;
        try { 
            //conectar
              conectar();
            //preparar la consulta..
              PreparedStatement ps= cx.prepareStatement("DELETE FROM VEHICULO WHERE ID =?");
           // setear los ?
              ps.setInt(1, id);
           // ejecutar la consulta
              filasAfectadas= ps.executeUpdate();
           // hacer el commit
              cx.commit();
          
           //cerrar la conexion
              desconectar();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return filasAfectadas;
		
	}
	public int actualizar(int id, String matricula, String marca,
			String modelo, String color, int numeroCaballos, boolean marchas) {
		int filasAfectadas=0;
        try { 
            //conectar
              conectar();
              
            //preparar la consulta..
              PreparedStatement ps= cx.prepareStatement("UPDATE VEHICULO SET MATRICULA=?, MARCA=?, MODELO=?, COLOR=?, NUMEROCABALLOS=?, MARCHAS=? WHERE ID=?");
          
           // setear los ?   
              ps.setString(1, matricula);
              ps.setString(2, marca);
              ps.setString(3, modelo);
              ps.setString(4, color);
              ps.setInt(5, numeroCaballos);
              ps.setBoolean(6, marchas);
              ps.setInt(7, id);
              
           // ejecutar la consulta
              filasAfectadas= ps.executeUpdate();
              
           // hacer el commit
              cx.commit();
          
           //cerrar la conexion
              desconectar();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return filasAfectadas;
	}
	
	}
