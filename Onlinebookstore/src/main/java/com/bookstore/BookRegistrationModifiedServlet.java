// GEDION MEKBEB AFEWORK 
// UGR/2160/14
package com.bookstore;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/addBook")
public class BookRegistrationModifiedServlet {
    private static final String query = "INSERT into Books(Title, Author, Price) values(?, ?, ?)";
//    @Setter
    private DBConnectionManager db;
//
//    @GetMapping
//    @ResponseBody
    public String showForm() {
        return "<html><body>"
                + "<h2>Book Registration Form</h2>"
                + "<form method='post' action='/Onlinebookstore/books/addBooks'>"
                + "Title: <input type='text' name='book_title'><br>"
                + "Author: <input type='text' name='book_author'><br>"
                + "Price: <input type='text' name='book_price'><br>"
                + "<input type='submit' value='Add Task'>"
                + "</form>"
                + "</body></html>";
    }

    @PostMapping
    @ResponseBody
    public String addTask(@RequestParam String description, @RequestParam String status, @RequestParam String dueDate) {
        db.openConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, description);
            ps.setString(2, status);
            ps.setString(3, dueDate.toString());
            int count = ps.executeUpdate();
            db.closeConnection();
            if (count == 1) {
                return "Book Registration Successful";
            } else {
                return "Book Registration Failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Book Registration Failed";
        } finally {
            db.closeConnection();
        }
    }
}
