package hw6Revised;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hw6Revised.Textbook.*;
import uca.mis3339.OracleWrapper;
public class BookJSON extends HttpServlet{
	private static final long serialVersionUID = 7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			OracleWrapper.prepareStatement("SELECT isbn, pages, year, edition, author, title, course FROM TEXTBOOKS");
			ResultSet rs = OracleWrapper.queryDB();
			out.println("{\"textbooks\":[");
			ArrayList<Textbook> library = new ArrayList();
			while (rs.next()) {
				Textbook.TextbookBuilder.setBuilderIsbn(rs.getString(1));
				TextbookBuilder.setBuilderPages(Integer.valueOf(String.valueOf( rs.getObject(2)))); 
				TextbookBuilder.setBuilderYear(Integer.valueOf(String.valueOf( rs.getObject(3))));
				Textbook.TextbookBuilder.setBuilderEdition(rs.getString(4));
				Textbook.TextbookBuilder.setBuilderAuthor(rs.getString(5));
				Textbook.TextbookBuilder.setBuilderTitle(rs.getString(6));
				Textbook.TextbookBuilder.setBuilderCourse(rs.getString(7));
				library.add(Textbook.TextbookBuilder.build());
			} // end while

			for (Textbook eachOne: library) {
				out.println(eachOne.toJSON());
				if (library.indexOf(eachOne) != library.size()-1) {
					out.println(",");
				}//end if
			}//end for
			out.println("]}");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // end catch
	} // end doGet
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // end doPost
} // end BookJSON