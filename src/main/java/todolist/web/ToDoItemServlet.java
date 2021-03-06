package todolist.web;

import todolist.config.ObjectMapperConfiguration;
import todolist.domain.ToDoItem;
import todolist.service.ToDoItemService;
import todolist.transfer.CreateToDoItemRequest;
import todolist.transfer.UpdateToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/to-do-items")
public class ToDoItemServlet extends HttpServlet {
    private ToDoItemService toDoItemService = new ToDoItemService();

    //endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateToDoItemRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), CreateToDoItemRequest.class);
        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        Long - wrapper class for primitive data type long
        try {
            toDoItemService.deleteToDoItem(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UpdateToDoItemRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), UpdateToDoItemRequest.class);
        try {
            toDoItemService.updateToDoItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();
            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(toDoItems);
            resp.getWriter().print(response);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }
}