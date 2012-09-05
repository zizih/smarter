package hz.smarter.Model;

public class HistorySingleton {

	private static History history = new History();

	public static History getHistoryInstance() {
		return history;
	}

	public static void initializeHistory() {
		history.setOFF(false);
		history.setON(false);
		history.setOFFTIME("00-00-00 00:00:00");
		history.setONTIME("00-00-00 00:00:00");
		history.setHOUR(-1);
		history.setPHONE("");
		history.setONCONTENT("on");
		history.setOFFCONTENT("off");
		history.setTIMECONTENT("00:00");
	}

}
