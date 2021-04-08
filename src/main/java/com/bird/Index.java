package com.bird;

import com.bird.controller.BookController;
import com.bird.dto.Result;
import com.bird.model.Book;

import org.bonitasoft.web.extension.rest.RestAPIContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

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
        // Query Parameter
        String queryID = req.getParameter("queryID");
        // Response body
        Map<String, Object> body = new HashMap<>();
        // Entities
        Book book = new Book();

        LOGGER.debug(format("TESTING:  %s", req.getParameter("name")));

        /*
        switch (queryID) {
            case "addBook":
                LOGGER.debug(format("Adding a new book:  %s", req.getParameter("name")));
                book = new Book(getRandomNumber(100000, 999999), req.getParameter("name"));
                bookController.save(book, context.getResourceProvider());
                body.put("data", book);
                // =====================================================================
                break;
            case "updateBook":
                LOGGER.debug(format("Updating the book:  %s", req.getParameter("id")));
                book = new Book(Integer.parseInt(req.getParameter("id")), req.getParameter("name"));
                bookController.update(book, context.getResourceProvider());
                body.put("data", book);
                break;
            case "deleteBook":
                LOGGER.debug(format("Deleting a book:  %s", req.getParameter("id")));
                break;
            case "getBooks":
                LOGGER.debug(format("Getting books:  %s", req.getParameter("queryID")));
                break;
            default:
                LOGGER.debug(format("This query doesn't exists:  %s", req.getParameter("queryID")));
                break;
        }*/

        return Result.builder()
            .body(body)
            .build();
    }
}