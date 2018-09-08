package common;

import com.typesafe.config.ConfigFactory;

public class SampleUtil {

	public static String makeUrl(String url) {
		return ConfigFactory.load().getString("baseUrl") + url;
	}

}
