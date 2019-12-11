/**
 * This class is used to represent the cyber cafe information.
 * 
 * @author Satrya Fajri Pratama
 * @version 1.0
 */
public class CyberCafe
{
	private static final int LENGTH = 10;
	private User[] computerUsers = new User[LENGTH];

	/**
	 * This method assigns the {@link User} object passed in the parameter to
	 * the <code>computerUsers</code> instance variable using the
	 * <code>computerNo</code> value passed in the parameter as the array index.
	 * 
	 * @param computerNo
	 *            The computer number.
	 * @param user
	 *            The {@link User} object to be stored.
	 */
	public void startUsage(int computerNo, User user)
	{
		computerUsers[computerNo] = user;
	}

	/**
	 * This overloaded method which receives the <code>computerNo</code> value
	 * passed in the parameter will remove the {@link User} object on that
	 * computer number if the {@link User} object is exist. Thus, this method
	 * will calculate the usage fee, and use this fee as the return value.
	 * Otherwise, this method will return 0 if there is no {@link User} object
	 * stored in the specified computer number.
	 * 
	 * @param computerNo
	 *            The computer number.
	 * @return The usage fee or 0 if there is no {@link User} object stored in
	 *         the specified computer number.
	 */
	public double endUsage(int computerNo)
	{
		double price = 0;
		User user = computerUsers[computerNo];

		if (user != null)
		{
			computerUsers[computerNo] = null;
			price = getPrice(user.getStart());
		}

		return price;
	}

	/**
	 * This overloaded method which receives the IC number will iterate through
	 * all elements of <code>computerUsers</code> array to search for the
	 * {@link User} object that matches the specified IC number using the
	 * <code>icNo</code> value passed in the parameter (refer to
	 * {@link String#equals(String)} method in Java API). If it is found, the
	 * {@link User} object will be removed from the <code>computerUsers</code>
	 * array, then this method will calculate the usage fee, and use this fee as
	 * the return value. Otherwise, this method will return 0 if there is no
	 * {@link User} object with the specified IC number.
	 * 
	 * @param icNo
	 *            The identification card number.
	 * @return The usage fee or 0 if there is no {@link User} object with the
	 *         specified plate number.
	 */
	public double endUsage(String icNo)
	{
		double price = 0;

		for (int i = 0; i < LENGTH; i++)
		{
			User user = computerUsers[i];

			if (user != null && user.getIcNo().equalsIgnoreCase(icNo))
			{
				computerUsers[i] = null;
				price = getPrice(user.getStart());

				break;
			}
		}

		return price;
	}

	/**
	 * This method will print the list of users and their computer number.
	 * However, this method is deprecated and should not be used.
	 */
	@Deprecated
	public void displayUsers()
	{
		System.out.println("+--------------+-----------------+");
		System.out.println("| Computer No. |      IC No.     |");
		System.out.println("+--------------+-----------------+");

		for (int i = 0; i < LENGTH; i++)
		{
			User user = computerUsers[i];

			if (user != null)
				System.out.println("| " + (i + 1) + "\t       | "
						+ user.getIcNo() + "\t |");
		}

		System.out.println("+--------------+-----------------+");
	}

	/**
	 * This method returns the array of IC numbers from the elements of
	 * <code>computerUsers</code> array.
	 * 
	 * @return The array of IC numbers.
	 */
	public String[] getIcNos()
	{
		int count = 0;

		for (User user : computerUsers)
			if (user != null)
				count++;

		int index = 0;
		String[] icNos = new String[count];

		for (User user : computerUsers)
			if (user != null)
				icNos[index++] = user.getIcNo();

		return icNos;
	}

	/**
	 * This method will return the first available computer number, or will
	 * return -1 if there are no available computers.
	 * 
	 * @return The first available computer number, or -1 if there are no
	 *         available computers.
	 */
	public int getAvailableComputerNo()
	{
		int index = -1;

		for (int i = 0; i < LENGTH; i++)
		{
			if (computerUsers[i] == null)
			{
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * This method will return <code>true</code> if there are no {@link User}
	 * objects stored in <code>computerUsers</code> array, or <code>false</code>
	 * if there is at least one {@link User} object stored in the array.
	 * 
	 * @return The empty status.
	 */
	public boolean isEmpty()
	{
		boolean empty = true;

		for (User user : computerUsers)
		{
			if (user != null)
			{
				empty = false;
				break;
			}
		}

		return empty;
	}

	/**
	 * This method receives the value of <code>start</code> in the parameter,
	 * calculates the duration, which is the rounded-up differences in hour
	 * between current time and the start time (refer to {@link Math#ceil()}
	 * method from Java API), and will return the usage fee based on the
	 * duration.
	 * 
	 * @param start
	 *            The start time in UNIX timestamp format.
	 * @return The usage fee.
	 */
	private double getPrice(long start)
	{
		double duration = Math
				.ceil((System.currentTimeMillis() - start) / 3_600_000d),
				price = duration;

		if (duration > 5)
			price = 5;

		return price;
	}
}