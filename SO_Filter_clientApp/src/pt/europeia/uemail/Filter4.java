package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class Filter4 implements Runnable {

	private UEmail email = new UEmail(null,null,null,null);
	private static Socket four;

	//Filter 4 socket to UEmailServer
	public static void Filter4Connection()  {

		try {

			four = new Socket("127.0.0.1",12345);
			ObjectOutputStream out = new ObjectOutputStream(four.getOutputStream());
			out.writeObject(4);
			
			while (true) {
				
				ObjectInputStream in = new ObjectInputStream(four.getInputStream());
				Object email2f = in.readObject();

				if (email2f instanceof UEmail) {

					Filter4 f4 = new Filter4((UEmail) email2f);
					new Thread(f4).start();
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

	public Filter4(UEmail email) {
		this.email = email;
	}

	public static void main(String [] args) {

		Filter4Connection();
	}

	@Override
	public void run() {

		try {

			synchronized(email) {

				if (new Random().nextBoolean()) {

					email.setSpam(true);
					email.setFilter(email.getFilter() + "Filter 4 ");

					new ObjectOutputStream(four.getOutputStream()).writeObject(email);

				} else {

					new ObjectOutputStream(four.getOutputStream()).writeObject(email);
				}

			}

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}


