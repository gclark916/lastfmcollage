package base;

public enum TimePeriod {
	 DAY7("7day", "7 days"), MONTH1("1month", "1 month"), MONTH3("3month", "3 months"), MONTH6("6month", "6 months"), MONTH12("12month", "12 months"), OVERALL("overall", "overall");
	
	String lastfmString;
	private String GUIString;
	
	TimePeriod(String lastfmString, String GUIString) {
		this.lastfmString = lastfmString;
		this.GUIString = GUIString;
	}

	public String getGUIString() {
		return GUIString;
	}
}
