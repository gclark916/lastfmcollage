package base;

public class CollageSettings {
	
	public String key;
	public String username;
	public int rowCount;
	public int colCount;
	public TimePeriod period;
	
	public CollageSettings(String username, int rowCount, int colCount, TimePeriod period, String key) {
		this.username = username;
		this.rowCount = rowCount;
		this.colCount = colCount;
		this.period = period;
		this.key = key;
	}
	
	public CollageSettings(String[] args) {
		username = "";
		rowCount = 0;
		colCount = 0;
		period = TimePeriod.DAY7;
		key = "d048f1e12a1c2039e45d9b94d622bc1e";
		
		for (int argIndex = 0, argCount = args.length; argIndex < argCount; argIndex++)
		{
			String arg = args[argIndex];
			switch (arg)
			{
			case "-u":
				username = args[argIndex+1];
				break;
			case "-p":
				String periodArg = args[argIndex+1];
				switch (periodArg)
				{
				case "overall":
					period = TimePeriod.OVERALL;
					break;
					
				case "7days":
					period = TimePeriod.DAY7;
					break;
					
				case "1month":
					period = TimePeriod.MONTH1;
					break;
					
				case "3month":
					period = TimePeriod.MONTH3;
					break;
					
				case "6month":
					period = TimePeriod.MONTH6;
					break;
					
				case "12month":
					period = TimePeriod.MONTH12;
					break;
				}
				break;
			case "-r":
				rowCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-c":
				colCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-k":
				key = args[argIndex+1];
				break;
			}
		}
		
		if (rowCount <= 0)
		{
			if (colCount <= 0)
			{
				rowCount = 3;
				colCount = 3;
			}
			
			rowCount = colCount;
		}
		else
		{
			if (colCount <= 0)
				colCount = rowCount;
		}
	}	
}
