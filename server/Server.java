// File Name Server.java
import java.net.*;
import java.io.*;

public class Server extends Thread {
   private ServerSocket serverSocket;
   //private Thread t;
   
   public Server(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }

   /*
   public void start () 
   {
      System.out.println("Starting server");
	  
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }*/
   
   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
			
			DataInputStream in = new DataInputStream(server.getInputStream());
			
			DataOutputStream out = new DataOutputStream(server.getOutputStream());

			while (true) 
			{
				String input = in.readUTF();
				
				System.out.println("Input from client : "+input);
				
				if (input.equals("q")) 
				{
					out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()+ "\nGoodbye!");
					server.close();
				}
			}
           
         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = Integer.parseInt(args[0]);
      try {
         Thread t = new Server(port);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}