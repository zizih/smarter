package hz.smarter.Model;

public class History {

	public boolean ON;
	public boolean OFF;
	public int PHONE;
	public int HOUR;
	public int MINUTE;
	public String ONTIME;
	public String OFFTIME;

	public History(boolean on, boolean off, int phone, int hour, int monute,
			String on_time, String off_time) {
	}

	public History() {
		setOFF(false);
		setON(false);
		setOFFTIME("00-00-00 00:00:00");
		setONTIME("00-00-00 00:00:00");
		setHOUR(-1);
		setPHONE(-1);
	}

	public boolean isON() {
		return ON;
	}

	public void setON(boolean oN) {
		ON = oN;
	}

	public boolean isOFF() {
		return OFF;
	}

	public void setOFF(boolean oFF) {
		OFF = oFF;
	}

	public int getPHONE() {
		return PHONE;
	}

	public void setPHONE(int pHONE) {
		PHONE = pHONE;
	}

	public int getHOUR() {
		return HOUR;
	}

	public void setHOUR(int hOUR) {
		HOUR = hOUR;
	}

	public int getMINUTE() {
		return MINUTE;
	}

	public void setMINUTE(int mINUTE) {
		MINUTE = mINUTE;
	}

	public String getONTIME() {
		return ONTIME;
	}

	public void setONTIME(String oNTIME) {
		ONTIME = oNTIME;
	}

	public String getOFFTIME() {
		return OFFTIME;
	}

	public void setOFFTIME(String oFFTIME) {
		OFFTIME = oFFTIME;
	}

}
