/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 14:43
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author chendongsheng5 2016/12/29 14:43
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 14:43
 * @modify by reason:{方法名}:{原因}
 */
public class In {

	// assume Unicode UTF-8 encoding
	private static final String CHARSET_NAME = "UTF-8";

	// assume language = English, country = US for consistency with System.out.
	private static final Locale LOCALE = Locale.US;

	// the default token separator; we maintain the invariant that this value
	// is held by the scanner's delimiter between calls
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

	// makes whitespace characters significant
	private static final Pattern EMPTY_PATTERN = Pattern.compile("");

	// used to read the entire input. source:
	// http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

	//// end: section (1 of 2) of code duplicated from In to StdIn.

	private Scanner scanner;

	/**
	 * Initializes an input stream from standard input.
	 */
	public In() {
		scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
		scanner.useLocale(LOCALE);
	}

	/**
	 * Initializes an input stream from a socket.
	 *
	 * @param socket the socket
	 * @throws IllegalArgumentException if cannot open {@code socket}
	 * @throws IllegalArgumentException if {@code socket} is {@code null}
	 */
	public In(Socket socket) {
		if (socket == null)
			throw new IllegalArgumentException("socket argument is null");
		try {
			InputStream is = socket.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		} catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + socket, ioe);
		}
	}

	/**
	 * Initializes an input stream from a URL.
	 *
	 * @param url the URL
	 * @throws IllegalArgumentException if cannot open {@code url}
	 * @throws IllegalArgumentException if {@code url} is {@code null}
	 */
	public In(URL url) {
		if (url == null)
			throw new IllegalArgumentException("url argument is null");
		try {
			URLConnection site = url.openConnection();
			InputStream is = site.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		} catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + url, ioe);
		}
	}

	/**
	 * Initializes an input stream from a file.
	 *
	 * @param file the file
	 * @throws IllegalArgumentException if cannot open {@code file}
	 * @throws IllegalArgumentException if {@code file} is {@code null}
	 */
	public In(File file) {
		if (file == null)
			throw new IllegalArgumentException("file argument is null");
		try {
			// for consistency with StdIn, wrap with BufferedInputStream instead of use
			// file as argument to Scanner
			FileInputStream fis = new FileInputStream(file);
			scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		} catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + file, ioe);
		}
	}

	/**
	 * Initializes an input stream from a filename or web page name.
	 *
	 * @param name the filename or web page name
	 * @throws IllegalArgumentException if cannot open {@code name} as
	 *                                  a file or URL
	 * @throws IllegalArgumentException if {@code name} is {@code null}
	 */
	public In(String name) {
		if (name == null)
			throw new IllegalArgumentException("argument is null");
		try {
			// first try to read file from local file system
			File file = new File(name);
			if (file.exists()) {
				// for consistency with StdIn, wrap with BufferedInputStream instead of use
				// file as argument to Scanner
				FileInputStream fis = new FileInputStream(file);
				scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
				scanner.useLocale(LOCALE);
				return;
			}

			// next try for files included in jar
			URL url = getClass().getResource(name);

			// or URL from web
			if (url == null) {
				url = new URL(name);
			}

			URLConnection site = url.openConnection();

			// in order to set User-Agent, replace above line with these two
			// HttpURLConnection site = (HttpURLConnection) url.openConnection();
			// site.addRequestProperty("User-Agent", "Mozilla/4.76");

			InputStream is = site.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
			scanner.useLocale(LOCALE);
		} catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + name, ioe);
		}
	}

	/**
	 * Initializes an input stream from a given {@link Scanner} source; use with
	 * {@code new Scanner(String)} to read from a string.
	 * <p>
	 * Note that this does not create a defensive copy, so the
	 * scanner will be mutated as you read on.
	 *
	 * @param scanner the scanner
	 * @throws IllegalArgumentException if {@code scanner} is {@code null}
	 */
	public In(Scanner scanner) {
		if (scanner == null)
			throw new IllegalArgumentException("scanner argument is null");
		this.scanner = scanner;
	}

	/**
	 * Returns true if this input stream exists.
	 *
	 * @return {@code true} if this input stream exists; {@code false} otherwise
	 */
	public boolean exists() {
		return scanner != null;
	}

	////  begin: section (2 of 2) of code duplicated from In to StdIn,
	////  with all methods changed from "public" to "public static".

	/**
	 * Returns true if input stream is empty (except possibly whitespace).
	 * Use this to know whether the next call to {@link #readString()},
	 * {@link #readDouble()}, etc will succeed.
	 *
	 * @return {@code true} if this input stream is empty (except possibly whitespace);
	 * {@code false} otherwise
	 */
	public boolean isEmpty() {
		return !scanner.hasNext();
	}

	/**
	 * Returns true if this input stream has a next line.
	 * Use this method to know whether the
	 * next call to {@link #readLine()} will succeed.
	 * This method is functionally equivalent to {@link #hasNextChar()}.
	 *
	 * @return {@code true} if this input stream has more input (including whitespace);
	 * {@code false} otherwise
	 */
	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}

	/**
	 * Returns true if this input stream has more inputy (including whitespace).
	 * Use this method to know whether the next call to {@link #readChar()} will succeed.
	 * This method is functionally equivalent to {@link #hasNextLine()}.
	 *
	 * @return {@code true} if this input stream has more input (including whitespace);
	 * {@code false} otherwise
	 */
	public boolean hasNextChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		boolean result = scanner.hasNext();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}

	/**
	 * Reads and returns the next line in this input stream.
	 *
	 * @return the next line in this input stream; {@code null} if no such line
	 */
	public String readLine() {
		String line;
		try {
			line = scanner.nextLine();
		} catch (NoSuchElementException e) {
			line = null;
		}
		return line;
	}

	/**
	 * Reads and returns the next character in this input stream.
	 *
	 * @return the next character in this input stream
	 */
	public char readChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		String ch = scanner.next();
		assert ch.length() == 1 : "Internal (Std)In.readChar() error!" + " Please contact the authors.";
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return ch.charAt(0);
	}

	/**
	 * Reads and returns the remainder of this input stream, as a string.
	 *
	 * @return the remainder of this input stream, as a string
	 */
	public String readAll() {
		if (!scanner.hasNextLine())
			return "";

		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		// not that important to reset delimeter, since now scanner is empty
		scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
		return result;
	}

	/**
	 * Reads the next token from this input stream and returns it as a {@code String}.
	 *
	 * @return the next {@code String} in this input stream
	 */
	public String readString() {
		return scanner.next();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code int},
	 * and returns the {@code int}.
	 *
	 * @return the next {@code int} in this input stream
	 */
	public int readInt() {
		return scanner.nextInt();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code double},
	 * and returns the {@code double}.
	 *
	 * @return the next {@code double} in this input stream
	 */
	public double readDouble() {
		return scanner.nextDouble();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code float},
	 * and returns the {@code float}.
	 *
	 * @return the next {@code float} in this input stream
	 */
	public float readFloat() {
		return scanner.nextFloat();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code long},
	 * and returns the {@code long}.
	 *
	 * @return the next {@code long} in this input stream
	 */
	public long readLong() {
		return scanner.nextLong();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code short},
	 * and returns the {@code short}.
	 *
	 * @return the next {@code short} in this input stream
	 */
	public short readShort() {
		return scanner.nextShort();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code byte},
	 * and returns the {@code byte}.
	 * <p>
	 * To read binary data, use {@link BinaryIn}.
	 *
	 * @return the next {@code byte} in this input stream
	 */
	public byte readByte() {
		return scanner.nextByte();
	}

	/**
	 * Reads the next token from this input stream, parses it as a {@code boolean}
	 * (interpreting either {@code "true"} or {@code "1"} as {@code true},
	 * and either {@code "false"} or {@code "0"} as {@code false}).
	 *
	 * @return the next {@code boolean} in this input stream
	 */
	public boolean readBoolean() {
		String s = readString();
		if ("true".equalsIgnoreCase(s))
			return true;
		if ("false".equalsIgnoreCase(s))
			return false;
		if ("1".equals(s))
			return true;
		if ("0".equals(s))
			return false;
		throw new InputMismatchException();
	}

	/**
	 * Reads all remaining tokens from this input stream and returns them as
	 * an array of strings.
	 *
	 * @return all remaining tokens in this input stream, as an array of strings
	 */
	public String[] readAllStrings() {
		// we could use readAll.trim().split(), but that's not consistent
		// since trim() uses characters 0x00..0x20 as whitespace
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if (tokens.length == 0 || tokens[0].length() > 0)
			return tokens;
		String[] decapitokens = new String[tokens.length - 1];
		for (int i = 0; i < tokens.length - 1; i++)
			decapitokens[i] = tokens[i + 1];
		return decapitokens;
	}

	/**
	 * Reads all remaining lines from this input stream and returns them as
	 * an array of strings.
	 *
	 * @return all remaining lines in this input stream, as an array of strings
	 */
	public String[] readAllLines() {
		ArrayList<String> lines = new ArrayList<String>();
		while (hasNextLine()) {
			lines.add(readLine());
		}
		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Reads all remaining tokens from this input stream, parses them as integers,
	 * and returns them as an array of integers.
	 *
	 * @return all remaining lines in this input stream, as an array of integers
	 */
	public int[] readAllInts() {
		String[] fields = readAllStrings();
		int[] vals = new int[fields.length];
		for (int i = 0; i < fields.length; i++)
			vals[i] = Integer.parseInt(fields[i]);
		return vals;
	}

	/**
	 * Reads all remaining tokens from this input stream, parses them as longs,
	 * and returns them as an array of longs.
	 *
	 * @return all remaining lines in this input stream, as an array of longs
	 */
	public long[] readAllLongs() {
		String[] fields = readAllStrings();
		long[] vals = new long[fields.length];
		for (int i = 0; i < fields.length; i++)
			vals[i] = Long.parseLong(fields[i]);
		return vals;
	}

	/**
	 * Reads all remaining tokens from this input stream, parses them as doubles,
	 * and returns them as an array of doubles.
	 *
	 * @return all remaining lines in this input stream, as an array of doubles
	 */
	public double[] readAllDoubles() {
		String[] fields = readAllStrings();
		double[] vals = new double[fields.length];
		for (int i = 0; i < fields.length; i++)
			vals[i] = Double.parseDouble(fields[i]);
		return vals;
	}

	///// end: section (2 of 2) of code duplicated from In to StdIn */

	/**
	 * Closes this input stream.
	 */
	public void close() {
		scanner.close();
	}

}
