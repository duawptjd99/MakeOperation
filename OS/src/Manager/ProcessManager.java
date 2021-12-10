package Manager;

import CPU.CPU;
import DMA.IOdevice;
import Global.Constants.EProcessState;
import Loader.Loader;
import Processing.Process;
import Processing.ProcessQueue;
import os.ExecutePanel;

public class ProcessManager {
	private Loader loader;
	private ProcessQueue readyQueue;
	private ProcessQueue ioQueue;
	private ProcessQueue waitQueue;


	private MemoryManager memoryManager;
	private CPU cpu;
	private ExecutePanel EXP;

	private int timeSlice = 15;
	private Process runningProcess;
	private IOdevice IOdevice;
	private int d=0;
	
	public ProcessManager() {
		this.loader = new Loader();
		this.readyQueue = new ProcessQueue();
		this.waitQueue = new ProcessQueue();
		this.ioQueue = new ProcessQueue();
		this.IOdevice = new DMA.IOdevice();
	}

	public void associate(MemoryManager memoryManager, CPU cpu, IOdevice IOdevice) {
		this.memoryManager = memoryManager;
		this.cpu = cpu;
		this.IOdevice = IOdevice;

	}

	public void associate2(ExecutePanel exp) {
		this.EXP = exp;
	}

	int s = 1;

	public void execute() {
		this.runningProcess = this.readyQueue.dequeue();
		this.memoryManager.setMemory(this.runningProcess);
		this.cpu.linkMemory(memoryManager);
		this.cpu.setTimeSlice(timeSlice);
		// this.cpu.setPCB(this.runningProcess.getPCB());

		readyQueue.showQueue();

		EXP.sendcode("CPU실행 TIMESLICE = 15초 | Process Name = " + this.runningProcess.getname());
		EXP.sendcode("-------------------------------------------------------------------------");
		System.out.println("CPU실행 TIMESLICE = 15초 | Process Name = " + this.runningProcess.getname());
		System.out.println("-------------------------------------------------------------------------");

		while (this.cpu.getState() == EProcessState.running) {
			this.cpu.executeAline();
			EXP.sendcode(this.cpu.getCode());
		}
		if (this.cpu.getState() != EProcessState.running) {
			if (this.cpu.getState() != EProcessState.terminated) {
				if (this.cpu.getState() == EProcessState.print) {
					EXP.sendcode("결과값 : " + this.cpu.getAC().getInt());
					System.out.println("결과값 : " + this.cpu.getAC().getInt());
					this.cpu.executeAline();
					this.cpu.setState(EProcessState.terminated);
				} else if (this.cpu.getState() == EProcessState.interrupt) {
					this.runningProcess.savePCB(this.cpu.savePCB());
					this.waitQueue.enqueue(runningProcess);
					EXP.sendcode("상태 : " + this.cpu.getState() + "\n");
					EXP.sendcode("Context Switching...\n");
					System.out.println("상태 : " + this.cpu.getState() + " Context Switching.. ");
					EXP.sendcode("PCB저장");
		//			contextSwitching();
					
					
					//contextSwitching();
					//String[] a = this.runningProcess.show1();
					//int[] b = this.runningProcess.show2();
 					//EXP.sendcode(a[0]+a[1]+a[2]+a[3]+a[4]);
 					//EXP.sendcode(b[0]+b[1]+b);
					//d=1;
				} else {

					this.waitQueue.enqueue(runningProcess);
					EXP.sendcode("상태 : " + this.cpu.getState() + "\n");
					EXP.sendcode("Context Switching...\n");
					System.out.println("상태 : " + this.cpu.getState() + " Context Switching.. ");
					contextSwitching();
				}
			}
			
			//System.out.println(this.cpu.getState());

		}

		this.EXP.sendcode("상태 : " + this.cpu.getState() + "\n");
		//this.runningProcess.savePCB(this.cpu.savePCB());

		this.readyQueue.removeDequeue();
		this.cpu.setState(EProcessState.running);
		this.cpu.resetCPU();
	}

	public void contextSwitching() {
		this.readyQueue.removeDequeue();
		this.runningProcess.savePCB(this.cpu.savePCB());
		this.runningProcess = this.readyQueue.dequeue();
		this.cpu.setState(EProcessState.running);
		this.cpu.resetCPU();
		execute();
	}

	public void sendProcess(Process process) {
		this.readyQueue.enqueue(process);
	}
}
