/**
 * This class is used to represent the computer user information.
 * 
 * @author Satrya Fajri Pratama
 * @version 1.0
 */
public class User
{
	private String icNo;
	private long start;

	/**
	 * This constructor is used to instantiate a new <code>User</code> object.
	 * This constructor will also initialize the value of <code>start</code>
	 * instance variable to current UNIX timestamp.
	 * 
	 * @param icNo
	 *            The identification card number.
	 */
	public User(String icNo)
	{
		this.icNo = icNo;
		this.start = System.currentTimeMillis();
	}

	/**
	 * This method is used to retrieve the identification card number.
	 * 
	 * @return The identification card number.
	 */
	public String getIcNo()
	{
		return icNo;
	}

	/**
	 * This method is used to retrieve the start time in UNIX timestamp format
	 * (number of milliseconds after January 1, 1970).
	 * 
	 * @return The start time in UNIX timestamp format.
	 */
	public long getStart()
	{
		return start;
	}
}