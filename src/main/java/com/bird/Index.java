package com.bird;

import com.bird.controller.BookController;
import com.bird.dto.Result;
import com.bird.dto.Utils;
import com.bird.model.Book;

import org.bonitasoft.web.extension.rest.RestAPIContext;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * @throws ServletException
	 */
	@Override
	protected Result execute(RestAPIContext context, HttpServletRequest req) throws NamingException, IOException, ServletException {
		// Controllers
		BookController bookController = new BookController();
		// Response body
		Map<String, Object> result = new HashMap<>();
		// Entities
		Book book = new Book();
		
		try {
			// Request Body
			JSONObject reqBody = getRequestBody(req);
			// Query Parameter
			String queryID = req.getParameter("queryID");
	
			if (queryID.equals("addBook"))
			{
				book = new Book(getRandomNumber(100000, 999999), reqBody.get("name").toString());
				bookController.save(book, context.getResourceProvider());
				result.put("data", book);
			}
			else if (queryID.equals("updateBook"))
			{
				book = new Book(Utils.toInteger(reqBody.get("id").toString()), reqBody.get("name").toString());
				bookController.update(book, context.getResourceProvider());
				result.put("data", book);
			}
		} catch (Exception e) {
			LOGGER.error("Error while executing bussiness logic [Result execute()]", e);
		}

		return Result.builder()
			.result(result)
			.build();
	}

	private JSONObject getRequestBody(HttpServletRequest request) throws IOException, ServletException {
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) Utils.toJSONObject(getStringBody(request));
		} catch (Exception e) {
			LOGGER.error("Error while trying to parse JSONObject [Index -> getBody()]", e);
		}
		return jsonObject;
	}

	private String getStringBody(HttpServletRequest request) {		
		// METHOD #2
		String requestData = null;
		try {
			requestData = request.getReader().lines().collect(Collectors.joining());
		} catch (IOException e) {
			LOGGER.error("Error while trying to get body string [Index -> getStringBody()]", e);
		}
		return requestData;
	}
}