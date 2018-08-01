package com.ddmc.commonutils.common.utils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class DevideStr {
	private String Str = "";

	private String[] retStr1;

	private int i = 0;

	public DevideStr(String strpara) {
		Str = strpara;
	}

	public String ConvertStr(String s, String sep) {
		int p = 0;
		if (s == null || s.length() == 0)
			return " ";
		if (s.substring(0, 1).equals(sep))
			s = " " + s;
		if (s.substring(s.length() - 1).equals(sep))
			s = s + " ";

		String tag = sep + sep;
		while (s.indexOf(tag) != -1) {
			p = s.indexOf(tag);
			s = s.substring(0, p + 1) + " " + s.substring(p + 1);
		}
		return s;
	}

	public String[] DevideOneStr(String FirstStr) {
		i = 0;
		if (Str == null)
			return null;
		if (Str.equals(""))
			return null;

		String tmp = Str;
		tmp = tmp.replace('\"', ' ');
		tmp = tmp.replace('\'', ' ');
		// tmp = tmp.trim();
		if (tmp.length() == 0)
			return null;

		Str = ConvertStr(Str, FirstStr);

		StringTokenizer stFirst = new StringTokenizer(Str, FirstStr);
		retStr1 = new String[stFirst.countTokens()];
		while (stFirst.hasMoreTokens()) {
			try {
				retStr1[i] = stFirst.nextToken().replace('\"', ' ');
				retStr1[i] = retStr1[i].trim();
				retStr1[i] = retStr1[i].replace('\'', ' ');
				retStr1[i] = retStr1[i].trim();

			} catch (NoSuchElementException e) {
				return null;
			}
			i++;

		}

		return retStr1;
	}

	public String[][] DevideTwoStr(String FirstStr, String SecondStr) {
		DevideOneStr(FirstStr);
		if (retStr1 == null)
			return null;

		retStr1[0] = ConvertStr(retStr1[0], SecondStr);

		StringTokenizer stSecond = new StringTokenizer(retStr1[0], SecondStr);
		int stNumSecond = stSecond.countTokens();
		if (stNumSecond == 0) {
			stNumSecond = stNumSecond + 1;
		}

		String[][] retStr = new String[i][stNumSecond];
		int k = 0;
		for (int j = 0; j < i; j++) {
			retStr1[j] = ConvertStr(retStr1[j], SecondStr);
			stSecond = new StringTokenizer(retStr1[j], SecondStr);
			while (stSecond.hasMoreTokens()) {
				try {
					retStr[j][k] = stSecond.nextToken();
				} catch (NoSuchElementException e) {
					return null;
				}
				k++;
			}
			k = 0;
		}
		return retStr;
	}
}
