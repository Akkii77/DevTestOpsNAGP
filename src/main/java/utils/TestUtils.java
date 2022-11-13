package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.util.concurrent.AtomicLongMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Utility class for all convenience methods and global methods that might be
 * needed from test classes.
 */
public class TestUtils {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(TestUtils.class.getName());

	/**
	 * Instantiates a new test utils.
	 */
	private TestUtils() {
		Log.info("Initialized BuilderEndpoints");
	}

	/** The random. */
	private static Random random = new Random();

	/** The date time format. */
	private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/** The date format. */
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/** The Constant counts. */
	private static final AtomicLongMap<String> counts = AtomicLongMap.create();

	/** The props. */
	private static Properties props = new Properties();
	static {

		try (final InputStreamReader reader = new InputStreamReader(
				TestUtils.class.getResourceAsStream("/stage.properties"), "UTF-8")) {
			props.load(reader);
		} catch (IOException e) {
			Log.error("unable to read properties file", e);
		}
	}

	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * Sets the property.
	 *
	 * @param key   the key
	 * @param value the value
	 */
	public static void setProperty(String key, String value) {
		props.setProperty(key, value);
	}

	/**
	 * Gets the property.
	 *
	 * @param key          the key
	 * @param defaultValue the default value
	 * @return the property
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = getProperty(key);
		return (value == null) ? defaultValue : value;
	}

	/**
	 * Gets the base url.
	 *
	 * @return base url
	 */
	public static String getbaseUrl() {
		return props.getProperty("engineer.ai.builder.baseUrl");
	}

	/**
	 * Unique text generator.
	 *
	 * @param text the text
	 * @return the string
	 */
	public static String uniqueTextGenerator(String text) {

		Date current = new Date();
		long unique = current.getTime();
		text = text + Long.toString(unique);
		return text;
	}

	
	/**
	 * Gets the value as an integer for the provided key from the property file(s).
	 *
	 * @param key the key
	 * @return the property as int
	 */
	public static int getPropertyAsInt(String key) {
		return Integer.parseInt(getProperty(key));
	}

	/**
	 * Gets the value as a boolean for the provided key from the property file(s).
	 *
	 * @param key the key
	 * @return the property as boolean
	 */
	public static boolean getPropertyAsBoolean(String key) {
		return Boolean.valueOf(getProperty(key));
	}

	/**
	 * Sleep in millis.
	 *
	 * @param timeInMillis the time in millis
	 * @return true, if successful
	 */
	public static boolean sleepInMillis(long timeInMillis) {
		if (timeInMillis > 0) {
			try {
				Thread.sleep(timeInMillis);
			} catch (Exception e) {
				Log.info(e.getMessage());
			}
		}
		return true;
	}

	/**
	 * Sleep.
	 *
	 * @param timeInSeconds the time in seconds
	 * @return true, if successful
	 */
	public static boolean sleep(double timeInSeconds) {
		return sleepInMillis((long) (timeInSeconds * 1000));
	}

	/**
	 * Gets the random boolean.
	 *
	 * @return the random boolean
	 */
	public static boolean getRandomBoolean() {
		return random.nextBoolean();
	}

	/**
	 * Gets the unique email.
	 *
	 * @return the unique email
	 */
	public static String getUniqueEmail() {
		return "email" + System.currentTimeMillis() + "@engineer.ai";
	}
	
	/**
	 * Gets the unique email.
	 *
	 * @return the unique email
	 */
	public static String getUniqueTempEmail(String emailprefix) {
		return emailprefix + System.currentTimeMillis() + "@gmail.com";
	}

	/**
	 * Gets the random string.
	 *
	 * @param stringPrefix the string prefix
	 * @return the random string
	 */
	public static String getUniqueString(String stringPrefix) {
		return stringPrefix + System.currentTimeMillis();
	}

	/**
	 * Gets the random element from list.
	 *
	 * @param      <T> the generic type
	 * @param list the list
	 * @return the random element from list
	 */
	public static <T extends Object> T getRandomElementFromList(List<T> list) {
		return list.get(random.nextInt(list.size()));
	}

	/**
	 * Gets the random json element from array.
	 *
	 * @param list the list
	 * @return the random json element from array
	 */
	public static JsonElement getRandomJsonElementFromArray(JsonArray list) {
		if (list.size() > 0) {
			return list.get(random.nextInt(list.size()));
		}

		return null;
	}

