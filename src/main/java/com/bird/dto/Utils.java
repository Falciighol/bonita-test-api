package com.bird.dto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class.getName());

    public static Integer toInteger(String s) {
        return Integer.parseInt(s);
    }

    public static JSONObject toJSONObject(String s) {
        JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			jsonObject = (JSONObject) parser.parse(s);
        } catch (Exception e) {
			LOGGER.error("Error while trying to parse JSONObject [Index -> getBody()]", e);
		}

        return jsonObject;
    }
}
