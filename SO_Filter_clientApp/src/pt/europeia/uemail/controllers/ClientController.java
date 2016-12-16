package pt.europeia.uemail.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pt.europeia.uemail.Departments;
import pt.europeia.uemail.UEmailClient;
import pt.europeia.uemail.UEmailServer;

public class ClientController {

	@FXML
	private TextField ToTF;

	@FXML
	private TextField SubjectTF;

	@FXML
	private TextArea BodyTA;

	@FXML
	private ListView<String> InBoxLV;

	@FXML
	private ListView <String> ContentLV;

	@FXML
	private ListView <String> receivedMailsLV;

	@FXML
	private ListView <String> eventsLV;
	
	@FXML
	private ComboBox<Departments> departments;
	
	@FXML
	public void initialize() {
		
		departments.getItems().setAll(Departments.values());
		departments.setValue(Departments.IT);
		
	}

	@FXML
	public void connect() throws IOException, InterruptedException, ClassNotFoundException {

		eventsLV.setItems(UEmailServer.events);

		InBoxLV.setItems(UEmailServer.clientEmails);

		receivedMailsLV.setItems(UEmailServer.serverEmails);

		UEmailServer.main(null);

	}

	//Creates the UEmail
	public void sendUEmail() throws IOException {

		UEmailClient.createEmail(ToTF.getText(), SubjectTF.getText(), BodyTA.getText());
		UEmailClient.sendEmail();

	}

	//Conversion to .txt
	public void toText() throws FileNotFoundException {

		Formatter f = new Formatter("Emails.txt");
		String listEmail = "";

		for (String e : UEmailServer.UEmails) {

			listEmail+= e + "\t";
		}

		f.format("%s", listEmail);
		f.close();		
	}

}
