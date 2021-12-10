package Global;

public class Constants {

	public enum EProcessState {
		nnew("nnew"), running("running"), wait("wait"), ready("ready"), terminated("terminated"),
		interrupt("interrupt"), timeExpired("timeExpired"),print("print");

		private String value;

		private EProcessState(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	public enum EState {
		nnew, running, wait, ready, terminated
	}

}
