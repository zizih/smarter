package hz.smarter.Model;

public class History {

	public boolean ON;
	public boolean OFF;
	public String PHONE;
	public int HOUR;
	public int MINUTE;
	public String ONTIME;
	public String OFFTIME;
	public String ONCONTENT;
	public String OFFCONTENT;
	public String TIMECONTENT;

	public History(boolean on, boolean off, int phone, int hour, int monute,
			String on_time, String off_time) {
	}

	public History() {
		setOFF(false);
		setON(false);
		setOFFTIME("00-00-00 00:00:00");
		setONTIME("00-00-00 00:00:00");
		setHOUR(-1);
		setPHONE("");
		setONCONTENT("on");
		setOFFCONTENT("off");
		setTIMECONTENT("00:00");
	}

	public String getTIMECONTENT() {
		return TIMECONTENT;
	}

	public void setTIMECONTENT(String tIMECONTENT) {
		TIMECONTENT = tIMECONTENT;
	}

	public String getONCONTENT() {
		return ONCONTENT;
	}

	public void setONCONTENT(String oNCONTENT) {
		ONCONTENT = oNCONTENT;
	}

	public String getOFFCONTENT() {
		return OFFCONTENT;
	}

	public void setOFFCONTENT(String oFFCONTENT) {
		OFFCONTENT = oFFCONTENT;
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

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
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
