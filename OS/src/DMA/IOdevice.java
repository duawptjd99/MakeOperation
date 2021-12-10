package DMA;

public class IOdevice {
	boolean device1;
	boolean device2;
	boolean device3;
	private IOHandler handler;

	public IOdevice() {
		this.handler = new IOHandler();

		this.device1 = false;
		this.device2 = false;
		this.device3 = false;
	}

	public String checkdevice() {
		if (device1 = true) {
			return handler.check(device1, 1);
		} else if (device2 = true) {
			return handler.check(device2, 2);
		} else if (device3 = true) {
			return handler.check(device3, 3);
		} else {
			return null;
		}
	}
}