package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class UEmailServer implements Serializable, Runnable {

	private UEmail email = new UEmail(null,null,null,null);

	private static ArrayList<Socket> filters = new ArrayList<Socket>();

	public static ObservableList<String> serverEmails = FXCollections.observableArrayList();

	public static ObservableList<String> clientEmails = FXCollections.observableArrayList();

	public static ObservableList<String> events = FXCollections.observableArrayList();

	public static ArrayList<String> UEmails = new ArrayList<String>();

	private ObjectInputStream in;
	
	private ObjectOutputStream out;

	//Filters Server Socket
	private static ServerSocket servF;

	//Client Server Socket
	private static ServerSocket servC;

	private static Socket listenerF;

	private static Socket listenerC;

	static int emailNumber = 1;

	//Connection to the Filters
	public static void startServer() throws IOException, ClassNotFoundException {

		//Server para os filtros
		servF = new ServerSocket(12345);

		//Server para os clientes
		servC = new ServerSocket(23456);

		//connects to all 4 Filters one at a time
		for (int times = 1 ; times < 5; times++) {

			//Inicia cada filtro criando um processo para os mesmos que na qual evoca o método main de cada filtro
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "pt.europeia.uemail.Filter"+times);
			pb.start();

			//Espera por uma ligação do filtro e aceita-a
			listenerF = servF.accept();

			//Guarda o socket que serve de conexão com filtro
			filters.add(listenerF);

			//Adiciona aos eventos do Server a String abaixo com o id (número) do filtro recebido pelo sokcet
			events.add("Connection to Filter " + new ObjectInputStream(listenerF.getInputStream()).readObject().toString() + " established.");

		}

		//Inicia uma Thread da própria class (método Run)
		new Thread(new UEmailServer()).start();

		//Adiciona aos eventos do server...
		events.add("Server is running.");

	}


	@Override
	public void run() {

		try {

			//Espera e aceita uma ligação do cliente
			listenerC = servC.accept();

			in = new ObjectInputStream(listenerC.getInputStream());

			//Guarda o que recebeu do cliente no atributo email...
			email = (UEmail) in.readObject();

			new Thread(new UEmailServer()).start();

			synchronized(email) {

				for (Socket each : filters) {

					try {
						
						//Envia o email para o filtro 
						out = new ObjectOutputStream(each.getOutputStream());
						out.writeObject(email);
						
						//Recebe o email do filtro
						in = new ObjectInputStream(each.getInputStream());
						email = (UEmail) in.readObject();
						
					} catch (IOException e) {
						
						e.printStackTrace();
						continue;

					}

				}

				UEmails.add(email.toString());

				if (email.isSpam()) {

					events.add(email.getFilter() + " says email " + emailNumber + " is a spam.");

					serverEmails.add(email.toString());

				} else {

					serverEmails.add(email.toString());

					clientEmails.add(("From: client@universidadeeuropeia.pt"  + "\nTo: " + email.getTo()+ "\nSubject: " + email.getSubject()));

				}
			}

			emailNumber++;

		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();

		}

	}


	public static void main (String[]args) throws ClassNotFoundException, IOException {

		startServer();
	}

}
