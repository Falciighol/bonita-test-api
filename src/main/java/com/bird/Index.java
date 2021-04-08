package com.bird;

import com.bird.controller.BookController;
import com.bird.dto.Result;
import com.bird.model.Book;

import org.bonitasoft.web.extension.rest.RestAPIContext;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Controller class
 */
public class Index extends AbstractIndex {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Index.class.getName());

	/**
	 * Ensure request is valid
	 *
	 * @param request the HttpRequest
	 */
	@Override
	public void validateInputParameters(HttpServletRequest request) {
		// To retrieve query parameters use the request.getParameter(..) method.
		// Be careful, parameter values are always returned as String values


	}

	public Integer getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	/**
	 * Execute business logic
	 *
	 * @param context
	 * @return Result
	 * @throws NamingException
	 */
	@Override
	protected Result execute(RestAPIContext context, HttpServletRequest req) throws NamingException {
		// Controllers
		BookController bookController = new BookController();
		// Response body
		Map<String, Object> body = new HashMap<>();
		// Entities
		Book book = new Book();
		
		try {
			// Request Body
			JSONObject reqBody = getBody(req);
			// Query Parameter
			String queryID = reqBody.getString("queryID");
	
			if (queryID.equals("addBook")) {
				book = new Book(getRandomNumber(100000, 999999), reqBody.getString("name"));
				bookController.save(book, context.getResourceProvider());
				body.put("data", book);
			} else if (queryID.equals("updateBook")) {
				book = new Book(reqBody.getInt("id"), reqBody.getString("name"));
				bookController.update(book, context.getResourceProvider());
				body.put("data", book);
			}
		} catch (Exception e) {
			LOGGER.error("Error while executing bussiness logic [Result execute()]", e);
		}

		return Result.builder()
			.body(body)
			.build();
	}

	private JSONObject getBody(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			jb.append(line);
		} catch (Exception e) { /*report an error*/ }
		JSONObject jsonObject = null;
		try {
			jsonObject =  HTTP.toJSONObject(jb.toString());
		} catch (JSONException e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}
		if (jsonObject == null) {
			throw new IOException("NULL JSON");
		}
		return jsonObject;
		// Work with the data using methods like...
		// int someInt = jsonObject.getInt("intParamName");
		// String someString = jsonObject.getString("stringParamName");
		// JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
		// JSONArray arr = jsonObject.getJSONArray("arrayParamName");
		// etc...
	}
}