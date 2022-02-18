import java.io.*;
import java.net.*;
import java.util.*;

class Client {

	public static void main(String[] args)
	{

	 try(Socket socket = new Socket("LocalHost", 8080)) {
			
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));

			Scanner input = new Scanner(System.in);
			String row = null;

			while (!"exit".equals(row))  
			
			{
				
		row = input.nextLine();
		out.println(row);
		out.flush();


		System.out.println("Server replied "+ in.readLine());
			
	}
			
			input.close();
		
	}

	catch (IOException e) {
			
		e.printStackTrace();

	}
}
}
