package hw6Revised;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uca.mis3339.OracleWrapper;
import javax.servlet.http.HttpServlet;
public class BookHTML extends HttpServlet {
	private static final long serialVersionUID =7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			List<Textbook> library = new ArrayList();
			OracleWrapper.prepareStatement("SELECT isbn, pages, year, edition, author, title, course FROM textbooks");
			ResultSet rs = OracleWrapper.queryDB();
			while (rs.next()) {
				library.add(new Textbook(rs.getString(1), Integer.valueOf(String.valueOf( rs.getObject(2))), Integer.valueOf(String.valueOf( rs.getObject(3))),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));

			}//end while
			out.println("<html><head><title>User Report</title></head><body>");
			out.println("<table border=\"0\" cellpadding=\"5\">");
			for (Textbook eachOne: library) {
				out.println(eachOne.toHTML());
			} // end for
			out.println("</table></body></html>");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // end catch
	} // end doGet

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} // end doPost
}//end BookHTML
