package Processing;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessQueue {

	private Queue<Process> Queue;
	//peek()은 front가가리키는값 , poll()은front가 가리키는값을 반환하고 삭제
	
	public ProcessQueue() {
		this.Queue = new LinkedList<Process>();
	}
	
	
	public void enqueue(Process process) {
		this.Queue.add(process);
	}

	public Process dequeue() {
		// TODO 자동 생성된 메소드 스텁
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
