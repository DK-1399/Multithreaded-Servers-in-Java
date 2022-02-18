import java.io.*;
import java.net.*;

class Server {
	public static void main(String[] args) {
		
	ServerSocket server = null;

     try {
		
         server = new ServerSocket(8080);
		server.setReuseAddress(true);

	while (true) {

	Socket client = server.accept();

	System.out.println("New client connected:" + client.getInetAddress().getHostAddress());

	ClientHandler clientSock= new ClientHandler(client);

		new Thread(clientSock).start();
		
	}
		}
	catch (IOException e) { 
		e.printStackTrace();
		}

		finally {

			if (server != null) {

				try {
					server.close();
				}

				catch (IOException e) {
					e.printStackTrace();
				}
		}
		}
	  }


	private static class ClientHandler implements Runnable {
		
    private final Socket clientSocket;

		
		public ClientHandler(Socket socket)
		{
		this.clientSocket = socket;
		}

		public void run()
		  {
			
			PrintWriter output = null;
			BufferedReader input = null;
			
			
			try {
				
				input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
			output = new PrintWriter( clientSocket.getOutputStream(), true);

		
				String clientinput;
				while ((clientinput = input.readLine()) != null) {

				System.out.printf(" Sent from the client:%s\n",clientinput);
				output.println(clientinput);
			}
			}
			catch (IOException e) {
				
				e.printStackTrace();
			}
			
			finally {
				try {
					if (output != null)
					 {
						output.close();
		
					}
			if (input != null)
			 {
				input.close();
				
				clientSocket.close();
				
					}
				   }
				
			catch (IOException e) 
			{
				
		e.printStackTrace();
		
		}
			}
		  }
	}

	}