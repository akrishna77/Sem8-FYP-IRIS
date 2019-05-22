package com.arv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class Transliterator {
	static String[] DevText = { "चरित्र", "अ", "आ", "इ", "ई", "ऒ", "ओ", "उ", "ऊ", "ऎ",
			"ए", "ऐ", "व", "वा", "वि", "वी", "वॆ", "वे", "वॊ", "वो", "वु",
			"वू", "रा", "रॆ", "रे", "रि", "री", "रॊ", "रो", "रु", "रू", "र्य",
			"र्या", "र्यि", "र्यी", "र्ये", "र्यु", "र्यू", "र्यॊ", "र्यो",
			"त", "ता", "तॆ", "ते", "ति", "ती", "तॊ", "तो", "तु", "तू", "त्य",
			"त्या", "त्यॆ", "त्ये", "त्यु", "त्यू", "त्यॊ", "त्यो", "त्यि",
			"त्यी", "य", "या", "यॆ", "ये", "यि", "यी", "यु", "यू", "यॊ", "यो",
			"प", "पा", "पॆ", "पे", "पि", "पी", "पु", "पू", "पॊ", "पो", "प्य",
			"प्या", "प्यॆ", "प्ये", "प्यि", "प्यी", "प्यु", "प्यू", "प्यॊ",
			"प्यो", "प्रा", "प्री", "प्रि", "प्रॆ", "प्रे", "प्रु", "प्रू",
			"प्रॊ", "प्रो", "प्रु", "प्रू", "सा", "सि", "सी", "सु", "सू", "सॊ",
			"सो", "सॆ", "से", "स्र", "स्रा", "स्रि", "स्री", "स्रु", "स्रू",
			"स्रॊ", "स्रो", "स्रॆ", "स्रे", "स्य", "स्या", "स्यि", "स्यी",
			"स्यॆ", "स्ये", "स्यु", "स्यू", "स्यॊ", "स्यो", "द", "दा", "दि",
			"दी", "दॊ", "दो", "दु", "दू", "दॆ", "दे", "ध", "धा", "धि", "धी",
			"धॊ", "धो", "धु", "धू", "धॆ", "धे", "फ", "फा", "फि", "फी", "फॊ",
			"फो", "फु", "फू", "फॆ", "फे", "फ्र", "फ्रा", "फ्रि", "फ्री",
			"फ्रॊ", "फ्रो", "फ्रु", "फ्रू", "फ्रॆ", "फ्रे", "ग", "गा", "गि",
			"गी", "गॊ", "गो", "गु", "गू", "गॆ", "गे", "ग्य", "ग्या", "ग्यि",
			"ग्यी", "ग्यो", "ग्यॊ", "ग्यु", "ग्यू", "ग्यॆ", "ग्ये", "ग्रा",
			"ग्रि", "ग्री", "ग्रॊ", "ग्रो", "ग्रु", "ग्रू", "ग्रॆ", "ग्रे",
			"घ", "घा", "घि", "घी", "घॊ", "घो", "घु", "घू", "घॆ", "घे", "ह",
			"हा", "हि", "ही", "हॊ", "हो", "हु", "हू", "हॆ", "हे", "ज", "जा",
			"जि", "जी", "जॊ", "जो", "जु", "जू", "जॆ", "जे", "ज्र", "ज्रा",
			"ज्रि", "ज्री", "ज्रॊ", "ज्रो", "ज्रु", "ज्रू", "ज्रॆ", "ज्रे",
			"ज्य", "ज्या", "ज्यि", "ज्यी", "ज्यॊ", "ज्यो", "ज्यु", "ज्यू",
			"ज्यॆ", "ज्ये", "झ", "झा", "झि", "झी", "झॊ", "झो", "झु", "झू",
			"झॆ", "झे", "क", "का", "कि", "की", "कॊ", "को", "कु", "कू", "कॆ",
			"के", "क्य", "क्या", "क्यि", "क्यी", "क्यॊ", "क्यो", "क्यु",
			"क्यू", "क्यॆ", "क्ये", "क्र", "क्रा", "क्रि", "क्री", "क्रॊ",
			"क्रो", "क्रु", "क्रू", "क्रॆ", "क्रे", "क्व", "क्वा", "क्वि",
			"क्वी", "क्वॊ", "क्वो", "क्वु", "क्वू", "क्वॆ", "कवे", "त्व",
			"त्वा", "त्वि", "त्वी", "त्वॊ", "त्वो", "त्वु", "त्वू", "त्वॆ",
			"त्वे", "र्व", "र्वा", "र्वि", "र्वी", "र्वु", "र्वू", "र्वॊ",
			"र्वो", "र्वॆ", "र्वे", "प्व", "प्वा", "प्वि", "प्वी", "प्वॊ",
			"प्वो", "प्वु", "प्वू", "प्वॆ", "प्वे", "स्व", "स्वा", "स्वि",
			"स्वी", "स्वॊ", "स्वो", "स्वु", "स्वू", "स्वॆ", "स्वे", "", "फ्व",
			"फ्वा", "फ्वि", "फ्वी", "फ्वॊ", "फ्वो", "फ्वु", "फ्वू", "फ्वॆ",
			"फ्वे", "ग्व", "ग्वा", "ग्वि", "ग्वी", "ग्वॊ", "ग्वो", "ग्वु",
			"ग्वू", "ग्वॆ", "ग्वे", "ह्व", "", "ज्व", "झा", "ज्वि", "ज्वी",
			"ज्वॊ", "ज्वो", "ज्वु", "ज्वू", "ज्वॆ", "ज्वे", "ल्व", "ल्वा",
			"ल्वि", "ल्वी", "ल्वॊ", "ल्वो", "ल्वु", "ल्वू", "ल्वॆ", "ल्वे",
			"ल", "ला", "लि", "ली", "लॊ", "लो", "लु", "लू", "लॆ", "ले", "ल्र",
			"ल्र", "ल्रा", "ल्रि", "ल्री", "ल्रॊ", "ल्रो", "ल्रु", "ल्रू",
			"ल्रॆ", "ल्रे", "ल्य", "ल्या", "ल्यि", "ल्यी", "ल्यॊ", "ल्यो",
			"ल्यु", "ल्यू", "ल्यॆ", "ल्ये", "ल्ह", "ल्हा", "ल्हि", "ल्ही",
			"ल्हॊ", "ल्हो", "ल्हु", "ल्हू", "ल्हॆ", "ल्हे", "क्श", "क्शा",
			"क्शि", "क्शी", "क्शॊ", "क्शो", "क्शु", "क्शू", "क्शॆ", "क्शे",
			"च", "चा", "चि", "ची", "चॊ", "चो", "चु", "चू", "चॆ", "चे", "छ",
			"छा", "छि", "छी", "छॊ", "छो", "छु", "छू", "छॆ", "छे", "छ्र्",
			"छ्रा", "छ्रि", "छ्री", "छ्रॊ", "छ्रो", "छ्रु", "छ्रू", "छ्रॆ",
			"छ्रे", "च्र्", "च्रा", "च्रि", "च्री", "च्रॊ", "च्रो", "च्रु",
			"च्रू", "च्रॆ", "च्रे", "च्य", "च्या", "च्यि", "च्यी", "च्यॊ",
			"च्यो", "च्यु", "च्यू", "च्यॆ", "च्ये", "छ्य", "व", "वा", "वि",
			"वी", "वॊ", "वो", "वु", "वू", "वॆ", "वे", "व्य", "व्या", "व्यि",
			"व्यी", "व्यॊ", "व्यो", "व्यु", "व्यू", "व्यॆ", "व्ये", "व्र",
			"व्रा", "व्रि", "व्री", "व्रु", "व्रू", "व्रॊ", "व्रो", "व्रॆ",
			"व्रे", "व्ह", "व्हा", "व्हि", "व्ही", "व्हॊ", "व्हो", "व्हु",
			"व्हू", "व्हॆ", "व्हे", "ब", "बा", "बि", "बी", "बॊ", "बो", "बु",
			"बू", "बॆ", "बे", "भ", "भा", "भि", "भी", "भॊ", "भो", "भु", "भू",
			"भॆ", "भे", "भ्र", "भ्रा", "भ्रि", "भ्री", "भ्रॊ", "भ्रो", "भ्रु",
			"भ्रू", "भ्रॆ", "भ्रे", "ब्र", "ब्रा", "ब्रि", "ब्री", "ब्रॊ",
			"ब्रो", "ब्रु", "ब्रू", "ब्रॆ", "ब्रे", "न", "ना", "नि", "नी",
			"नु", "नू", "नॊ", "नो", "नॆ", "ने", "न्ह", "न्हा", "न्हि", "न्ही",
			"न्हॊ", "न्हो", "न्हु", "न्हू", "न्हॆ", "न्हे", "न्य", "न्या",
			"न्यु", "न्यू", "न्यि", "न्यी", "न्यॊ", "न्यो", "न्यॆ", "न्ये",
			"न्व", "न्वा", "न्वि", "न्वी", "न्वॊ", "न्वो", "न्वु", "न्वू",
			"न्वॆ", "न्वे", "ञ", "ञा", "ञि", "ञी", "ञॊ", "ञो", "ञु", "ञू",
			"ञॆ", "ञे", "ङ", "ङा", "ङि", "ङी", "ङॊ", "ङो", "ङु", "ङू", "ङॆ",
			"ङे", "मा", "मि", "मी", "मॊ", "मो", "मु", "मू", "मॆ", "मे", "म्य",
			"म्या", "म्यि", "म्यी", "म्यॊ", "म्यो", "म्यु", "म्यू", "म्यॆ",
			"म्ये", "म्र", "म्रा", "म्रॊ", "म्रो", "म्रि", "म्री", "म्रु",
			"म्रू", "म्रॆ", "म्रे", "म्व", "म्व", "म्वा", "म्वि", "म्वी",
			"म्वॊ", "म्वो", "म्वु", "म्वू", "म्वॆ", "म्वे", "है" };

	static String[] English = { "charithr", "a", "aa", "i", "ii", "o", "o", "u", "uu", "e",
			"e", "ai", "v", "vaa", "vi", "vii", "ve", "ve", "vo", "vo", "vu",
			"vuu", "raa", "re", "re", "ri", "rii", "ro", "ro", "ru", "ruu",
			"ry", "ryaa", "ryi", "ryii", "rye", "ryu", "ryuu", "ryo", "ryo",
			"t", "taa", "te", "te", "ti", "tii", "to", "to", "tu", "tuu", "ty",
			"tyaa", "tye", "tye", "tyu", "tyuu", "tyo", "tyo", "tyi", "tyii",
			"y", "yaa", "ye", "ye", "yi", "yii", "yu", "yuu", "yo", "yo", "p",
			"paa", "pe", "pe", "pi", "pii", "pu", "puu", "po", "po", "py",
			"pyaa", "pye", "pye", "pyi", "pyii", "pyu", "pyuu", "pyo", "pyo",
			"praa", "prii", "pri", "pre", "pre", "pru", "pruu", "pro", "pro",
			"pru", "pruu", "saa", "si", "sii", "su", "suu", "so", "so", "se",
			"se", "sr", "sraa", "sri", "srii", "sru", "sruu", "sro", "sro",
			"sre", "sre", "sy", "syaa", "syi", "syii", "sye", "sye", "syu",
			"syuu", "syo", "syo", "d", "daa", "di", "dii", "do", "do", "du",
			"duu", "de", "de", "dh", "dhaa", "dhi", "dhii", "dho", "dho",
			"dhu", "dhuu", "dhe", "dhe", "ph", "phaa", "phi", "phii", "pho",
			"pho", "phu", "phuu", "phe", "phe", "phr", "phraa", "phri",
			"phrii", "phro", "phro", "phru", "phruu", "phre", "phre", "g",
			"gaa", "gi", "gii", "go", "go", "gu", "guu", "ge", "ge", "gy",
			"gyaa", "gyi", "gyii", "gyo", "gyo", "gyu", "gyuu", "gye", "gye",
			"graa", "gri", "grii", "gro", "gro", "gru", "gruu", "gre", "gre",
			"gh", "ghaa", "ghi", "ghii", "gho", "gho", "ghu", "ghuu", "ghe",
			"ghe", "h", "haa", "hi", "hii", "ho", "ho", "hu", "huu", "he",
			"he", "j", "jaa", "ji", "jii", "jo", "jo", "ju", "juu", "je", "je",
			"jr", "jraa", "jri", "jrii", "jro", "jro", "jru", "jruu", "jre",
			"jre", "jy", "jyaa", "jyi", "jyii", "jyo", "jyo", "jyu", "jyuu",
			"jye", "jye", "jh", "jhaa", "jhi", "jhii", "jho", "jho", "jhu",
			"jhuu", "jhe", "jhe", "k", "kaa", "ki", "kii", "ko", "ko", "ku",
			"kuu", "ke", "ke", "ky", "kyaa", "kyi", "kyii", "kyo", "kyo",
			"kyu", "kyuu", "kye", "kye", "kr", "kraa", "kri", "krii", "kro",
			"kro", "kru", "kruu", "kre", "kre", "kv", "kvaa", "kvi", "kvii",
			"kvo", "kvo", "kvu", "kvuu", "kve", "kve", "tv", "tvaa", "tvi",
			"tvii", "tvo", "tvo", "tvu", "tvuu", "tve", "tve", "rv", "rvaa",
			"rvi", "rvii", "rvu", "rvuu", "rvo", "rvo", "rve", "rve", "pv",
			"pvaa", "pvi", "pvii", "pvo", "pvo", "pvu", "pvuu", "pve", "pve",
			"sv", "svaa", "svi", "svii", "svo", "svo", "svu", "svuu", "sve",
			"sve", "", "phv", "phvaa", "phvi", "phvii", "phvo", "phvo", "phvu",
			"phvuu", "phve", "phve", "gv", "gvaa", "gvi", "gvii", "gvo", "gvo",
			"gvu", "gvuu", "gve", "gve", "hv", "", "jv", "jhaa", "jvi", "jvii",
			"jvo", "jvo", "jvu", "jvuu", "jve", "jve", "lv", "lvaa", "lvi",
			"lvii", "lvo", "lvo", "lvu", "lvuu", "lve", "lve", "l", "laa",
			"li", "lii", "lo", "lo", "lu", "luu", "le", "le", "lr", "lr",
			"lraa", "lri", "lrii", "lro", "lro", "lru", "lruu", "lre", "lre",
			"ly", "lyaa", "lyi", "lyii", "lyo", "lyo", "lyu", "lyuu", "lye",
			"lye", "lh", "lhaa", "lhi", "lhii", "lho", "lho", "lhu", "lhuu",
			"lhe", "lhe", "ksh", "kshaa", "kshi", "kshii", "ksho", "ksho",
			"kshu", "kshuu", "kshe", "kshe", "c", "caa", "ci", "cii", "co",
			"co", "cu", "cuu", "ce", "ce", "ch", "chaa", "chi", "chii", "cho",
			"cho", "chu", "chuu", "che", "che", "chr", "chraa", "chri",
			"chrii", "chro", "chro", "chru", "chruu", "chre", "chre", "cr",
			"craa", "cri", "crii", "cro", "cro", "cru", "cruu", "cre", "cre",
			"cy", "cyaa", "cyi", "cyii", "cyo", "cyo", "cyu", "cyuu", "cye",
			"cye", "chy", "v", "vaa", "vi", "vii", "vo", "vo", "vu", "vuu",
			"ve", "ve", "vy", "vyaa", "vyi", "vyii", "vyo", "vyo", "vyu",
			"vyuu", "vye", "vye", "vr", "vraa", "vri", "vrii", "vru", "vruu",
			"vro", "vro", "vre", "vre", "vh", "vhaa", "vhi", "vhii", "vho",
			"vho", "vhu", "vhuu", "vhe", "vhe", "b", "baa", "bi", "bii", "bo",
			"bo", "bu", "buu", "be", "be", "bh", "bhaa", "bhi", "bhii", "bho",
			"bho", "bhu", "bhuu", "bhe", "bhe", "bhr", "bhraa", "bhri",
			"bhrii", "bhro", "bhro", "bhru", "bhruu", "bhre", "bhre", "br",
			"braa", "bri", "brii", "bro", "bro", "bru", "bruu", "bre", "bre",
			"n", "naa", "ni", "nii", "nu", "nuu", "no", "no", "ne", "ne", "nh",
			"nhaa", "nhi", "nhii", "nho", "nho", "nhu", "nhuu", "nhe", "nhe",
			"ny", "nyaa", "nyu", "nyuu", "nyi", "nyii", "nyo", "nyo", "nye",
			"nye", "nv", "nvaa", "nvi", "nvii", "nvo", "nvo", "nvu", "nvuu",
			"nve", "nve", "ny", "nyaa", "nyi", "nyii", "nyo", "nyo", "nyu",
			"nyuu", "nye", "nye", "ng", "ngaa", "ngi", "ngii", "ngo", "ngo",
			"ngu", "nguu", "nge", "nge", "maa", "mi", "mii", "mo", "mo", "mu",
			"muu", "me", "me", "my", "myaa", "myi", "myii", "myo", "myo",
			"myu", "myuu", "mye", "mye", "mr", "mraa", "mro", "mro", "mri",
			"mrii", "mru", "mruu", "mre", "mre", "mv", "mv", "mvaa", "mvi",
			"mvii", "mvo", "mvo", "mvu", "mvuu", "mve", "mve", "hai" };
	
	public static String replaceTamilCharacters(String unicode_value) {

		unicode_value = unicode_value.toLowerCase();

		unicode_value = unicode_value.replaceAll("ஸ்ரீ", "sr");
		unicode_value = unicode_value.replaceAll("க்ஷௌ", "kshau");
		unicode_value = unicode_value.replaceAll("க்ஷை", "kshai");
		unicode_value = unicode_value.replaceAll("க்ஷா", "kshaa");

		unicode_value = unicode_value.replaceAll("க்ஷீ", "kshee");
		unicode_value = unicode_value.replaceAll("க்ஷி", "kshi");
		unicode_value = unicode_value.replaceAll("க்ஷூ", "kshoo");
		unicode_value = unicode_value.replaceAll("க்ஷு", "kshu");
		unicode_value = unicode_value.replaceAll("க்ஷே", "kshey");
		unicode_value = unicode_value.replaceAll("க்ஷெ", "kshe");
		unicode_value = unicode_value.replaceAll("க்ஷொ", "ksho");
		unicode_value = unicode_value.replaceAll("க்ஷோ", "kshoh");

		unicode_value = unicode_value.replaceAll("க்ஷ்", "ksh");
		unicode_value = unicode_value.replaceAll("க்ஷ", "ksha");

		unicode_value = unicode_value.replaceAll("ஞௌ", "njau");
		unicode_value = unicode_value.replaceAll("ஞை", "njai");
		unicode_value = unicode_value.replaceAll("ஞே", "njey");
		unicode_value = unicode_value.replaceAll("ஞோ", "njo");
		unicode_value = unicode_value.replaceAll("ஞா", "njaa");
		unicode_value = unicode_value.replaceAll("ஞூ", "njoo");
		unicode_value = unicode_value.replaceAll("ஞீ", "njee");

		unicode_value = unicode_value.replaceAll("ஞி", "nji");
		unicode_value = unicode_value.replaceAll("ஞெ", "nje");
		unicode_value = unicode_value.replaceAll("ஞொ", "njo");
		unicode_value = unicode_value.replaceAll("ஞு", "nju");
		unicode_value = unicode_value.replaceAll("ஞூ", "njoo");

		unicode_value = unicode_value.replaceAll("ஞ்", "nj");
		unicode_value = unicode_value.replaceAll("ஞ", "nja");

		unicode_value = unicode_value.replaceAll("ஙௌ", "ngau");
		unicode_value = unicode_value.replaceAll("ஙை", "ngai");
		unicode_value = unicode_value.replaceAll("ஙே", "ngey");
		unicode_value = unicode_value.replaceAll("ஙோ", "ngoh");
		unicode_value = unicode_value.replaceAll("ஙா", "ngaa");
		unicode_value = unicode_value.replaceAll("ஙூ", "ngoo");
		unicode_value = unicode_value.replaceAll("ஙீ", "ngee");

		unicode_value = unicode_value.replaceAll("ஙி", "ngi");
		unicode_value = unicode_value.replaceAll("ஙெ", "nge");
		unicode_value = unicode_value.replaceAll("ஙொ", "ngo");
		unicode_value = unicode_value.replaceAll("ஙு", "ngu");

		unicode_value = unicode_value.replaceAll("ங்", "ng");
		unicode_value = unicode_value.replaceAll("ங", "nga");

		unicode_value = unicode_value.replaceAll("ஷௌ", "shau");
		unicode_value = unicode_value.replaceAll("ஷை", "shai");
		unicode_value = unicode_value.replaceAll("ஷே", "shey");
		unicode_value = unicode_value.replaceAll("ஷோ", "shoh");
		unicode_value = unicode_value.replaceAll("ஷா", "shaa");
		unicode_value = unicode_value.replaceAll("ஷூ", "shoo");
		unicode_value = unicode_value.replaceAll("ஷீ", "shee");

		unicode_value = unicode_value.replaceAll("ஷி", "shi");
		unicode_value = unicode_value.replaceAll("ஷெ", "she");
		unicode_value = unicode_value.replaceAll("ஷொ", "sho");
		unicode_value = unicode_value.replaceAll("ஷு", "shu");

		unicode_value = unicode_value.replaceAll("ஷ்", "sh");
		unicode_value = unicode_value.replaceAll("ஷ", "sha");

		unicode_value = unicode_value.replaceAll("ஃபௌ", "phau");
		unicode_value = unicode_value.replaceAll("ஃபை", "phai");
		unicode_value = unicode_value.replaceAll("ஃபே", "phey");
		unicode_value = unicode_value.replaceAll("ஃபோ", "phoh");
		unicode_value = unicode_value.replaceAll("ஃபா", "phaa");
		unicode_value = unicode_value.replaceAll("ஃபூ", "phoo");
		unicode_value = unicode_value.replaceAll("ஃபீ", "phee");

		unicode_value = unicode_value.replaceAll("ஃபி", "phi");
		unicode_value = unicode_value.replaceAll("ஃபெ", "phe");
		unicode_value = unicode_value.replaceAll("ஃபொ", "pho");
		unicode_value = unicode_value.replaceAll("ஃபு", "phu");

		unicode_value = unicode_value.replaceAll("ஃப்", "ph");
		unicode_value = unicode_value.replaceAll("ஃப", "pha");

		unicode_value = unicode_value.replaceAll(" நௌ", " nau");
		unicode_value = unicode_value.replaceAll(" நை", " nai");
		unicode_value = unicode_value.replaceAll(" நே", " ney");
		unicode_value = unicode_value.replaceAll(" நோ", " noh");
		unicode_value = unicode_value.replaceAll(" நா", " naa");
		unicode_value = unicode_value.replaceAll(" நூ", " noo");
		unicode_value = unicode_value.replaceAll(" நீ", " nee");

		unicode_value = unicode_value.replaceAll(" நி", " ni");
		unicode_value = unicode_value.replaceAll(" நெ", " ne");
		unicode_value = unicode_value.replaceAll(" நொ", " no");
		unicode_value = unicode_value.replaceAll(" நு", " nu");

		unicode_value = unicode_value.replaceAll(" ந்", " n");
		unicode_value = unicode_value.replaceAll(" ந", " na");

		unicode_value = unicode_value.replaceAll("ந்தௌ", "nthau");
		unicode_value = unicode_value.replaceAll("ந்தை", "nthai");
		unicode_value = unicode_value.replaceAll("ந்தே", "nthey");
		unicode_value = unicode_value.replaceAll("ந்தோ", "nthoh");
		unicode_value = unicode_value.replaceAll("ந்தா", "nthaa");
		unicode_value = unicode_value.replaceAll("ந்தூ", "nthoo");
		unicode_value = unicode_value.replaceAll("ந்தீ", "nthee");

		unicode_value = unicode_value.replaceAll("ந்தி", "nthi");
		unicode_value = unicode_value.replaceAll("ந்தெ", "nthe");
		unicode_value = unicode_value.replaceAll("ந்தொ", "ntho");
		unicode_value = unicode_value.replaceAll("ந்து", "nthu");
		unicode_value = unicode_value.replaceAll("ந்த்", "nth");
		unicode_value = unicode_value.replaceAll("ந்த", "ntha");

		unicode_value = unicode_value.replaceAll("ழௌ", "zhau");
		unicode_value = unicode_value.replaceAll("ழை", "zhai");
		unicode_value = unicode_value.replaceAll("ழே", "zhey");
		unicode_value = unicode_value.replaceAll("ழோ", "zhoh");
		unicode_value = unicode_value.replaceAll("ழா", "zhaa");
		unicode_value = unicode_value.replaceAll("ழூ", "zhoo");
		unicode_value = unicode_value.replaceAll("ழீ", "zhee");

		unicode_value = unicode_value.replaceAll("ழி", "zhi");
		unicode_value = unicode_value.replaceAll("ழெ", "zhe");
		unicode_value = unicode_value.replaceAll("ழொ", "zho");
		unicode_value = unicode_value.replaceAll("ழு", "zhu");

		unicode_value = unicode_value.replaceAll("ழ்", "zh");
		unicode_value = unicode_value.replaceAll("ழ", "zha");

		unicode_value = unicode_value.replaceAll("ஜௌ", "jau");
		unicode_value = unicode_value.replaceAll("ஜை", "jai");
		unicode_value = unicode_value.replaceAll("ஜே", "jey");
		unicode_value = unicode_value.replaceAll("ஜோ", "joh");
		unicode_value = unicode_value.replaceAll("ஜா", "jaa");
		unicode_value = unicode_value.replaceAll("ஜூ", "joo");
		unicode_value = unicode_value.replaceAll("ஜீ", "jee");

		unicode_value = unicode_value.replaceAll("ஜி", "ji");
		unicode_value = unicode_value.replaceAll("ஜெ", "je");
		unicode_value = unicode_value.replaceAll("ஜொ", "jo");
		unicode_value = unicode_value.replaceAll("ஜு", "ju");

		unicode_value = unicode_value.replaceAll("ஜ்", "j");
		unicode_value = unicode_value.replaceAll("ஜ", "ja");

		unicode_value = unicode_value.replaceAll("தௌ", "thau");
		unicode_value = unicode_value.replaceAll("தை", "thai");
		unicode_value = unicode_value.replaceAll("தே", "they");
		unicode_value = unicode_value.replaceAll("தோ", "thoh");
		unicode_value = unicode_value.replaceAll("தா", "thaa");
		unicode_value = unicode_value.replaceAll("தூ", "thoo");
		unicode_value = unicode_value.replaceAll("தீ", "thee");

		unicode_value = unicode_value.replaceAll("தி", "thi");
		unicode_value = unicode_value.replaceAll("தெ", "the");
		unicode_value = unicode_value.replaceAll("தொ", "tho");
		unicode_value = unicode_value.replaceAll("து", "thu");

		unicode_value = unicode_value.replaceAll("த்", "th");
		unicode_value = unicode_value.replaceAll("த", "tha");

		unicode_value = unicode_value.replaceAll("ஹௌ", "hau");
		unicode_value = unicode_value.replaceAll("ஹை", "hai");
		unicode_value = unicode_value.replaceAll("ஹே", "hey");
		unicode_value = unicode_value.replaceAll("ஹோ", "hoh");
		unicode_value = unicode_value.replaceAll("ஹா", "haa");
		unicode_value = unicode_value.replaceAll("ஹூ", "hoo");
		unicode_value = unicode_value.replaceAll("ஹீ", "hee");

		unicode_value = unicode_value.replaceAll("ஹி", "hi");
		unicode_value = unicode_value.replaceAll("ஹெ", "he");
		unicode_value = unicode_value.replaceAll("ஹொ", "ho");
		unicode_value = unicode_value.replaceAll("ஹு", "hu");

		unicode_value = unicode_value.replaceAll("ஹ்", "h");
		unicode_value = unicode_value.replaceAll("ஹ", "ha");

		unicode_value = unicode_value.replaceAll("கௌ", "kau");
		unicode_value = unicode_value.replaceAll("கை", "kai");
		unicode_value = unicode_value.replaceAll("கே", "key");
		unicode_value = unicode_value.replaceAll("கோ", "koh");
		unicode_value = unicode_value.replaceAll("கா", "kaa");
		unicode_value = unicode_value.replaceAll("கூ", "koo");
		unicode_value = unicode_value.replaceAll("கீ", "kee");

		unicode_value = unicode_value.replaceAll("கி", "ki");
		unicode_value = unicode_value.replaceAll("கெ", "ke");
		unicode_value = unicode_value.replaceAll("கொ", "ko");
		unicode_value = unicode_value.replaceAll("கு", "ku");

		unicode_value = unicode_value.replaceAll("க்", "k");
		unicode_value = unicode_value.replaceAll("க", "ka");

		unicode_value = unicode_value.replaceAll("ஸௌ", "Sau");
		unicode_value = unicode_value.replaceAll("ஸை", "Sai");
		unicode_value = unicode_value.replaceAll("ஸே", "Sey");
		unicode_value = unicode_value.replaceAll("ஸோ", "Soh");
		unicode_value = unicode_value.replaceAll("ஸா", "Saa");
		unicode_value = unicode_value.replaceAll("ஸூ", "Soo");
		unicode_value = unicode_value.replaceAll("ஸீ", "See");

		unicode_value = unicode_value.replaceAll("ஸி", "Si");
		unicode_value = unicode_value.replaceAll("ஸெ", "Se");
		unicode_value = unicode_value.replaceAll("ஸொ", "So");
		unicode_value = unicode_value.replaceAll("ஸு", "Su");

		unicode_value = unicode_value.replaceAll("ஸ்", "S");
		unicode_value = unicode_value.replaceAll("ஸ", "Sa");

		unicode_value = unicode_value.replaceAll("ரௌ", "rau");
		unicode_value = unicode_value.replaceAll("ரை", "rai");
		unicode_value = unicode_value.replaceAll("ரே", "rey");
		unicode_value = unicode_value.replaceAll("ரோ", "roh");
		unicode_value = unicode_value.replaceAll("ரா", "raa");
		unicode_value = unicode_value.replaceAll("ரூ", "roo");
		unicode_value = unicode_value.replaceAll("ரீ", "ree");

		unicode_value = unicode_value.replaceAll("ரி", "ri");
		unicode_value = unicode_value.replaceAll("ரெ", "re");
		unicode_value = unicode_value.replaceAll("ரொ", "ro");
		unicode_value = unicode_value.replaceAll("ரு", "ru");

		unicode_value = unicode_value.replaceAll("ர்", "r");
		unicode_value = unicode_value.replaceAll("ர", "ra");

		unicode_value = unicode_value.replaceAll("றௌ", "Rau");
		unicode_value = unicode_value.replaceAll("றை", "Rai");
		unicode_value = unicode_value.replaceAll("றே", "Rey");
		unicode_value = unicode_value.replaceAll("றோ", "Roh");
		unicode_value = unicode_value.replaceAll("றா", "Raa");
		unicode_value = unicode_value.replaceAll("றூ", "Roo");
		unicode_value = unicode_value.replaceAll("றீ", "Ree");

		unicode_value = unicode_value.replaceAll("றி", "Ri");
		unicode_value = unicode_value.replaceAll("றெ", "Re");
		unicode_value = unicode_value.replaceAll("றொ", "Ro");
		unicode_value = unicode_value.replaceAll("று", "Ru");

		unicode_value = unicode_value.replaceAll("ற்", "R");
		unicode_value = unicode_value.replaceAll("ற", "Ra");

		unicode_value = unicode_value.replaceAll("டௌ", "tau");
		unicode_value = unicode_value.replaceAll("டை", "tai");
		unicode_value = unicode_value.replaceAll("டே", "tey");
		unicode_value = unicode_value.replaceAll("டோ", "toh");
		unicode_value = unicode_value.replaceAll("டா", "taa");
		unicode_value = unicode_value.replaceAll("டூ", "too");
		unicode_value = unicode_value.replaceAll("டீ", "tee");

		unicode_value = unicode_value.replaceAll("டி", "ti");
		unicode_value = unicode_value.replaceAll("டெ", "te");
		unicode_value = unicode_value.replaceAll("டொ", "to");
		unicode_value = unicode_value.replaceAll("டு", "tu");

		unicode_value = unicode_value.replaceAll("ட்", "t");
		unicode_value = unicode_value.replaceAll("ட", "ta");

		unicode_value = unicode_value.replaceAll("சௌ", "sau");
		unicode_value = unicode_value.replaceAll("சை", "sai");
		unicode_value = unicode_value.replaceAll("சே", "sey");
		unicode_value = unicode_value.replaceAll("சோ", "soh");
		unicode_value = unicode_value.replaceAll("சா", "saa");
		unicode_value = unicode_value.replaceAll("சூ", "soo");
		unicode_value = unicode_value.replaceAll("சீ", "see");

		unicode_value = unicode_value.replaceAll("சி", "si");
		unicode_value = unicode_value.replaceAll("செ", "se");
		unicode_value = unicode_value.replaceAll("சொ", "so");
		unicode_value = unicode_value.replaceAll("சு", "su");

		unicode_value = unicode_value.replaceAll("ச்", "s");
		unicode_value = unicode_value.replaceAll("ச", "sa");

		unicode_value = unicode_value.replaceAll("பௌ", "bau");
		unicode_value = unicode_value.replaceAll("பை", "bai");
		unicode_value = unicode_value.replaceAll("பே", "bey");
		unicode_value = unicode_value.replaceAll("போ", "boh");
		unicode_value = unicode_value.replaceAll("பா", "baa");
		unicode_value = unicode_value.replaceAll("பூ", "boo");
		unicode_value = unicode_value.replaceAll("பீ", "bee");

		unicode_value = unicode_value.replaceAll("பி", "bi");
		unicode_value = unicode_value.replaceAll("பெ", "be");
		unicode_value = unicode_value.replaceAll("பொ", "bo");
		unicode_value = unicode_value.replaceAll("பு", "bu");

		unicode_value = unicode_value.replaceAll("ப்", "b");
		unicode_value = unicode_value.replaceAll("ப", "ba");

		unicode_value = unicode_value.replaceAll("மௌ", "mau");
		unicode_value = unicode_value.replaceAll("மை", "mai");
		unicode_value = unicode_value.replaceAll("மே", "mey");
		unicode_value = unicode_value.replaceAll("மோ", "moh");
		unicode_value = unicode_value.replaceAll("மா", "maa");
		unicode_value = unicode_value.replaceAll("மூ", "moo");
		unicode_value = unicode_value.replaceAll("மீ", "mee");

		unicode_value = unicode_value.replaceAll("மி", "mi");
		unicode_value = unicode_value.replaceAll("மெ", "me");
		unicode_value = unicode_value.replaceAll("மொ", "mo");
		unicode_value = unicode_value.replaceAll("மு", "mu");

		unicode_value = unicode_value.replaceAll("ம்", "m");
		unicode_value = unicode_value.replaceAll("ம", "ma");

		unicode_value = unicode_value.replaceAll("யௌ", "yau");
		unicode_value = unicode_value.replaceAll("யை", "yai");
		unicode_value = unicode_value.replaceAll("யே", "yey");
		unicode_value = unicode_value.replaceAll("யோ", "yoh");
		unicode_value = unicode_value.replaceAll("யா", "yaa");
		unicode_value = unicode_value.replaceAll("யூ", "yoo");
		unicode_value = unicode_value.replaceAll("யீ", "yee");

		unicode_value = unicode_value.replaceAll("யி", "yi");
		unicode_value = unicode_value.replaceAll("யெ", "ye");
		unicode_value = unicode_value.replaceAll("யொ", "yo");
		unicode_value = unicode_value.replaceAll("யு", "yu");

		unicode_value = unicode_value.replaceAll("ய்", "y");
		unicode_value = unicode_value.replaceAll("ய", "ya");

		unicode_value = unicode_value.replaceAll("னௌ", "nau");
		unicode_value = unicode_value.replaceAll("னை", "nai");
		unicode_value = unicode_value.replaceAll("னே", "ney");
		unicode_value = unicode_value.replaceAll("னோ", "noh");
		unicode_value = unicode_value.replaceAll("னா", "naa");
		unicode_value = unicode_value.replaceAll("னூ", "noo");
		unicode_value = unicode_value.replaceAll("னீ", "nee");

		unicode_value = unicode_value.replaceAll("னி", "ni");
		unicode_value = unicode_value.replaceAll("னெ", "ne");
		unicode_value = unicode_value.replaceAll("னொ", "no");
		unicode_value = unicode_value.replaceAll("னு", "nu");

		unicode_value = unicode_value.replaceAll("ன்", "n");
		unicode_value = unicode_value.replaceAll("ன", "na");
		unicode_value = unicode_value.replaceAll("ந", "na");
		
		unicode_value = unicode_value.replaceAll("ணௌ", "Nau");
		unicode_value = unicode_value.replaceAll("ணை", "Nai");
		unicode_value = unicode_value.replaceAll("ணே", "Ney");
		unicode_value = unicode_value.replaceAll("ணோ", "Noh");
		unicode_value = unicode_value.replaceAll("ணா", "Naa");
		unicode_value = unicode_value.replaceAll("ணூ", "Noo");
		unicode_value = unicode_value.replaceAll("ணீ", "Nee");

		unicode_value = unicode_value.replaceAll("ணி", "Ni");
		unicode_value = unicode_value.replaceAll("ணெ", "Ne");
		unicode_value = unicode_value.replaceAll("ணொ", "No");
		unicode_value = unicode_value.replaceAll("ணு", "Nu");

		unicode_value = unicode_value.replaceAll("ண்", "N");
		unicode_value = unicode_value.replaceAll("ண", "Na");

		unicode_value = unicode_value.replaceAll("லௌ", "lau");
		unicode_value = unicode_value.replaceAll("லை", "lai");
		unicode_value = unicode_value.replaceAll("லே", "ley");
		unicode_value = unicode_value.replaceAll("லோ", "loh");
		unicode_value = unicode_value.replaceAll("லா", "laa");
		unicode_value = unicode_value.replaceAll("லூ", "loo");
		unicode_value = unicode_value.replaceAll("லீ", "lee");

		unicode_value = unicode_value.replaceAll("லி", "li");
		unicode_value = unicode_value.replaceAll("லெ", "le");
		unicode_value = unicode_value.replaceAll("லொ", "lo");
		unicode_value = unicode_value.replaceAll("லு", "lu");

		unicode_value = unicode_value.replaceAll("ல்", "l");
		unicode_value = unicode_value.replaceAll("ல", "la");

		unicode_value = unicode_value.replaceAll("ளௌ", "Lau");
		unicode_value = unicode_value.replaceAll("ளை", "Lai");
		unicode_value = unicode_value.replaceAll("ளே", "Ley");
		unicode_value = unicode_value.replaceAll("ளோ", "Loh");
		unicode_value = unicode_value.replaceAll("ளா", "Laa");
		unicode_value = unicode_value.replaceAll("ளூ", "Loo");
		unicode_value = unicode_value.replaceAll("ளீ", "Lee");

		unicode_value = unicode_value.replaceAll("ளி", "Li");
		unicode_value = unicode_value.replaceAll("ளெ", "Le");
		unicode_value = unicode_value.replaceAll("ளொ", "Lo");
		unicode_value = unicode_value.replaceAll("ளு", "Lu");

		unicode_value = unicode_value.replaceAll("ள்", "L");
		unicode_value = unicode_value.replaceAll("ள", "La");

		unicode_value = unicode_value.replaceAll("வௌ", "vau");
		unicode_value = unicode_value.replaceAll("வை", "vai");
		unicode_value = unicode_value.replaceAll("வே", "vey");
		unicode_value = unicode_value.replaceAll("வோ", "voh");
		unicode_value = unicode_value.replaceAll("வா", "vaa");
		unicode_value = unicode_value.replaceAll("வூ", "voo");
		unicode_value = unicode_value.replaceAll("வீ", "vee");

		unicode_value = unicode_value.replaceAll("வி", "vi");
		unicode_value = unicode_value.replaceAll("வெ", "ve");
		unicode_value = unicode_value.replaceAll("வொ", "vo");
		unicode_value = unicode_value.replaceAll("வு", "vu");

		unicode_value = unicode_value.replaceAll("வ்", "v");
		unicode_value = unicode_value.replaceAll("வ", "va");
		

		unicode_value = unicode_value.replaceAll("ஔ", "au");
		unicode_value = unicode_value.replaceAll("ஐ", "ai");
		unicode_value = unicode_value.replaceAll("ஆ", "aa");
		unicode_value = unicode_value.replaceAll("ஏ", "ey");
		unicode_value = unicode_value.replaceAll("ஈ", "ee");
		unicode_value = unicode_value.replaceAll("ஊ", "oo");
		unicode_value = unicode_value.replaceAll("ஓ", "oh");
		unicode_value = unicode_value.replaceAll("இ", "i");
		unicode_value = unicode_value.replaceAll("அ", "a");
		unicode_value = unicode_value.replaceAll("எ", "e");
		unicode_value = unicode_value.replaceAll("உ", "u");
		unicode_value = unicode_value.replaceAll("ஒ", "o");

		return unicode_value;
	}

	public static ArrayList<String> TamiltoEnglish(ArrayList<String> tamilList) throws IOException {
		
		ArrayList<String> englishList =  new ArrayList<String>();
		
		for(String str : tamilList)
		{
			String english = replaceTamilCharacters(str);
			englishList.add(english);	
		}
		
		return englishList;
	}
	
	public static String replaceEnglishCharacters(String unicode_value) {

		unicode_value = unicode_value.toLowerCase();

		unicode_value = unicode_value.replaceAll("sr", "ஸ்ரீ");
		unicode_value = unicode_value.replaceAll("kshau", "க்ஷௌ");
		unicode_value = unicode_value.replaceAll("kshai", "க்ஷை");
		unicode_value = unicode_value.replaceAll("kshaa", "க்ஷா");
		unicode_value = unicode_value.replaceAll("ksha", "க்ஷ");
		unicode_value = unicode_value.replaceAll("kshee", "க்ஷீ");
		unicode_value = unicode_value.replaceAll("kshi", "க்ஷி");
		unicode_value = unicode_value.replaceAll("kshoo", "க்ஷூ");
		unicode_value = unicode_value.replaceAll("kshu", "க்ஷு");
		unicode_value = unicode_value.replaceAll("kshey", "க்ஷே");
		unicode_value = unicode_value.replaceAll("kshe", "க்ஷெ");
		unicode_value = unicode_value.replaceAll("ksho", "க்ஷொ");
		unicode_value = unicode_value.replaceAll("kshoh", "க்ஷோ");

		unicode_value = unicode_value.replaceAll("ksh", "க்ஷ்");

		unicode_value = unicode_value.replaceAll("njau", "ஞௌ");
		unicode_value = unicode_value.replaceAll("njai", "ஞை");
		unicode_value = unicode_value.replaceAll("njey", "ஞே");
		unicode_value = unicode_value.replaceAll("njo", "ஞோ");
		unicode_value = unicode_value.replaceAll("njaa", "ஞா");
		unicode_value = unicode_value.replaceAll("njoo", "ஞூ");
		unicode_value = unicode_value.replaceAll("njee", "ஞீ");
		unicode_value = unicode_value.replaceAll("nja", "ஞ");
		unicode_value = unicode_value.replaceAll("nji", "ஞி");
		unicode_value = unicode_value.replaceAll("nje", "ஞெ");
		unicode_value = unicode_value.replaceAll("njo", "ஞொ");
		unicode_value = unicode_value.replaceAll("nju", "ஞு");
		unicode_value = unicode_value.replaceAll("njoo", "ஞூ");

		unicode_value = unicode_value.replaceAll("nj", "ஞ்");

		unicode_value = unicode_value.replaceAll("ngau", "ஙௌ");
		unicode_value = unicode_value.replaceAll("ngai", "ஙை");
		unicode_value = unicode_value.replaceAll("ngey", "ஙே");
		unicode_value = unicode_value.replaceAll("ngoh", "ஙோ");
		unicode_value = unicode_value.replaceAll("ngaa", "ஙா");
		unicode_value = unicode_value.replaceAll("ngoo", "ஙூ");
		unicode_value = unicode_value.replaceAll("ngee", "ஙீ");
		unicode_value = unicode_value.replaceAll("nga", "ங");
		unicode_value = unicode_value.replaceAll("ngi", "ஙி");
		unicode_value = unicode_value.replaceAll("nge", "ஙெ");
		unicode_value = unicode_value.replaceAll("ngo", "ஙொ");
		unicode_value = unicode_value.replaceAll("ngu", "ஙு");

		unicode_value = unicode_value.replaceAll("ng", "ங்");

		unicode_value = unicode_value.replaceAll("shau", "ஷௌ");
		unicode_value = unicode_value.replaceAll("shai", "ஷை");
		unicode_value = unicode_value.replaceAll("shey", "ஷே");
		unicode_value = unicode_value.replaceAll("shoh", "ஷோ");
		unicode_value = unicode_value.replaceAll("shaa", "ஷா");
		unicode_value = unicode_value.replaceAll("shoo", "ஷூ");
		unicode_value = unicode_value.replaceAll("shee", "ஷீ");
		unicode_value = unicode_value.replaceAll("sha", "ஷ");
		unicode_value = unicode_value.replaceAll("shi", "ஷி");
		unicode_value = unicode_value.replaceAll("she", "ஷெ");
		unicode_value = unicode_value.replaceAll("sho", "ஷொ");
		unicode_value = unicode_value.replaceAll("shu", "ஷு");

		unicode_value = unicode_value.replaceAll("sh", "ஷ்");

		unicode_value = unicode_value.replaceAll("phau", "ஃபௌ");
		unicode_value = unicode_value.replaceAll("phai", "ஃபை");
		unicode_value = unicode_value.replaceAll("phey", "ஃபே");
		unicode_value = unicode_value.replaceAll("phoh", "ஃபோ");
		unicode_value = unicode_value.replaceAll("phaa", "ஃபா");
		unicode_value = unicode_value.replaceAll("phoo", "ஃபூ");
		unicode_value = unicode_value.replaceAll("phee", "ஃபீ");
		unicode_value = unicode_value.replaceAll("pha", "ஃப");
		unicode_value = unicode_value.replaceAll("phi", "ஃபி");
		unicode_value = unicode_value.replaceAll("phe", "ஃபெ");
		unicode_value = unicode_value.replaceAll("pho", "ஃபொ");
		unicode_value = unicode_value.replaceAll("phu", "ஃபு");

		unicode_value = unicode_value.replaceAll("ph", "ஃப்");

		unicode_value = unicode_value.replaceAll(" nau", " நௌ");
		unicode_value = unicode_value.replaceAll(" nai", " நை");
		unicode_value = unicode_value.replaceAll(" ney", " நே");
		unicode_value = unicode_value.replaceAll(" noh", " நோ");
		unicode_value = unicode_value.replaceAll(" naa", " நா");
		unicode_value = unicode_value.replaceAll(" noo", " நூ");
		unicode_value = unicode_value.replaceAll(" nee", " நீ");
		unicode_value = unicode_value.replaceAll(" na", " ந");
		unicode_value = unicode_value.replaceAll(" ni", " நி");
		unicode_value = unicode_value.replaceAll(" ne", " நெ");
		unicode_value = unicode_value.replaceAll(" no", " நொ");
		unicode_value = unicode_value.replaceAll(" nu", " நு");

		unicode_value = unicode_value.replaceAll("-nau", "நௌ");
		unicode_value = unicode_value.replaceAll("-nai", "நை");
		unicode_value = unicode_value.replaceAll("-ney", "நே");
		unicode_value = unicode_value.replaceAll("-noh", "நோ");
		unicode_value = unicode_value.replaceAll("-naa", "நா");
		unicode_value = unicode_value.replaceAll("-noo", "நூ");
		unicode_value = unicode_value.replaceAll("-nee", "நீ");
		unicode_value = unicode_value.replaceAll("-na", "ந");
		unicode_value = unicode_value.replaceAll("-ni", "நி");
		unicode_value = unicode_value.replaceAll("-ne", "நெ");
		unicode_value = unicode_value.replaceAll("-no", "நொ");
		unicode_value = unicode_value.replaceAll("-nu", "நு");

		unicode_value = unicode_value.replaceAll("wau", "வௌ");
		unicode_value = unicode_value.replaceAll("wai", "வை");
		unicode_value = unicode_value.replaceAll("wey", "வே");
		unicode_value = unicode_value.replaceAll("woh", "வோ");
		unicode_value = unicode_value.replaceAll("waa", "வா");
		unicode_value = unicode_value.replaceAll("woo", "வூ");
		unicode_value = unicode_value.replaceAll("wee", "வீ");
		unicode_value = unicode_value.replaceAll("wa", "வ");
		unicode_value = unicode_value.replaceAll("wi", "வி");
		unicode_value = unicode_value.replaceAll("we", "வெ");
		unicode_value = unicode_value.replaceAll("wo", "வொ");
		unicode_value = unicode_value.replaceAll("wu", "வு");

		unicode_value = unicode_value.replaceAll(" n", " ந்");
		unicode_value = unicode_value.replaceAll("-n", "ந்");
		unicode_value = unicode_value.replaceAll("w", "வ்");

		unicode_value = unicode_value.replaceAll("nthau", "ந்தௌ");
		unicode_value = unicode_value.replaceAll("nthai", "ந்தை");
		unicode_value = unicode_value.replaceAll("nthey", "ந்தே");
		unicode_value = unicode_value.replaceAll("nthoh", "ந்தோ");
		unicode_value = unicode_value.replaceAll("nthaa", "ந்தா");
		unicode_value = unicode_value.replaceAll("nthoo", "ந்தூ");
		unicode_value = unicode_value.replaceAll("nthee", "ந்தீ");
		unicode_value = unicode_value.replaceAll("ntha", "ந்த");
		unicode_value = unicode_value.replaceAll("nthi", "ந்தி");
		unicode_value = unicode_value.replaceAll("nthe", "ந்தெ");
		unicode_value = unicode_value.replaceAll("ntho", "ந்தொ");
		unicode_value = unicode_value.replaceAll("nthu", "ந்து");
		unicode_value = unicode_value.replaceAll("nth", "ந்த்");

		unicode_value = unicode_value.replaceAll("dhau", "தௌ");
		unicode_value = unicode_value.replaceAll("dhai", "தை");
		unicode_value = unicode_value.replaceAll("dhey", "தே");
		unicode_value = unicode_value.replaceAll("dhoh", "தோ");
		unicode_value = unicode_value.replaceAll("dhaa", "தா");
		unicode_value = unicode_value.replaceAll("dhoo", "தூ");
		unicode_value = unicode_value.replaceAll("dhee", "தீ");
		unicode_value = unicode_value.replaceAll("dha", "த");
		unicode_value = unicode_value.replaceAll("dhi", "தி");
		unicode_value = unicode_value.replaceAll("dhe", "தெ");
		unicode_value = unicode_value.replaceAll("dho", "தொ");
		unicode_value = unicode_value.replaceAll("dhu", "து");

		unicode_value = unicode_value.replaceAll("dh", "த்");

		unicode_value = unicode_value.replaceAll("chau", "சௌ");
		unicode_value = unicode_value.replaceAll("chai", "சை");
		unicode_value = unicode_value.replaceAll("chey", "சே");
		unicode_value = unicode_value.replaceAll("choh", "சோ");
		unicode_value = unicode_value.replaceAll("chaa", "சா");
		unicode_value = unicode_value.replaceAll("choo", "சூ");
		unicode_value = unicode_value.replaceAll("chee", "சீ");
		unicode_value = unicode_value.replaceAll("cha", "ச");
		unicode_value = unicode_value.replaceAll("chi", "சி");
		unicode_value = unicode_value.replaceAll("che", "செ");
		unicode_value = unicode_value.replaceAll("cho", "சொ");
		unicode_value = unicode_value.replaceAll("chu", "சு");

		unicode_value = unicode_value.replaceAll("ch", "ச்");

		unicode_value = unicode_value.replaceAll("zhau", "ழௌ");
		unicode_value = unicode_value.replaceAll("zhai", "ழை");
		unicode_value = unicode_value.replaceAll("zhey", "ழே");
		unicode_value = unicode_value.replaceAll("zhoh", "ழோ");
		unicode_value = unicode_value.replaceAll("zhaa", "ழா");
		unicode_value = unicode_value.replaceAll("zhoo", "ழூ");
		unicode_value = unicode_value.replaceAll("zhee", "ழீ");
		unicode_value = unicode_value.replaceAll("zha", "ழ");
		unicode_value = unicode_value.replaceAll("zhi", "ழி");
		unicode_value = unicode_value.replaceAll("zhe", "ழெ");
		unicode_value = unicode_value.replaceAll("zho", "ழொ");
		unicode_value = unicode_value.replaceAll("zhu", "ழு");

		unicode_value = unicode_value.replaceAll("zh", "ழ்");

		unicode_value = unicode_value.replaceAll("zau", "ழௌ");
		unicode_value = unicode_value.replaceAll("zai", "ழை");
		unicode_value = unicode_value.replaceAll("zey", "ழே");
		unicode_value = unicode_value.replaceAll("zoh", "ழோ");
		unicode_value = unicode_value.replaceAll("zaa", "ழா");
		unicode_value = unicode_value.replaceAll("zoo", "ழூ");
		unicode_value = unicode_value.replaceAll("zee", "ழீ");
		unicode_value = unicode_value.replaceAll("za", "ழ");
		unicode_value = unicode_value.replaceAll("zi", "ழி");
		unicode_value = unicode_value.replaceAll("ze", "ழெ");
		unicode_value = unicode_value.replaceAll("zo", "ழொ");
		unicode_value = unicode_value.replaceAll("zu", "ழு");

		unicode_value = unicode_value.replaceAll("z", "ச்");

		unicode_value = unicode_value.replaceAll("jau", "ஜௌ");
		unicode_value = unicode_value.replaceAll("jai", "ஜை");
		unicode_value = unicode_value.replaceAll("jey", "ஜே");
		unicode_value = unicode_value.replaceAll("joh", "ஜோ");
		unicode_value = unicode_value.replaceAll("jaa", "ஜா");
		unicode_value = unicode_value.replaceAll("joo", "ஜூ");
		unicode_value = unicode_value.replaceAll("jee", "ஜீ");
		unicode_value = unicode_value.replaceAll("ja", "ஜ");
		unicode_value = unicode_value.replaceAll("ji", "ஜி");
		unicode_value = unicode_value.replaceAll("je", "ஜெ");
		unicode_value = unicode_value.replaceAll("jo", "ஜொ");
		unicode_value = unicode_value.replaceAll("ju", "ஜு");

		unicode_value = unicode_value.replaceAll("j", "ஜ்");

		unicode_value = unicode_value.replaceAll("thau", "தௌ");
		unicode_value = unicode_value.replaceAll("thai", "தை");
		unicode_value = unicode_value.replaceAll("they", "தே");
		unicode_value = unicode_value.replaceAll("thoh", "தோ");
		unicode_value = unicode_value.replaceAll("thaa", "தா");
		unicode_value = unicode_value.replaceAll("thoo", "தூ");
		unicode_value = unicode_value.replaceAll("thee", "தீ");
		unicode_value = unicode_value.replaceAll("tha", "த");
		unicode_value = unicode_value.replaceAll("thi", "தி");
		unicode_value = unicode_value.replaceAll("the", "தெ");
		unicode_value = unicode_value.replaceAll("tho", "தொ");
		unicode_value = unicode_value.replaceAll("thu", "து");

		unicode_value = unicode_value.replaceAll("th", "த்");

		unicode_value = unicode_value.replaceAll("hau", "ஹௌ");
		unicode_value = unicode_value.replaceAll("hai", "ஹை");
		unicode_value = unicode_value.replaceAll("hey", "ஹே");
		unicode_value = unicode_value.replaceAll("hoh", "ஹோ");
		unicode_value = unicode_value.replaceAll("haa", "ஹா");
		unicode_value = unicode_value.replaceAll("hoo", "ஹூ");
		unicode_value = unicode_value.replaceAll("hee", "ஹீ");
		unicode_value = unicode_value.replaceAll("ha", "ஹ");
		unicode_value = unicode_value.replaceAll("hi", "ஹி");
		unicode_value = unicode_value.replaceAll("he", "ஹெ");
		unicode_value = unicode_value.replaceAll("ho", "ஹொ");
		unicode_value = unicode_value.replaceAll("hu", "ஹு");

		unicode_value = unicode_value.replaceAll("h", "ஹ்");

		unicode_value = unicode_value.replaceAll("kau", "கௌ");
		unicode_value = unicode_value.replaceAll("kai", "கை");
		unicode_value = unicode_value.replaceAll("key", "கே");
		unicode_value = unicode_value.replaceAll("koh", "கோ");
		unicode_value = unicode_value.replaceAll("kaa", "கா");
		unicode_value = unicode_value.replaceAll("koo", "கூ");
		unicode_value = unicode_value.replaceAll("kee", "கீ");
		unicode_value = unicode_value.replaceAll("ka", "க");
		unicode_value = unicode_value.replaceAll("ki", "கி");
		unicode_value = unicode_value.replaceAll("ke", "கெ");
		unicode_value = unicode_value.replaceAll("ko", "கொ");
		unicode_value = unicode_value.replaceAll("ku", "கு");

		unicode_value = unicode_value.replaceAll("k", "க்");

		unicode_value = unicode_value.replaceAll("-sau", "ஸௌ");
		unicode_value = unicode_value.replaceAll("-sai", "ஸை");
		unicode_value = unicode_value.replaceAll("-sey", "ஸே");
		unicode_value = unicode_value.replaceAll("-soh", "ஸோ");
		unicode_value = unicode_value.replaceAll("-saa", "ஸா");
		unicode_value = unicode_value.replaceAll("-soo", "ஸூ");
		unicode_value = unicode_value.replaceAll("-see", "ஸீ");
		unicode_value = unicode_value.replaceAll("-sa", "ஸ");
		unicode_value = unicode_value.replaceAll("-si", "ஸி");
		unicode_value = unicode_value.replaceAll("-se", "ஸெ");
		unicode_value = unicode_value.replaceAll("-so", "ஸொ");
		unicode_value = unicode_value.replaceAll("-su", "ஸு");

		unicode_value = unicode_value.replaceAll("-s", "ஸ்");

		unicode_value = unicode_value.replaceAll("Sau", "ஸௌ");
		unicode_value = unicode_value.replaceAll("Sai", "ஸை");
		unicode_value = unicode_value.replaceAll("Sey", "ஸே");
		unicode_value = unicode_value.replaceAll("Soh", "ஸோ");
		unicode_value = unicode_value.replaceAll("Saa", "ஸா");
		unicode_value = unicode_value.replaceAll("Soo", "ஸூ");
		unicode_value = unicode_value.replaceAll("See", "ஸீ");
		unicode_value = unicode_value.replaceAll("Sa", "ஸ");
		unicode_value = unicode_value.replaceAll("Si", "ஸி");
		unicode_value = unicode_value.replaceAll("Se", "ஸெ");
		unicode_value = unicode_value.replaceAll("So", "ஸொ");
		unicode_value = unicode_value.replaceAll("Su", "ஸு");

		unicode_value = unicode_value.replaceAll("S", "ஸ்");

		unicode_value = unicode_value.replaceAll("rau", "ரௌ");
		unicode_value = unicode_value.replaceAll("rai", "ரை");
		unicode_value = unicode_value.replaceAll("rey", "ரே");
		unicode_value = unicode_value.replaceAll("roh", "ரோ");
		unicode_value = unicode_value.replaceAll("raa", "ரா");
		unicode_value = unicode_value.replaceAll("roo", "ரூ");
		unicode_value = unicode_value.replaceAll("ree", "ரீ");
		unicode_value = unicode_value.replaceAll("ra", "ர");
		unicode_value = unicode_value.replaceAll("ri", "ரி");
		unicode_value = unicode_value.replaceAll("re", "ரெ");
		unicode_value = unicode_value.replaceAll("ro", "ரொ");
		unicode_value = unicode_value.replaceAll("ru", "ரு");

		unicode_value = unicode_value.replaceAll("r", "ர்");

		unicode_value = unicode_value.replaceAll("Rau", "றௌ");
		unicode_value = unicode_value.replaceAll("Rai", "றை");
		unicode_value = unicode_value.replaceAll("Rey", "றே");
		unicode_value = unicode_value.replaceAll("Roh", "றோ");
		unicode_value = unicode_value.replaceAll("Raa", "றா");
		unicode_value = unicode_value.replaceAll("Roo", "றூ");
		unicode_value = unicode_value.replaceAll("Ree", "றீ");
		unicode_value = unicode_value.replaceAll("Ra", "ற");
		unicode_value = unicode_value.replaceAll("Ri", "றி");
		unicode_value = unicode_value.replaceAll("Re", "றெ");
		unicode_value = unicode_value.replaceAll("Ro", "றொ");
		unicode_value = unicode_value.replaceAll("Ru", "று");

		unicode_value = unicode_value.replaceAll("R", "ற்");

		unicode_value = unicode_value.replaceAll("fau", "ஃபௌ");
		unicode_value = unicode_value.replaceAll("fai", "ஃபை");
		unicode_value = unicode_value.replaceAll("fey", "ஃபே");
		unicode_value = unicode_value.replaceAll("foh", "ஃபோ");
		unicode_value = unicode_value.replaceAll("faa", "ஃபா");
		unicode_value = unicode_value.replaceAll("foo", "ஃபூ");
		unicode_value = unicode_value.replaceAll("fee", "ஃபீ");
		unicode_value = unicode_value.replaceAll("fa", "ஃப");
		unicode_value = unicode_value.replaceAll("fi", "ஃபி");
		unicode_value = unicode_value.replaceAll("fe", "ஃபெ");
		unicode_value = unicode_value.replaceAll("fo", "ஃபொ");
		unicode_value = unicode_value.replaceAll("fu", "ஃபு");

		unicode_value = unicode_value.replaceAll("ph", "ஃப்");

		unicode_value = unicode_value.replaceAll("tau", "டௌ");
		unicode_value = unicode_value.replaceAll("tai", "டை");
		unicode_value = unicode_value.replaceAll("tey", "டே");
		unicode_value = unicode_value.replaceAll("toh", "டோ");
		unicode_value = unicode_value.replaceAll("taa", "டா");
		unicode_value = unicode_value.replaceAll("too", "டூ");
		unicode_value = unicode_value.replaceAll("tee", "டீ");
		unicode_value = unicode_value.replaceAll("ta", "ட");
		unicode_value = unicode_value.replaceAll("ti", "டி");
		unicode_value = unicode_value.replaceAll("te", "டெ");
		unicode_value = unicode_value.replaceAll("to", "டொ");
		unicode_value = unicode_value.replaceAll("tu", "டு");

		unicode_value = unicode_value.replaceAll("t", "ட்");

		unicode_value = unicode_value.replaceAll("sau", "சௌ");
		unicode_value = unicode_value.replaceAll("sai", "சை");
		unicode_value = unicode_value.replaceAll("sey", "சே");
		unicode_value = unicode_value.replaceAll("soh", "சோ");
		unicode_value = unicode_value.replaceAll("saa", "சா");
		unicode_value = unicode_value.replaceAll("soo", "சூ");
		unicode_value = unicode_value.replaceAll("see", "சீ");
		unicode_value = unicode_value.replaceAll("sa", "ச");
		unicode_value = unicode_value.replaceAll("si", "சி");
		unicode_value = unicode_value.replaceAll("se", "செ");
		unicode_value = unicode_value.replaceAll("so", "சொ");
		unicode_value = unicode_value.replaceAll("su", "சு");

		unicode_value = unicode_value.replaceAll("s", "ச்");

		unicode_value = unicode_value.replaceAll("pau", "பௌ");
		unicode_value = unicode_value.replaceAll("pai", "பை");
		unicode_value = unicode_value.replaceAll("pey", "பே");
		unicode_value = unicode_value.replaceAll("poh", "போ");
		unicode_value = unicode_value.replaceAll("paa", "பா");
		unicode_value = unicode_value.replaceAll("poo", "பூ");
		unicode_value = unicode_value.replaceAll("pee", "பீ");
		unicode_value = unicode_value.replaceAll("pa", "ப");
		unicode_value = unicode_value.replaceAll("pi", "பி");
		unicode_value = unicode_value.replaceAll("pe", "பெ");
		unicode_value = unicode_value.replaceAll("po", "பொ");
		unicode_value = unicode_value.replaceAll("pu", "பு");

		unicode_value = unicode_value.replaceAll("p", "ப்");

		unicode_value = unicode_value.replaceAll("bau", "பௌ");
		unicode_value = unicode_value.replaceAll("bai", "பை");
		unicode_value = unicode_value.replaceAll("bey", "பே");
		unicode_value = unicode_value.replaceAll("boh", "போ");
		unicode_value = unicode_value.replaceAll("baa", "பா");
		unicode_value = unicode_value.replaceAll("boo", "பூ");
		unicode_value = unicode_value.replaceAll("bee", "பீ");
		unicode_value = unicode_value.replaceAll("ba", "ப");
		unicode_value = unicode_value.replaceAll("bi", "பி");
		unicode_value = unicode_value.replaceAll("be", "பெ");
		unicode_value = unicode_value.replaceAll("bo", "பொ");
		unicode_value = unicode_value.replaceAll("bu", "பு");

		unicode_value = unicode_value.replaceAll("b", "ப்");

		unicode_value = unicode_value.replaceAll("mau", "மௌ");
		unicode_value = unicode_value.replaceAll("mai", "மை");
		unicode_value = unicode_value.replaceAll("mey", "மே");
		unicode_value = unicode_value.replaceAll("moh", "மோ");
		unicode_value = unicode_value.replaceAll("maa", "மா");
		unicode_value = unicode_value.replaceAll("moo", "மூ");
		unicode_value = unicode_value.replaceAll("mee", "மீ");
		unicode_value = unicode_value.replaceAll("ma", "ம");
		unicode_value = unicode_value.replaceAll("mi", "மி");
		unicode_value = unicode_value.replaceAll("me", "மெ");
		unicode_value = unicode_value.replaceAll("mo", "மொ");
		unicode_value = unicode_value.replaceAll("mu", "மு");

		unicode_value = unicode_value.replaceAll("m", "ம்");

		unicode_value = unicode_value.replaceAll("yau", "யௌ");
		unicode_value = unicode_value.replaceAll("yai", "யை");
		unicode_value = unicode_value.replaceAll("yey", "யே");
		unicode_value = unicode_value.replaceAll("yoh", "யோ");
		unicode_value = unicode_value.replaceAll("yaa", "யா");
		unicode_value = unicode_value.replaceAll("yoo", "யூ");
		unicode_value = unicode_value.replaceAll("yee", "யீ");
		unicode_value = unicode_value.replaceAll("ya", "ய");
		unicode_value = unicode_value.replaceAll("yi", "யி");
		unicode_value = unicode_value.replaceAll("ye", "யெ");
		unicode_value = unicode_value.replaceAll("yo", "யொ");
		unicode_value = unicode_value.replaceAll("yu", "யு");

		unicode_value = unicode_value.replaceAll("y", "ய்");

		unicode_value = unicode_value.replaceAll("dau", "டௌ");
		unicode_value = unicode_value.replaceAll("dai", "டை");
		unicode_value = unicode_value.replaceAll("dey", "டே");
		unicode_value = unicode_value.replaceAll("doh", "டோ");
		unicode_value = unicode_value.replaceAll("daa", "டா");
		unicode_value = unicode_value.replaceAll("doo", "டூ");
		unicode_value = unicode_value.replaceAll("dee", "டீ");
		unicode_value = unicode_value.replaceAll("da", "ட");
		unicode_value = unicode_value.replaceAll("di", "டி");
		unicode_value = unicode_value.replaceAll("de", "டெ");
		unicode_value = unicode_value.replaceAll("do", "டொ");
		unicode_value = unicode_value.replaceAll("du", "டு");

		unicode_value = unicode_value.replaceAll("d", "ட்");

		unicode_value = unicode_value.replaceAll("nau", "னௌ");
		unicode_value = unicode_value.replaceAll("nai", "னை");
		unicode_value = unicode_value.replaceAll("ney", "னே");
		unicode_value = unicode_value.replaceAll("noh", "னோ");
		unicode_value = unicode_value.replaceAll("naa", "னா");
		unicode_value = unicode_value.replaceAll("noo", "னூ");
		unicode_value = unicode_value.replaceAll("nee", "னீ");
		unicode_value = unicode_value.replaceAll("na", "ன");
		unicode_value = unicode_value.replaceAll("ni", "னி");
		unicode_value = unicode_value.replaceAll("ne", "னெ");
		unicode_value = unicode_value.replaceAll("no", "னொ");
		unicode_value = unicode_value.replaceAll("nu", "னு");

		unicode_value = unicode_value.replaceAll("n", "ன்");

		unicode_value = unicode_value.replaceAll("Nau", "ணௌ");
		unicode_value = unicode_value.replaceAll("Nai", "ணை");
		unicode_value = unicode_value.replaceAll("Ney", "ணே");
		unicode_value = unicode_value.replaceAll("Noh", "ணோ");
		unicode_value = unicode_value.replaceAll("Naa", "ணா");
		unicode_value = unicode_value.replaceAll("Noo", "ணூ");
		unicode_value = unicode_value.replaceAll("Nee", "ணீ");
		unicode_value = unicode_value.replaceAll("Na", "ண");
		unicode_value = unicode_value.replaceAll("Ni", "ணி");
		unicode_value = unicode_value.replaceAll("Ne", "ணெ");
		unicode_value = unicode_value.replaceAll("No", "ணொ");
		unicode_value = unicode_value.replaceAll("Nu", "ணு");

		unicode_value = unicode_value.replaceAll("N", "ண்");

		unicode_value = unicode_value.replaceAll("lau", "லௌ");
		unicode_value = unicode_value.replaceAll("lai", "லை");
		unicode_value = unicode_value.replaceAll("ley", "லே");
		unicode_value = unicode_value.replaceAll("loh", "லோ");
		unicode_value = unicode_value.replaceAll("laa", "லா");
		unicode_value = unicode_value.replaceAll("loo", "லூ");
		unicode_value = unicode_value.replaceAll("lee", "லீ");
		unicode_value = unicode_value.replaceAll("la", "ல");
		unicode_value = unicode_value.replaceAll("li", "லி");
		unicode_value = unicode_value.replaceAll("le", "லெ");
		unicode_value = unicode_value.replaceAll("lo", "லொ");
		unicode_value = unicode_value.replaceAll("lu", "லு");

		unicode_value = unicode_value.replaceAll("l", "ல்");

		unicode_value = unicode_value.replaceAll("Lau", "ளௌ");
		unicode_value = unicode_value.replaceAll("Lai", "ளை");
		unicode_value = unicode_value.replaceAll("Ley", "ளே");
		unicode_value = unicode_value.replaceAll("Loh", "ளோ");
		unicode_value = unicode_value.replaceAll("Laa", "ளா");
		unicode_value = unicode_value.replaceAll("Loo", "ளூ");
		unicode_value = unicode_value.replaceAll("Lee", "ளீ");
		unicode_value = unicode_value.replaceAll("La", "ள");
		unicode_value = unicode_value.replaceAll("Li", "ளி");
		unicode_value = unicode_value.replaceAll("Le", "ளெ");
		unicode_value = unicode_value.replaceAll("Lo", "ளொ");
		unicode_value = unicode_value.replaceAll("Lu", "ளு");

		unicode_value = unicode_value.replaceAll("L", "ள்");

		unicode_value = unicode_value.replaceAll("vau", "வௌ");
		unicode_value = unicode_value.replaceAll("vai", "வை");
		unicode_value = unicode_value.replaceAll("vey", "வே");
		unicode_value = unicode_value.replaceAll("voh", "வோ");
		unicode_value = unicode_value.replaceAll("vaa", "வா");
		unicode_value = unicode_value.replaceAll("voo", "வூ");
		unicode_value = unicode_value.replaceAll("vee", "வீ");
		unicode_value = unicode_value.replaceAll("va", "வ");
		unicode_value = unicode_value.replaceAll("vi", "வி");
		unicode_value = unicode_value.replaceAll("ve", "வெ");
		unicode_value = unicode_value.replaceAll("vo", "வொ");
		unicode_value = unicode_value.replaceAll("vu", "வு");

		unicode_value = unicode_value.replaceAll("v", "வ்");

		unicode_value = unicode_value.replaceAll("gau", "கௌ");
		unicode_value = unicode_value.replaceAll("gai", "கை");
		unicode_value = unicode_value.replaceAll("gey", "கே");
		unicode_value = unicode_value.replaceAll("goh", "கோ");
		unicode_value = unicode_value.replaceAll("gaa", "கா");
		unicode_value = unicode_value.replaceAll("goo", "கூ");
		unicode_value = unicode_value.replaceAll("gee", "கீ");
		unicode_value = unicode_value.replaceAll("ga", "க");
		unicode_value = unicode_value.replaceAll("gi", "கி");
		unicode_value = unicode_value.replaceAll("ge", "கெ");
		unicode_value = unicode_value.replaceAll("go", "கொ");
		unicode_value = unicode_value.replaceAll("gu", "கு");

		unicode_value = unicode_value.replaceAll("g", "க்");

		unicode_value = unicode_value.replaceAll("au", "ஔ");
		unicode_value = unicode_value.replaceAll("ai", "ஐ");
		unicode_value = unicode_value.replaceAll("aa", "ஆ");
		unicode_value = unicode_value.replaceAll("ey", "ஏ");
		unicode_value = unicode_value.replaceAll("ee", "ஈ");
		unicode_value = unicode_value.replaceAll("oo", "ஊ");
		unicode_value = unicode_value.replaceAll("oh", "ஓ");
		unicode_value = unicode_value.replaceAll("i", "இ");
		unicode_value = unicode_value.replaceAll("a", "அ");
		unicode_value = unicode_value.replaceAll("e", "எ");
		unicode_value = unicode_value.replaceAll("u", "உ");
		unicode_value = unicode_value.replaceAll("o", "ஒ");

		return unicode_value;
	}

	public static void EnglishtoTamil() throws IOException {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File(
										"C:/Users/arkr94/Desktop/FYP1/input_eng.txt")),
						"UTF-8"));
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
			everything = replaceEnglishCharacters(everything);
		} finally {
			br.close();
		}
		Writer out = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(
								"C:/Users/arkr94/Desktop/FYP1/output_tamil.txt"),
						"UTF-8"));
		try {
			out.write(everything);
		} finally {
			out.close();
		}
	}

	public static ArrayList<String> HinditoEnglish(ArrayList<String> hindiList) throws IOException {
//		BufferedReader br = new BufferedReader(
//				new InputStreamReader(
//						new FileInputStream(
//								new File(
//										"C:/Users/arkr94/Desktop/FYP1/hindi.txt")),
//						"UTF-8"));
//		String everything = "";
//		try {
//			StringBuilder sb = new StringBuilder();
//			String line = br.readLine();
//								
//			while (line != null) {
//				sb.append(line);
//				sb.append(System.lineSeparator());
//				line = br.readLine();
//			}
//			everything = sb.toString();
//			for (int i = DevText.length-1; i>=0; i--) {
//				everything = everything.replaceAll(DevText[i],English[i]);
//			}
//
//		} finally {
//			br.close();
//		}
		
		ArrayList<String> englishList =  new ArrayList<String>();
		
		for(String str : hindiList)
		{
			for (int i = DevText.length-1; i>=0; i--) {
				str = str.replaceAll(DevText[i],English[i]);
			}
			
			englishList.add(str);	
		}
		
		return englishList;
		
//		Writer out = new BufferedWriter(
//				new OutputStreamWriter(
//						new FileOutputStream(
//								"C:/Users/arkr94/Desktop/FYP1/hindi2english.txt"),
//						"UTF-8"));
//		try {
//			out.write(everything);
//		} finally {
//			out.close();
//		}
//		
		
	}

}
