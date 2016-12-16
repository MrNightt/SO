package pt.europeia.uemail;

public class MemSlot {

	private DepartmentMessages messages;
	private long lastAccessDate;

	public MemSlot() {

		touch();
	}

	public String toString(int index) {
		return "MemSlot(index=" + index + "; lastAccessDate=" + lastAccessDate + ")";
	}

	public DepartmentMessages getMessageContainer() {
		return messages;
	}

	public void setMessageContainer(DepartmentMessages container) {
		touch();
		messages = container;
	}
	
	public MemSlot touch() {
		lastAccessDate = System.currentTimeMillis();
		return this;
	}

	public long getLastAccessDate() {
		return lastAccessDate;
	}
}
