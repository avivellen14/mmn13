
/**
 * Write a description of class Rent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rent
{
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;

    final int DAILY_PRICE_A_TYPE=100;
    final int DAILY_PRICE_B_TYPE=150;
    final int DAILY_PRICE_C_TYPE=180;
    final int DAILY_PRICE_D_TYPE=240;
    final int PRICE_PRECENTAGE_DISCOUNT=10;

    final int DAYS_IN_WEEK=7;

    /**
     * Creates a new Rent object
     * <BR> The return date must be at least one day after the pick up date, otherwise set it to one day after the pick up date.
     * @param name the client name
     * @param car the rented car
     * @param pick the pickup date
     * @param ret the return date
     */
    public Rent (String name, Car car, Date pick, Date ret){
        _name=name;
        _car=new Car(car);
        _pickDate=new Date(pick);
        if (pick.before(ret)==true)
            _returnDate=new Date(ret);
        else
            _returnDate=new Date(pick.tomorrow());
    }

    /**
     * Copy constructor
     * @param other the rent to be copied
     */
    public Rent (Rent other){
        _name=other._name;
        _car=new Car(other._car);
        _pickDate=new Date(other._pickDate);
        _returnDate=new Date(other._returnDate);
    }

    /**
     * Gets the name
     * @return the name
     */
    public String getName(){
        return _name;
    }

    /**
     * Gets the car
     * @return the car
     */
    public Car getCar(){
        return new Car(_car);
    }

    /**
     * Gets the pick up date
     * @return the pick up date
     */
    public Date getPickDate(){
        return new Date(_pickDate);
    }

    /**
     * Gets the return date
     * @return the return date
     */
    public Date getReturnDate(){
        return new Date(_returnDate);
    }

    /**
     * Sets the client name
     * @param name - the client name (You can assume that the name is a valid name)
     */
    public void setName​(String name){
        _name=name;
    }

    /**
     * Sets the rented car
     * @param car - the rented car (You can assume that car is not null)
     */
    public void setCar​(Car car){
        _car=new Car(car);
    }

    /**
     * Sets the pick up date
     * <BR>The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     * @param pickDate - the pick up date (You can assume that pick up date is not null)
     */
    public void setPickDate​(Date pickDate){
        if (pickDate.before(_returnDate))
            _pickDate=new Date(pickDate);
    }

    /**
     * Sets the return date
     * <BR>The return date must be at least one day after the pick up date, otherwise - don't change the return date
     * @param returnDate - the return date (You can assume that return date is not null)
     */
    public void setReturnDate​(Date returnDate){
        if (returnDate.after(_pickDate))
            _returnDate=new Date(returnDate);
    }

    /**
     * Check if 2 rents are the same
     * @param other - the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other){
        if (this._name==other._name && this._car.equals(other._car) && this._pickDate.equals(other._pickDate) && this._returnDate.equals(other._returnDate))
            return true;
        return false;
    }

    /**
     * Returns the number of rent days
     * @return the number of rent days
     */
    public int howManyDays(){
        return _returnDate.difference(_pickDate);
    }

    /**
     * Returns the rent total price
     * @return the rent total price
     */
    public int getPrice(){
        int numOfDays = this.howManyDays();     //sum of days
        int restOfDays = numOfDays % DAYS_IN_WEEK;      //days that do not get discount
        int discountDays = numOfDays - restOfDays;      //days that get discount for
        int dailyPrice=0;

        if (_car.getType()=='A')
            dailyPrice= DAILY_PRICE_A_TYPE;

        else if(_car.getType()=='B')
            dailyPrice= DAILY_PRICE_B_TYPE;

        else if(_car.getType()=='C')
            dailyPrice= DAILY_PRICE_C_TYPE;

        else if(_car.getType()=='D')
            dailyPrice= DAILY_PRICE_D_TYPE;

        return numOfDays*dailyPrice - (discountDays*dailyPrice*PRICE_PRECENTAGE_DISCOUNT/100);
    }

    /**
     * Try to upgrade the car to a better car
     * <BR>If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade (Car newCar){
        int price=0;

        if(newCar.better(_car)){
            int priceCar=this.getPrice(); //price of rent with _car

            this.setCar(newCar);    //setting this car to newCar
            int priceNewCar=this.getPrice();    //price of rent with newCar

            price=priceNewCar-priceCar;
        }

        return price;
    }

    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format:
     * <BR>Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString(){
        return ("Name:" + _name + " From:" + _pickDate.toString() + " To:" + _returnDate.toString() +
            " Type:" + _car.getType() + " Days:" + this.howManyDays() + " Price:" + this.getPrice());
    }
}//end of class
