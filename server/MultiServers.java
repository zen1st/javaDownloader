import java.net.*;
import java.io.*;

public class MultiServers extends Thread{

    private Socket socket = null;

    public MultiServers(Socket socket) throws IOException{

        super("MultiServers");
        this.socket = socket;
		//this.socket.setSoTimeout(10000);

    }

	public void run() {
		while(true) 
		{
			try 
			{
				System.out.println("Just connected to " + this.socket.getRemoteSocketAddress());
			
				DataInputStream in = new DataInputStream(this.socket.getInputStream());
			
				DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

				while(true)
				{
					String input = in.readUTF();
					if(!input.isEmpty())
					{
						switch(input) 
						{
							case "q" :
								out.writeUTF("Thank you for connecting to " + this.socket.getLocalSocketAddress()+ " Goodbye!");
								this.socket.close();
								break;
							default :
								//System.out.println("Input from Client " + this.socket.getRemoteSocketAddress() + " : " + input);
								FileHelper fh = new FileHelper(input);
								//String[] words=input.split("\\s");
								out.writeUTF(fh.getFileContent());
						}
					}
				}
			}
			/*
			catch (SocketTimeoutException s) 
			{
				System.out.println("Socket timed out!");
				break;
			}*/
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        boolean listeningSocket = true;
		
		int port = Integer.parseInt(args[0]);
		
        try {
            serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			
        } catch (IOException e) {
            System.err.println("Could not listen on port : " + port);
        }

        while(listeningSocket){
            Socket clientSocket = serverSocket.accept();
			
			MultiServers server = new MultiServers(clientSocket);
			
            server.start();
        }
        serverSocket.close();       
    }

}