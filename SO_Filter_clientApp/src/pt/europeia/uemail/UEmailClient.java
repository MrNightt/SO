package pt.europeia.uemail;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UEmailClient {

	public static UEmail email = new UEmail(null,null,null,null);
	static Socket ss ;

	//Creates the UEmail
	public static void createEmail(String To, String Subject, String Body){

		email.setTo(To);
		email.setSubject(Subject);
		email.setBody(Body);
	}

	public static void sendEmail() throws IOException {

		ss = new Socket("127.0.0.1",23456);
		ObjectOutputStream out = new ObjectOutputStream(ss.getOutputStream());
		out.writeObject(email);
		out.close();

	}

}