	/**
	 * Gets the random string from json array.
	 *
	 * @param list the list
	 * @return the random string from json array
	 */
	public static String getRandomStringFromJsonArray(JsonArray list) {
		if (list.size() > 0) {
			return list.get(random.nextInt(list.size())).getAsString();
		}

		return null;
	}

	/**
	 * Gets the random enum.
	 *
	 * @param       <T> the generic type
	 * @param clazz the clazz
	 * @return the random enum
	 */
	public static <T extends Enum<?>> T getRandomEnum(Class<T> clazz) {
		T[] enumArray = clazz.getEnumConstants();
		int randomIndex = random.nextInt(enumArray.length);
		return enumArray[randomIndex];
	}

	/**
	 * Gets the difference.
	 *
	 * @param           <T> the generic type
	 * @param firstSet  the first set
	 * @param secondSet the second set
	 * @return the difference
	 */
	public static <T> Set<T> getDifference(Set<T> firstSet, Set<T> secondSet) {
		Set<T> temp = new HashSet<>(firstSet);
		for (T value : secondSet) {
			if (!temp.add(value)) {
				temp.remove(value);
			}
		}
		return temp;
	}

	/**
	 * Gets the date time as string.
	 *
	 * @return the date time as string
	 */
	public static String getDateTimeAsString() {
		return LocalDateTime.now().format(dateTimeFormat);
	}

	/**
	 * Gets the date as string.
	 *
	 * @return the date as string
	 */
	public static String getDateAsString() {
		return LocalDateTime.now().format(dateFormat);
	}

	

	/**
	 * Gets the first value or.
	 *
	 * @param              <T> the generic type
	 * @param array        the array
	 * @param defaultValue the default value
	 * @return the first value or
	 */
	public static <T> T getFirstValueOr(T[] array, T defaultValue) {
		if ((array != null) && (array.length > 0) && (array[0] != null)) {
			return array[0];
		}
		return defaultValue;
	}

	/**
	 * Gets the random int.
	 *
	 * @param limit the limit
	 * @return the random int
	 */
	public static int getRandomInt(Integer... limit) {
		Integer bound;
		if ((bound = getFirstValueOr(limit, null)) != null) {
			return random.nextInt(bound);
		}

		return random.nextInt();
	}

	/**
	 * Gets the count for.
	 *
	 * @param key the key
	 * @return the count for
	 */
	public static Integer getCountFor(String key) {
		return (int) counts.get(key);
	}

	/**
	 * Gets the and increment count for.
	 *
	 * @param key the key
	 * @return the and increment count for
	 */
	public static int getAndIncrementCountFor(String key) {
		return (int) counts.getAndIncrement(key);
	}

	/**
	 * Gets the and decrement count for.
	 *
	 * @param key the key
	 * @return the and decrement count for
	 */
	public static int getAndDecrementCountFor(String key) {
		return (int) counts.getAndDecrement(key);
	}

	/**
	 * Increment count for.
	 *
	 * @param key the key
	 * @return the int
	 */
	public static int incrementCountFor(String key) {
		return (int) counts.incrementAndGet(key);
	}

	/**
	 * Decrement count for.
	 *
	 * @param key the key
	 * @return the int
	 */
	public static int decrementCountFor(String key) {
		return (int) counts.decrementAndGet(key);
	}

	/**
	 * Delete counts for.
	 *
	 * @param key the key
	 */
	public static void deleteCountsFor(String key) {
		counts.remove(key);
	}

	/**
	 * Gets the counts as map.
	 *
	 * @return the counts as map
	 */
	public static Map<String, Long> getCountsAsMap() {
		return counts.asMap();
	}

	/**
	 * Gets the current date time.
	 *
	 * @return the current date time
	 */
	public static String getCurrentDateTime() {
		DateFormat sdf = new SimpleDateFormat("dd_MMMM_HH_mm_ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
		
	
	// Get absolute path
				public static String getPath() {
					String path = "";
					File file = new File("");
					String absolutePathOfFirstFile = file.getAbsolutePath();
					path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
					return path;
				}

				// Creating file name
				public static String getFileName(String file) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
					Calendar cal = Calendar.getInstance();
					String fileName = file + dateFormat.format(cal.getTime());
					return fileName;
				}
}
