package _01;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEmcodeDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String o = "中文字+_!@#$%^&";
		String s = URLEncoder.encode(o, "UTF-8");
		System.out.println(s);		
		String t = URLDecoder.decode(s, "UTF-8");
		System.out.println(o);
		System.out.println(t);
	}

}
