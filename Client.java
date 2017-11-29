// File Name Client.java
import java.net.*;
import java.io.*;

public class Client {

   public static void main(String [] args) {
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
	  
      try {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
		 
         OutputStream outToServer = client.getOutputStream();
		 
         DataOutputStream out = new DataOutputStream(outToServer);
		 
		 InputStream inFromServer = client.getInputStream();
		 
		 DataInputStream in = new DataInputStream(inFromServer);
			
		while (true) 
		{
            System.out.print("Enter something : ");
			
            String input = System.console().readLine();
	  
			if(!input.isEmpty())
			{
				switch(input) 
				{
					case "q" :
						out.writeUTF(input);
						System.out.println("Server says " + in.readUTF());
						System.out.println("Exit!");
						client.close();
						System.exit(0);
						break;
					default :
						out.writeUTF(input);
						
						//System.out.println(in.readUTF());
						
						String str = in.readUTF();
						
						try(PrintWriter pw = new PrintWriter(input))
						{
							pw.println(str);
						}
						catch ( IOException e)
						{
							e.printStackTrace();
						}
						
				}
			}
            //System.out.println("input : " + input);
            //System.out.println("-----------\n");
        }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}