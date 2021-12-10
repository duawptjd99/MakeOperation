package DMA;

public class IOHandler {

	public IOHandler() {

	}

	public String check(boolean device, int i) {
		if(i==1) {
			return "printing";
		}else if(i==2) {
			return "stop";
		}else {
			return "exit";
		}
		
	}

}
