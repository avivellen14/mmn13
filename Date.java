/**
 * This class represents a Date object
 * 
 * @author course staff
 * @version (2023a)
 */

public class Date
{
    private int _day;       
    private int _month;     
    private int _year;      

    final int MONTH_IN_YEAR=12;
    final int MAX_YEAR=9999;
    final int MIN_YEAR=1000;
    final int DEFAULT_DAY=1;
    final int DEFAULT_MONTH=1;
    final int DEFAULT_YEAR=2000;
    final int DAYS_IN_DECEMBER= 31;
    final int DAYS_IN_NOVEMBER=30;
    final int DAYS_IN_LEAP_FEBRUARY=29;
    final int DAYS_IN_DEFAULT_FEBRUARY=28;

    /**
     * Checks if the date is valid.
     * @param day 
     * @param month
     * @param year
     * return true if the date created by day,month and year is valid
     */
    private boolean isDateValid(int day, int month, int year){
        if (year <MIN_YEAR || year >MAX_YEAR)
            return false;

        if (month <1 || month>MONTH_IN_YEAR)
            return false;

        switch (month){ //veryfing that the day is proper according to the given month
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:if(day<1 || day>DAYS_IN_DECEMBER)
                    return false;
                break;
            case 4:
            case 6:
            case 9:
            case 11:if(day<1 || day>DAYS_IN_NOVEMBER)
                    return false;
                break;
            case 2: if(isLeapYear(year)==true){
                    if(day<1 || day >DAYS_IN_LEAP_FEBRUARY)
                        return false;
                }
                else if(day<1 || day >DAYS_IN_DEFAULT_FEBRUARY){
                    return false;
                }    
        }
        return true;
    }

    private boolean isLeapYear(int year){
        return (year%4==0 && year%100!=0 || year%400==0);
    }

    // computes the day number since the beginning of the Christian counting of years 
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        } 
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }
    //constructor:
    /** 
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * 
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year) {            
        if (isDateValid(day,month,year)){
            _day=day;
            _month=month;
            _year=year;
        }
        else{
            _day=DEFAULT_DAY;
            _month=DEFAULT_MONTH;
            _year=DEFAULT_YEAR;
        }   
    }

    /**
     * Copy constructor
     * @param other The date to be copied
     */
    public Date (Date other){
        _day=other._day;
        _month=other._month;
        _year=other._year;
    }

    /** 
     * Gets the year 
     * @return the year
     */
    public int getYear(){
        return _year;
    }

    /** 
     * Gets the month 
     * @return the month
     */
    public int getMonth(){
        return _month;
    }

    /** 
     * Gets the day
     * @return the day
     */
    public int getDay(){
        return _day;
    }

    /** 
     * Sets the year
     * (only if date remains valid)
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet){
        if(isDateValid(_day,_month,yearToSet )==true) 
            _year = yearToSet;
    }

    /** 
     * Set the month
     * (only if date remains valid)
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet){
        if(isDateValid(_day,monthToSet,_year)==true)
            _month = monthToSet;
    }

    /** 
     * Set the day
     * (only if date remains valid)
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet){
        if(isDateValid(dayToSet,_month,_year)==true)   
            _day = dayToSet;
    }

    /** 
     *  Check if 2 dates are the same
     *  @param other the date to compare this date to
     *  @return true if the dates are the same, otherwise false
     */
    public boolean equals(Date other){
        if(_day == other._day && _month == other._month && _year == other._year)
            return true;
        return false;
    }

    /** 
     *  Check if this date is before other date
     *  @param other date to compare this date to
     *  @return true if this date is before other date, otherwise false
     */
    public boolean before(Date other){
        if ((_year < other._year || (_year == other._year && _month < other._month) || 
            (_year == other._year && _month == other._month && _day < other._day)))
            return true;
        return false;
    }

    /** 
     *  Check if this date is after other date
     *  @param other date to compare this date to
     *  @return true if this date is after other date
     */
    public boolean after (Date other){
        return other.before(this);
    }

    /**
     * calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference (Date other){
        return Math.abs(calculateDate(this._day,this._month,this._year)-calculateDate(other._day,other._month,other._year));
    }

    /**
     * Returns a String that represents this date
     *
     * @return String that represents this date
     * in the following format:
     * day (2 digits) / month(2 digits) / year (4 digits) for example: 05/04/2019
     */
    public String toString() {
        String s = "";
        if(_day < 10)
            s = s + "0" + _day + "/";
        else 
            s = s + _day + "/";
        if(_month < 10)
            s = s + "0" + _month + "/";
        else 
            s = s + _month + "/";
        return s + _year;
    }    

    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow(){
        Date newDate=new Date(_day,_month,_year);
        if (isDateValid(newDate._day+1,newDate._month,newDate._year)){
            newDate._day+=1;
        }
        else if(isDateValid(1,newDate._month+1,newDate._year)){
            newDate._day=1;
            newDate._month+=1;
        }
        else{
            newDate._day=1;
            newDate._month=1;
            newDate._year+=1; 
        }
        return newDate;         
    }
}//end of class
