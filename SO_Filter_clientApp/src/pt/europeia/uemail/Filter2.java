package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class Filter2 implements Runnable {

	private UEmail email = new UEmail(null,null,null,null);
	private static Socket two;

	//Filter 2 socket to UEmailServer
	public static void Filter2Connection()  {

		try {

			two = new Socket("127.0.0.1",12345);
			ObjectOutputStream out = new ObjectOutputStream(two.getOutputStream());
			out.writeObject(2);
			

			while (true) {
				
				ObjectInputStream in = new ObjectInputStream(two.getInputStream());
				Object email2f = in.readObject();

				if (email2f instanceof UEmail) {

					Filter2 f2 = new Filter2((UEmail) email2f);
					new Thread(f2).start();
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

	public Filter2(UEmail email) {
		this.email = email;
	}

	public static void main(String [] args) {

		Filter2Connection();
	}

	@Override
	public void run() {

		try {

			synchronized(email) {

				if (new Random().nextBoolean()) {

					email.setSpam(true);
					email.setFilter(email.getFilter() + "Filter 2 ");

					new ObjectOutputStream(two.getOutputStream()).writeObject(email);

				} else {

					new ObjectOutputStream(two.getOutputStream()).writeObject(email);
				}

			}

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}


	}

}


