package serverATM;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerBankomat implements Runnable {

	private int port=8100;
	private ServerSocket serverSocket=null;
	private Thread thread=null;
	Socket socket=null;
	private ServerThread client=null;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
	

			
	public ServerBankomat() {
		try 
		{
			serverSocket=new ServerSocket(port);
			System.out.println("Server se nalazi na portu "+serverSocket.getLocalPort());
			System.out.println("Ceka se klijent");
			thread=new Thread(this);
			thread.start();
		}
		catch (IOException e) {System.out.println("Greska : " + e); }
	}
		@Override
	public void run() {
		// TODO Auto-generated method stub
			while(thread!=null) 
			{
				try {
					//ceka se povezivanje klijenta na server,a onda se dodaje nova nit
					addThreadClient(serverSocket.accept());
					}
				catch (IOException e) { System.out.println("Greska : " + e); }
		   }
		}
	public void addThreadClient(Socket socket) {
		client=new ServerThread(this, socket);
		client.start();
	}
	public int Isplata(Korisnik k,int isplati) throws IOException
	{	
		int a=k.getStanje();
		System.out.println("u metodu sam");
		a-=isplati;
		System.out.println("oduzeo sam");
		System.out.println("trenutno stanje"+a);
		return a;
	}
	
	public int Uplata(Korisnik k,int uplati) throws IOException
	{	
		int a=k.Stanje;
		System.out.println("u metodu sam");
		a+=uplati;
		System.out.println("oduzeo sam");
		System.out.println("trenutno stanje"+a);
		
		return a;
		
	}
		
	
public static void main(String args[]) {
	ServerBankomat server=new ServerBankomat();
}
}
