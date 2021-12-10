package CPU;

import Global.Constants.EProcessState;

public class Registers {

	private int value;
	private String code;
	private EProcessState eProcessState;
	
	public Registers(int value) {
		this.value = value;
	}

	public Registers(String code) {
		this.code = code;
	}
	
	public Registers(EProcessState eState) {
		this.eProcessState = eState;
	}
	
	public Registers(int value, String code) {
		this.value = value;
		this.code = code;
	}
	

	public int getInt() {
		return value;
	}
	
	public void addInt() {
		this.value++;
	}

	public String getcode() {
		return code;
	}
	
	public EProcessState getState() {
		return eProcessState;
	}

	public void setInt(int value) {
		this.value = value;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setState(EProcessState eProcessState) {
		this.eProcessState = eProcessState;
	}

}
