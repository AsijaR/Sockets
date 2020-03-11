package serverATM;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread  extends Thread{
	private Socket socket=null;
	private ServerBankomat server=null;
	private ObjectInputStream ois=null;
	private ObjectOutputStream oos=null;
	ArrayList<Korisnik> listaKorisnika;
	
	public ServerThread(ServerBankomat s,Socket sc) 
	{
		server=s;
		socket=sc;
		listaKorisnika = new ArrayList();
		listaKorisnika.add(new Korisnik("Asija","Ramovic","8880",500));
		listaKorisnika.add(new Korisnik("Melida","Radoncic","5278",1000));
		listaKorisnika.add(new Korisnik("Milan","Petrovic","7820",500));
		
	}
	public void run() {
		try {
			System.out.println("Klijent"+socket.getRemoteSocketAddress()+"je konektovan na server..");
			ois=new ObjectInputStream(socket.getInputStream());
			oos=new ObjectOutputStream(socket.getOutputStream());
			String actiona=(String)ois.readObject();
			System.out.println("Poruka je procitana");
			System.out.println("Broj racuna je:"+actiona);
			Korisnik trenutniKorisnik;
			listaKorisnika.forEach(korisnik->{
				if(korisnik.getBrRacuna().equals(actiona)) 
				{
					try 
					{
					//	trenutniKorisnik = k;
						oos.writeObject(korisnik.getIme());
						oos.writeObject(korisnik.getPrezime());
						oos.writeObject(korisnik.getBrRacuna());
						oos.writeObject(korisnik.getStanje());
						
						while(true) 
							{
							String action=(String)ois.readObject();
							System.out.println("Stigla je "+action);
								try {
									switch(action)
										{
											case "isplata":
												int isplata=(Integer)ois.readObject();
											    System.out.println("proticitao sam "+isplata + " dinara");
											    if(isplata<=0||isplata>korisnik.getStanje()) 
											    	{	oos.writeObject(korisnik.getStanje());
											    		String poruka="Molimo Vas pokusajte ponovo";
											    		oos.writeObject(poruka);
											    		oos.flush();
											    		break;
											    	}
											    else 
											    	{
											    		int racun=server.Isplata(korisnik,isplata);
											    		oos.writeObject(racun);
											    		korisnik.setStanje(racun);
											    		String porukaP="Uspesno islacen novac";
											    		oos.writeObject(porukaP);
											    		System.out.println("Poslao sam poruku");
											    		oos.flush();
											    		break;
													}
												
											case "uplata":
												int uplata=(Integer)ois.readObject();
												System.out.println("proticito sam "+uplata+ " dinara");
												if(uplata<=0) 
												{
													oos.writeObject(korisnik.getStanje());
										    		String poruka="Molimo Vas pokusajte ponovo";
										    		oos.writeObject(poruka);
										    		oos.flush();
										    		break;
										    	}
												else 
												{
													int racunn=server.Uplata(korisnik, uplata);
													oos.writeObject(racunn);
													korisnik.setStanje(racunn);
													String porukaPp="Uspesno uplacen novac";
													oos.writeObject(porukaPp);
													System.out.println("Poslao sam poruku");
										    		oos.flush();
													break;
												}
										}
								}
							catch (IOException e) { e.printStackTrace(); }
							catch (ClassNotFoundException e) { e.printStackTrace();}
						}
				}	
				catch(IOException e) { e.printStackTrace(); } 
				catch (ClassNotFoundException e1) { e1.printStackTrace(); }
				
				}
			}
		);
			ois.close();
			socket.close();
			System.out.println("Klijet "+socket.getRemoteSocketAddress()+"se diskonektovao sa servera");			
		}
		catch(Exception e) {System.out.println("Greska: "+e);}
	}
	
}

