package Processing;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessQueue {

	private Queue<Process> Queue;
	//peek()�� front������Ű�°� , poll()��front�� ����Ű�°��� ��ȯ�ϰ� ����
	
	public ProcessQueue() {
		this.Queue = new LinkedList<Process>();
	}
	
	
	public void enqueue(Process process) {
		this.Queue.add(process);
	}

	public Process dequeue() {
		// TODO �ڵ� ������ �޼ҵ� ����
		return this.Queue.peek();
	}
	
	public void removeDequeue() {
		this.Queue.poll();
	}
	
	public int getSize() {
		return this.Queue.size();
	}

	public void showQueue() {
		System.out.println(this.Queue);
	}
}
