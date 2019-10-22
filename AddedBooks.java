package hw6Revised;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hw6.Textbook.*;
import uca.mis3339.OracleWrapper;
public class AddedBooks extends HttpServlet {
	private static final long serialVersionUID = 5L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			OracleWrapper.prepareStatement("SELECT isbn, pages, year, edition, author, title, course FROM TEXTBOOKS");
			ResultSet rs = OracleWrapper.queryDB();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title> servlet</title></head><body>");
			out.println("<form action=\"/html\" method=\"post\">");
			out.println("<table border=\"0\" cellpadding=\"5\">");
			out.println("<tr><td>The isbn:</td><td><input type=\"textbox\" name=\"isbn\"></td></tr>");
			out.println("<tr><td>Number of pages:</td><td><input type=\"textbox\" name=\"pages\"></td></tr>");
			out.println("<tr><td>Year published:</td><td><input type=\"textbox\" name=\"year\"></td></tr>");
			out.println("<tr><td>The edition:</td><td><input type=\"textbox\" name=\"edition\"></td></tr>");
			out.println("<tr><td>The author:</td><td><input type=\"textbox\" name=\"author\"></td></tr>");
			out.println("<tr><td>The title:</td><td><input type=\"textbox\" name=\"title\"></td></tr>");
			out.println("<tr><td>The course:</td><td><input type=\"textbox\" name=\"course\"></td></tr>");
			out.println("<tr><td colspan=\"2\"><input type=\"submit\" value=\"Register Textbook\"></td></tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body></html>");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // end catch
	} // end doGet
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String isbn = request.getParameter("isbn");
			Integer pages = (Integer.valueOf(String.valueOf(request.getParameter("pages"))));
			Integer year = (Integer.valueOf(String.valueOf(request.getParameter("year"))));
			String edition = request.getParameter("edition");
			String author = request.getParameter("author");
			String title = request.getParameter("title");
			String course = request.getParameter("course");
			OracleWrapper.prepareStatement("INSERT INTO TEXTBOOKS (isbn, pages, year, edition, author, title, course) VALUES (?,?,?,?,?,?,?)", isbn, pages, year, edition, author, title, course);
			OracleWrapper.updateDB();

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}//end catch
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean register;
		if (register = true) {
			out.println("<html><body><title>Registration Successful!</title>");
			out.println("<h1>Registration successful your book has been added to the library!</h1>");
		} else {
			out.println("<html><body><title>Registration Unsuccessful!</title>");
			out.println("<h1>Either a book with the same isbn exist or you incorrectly filled out the form!</h1>");
		}// end else
		out.println("</body></html>");
	}//end doPost
}//end AddedBooks