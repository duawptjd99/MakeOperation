package CPU;

import java.util.Vector;

import Global.Constants.EProcessState;
import Manager.MemoryManager;
import os.ExecutePanel;

public class CPU {

	private Registers MAR;
	private Registers MBR;
	private Registers IR;
	private Registers AC;
	private Registers PC;
	private Registers SP;
	private Registers CU;
	private Registers ALU;
	private Registers Status;

	private MemoryManager memory;
	private String currentCode1;
	private int currentCode2;

	private String code;
	private int time = 0;
	private int maxtime = 0;

	private ExecutePanel e;

	public CPU() {

		this.MAR = new Registers(0, "");
		this.MBR = new Registers(0, "");
		this.IR = new Registers(0, "");
		this.AC = new Registers("");
		this.PC = new Registers(0);
		this.SP = new Registers(0);
		this.CU = new Registers("");
		this.ALU = new Registers(0);
		this.Status = new Registers(EProcessState.running);
	}

	public void resetCPU() {
		this.MAR.setCode("");
		this.MAR.setInt(0);
		this.MBR.setCode("");
		this.MBR.setInt(0);
		this.IR.setCode("");
		this.IR.setInt(0);
		this.AC.setCode("");
		this.PC.setInt(0);
		this.SP.setInt(0);
		this.CU.setCode("");
		this.ALU.setInt(0);
		this.time = 0;
		this.Status.setState(EProcessState.running);
	}

	public void setTimeSlice(int maxtime) {
		this.maxtime = maxtime;
	}

	public void seperateCode(String code) {
		this.code = code;
		String[] splitCode = new String[2];
		if (code.length() > 3 && code.substring(3, 4).equals(" ")) {
			splitCode = code.split(" ");
			this.currentCode1 = splitCode[0];
			this.currentCode2 = Integer.parseInt(splitCode[1]);
			// System.out.println(code);
		} else {
			this.currentCode1 = code;
		}
	}

	public String getCode() {
		return code;
	}

	public void executeAline() {

		this.fetch();
		//

		this.IRop(IR.getcode());

		this.addPC();
		try {
			Thread.sleep(1000);
			time++;
			System.out.println(time + " 초");
			if (time == maxtime) {
				this.Status.setState(EProcessState.timeExpired);
			}
		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	public void fetch() {
		this.MAR.setInt(this.PC.getInt());
		//
		this.seperateCode(this.memory.getCodeSegment(this.PC.getInt()));
		//
		this.SP.setInt(currentCode2);
		//
		this.MBR.setCode(this.currentCode1);
		//
		this.IR.setCode(this.MBR.getcode());

	}

	public void IRop(String code) {
		if (code.equals("lda")) {
			this.MAR.setInt(this.SP.getInt());
			this.MBR.setInt(this.MAR.getInt());
			this.AC.setInt(this.MBR.getInt());
		} else if (code.equals("sta")) {
			this.MAR.setCode(this.IR.getcode());
			this.MBR.setInt(this.AC.getInt());
			this.memory.saveMemory(this.MBR.getInt(), 4);// int형 가정
		} else if (code.equals("add")) {
			this.MAR.setInt(this.SP.getInt());
			this.MBR.setInt(this.MAR.getInt());
			this.ALU.setInt(this.MBR.getInt());
			this.ALU.setInt(this.AC.getInt() + this.ALU.getInt());
			this.AC.setInt(this.ALU.getInt());
			this.memory.saveMemory(this.AC.getInt(), 4);
		} else if (code.equals("cmp")) { // 메모리 두개 꺼내와서 비교, 앞에꺼(AC) 기준으로 비교
			this.MBR.setInt(this.memory.getMemoryValue(this.SP.getInt()));
			this.AC.setInt(this.MBR.getInt());
			this.MBR.setInt(this.memory.getMemoryValue(this.SP.getInt() + 4));
			this.ALU.setInt(this.MBR.getInt());
			this.ALU.setInt(this.compare(this.AC.getInt(), this.ALU.getInt()));
			this.AC.setInt(this.ALU.getInt());
		} else if (code.equals("jmp")) {
			this.PC.setInt(this.SP.getInt());
		} else if (code.equals("halt")) {
			this.Status.setState(EProcessState.terminated);
		} else if (code.equals("print")) {// 결과출력
			this.Status.setState(EProcessState.print);
		} else if (code.equals("IOdevice1")) {
			this.Status.setState(EProcessState.interrupt);
		} else if (code.equals("IOdevice2")) {
			this.Status.setState(EProcessState.interrupt);
		} else if (code.equals("IOdevice3")) {
			this.Status.setState(EProcessState.interrupt);
		}
	}

	public int compare(int x, int y) {
		if (x > y) {
			return 1;
		} else if (x < y) {
			return 0;
		} else {
			return -1;
		}
	}

	public EProcessState getState() {
		return this.Status.getState();
	}

	public void setState(EProcessState eProcessState) {
		this.Status.setState(eProcessState);
	}

	public void addPC() {
		this.PC.addInt();

	}

	public Vector<Registers> savePCB() {
		Vector<Registers> vector = new Vector<Registers>();
		vector.addElement(this.MAR);
		vector.addElement(this.MBR);
		vector.addElement(this.IR);
		vector.addElement(this.AC);
		vector.addElement(this.PC);
		vector.addElement(this.SP);
		vector.addElement(this.CU);
		vector.addElement(this.ALU);
		vector.addElement(this.Status);

		return vector;

	}

	public Registers getMAR() {
		return MAR;
	}

	public void setMAR(Registers mAR) {
		MAR = mAR;
	}

	public Registers getMBR() {
		return MBR;
	}

	public void setMBR(Registers mBR) {
		MBR = mBR;
	}

	public Registers getIR() {
		return IR;
	}

	public void setIRop(Registers iR) {
		IR = iR;
	}

	public Registers getAC() {
		return AC;
	}

	public void setAC(Registers aC) {
		AC = aC;
	}

	public Registers getPC() {
		return PC;
	}

	public void setPC(Registers pC) {
		PC = pC;
	}

	public Registers getSP() {
		return SP;
	}

	public void setSP(Registers sP) {
		SP = sP;
	}

	public Registers getCU() {
		return CU;
	}

	public void setCU(Registers cU) {
		CU = cU;
	}

	public Registers getALU() {
		return ALU;
	}

	public void setALU(Registers aLU) {
		ALU = aLU;
	}

	public void linkMemory(MemoryManager memoryManager) {
		this.memory = memoryManager;

	}

	public void setPCB(Vector<Registers> pcb) {
		// TODO 자동 생성된 메소드 스텁
		this.MAR = pcb.elementAt(0);
		this.MBR = pcb.elementAt(1);
		this.IR = pcb.elementAt(2);
		this.AC = pcb.elementAt(3);
		this.PC = pcb.elementAt(4);
		this.SP = pcb.elementAt(5);
		this.CU = pcb.elementAt(6);
		this.ALU = pcb.elementAt(7);
		this.Status = pcb.elementAt(8);
	}

}
