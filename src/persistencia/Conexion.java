package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	  private Connection cn ;
		public Conexion(){
			try
	        {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            cn=DriverManager.getConnection("jdbc:odbc:Informatica");

	        }
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Problemas con la conexion");
			}//mostrar error. 
	       
		}
		
		 public Connection getConexion ()
		    {
		        return cn;
		    }
		

}
