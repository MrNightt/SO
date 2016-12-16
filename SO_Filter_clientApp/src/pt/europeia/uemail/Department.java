package pt.europeia.uemail;

public class Department {

	private String name;
	private Integer memSlot;
	private Boolean memSlotActive;
	private String diskPath;

	public Department(String name, String diskPath) {
		super();

		this.name = name;
		this.diskPath = diskPath;
		this.memSlotActive = false;
	}

	@Override
	public String toString() {
		return "Department(name=" + getName() + "; diskPath=" + getDiskPath() + "; memSlot=" + getMemSlot() + "; memSlotActive=" + getMemSlotActive() + ")";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMemSlot() {
		return memSlot;
	}

	public void setMemSlot(Integer memSlot) {
		this.memSlot = memSlot;
	}

	public Boolean getMemSlotActive() {
		return memSlotActive;
	}

	public void setMemSlotActive(Boolean memSlotActive) {
		this.memSlotActive = memSlotActive;
	}

	public String getDiskPath() {
		return diskPath;
	}

	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}

}
