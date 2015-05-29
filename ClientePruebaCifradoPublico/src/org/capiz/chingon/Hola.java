package org.capiz.chingon;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class Hola {

	public static void main(String args[]){
		
		DatabaseConnection dbCon = new DatabaseConnection("localhost","UPIITA","jirachi","sharPedo319");
		Connection con = dbCon.getConnection();
		try{
			String query = "Call insertaAlumno(?,?)";
			CallableStatement call = con.prepareCall(query);
			call.setString(1,args[0]);
			call.setString(2, args[1]);
			call.executeQuery();
			dbCon.closeConnection();
			System.out.println("¡Éxito!");
		}catch(SQLException e){
			e.printStackTrace();
		}

		System.out.println("Finalizando programa...");
	}
}
