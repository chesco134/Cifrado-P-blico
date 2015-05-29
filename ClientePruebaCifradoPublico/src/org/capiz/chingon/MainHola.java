package org.capiz.chingon;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainHola {

	public static void main(String args[]) {
		
		try{
			PublicEncryption secure = new PublicEncryption();
			// to encrypt a file
			secure.makeKey();
			secure.saveKey(new File("encryptedKeyFile.cpz"), new File("public.der"));
			String mame = new String(secure.encodedPublicKey);
			System.out.println("Hemos guardado la llave de " + mame.length() + " bytes\n");
			File inputFile = new File("hola.html");
			//File cipheredFile = new File("hola.html.crypt");
			byte[] barr = new byte[(int)inputFile.length()]; //new String("Hola").getBytes();
			FileInputStream fis = new FileInputStream(inputFile);
			fis.read(barr);
			fis.close();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			secure.encrypt(barr, baos);
			byte[] cipheredBytes = baos.toByteArray();
			baos.close();
			DatabaseConnection con = new DatabaseConnection("localhost","HelloDatabase","jirachi","sharPedo319");
			Connection connection = con.getConnection();
			connection.createStatement().executeQuery("insert into perfilUsuarioLaboratorio(boleta,psswd) values('" + 2011640347 + "','" + new String(cipheredBytes) + "');");
			con.closeConnection();
			System.out.println("La cadena cifrada es: " + new String(cipheredBytes));
			secure.loadKey(new File("encryptedKeyFile.cpz"), new File("private.der"));
			ByteArrayOutputStream recuperado = new ByteArrayOutputStream();
			secure.decrypt(new ByteArrayInputStream(cipheredBytes), recuperado);
			System.out.println("\tLeimos... \n\n" + new String(recuperado.toByteArray()));
		}catch(IOException e){
			
		}catch(NoSuchAlgorithmException e2){
			
		}catch(InvalidKeyException e3){
			
		}catch(GeneralSecurityException e4){
			
		}catch(SQLException e){
			
		}
	}
}
