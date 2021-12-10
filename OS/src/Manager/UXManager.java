package Manager;

import java.util.Scanner;

import CPU.CPU;
import Loader.Loader;
import Processing.Process;

public class UXManager {

	private Loader loader;
	private ProcessManager processManager;

	public UXManager() {

	}

	public void associate(Loader loader, ProcessManager processManager) {
		this.loader = loader;
		this.processManager = processManager;
	}

	public void run(String PName) {
		System.out.println(PName);
		if (PName.equals("p34")) {
			setting("p3");
			setting("p4");
		} else if (PName.equals("p51")) {
			setting("p5");
			setting("p1");
		} else {
			setting(PName);
		}
		this.processManager.execute();
	}

	public void setting(String PName) {
		Scanner scanner = new Scanner(PName + ".txt");
		String fileName = scanner.nextLine();
		Process process = this.loader.load(fileName);
		process.setname(PName);
		this.processManager.sendProcess(process);
	}
}
