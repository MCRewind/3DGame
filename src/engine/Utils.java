package engine;

import java.io.InputStream;
import java.util.Scanner;


public class Utils {

	//finds specific resources through the classpath and returns them as a string
	@SuppressWarnings("resource")
	public static String loadResource(String fileName) throws Exception {
        String result = "";
        System.out.println(fileName);
        try (InputStream in = Utils.class.getClass().getResourceAsStream(fileName)) {
            result = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
        } 
        return result;
    }
}