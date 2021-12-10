package Manager;

public class MemoryManager {
	
	private int[] PSize;
	private int[] Memory;
	private int i = 0;
	private int S = 0;
	private Processing.Process process;

	public MemoryManager() {
		this.Memory = new int[10000];
		this.PSize = new int[10];
	}

	public void saveMemory(int value, int stack) {
		this.Memory[i] = value;
		i = i + stack;
	}
	
	
	public int getMemoryValue(int stack) {
		return Memory[this.PSize[S-1]+stack];	
	}

	public void showMemory() {
		for (int a = 0; a < 9; a = a + 4) {
			System.out.println(this.Memory[a]);
		}
	}

	public String getCodeSegment(int add) {
		return process.getCode(add);
	}

	public int getStackSegment(int add) {
		return process.getStack(add);
	}

	public void setMemory(Processing.Process process) {
		this.process = process;
		this.PSize[S]=this.process.getStackSize();
		S++;
	}
	public void rem() {
		S--;
	}
}
