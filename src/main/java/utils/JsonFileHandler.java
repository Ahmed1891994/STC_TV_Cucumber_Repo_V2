package utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonFileHandler {
	public JSONObject loadJSONs(String mydata) {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(mydata + ".json");
			 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
			// Step 1: Open an InputStream to read the JSON file
			// Step 2: Create an InputStreamReader to handle character encoding (UTF-8)
			// Step 3: Initialize a JSONTokener to parse the JSON data
			MyLogger.info("Loading JSON file: " + mydata);
			JSONTokener tokener = new JSONTokener(inputStreamReader);
			// Step 4: Create a JSONObject from the tokener
			return new JSONObject(tokener);
		} catch (IOException e) {
			// Step 5: Handle any I/O-related exceptions (e.g., file not found, read error)
			MyLogger.error("Error loading JSON file: " + mydata, e);
			throw new RuntimeException("Encountered an error: " + e.getMessage());
		}
	}

}
