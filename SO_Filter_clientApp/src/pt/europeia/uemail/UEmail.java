package pt.europeia.uemail;

import java.io.Serializable;

public class UEmail implements Serializable{

	private String From;
	private String To;
	private String Subject;
	private String Body;
	private boolean isSpam = false;
	private String filter;
	private boolean open;
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public UEmail(String from, String to, String subject, String body) {
		From = from;
		To = to;
		Subject = subject;
		Body = body;
		filter = "";
		open = false;
	}
	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public boolean isSpam() {
		return isSpam;
	}
	public void setSpam(boolean isSpam) {
		this.isSpam = isSpam;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	
	@Override
	public String toString() {
		return UEmailServer.emailNumber+"\nFrom: " + "client@universidadeeuropeia.pt" + "\nTo: " + To + "\nSubject: " + Subject + " \nBody: " + Body + "\n"+ filter + " \nisSpam: " + isSpam;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}



}
