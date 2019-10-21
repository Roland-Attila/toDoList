package todolist;

import todolist.domain.ToDoItem;
import todolist.persistance.ToDoItemRepository;
import todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        CreateToDoItemRequest request = new CreateToDoItemRequest();
        request.setDescription("Learn JDBC");
        request.setDeadline(LocalDate.now().plusWeeks(1));
        ToDoItemRepository toDoItemRepository = new ToDoItemRepository();
//        toDoItemRepository.createToDoItem(request);

//        toDoItemRepository.updateToDoUpdate(true, 1);
//        toDoItemRepository.deleteToDoItem(1);
        List<ToDoItem> toDoItems = toDoItemRepository.getToDoItems();
        System.out.println(toDoItems);
    }
}
