package com.bird;

import com.bird.controller.BookController;
import com.bird.dto.Result;
import com.bird.dto.Utils;
import com.bird.model.Book;

import org.bonitasoft.web.extension.rest.RestAPIContext;
import org.json.simple.JSONObject;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		List<Book> books = new ArrayList<Book>();
		String queryID = "";
		Boolean queryStatus = false;
	
		try {
			// Request Body
			JSONObject reqBody = null;
			// Query Parameter
			queryID = req.getParameter("queryID");
			if (queryID == null || queryID.equals(""))
			{
				throw new Exception("The 'queryID' parameter is required.");
			}
			else if (!queryID.contains("get"))
			{
				reqBody = getRequestBody(req);
			}

			if (queryID.contains("ById")) {
				if (req.getParameter("id") == null || req.getParameter("id").equals(""))
				{
					throw new Exception("The 'id' parameter is required.");
				}
			}
	
			if (queryID.equals("addBook"))
			{
				book = new Book(Utils.getRandomNumber(100000, 999999), reqBody.get("name").toString());
				queryStatus = bookController.save(book, context.getResourceProvider());
				result = getResponseBody(
					book,
					false,
					((queryStatus) ? "Successfully added!" : "No records were added!")
				);
			}
			else if (queryID.equals("updateBook"))
			{
				book = new Book(Utils.toInteger(reqBody.get("id").toString()), reqBody.get("name").toString());
				queryStatus = bookController.update(book, context.getResourceProvider());
				result = getResponseBody(
					book,
					false,
					((queryStatus) ? "Successfully updated!" : "No records were updated!")
				);
			}
			else if (queryID.equals("deleteBook"))
			{
				book = new Book(Utils.toInteger(reqBody.get("id").toString()), reqBody.get("name").toString());
				queryStatus = bookController.delete(book, context.getResourceProvider());
				result = getResponseBody(
					book,
					false,
					((queryStatus) ? "Successfully deleted!" : "No records were deleted!")
				);
			}
			else if (queryID.equals("getBooks"))
			{
				books = bookController.getBooks(context.getResourceProvider());
				result = getResponseBody(
					books,
					false,
					((books.size() > 0) ? "Books successfully retrieved!" : "No books were found!")
				);
			}
			else if (queryID.equals("getBookById"))
			{
				books = bookController.getById(Utils.toInteger(req.getParameter("id")), context.getResourceProvider());
				result = getResponseBody(
					books,
					false,
					((books.size() > 0) ? "Books successfully retrieved!" : format("No books were found with id %s!", req.getParameter("id")))
				);
			}
			else
			{
				result = getResponseBody(
					null,
					true,
					format("The provided query '%s' doesn't exist.", queryID)
				);
			}
		} catch (Exception e) {
			result = getResponseBody(
				null,
				true,
				("An error accurred! -> " + ((e.getMessage() != null) ? e.getMessage() : e))
			);
			LOGGER.error("Error while executing bussiness logic [Result execute()]", e);
		}

		return Result.builder()
			.result(result)
			.build();
	}

	/**
	 * 
	 * @param Object data
	 * @param Boolean error
	 * @param String message
	 * @return Map result
	 */
	public Map<String, Object> getResponseBody(Object data, Boolean error, String message) {
		Map<String, Object> result = new HashMap<>();
		result.put("data", data);
		result.put("error", error);
		result.put("message", message);
		return result;
	};

	private JSONObject getRequestBody(HttpServletRequest request) throws IOException, ServletException {
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) Utils.toJSONObject(getRequestStringBody(request));
		} catch (Exception e) {
			LOGGER.error("Error while trying to parse JSONObject [Index -> getBody()]", e);
		}
		return jsonObject;
	}

	private String getRequestStringBody(HttpServletRequest request) {		
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