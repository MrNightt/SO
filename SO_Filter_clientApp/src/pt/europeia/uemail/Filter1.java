package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class Filter1 implements Runnable {

	private UEmail email = new UEmail(null,null,null,null);
	private static Socket one;

	//Filter 1 socket to UEmailServer
	public static void Filter1Connection()  {

		try {

			one = new Socket("127.0.0.1",12345);
			ObjectOutputStream out = new ObjectOutputStream(one.getOutputStream());
			out.writeObject(1);

			while (true) {

				ObjectInputStream in = new ObjectInputStream(one.getInputStream());
				Object email2f = in.readObject();

				if (email2f instanceof UEmail) {

					Filter1 f1 = new Filter1((UEmail) email2f);
					new Thread(f1).start();
				}
			}




		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}

	public Filter1(UEmail email) {
		this.email = email;
	}

	public static void main(String [] args) {

		Filter1Connection();
	}

	@Override
	public void run() {

		try {

			synchronized(email) {

				if (new Random().nextBoolean()) {

					email.setSpam(true);
					email.setFilter("Filter 1 ");

					//Envia o email filtrado
					new ObjectOutputStream(one.getOutputStream()).writeObject(email);

				} else {
					
					//Envia o email filtrado
					new ObjectOutputStream(one.getOutputStream()).writeObject(email);
				}
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}


