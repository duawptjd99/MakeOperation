package Processing;

import java.util.Vector;

import CPU.Registers;
import Global.Constants.EProcessState;

public class PCB {
	private int id;
	private EProcessState eProcessState;

	private Registers MAR;
	private Registers MBR;
	private Registers IR;
	private Registers AC;
	private Registers PC;
	private Registers SP;
	private Registers CU;
	private Registers ALU;
	private Registers Status;

	private Vector<Registers> vector;

	public PCB() {
		this.vector = new Vector<Registers>();
	}

	public void savePCB(Vector<Registers> vector) {
		this.vector = vector;
		this.MAR = vector.elementAt(0);
		this.MBR = vector.elementAt(1);
		this.IR = vector.elementAt(2);
		this.AC = vector.elementAt(3);
		this.PC = vector.elementAt(4);
		this.SP = vector.elementAt(5);
		this.CU = vector.elementAt(6);
		this.ALU = vector.elementAt(7);
		this.Status = vector.elementAt(8);
	}
	
	public String[] show1() {
		String[] a = new String[5];
		a[0]=this.MAR.getcode();
		a[1]=this.MBR.getcode();
		a[2]=this.IR.getcode();
		a[3]=this.SP.getcode();
		a[4]=this.CU.getcode();
		
		return a;
		
	}
	
	public int[] show2() {
		int[] a = new int[6];
		a[0]=this.PC.getInt();
		a[1]=this.IR.getInt();
		a[2]=this.MAR.getInt();
		a[3]=this.MBR.getInt();
		a[4]=this.ALU.getInt();
		a[5]=this.AC.getInt();
		return a;
	}
	
	public Vector<Registers> getvector() {
		return this.vector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EProcessState getState() {
		return eProcessState;
	}

	public void setState(EProcessState running) {
		this.eProcessState = running;
	}

}
