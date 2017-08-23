package ir.adnan.lib_requirement_code.util.time;

public class Date {
	
	private int year;
	private int month;
	private int date;
	
	public Date() {
	}

	public Date(int date) {
		this.date = date;
	}

	public Date(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
	}

	private String monthNamePersian[] = { "فروردین", "اردیبهشت", "خرداد",
			"تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن",
			"اسفند" };
	private final String[] monthNameGeorgorian = { "January", "February",
			"March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	
	private final String[] monthNameGeorgorianShort = { "Jan", "Feb",
			"Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
			"Oct", "Nov", "Dec" };

	private String monthNameHijri[] = { "محرم", "صفر", "ربیع الاول",
			"ربیع ثانی", "جمادی الاول", "جمادی الثانی", "رجب", "شعبان",
			"رمضان", "شوال", "ذی القعده", "ذی الحجه" };

	@Deprecated
	public String toString() {
		return "Error";
	}

	public String getMonthName() {
		return monthNamePersian[month];
	}

	public String toStringHi() {
		return date + " " + monthNameHijri[month - 1] + " " + year;
	}

	public String toStringHiWithOutYear() {
		return date + " " + monthNameHijri[month - 1];
	}

	public String toStringGe() {
		return date + " " + monthNameGeorgorian[month] + " " + year;
	}

	public String toStringGeWithOutYear() {
		return date + " " + monthNameGeorgorian[month];
	}
	
	public String toStringGeWithOutYearShortSilab() {
		return date + " " + monthNameGeorgorianShort[month];
	}

	public String toStringSh() {
		return date + " " + monthNamePersian[month] + " " + year;
	}

	public String toStringShWithOutNumber() {
		return monthNamePersian[month] + " " + year;
	}
	
	public String toStringShWithOutYear() {
		return date + " " + monthNamePersian[month];
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}
