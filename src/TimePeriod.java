
public enum TimePeriod {
	OVERALL("overall"), DAY7("7day"), MONTH1("1month"), MONTH3("3month"), MONTH6("6month"), MONTH12("12month");
	
	String lastfmString;
	
	TimePeriod(String lastfmString) {
		this.lastfmString = lastfmString;
	}
}
