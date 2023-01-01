/**
 * This class represents a Car object
 * 
 * @author course staff
 * @version (2023a)
 */

public class Car
{
    private int _id;       
    private char _type;     
    private String _brand;  
    private boolean _isManual;

    final int ID_MAX_VALUE=9999999;
    final int ID_MIN_VALUE=1000000;
    final char DEFAULT_TYPE= 'A';

    /**
     * Creates a new Car object
     * <BR>id should be a 7 digits number, otherwise set it to 9999999
     * <BR>type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id the id of the car (7 digits number)
     * @param type the type of the car ('A','B','C' or 'D')
     * @param brand the car's brand
     * @param isManual flag indicating if the car is manual
     */
    public Car (int id, char type, String brand, boolean isManual)
    {
        if (id>= ID_MIN_VALUE && id<=ID_MAX_VALUE){
            _id = id;
        }
        else
            _id=ID_MAX_VALUE;
        if (type != 'B' && type != 'C' && type != 'D'){
            _type=DEFAULT_TYPE;
        }
        else
            _type = type;
        _brand = brand;
        _isManual = isManual;
    }

    /**
     * Copy constructor
     * Initialize an instance of car identical to the given car
     * @param other the car to copy
     */
    public Car(Car other)
    {
        this(other._id, other._type, other._brand, other._isManual);
    }

    /** 
     * Gets the id 
     * @return the id
     */
    public int getId(){
        return _id;
    }

    /** 
     * Gets the type 
     * @return the type 
     */
    public char getType(){
        return _type;
    }

    /** 
     * Gets the brand
     * @return the brand
     */
    public String getBrand(){
        return _brand;
    }

    /** 
     * Gets the isManual flag 
     * @return the isManual flag 
     */
    public boolean isManual(){
        return _isManual;
    }

    /** 
     * Sets the id (only if the given id is valid)
     * @param id the id value to be set
     */
    public void setId(int id){
        if (id>= ID_MIN_VALUE && id<=ID_MAX_VALUE){
            _id = id;
        }
    }

    /** 
     * Sets the type (only if the given type is valid)
     * @param type the type value to be set
     */
    public void setType(char type){
        if (type== 'A' || type == 'B' || type == 'C' || type == 'D'){
            _type = type;
        }
    }

    /** 
     * Sets the brand of the car
     * @param brand the brand value to be set
     */
    public void setBrand(String brand){
        _brand = brand;
    }

    /** 
     * Sets the isManual flag of the car 
     * @param isManual the isManual flag to be set
     */
    public void setIsManual(boolean isManual){
        _isManual = isManual;
    }

    /**
     * Returns a String object that represents this car
     *
     * @return String that represents this car in the following format:
     * <BR>id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString() {
        return ("id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + (_isManual?"manual":"auto"));
    }

    /** 
     *  Check if 2 cars are the same
     *  <BR>Cars are considered the same if they have the same type, brand and gear
     *  @param other the other car to compare this car to
     *  @return true if the cars are the same
     */
    public boolean equals(Car other){
        return(_type == other._type && _brand.equals(other._brand) && _isManual == other._isManual);
    }

    /** 
     *  Check if this car is better than the other car
     *  <BR>A car is considered better than another car if it's type is higher.
     *  <BR>If both cars have the same type, an automated car is better than a manual car.
     *  @param other car to compare this car to
     *  @return true if this car is better than the other car
     */
    public boolean better(Car other){
        return (_type > other._type || (_type == other._type && !_isManual && other._isManual));
    }

    /**
     * Check if this car is worse than the other car
     * @param other the other car to compare this car to
     * @return true if this car is worse than the other car.
     */
    public boolean worse (Car other){
        return other.better(this);
    }
}//end of class
