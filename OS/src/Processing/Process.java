package Processing;

import java.util.Vector;

import CPU.CPU;
import CPU.Registers;
import Global.Constants.EProcessState;

public class Process {

	// components
	private PCB pcb;
	private Segment codeSegment;
	private Segment stackSegment;

	private String name = "";
	private String currentCode1;
	private int currentCode2;

	public Process(String[] codes, int stackSegmentSize) {
		this.pcb = new PCB();
		this.codeSegment = new Segment(codes);
		this.stackSegment = new Segment(stackSegmentSize);

	}

	public void setname(String name) {
		this.name = name;
	}
	
	public String getname() {
		return this.name;
	}

	public Vector<Registers> getPCB() {
		return this.pcb.getvector();
	}
	
	public void savePCB(Vector<Registers> vector) {
		this.pcb.savePCB(vector);
	}
	public String[] show1() {

		return this.pcb.show1();
	}
	
	public int[] show2() {
		return this.pcb.show2();
	}
	public String getCode(int address) {
		return codeSegment.code[address];
	}

	public int getStack(int address) {
		return stackSegment.stack[address];
	}

	public int getStackSize() {
		return stackSegment.stack.length;
	}

	public class Segment {
		private String[] code;
		private int[] stack;

		public Segment(int size) {
			this.stack = new int[size];
		}

		public Segment(String[] code) {
			this.code = code;
		}

	}

	public Segment getCodeSegment() {
		return codeSegment;
	}

	public void setCodeSegment(Segment codeSegment) {
		this.codeSegment = codeSegment;
	}

	public Segment getStackSegment() {
		return stackSegment;
	}

	public void setStackSegment(Segment stackSegment) {
		this.stackSegment = stackSegment;
	}

}