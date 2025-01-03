// GEDION MEKBEB AFEWORK 
// UGR/2160/14
package com.bookstore;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@RequestMapping("/searchBooks")
public class SearchBooksServlet {
    private static final String query = "SELECT * from Books WHERE Title like ?";

    @Setter
    private DBConnectionManager db;

    @GetMapping
    @ResponseBody
    public String searchTasks(@RequestParam("Title") String description) {
        StringBuilder htmlResponse = new StringBuilder();
        db.openConnection();
        Connection connection = db.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + description + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> books = new ArrayList<Book>();
            while (rs.next()) {
                books.add(new Book());
            }

            htmlResponse.append("<html><head> </head><body>");
            htmlResponse.append("<h2>Showing search results for: '" + Title + "'</h2>");
            htmlResponse.append("<table style='border: 2px solid black; border-spacing: 10px;'>");
            htmlResponse.append("<tr>");
            htmlResponse.append("<th>BOOK ID</th>");
            htmlResponse.append("<th>TITLE</th>");
            htmlResponse.append("<th>AUTHOR</th>");
            htmlResponse.append("<th>PRICE</th>");
            htmlResponse.append("</tr>");
            for (Book book : books) {
                htmlResponse.append("<tr>");
                htmlResponse.append("<td>").append(books.id).append("</td>");
                htmlResponse.append("<td>").append(books.title).append("</td>");
                htmlResponse.append("<td>").append(books.author).append("</td>");
                htmlResponse.append("<td>").append(books.price).append("</td>");
                htmlResponse.append("</tr>");
            }
            htmlResponse.append("</table>");
            htmlResponse.append("</body></html>");
        } catch (SQLException se) {
            se.printStackTrace();
            htmlResponse.append("<h1>").append(se.getMessage()).append("</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            htmlResponse.append("<h1>").append(e.getMessage()).append("</h1>");
        }

        htmlResponse.append("</body></html>");
        db.closeConnection();
        return htmlResponse.toString();
    }
}
