package os;

import CPU.CPU;

public class Main {

	public static void main(String[] args) {
		// TODO �ڵ� ������ �޼ҵ� ����}
		CPU cpu = new CPU();
		BIOS bios = new BIOS(cpu);
	}

}
