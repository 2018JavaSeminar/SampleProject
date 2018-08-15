package common;

public class SampleEnum {

	public enum ProcDiv {
		INS(0), UPD(1), DEL(2);

		private final int id;

		private ProcDiv(final int id) {
			this.id = id;
		}

		public int getProcDiv() {
			return id;
		}
	}

	public enum Result {
		STATUS_SUCCESS(0), STATUS_ERROR(1);

		private final int id;

		private Result(final int id) {
			this.id = id;
		}

		public int getStatus() {
			return id;
		}
	}

}
