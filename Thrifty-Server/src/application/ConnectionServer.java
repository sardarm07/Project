package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;
import java.sql.*;

public class ConnectionServer extends Thread {
	   protected ServerSocket serverSocket;
	   protected static Vector clients; 
	   protected static Connection con;
	   private Socket clientSocket;
	   
	   public ConnectionServer(int port) throws IOException, ClassNotFoundException, SQLException {
	      serverSocket = new ServerSocket(port);
	      clients = new Vector (); 
	      clientSocket = null;
	      Class.forName("com.mysql.cj.jdbc.Driver");  
	      con=DriverManager.getConnection(  
	      "jdbc:mysql://localhost:3306/thrifty","root","admin");
	      //serverSocket.setSoTimeout(10000);
	   }

	   public void run() {
	      while(true) {
	         try {
	            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
	            clientSocket = serverSocket.accept ();
	            
	            System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
	            
	            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
	            System.out.println(in.readUTF());
	            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + clientSocket.getLocalSocketAddress());
	            ClientThread c = new ClientThread (clientSocket); 
	            clients.add (c); 
	            c.start ();
	            
	         }catch(SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e) {
	            e.printStackTrace();
	            break;
	         }
	      }
	   }
}