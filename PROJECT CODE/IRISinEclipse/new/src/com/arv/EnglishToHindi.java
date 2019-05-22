package com.arv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class EnglishToHindi {

	public static Boolean isConsonent(char a, Boolean hflag) {
		String str = "aieouh";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return false;
			}
		}
		return true;
	}

	public static Boolean isTrueVowel(char a) {
		String str = "aieou";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return true;
			}
		}
		return false;
	}

	public static Boolean isDigit(char a) {
		String str = "0123456789";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return true;
			}
		}
		return false;
	}

	public static Boolean isPunct(char a) {
		String str = ",.><?/+=-_}{[]*&^%$#@!~`\"\\|:;";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return true;
			}
		}
		return false;
	}

	public static Boolean isVowel(char a) {
		String str = "aieouh";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return true;
			}
		}
		return false;
	}

	public static Boolean isSpecial(char a) {
		String str = "hy";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == a) {
				return true;
			}
		}
		return false;
	}

	public static String GetMatra(String str) {
		int i = 0;
		if (str.length() < 1) {
			return "्";
		}
		while (str.charAt(i) == 'h') {
			i++;
			if (i >= str.length()) {
				break;
			}
		}
		if (i < str.length()) {
			str = str.substring(i);
		}
		if (str.equals("aa")) {
			return "ा";
		}
		if (str.equals("ai")) {
			return "ै";
		} else if (str.equals("e")) {
			return "े";
		} else if (str.equals("ee")) {
			return "ी";
		} else if (str.equals("i")) {
			return "ि";
		} else if (str.equals("u")) {
			return "ु";

		} else if (str.equals("oo")) {
			return "ू";
		} else if (str.equals("o")) {
			return "ो";
		} else if (str.equals("ou")) {
			return "ौ";
		} else if (str.equals("h")) {
			return "";
		} else if (str.equals("hh")) {
			return "";
		}
		return "";

	}

	public static String GethShift(String str, TotalCoreSoundCharacter tcsc) {
		// खगघङचछजटठडढणतथदधनपफमयरऱलवशषसह
		tcsc.soundChar = 2;
		if (str.indexOf("kh") == 0) {
			return "ख";
		} else if (str.indexOf("gh") == 0) {
			return "घ";
		} else if (str.indexOf("bh") == 0) {
			return "भ";
		} else if (str.indexOf("chh") == 0) {
			tcsc.soundChar = 3;
			return "छ";
		} else if (str.indexOf("ch") == 0) {
			return "च";
		} else if (str.indexOf("jh") == 0) {
			return "झ";
		} else if (str.indexOf("thh") == 0) {
			tcsc.soundChar = 3;
			return "ट";
		} else if (str.indexOf("th") == 0) {
			return "थ";
		} else if (str.indexOf("gh") == 0) {
			return "घ";
		} else if (str.indexOf("dhh") == 0) {
			tcsc.soundChar = 3;
			return "ड";
		} else if (str.indexOf("dh") == 0) {
			return "ध";
		} else if (str.indexOf("shh") == 0) {
			tcsc.soundChar = 3;
			return "ष";
		} else if (str.indexOf("sh") == 0) {
			return "श";
		} else if (str.indexOf("rh") == 0) {
			return "ण";
		} else if (str.indexOf("h") >= 1) {
			/*
			 * VERY IMORTANT STEP
			 */
			String sound = "";
			tcsc.soundChar = 0;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (!isTrueVowel(c)) {
					sound = sound + ResolveCharacterSound(c);
					tcsc.soundChar++;
				} else {
					break;
				}

			}
			return sound;

		}
		tcsc.soundChar = 1;
		return null;
	}

	public static String GetCoreSound(String str, TotalCoreSoundCharacter tcsc) {
		String soundmap = "अबसदइफगहईजकलमनओपकरसतउववज़यज";
		String h_shift = GethShift(str, tcsc);
		if (h_shift == null) {
			int position = ((str.charAt(0)) - 'a');
			if (position < soundmap.length() && position >= 0) {
				return "" + soundmap.charAt(position);
			}
			tcsc.soundChar = 1;
			return str;

		} else {
			return h_shift;
		}
	}

	public static String GetSpecialSound(String str) {
		// अआइईउऊऑऒओऔ
		if (str == "aa") {
			return "आ";
		} else if (str == "au") {
			return "औ";
		} else if (str == "e") {
			return "इ";
		} else if (str == "ee") {
			return "ई";
		} else if (str == "i") {
			return "इ";
		} else if (str == "o") {
			return "ओ";
		} else if (str == "x") {
			return "एक्स";
		}
		return null;

	}

	public static String ResolveCharacterSound(char c) {
		String str = "" + c;
		TotalCoreSoundCharacter tcsc = new TotalCoreSoundCharacter(0);
		if (isPunct(c)) {
			return str;
		} else if (isDigit(c)) {
			return "" + (char) ('०' + (c - '0'));
		} else if (isConsonent(str.charAt(0), false)) {
			return "" + GetCoreSound(str, tcsc) + "्";
		} else {
			return "" + GetCoreSound(str, tcsc);
		}
	}

	public static String GetSound(String str) {
		TotalCoreSoundCharacter tcsc = new TotalCoreSoundCharacter(0);
		str = str.toLowerCase().trim();
		if (str == null || str == "") {
			return "";
		}
		String SpecialSound = GetSpecialSound(str);
		if (SpecialSound != null) {
			return SpecialSound;
		}
		if (str.length() == 1) {

			return ResolveCharacterSound(str.charAt(0));

		} else {

			String core_sound = GetCoreSound(str, tcsc);
			String matra = "";
			try {
				matra = GetMatra(str.substring(tcsc.soundChar));
			} catch (Exception exp) {
				matra = "";
			}
			return "" + core_sound + matra;
		}
		// return str;

	}

	public static String SuperTrim(String str) {
		while (true) {
			String trimmed = str.replace("  ", " ");
			if (trimmed.length() == str.length()) {
				break;
			}
			str = trimmed;
		}

		return str;
	}

	public static String DoTransLitrate(String str) {
		int i = 0;
		String ret = "";
		int pi = 0;
		Boolean vowelFlag = false;
		str = str.toLowerCase();
		while (i < str.length()) {
			try {

				if ((str.charAt(i) == 'h' && vowelFlag)
						|| (isConsonent(str.charAt(i), vowelFlag) && i > 0)
						|| (str.charAt(i) == ' ') || isPunct(str.charAt(i))
						|| isDigit(str.charAt(i)) || ((i - pi) > 5)) {
					if (pi < i) {
						ret += GetSound(str.substring(pi, pi+(i - pi)));
					}
					if (str.charAt(i) == ' ') {
						ret += ' ';
					}
					if (((str.charAt(i) == ' ') || isPunct(str.charAt(i)))) {
						ret += str.charAt(i);
						pi = i + 1;
					} else if (isDigit(str.charAt(i))) {
						String digi = "" + (char) ('०' + (str.charAt(i) - '0'));
						ret += digi;
						pi = i + 1;
					} else {
						pi = i;
					}
					vowelFlag = false;

				} else if (isVowel(str.charAt(i)) && str.charAt(i) != 'h') {
					vowelFlag = true;
				}
				++i;
			} catch (Exception exp) {
			}
		}
		if (pi < i) {
			try {
				ret += GetSound(str.substring(pi, pi+ (i - pi)));
			} catch (Exception exp) {
			}
		}
		return SuperTrim(ret);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File(
										"C:/Users/arkr94/Desktop/FYP1/input_english.txt")),
						"UTF-8"));
		String hindi = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			hindi = sb.toString();
			hindi = DoTransLitrate(hindi);

		} finally {
			br.close();
		}
		Writer out = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(
								"C:/Users/arkr94/Desktop/FYP1/output_hindi.txt"),
						"UTF-8"));
		try {
			out.write(hindi);
		} finally {
			out.close();
		}
	}
}
