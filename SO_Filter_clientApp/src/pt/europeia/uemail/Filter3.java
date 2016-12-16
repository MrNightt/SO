package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class Filter3 implements Runnable {

	private UEmail email = new UEmail(null,null,null,null);
	private static Socket three;

	//Filter 3 socket to UEmailServer
	public static void Filter3Connection()  {

		try {

			three = new Socket("127.0.0.1",12345);
			ObjectOutputStream out = new ObjectOutputStream(three.getOutputStream());
			out.writeObject(3);
			

			while (true) {
				
				ObjectInputStream in = new ObjectInputStream(three.getInputStream());
				Object email2f = in.readObject();

				if (email2f instanceof UEmail) {

					Filter3 f3 = new Filter3((UEmail) email2f);
					new Thread(f3).start();
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

	public Filter3(UEmail email) {
		this.email = email;
	}

	public static void main(String [] args) {

		Filter3Connection();
	}

	@Override
	public void run() {

		try {

			synchronized(email) {

				if (new Random().nextBoolean()) {

					email.setSpam(true);
					email.setFilter(email.getFilter() + "Filter 3 ");

					new ObjectOutputStream(three.getOutputStream()).writeObject(email);

				} else {

					new ObjectOutputStream(three.getOutputStream()).writeObject(email);
				}

			}

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}


