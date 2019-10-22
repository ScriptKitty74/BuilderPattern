package hw6Revised;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hw6Revised.Textbook.TextbookBuilder;
import uca.mis3339.OracleWrapper;

public class Textbook {
	public static class TextbookBuilder {
		private static String builderIsbn;
		private static int builderPages;
		private static int builderYear;
		private static String builderEdition;
		private static String builderAuthor;
		private static String builderTitle;
		private static String builderCourse;

		public static Textbook build() {
			Textbook book = new Textbook(builderIsbn, builderPages, builderYear, builderEdition, builderAuthor, builderTitle, builderCourse);
			return book;
		}//end build
		public static void setBuilderIsbn(String builderIsbn) {
			TextbookBuilder.builderIsbn = builderIsbn;
		}// end builderIsbn
		public static void setBuilderPages(int builderPages) {
			TextbookBuilder.builderPages = builderPages;
		}// end builderPage
		public static void setBuilderYear(int builderYear) {
			TextbookBuilder.builderYear = builderYear;
		}//end builderYear
		public static void setBuilderEdition(String builderEdition) {
			TextbookBuilder.builderEdition = builderEdition;
		}// end builderEdition
		public static void setBuilderAuthor(String builderAuthor) {
			TextbookBuilder.builderAuthor = builderAuthor;
		}// end builderAuthor
		public static void setBuilderTitle(String builderTitle) {
			TextbookBuilder.builderTitle = builderTitle;
		}// end builderTitle
		public static void setBuilderCourse(String builderCourse) {
			TextbookBuilder.builderCourse = builderCourse;
		}// end builderCourse
	}// end builder

	private final String isbn;
	private final int pages;
	private final int year;
	private final String edition;
	private final String author;
	private final String title;
	private final String course;
	{
	ArrayList<Textbook> books = new ArrayList<Textbook>();
	try {
		OracleWrapper.prepareStatement("SELECT * FROM TEXTBOOKS");
		ResultSet rs = OracleWrapper.queryDB();					
		while (rs.next()) {
			TextbookBuilder.setBuilderIsbn(String.valueOf( rs.getObject(1))); 
			TextbookBuilder.setBuilderPages(Integer.valueOf(String.valueOf( rs.getObject(2)))); 
			TextbookBuilder.setBuilderYear(Integer.valueOf(String.valueOf( rs.getObject(3)))); 
			TextbookBuilder.setBuilderEdition(String.valueOf( rs.getObject(4))); 
			TextbookBuilder.setBuilderAuthor(String.valueOf( rs.getObject(5))); 
			TextbookBuilder.setBuilderTitle(String.valueOf( rs.getObject(6))); 
			TextbookBuilder.setBuilderCourse(String.valueOf( rs.getObject(7))); 
			books.add(TextbookBuilder.build());
		} // end while
		rs.close();
		for (Textbook eachOne: books) {
			System.out.println(eachOne.toString());
		}//end for
	}/*end try*/ catch (SQLException e) {
		System.out.println(e.getMessage());
	} // end catch
	}
	public Textbook(String isbn, int pages, int year, String edition, String author, String title, String course) {
		this.isbn = isbn;
		this.pages = pages;
		this.year = year;
		this.edition = edition;
		this.author = author;
		this.title = title;
		this.course = course;
	}//end ctor
	
	public String toJSON() {
		return ("{\"isbn\":\"" + this.isbn + "\", " +
				"\"pages\":\"" + this.pages + "\", " +
				"\"year\":\"" + this.year + "\", " +
				"\"edition\":\"" + this.edition + "\", " +
				"\"author\":\"" + this.author + "\", " +
				"\"title\":\"" + this.title + "\", " +
				"\"course\":\"" + this.course + "\"}");
	}// end toJSON
	
	public String toHTML() {
		return "<tr><td>isbn: </td><td>" + this.isbn + "</td></tr>"
				+ "<tr><td>pages: </td><td>" + this.pages + "</td></tr>"
				+ "<tr><td>year: </td><td>" + this.year + "</td></tr>"
				+ "<tr><td>edition: </td><td>" + this.edition + "</td></tr>"
				+ "<tr><td>author: </td><td>" + this.author + "</td></tr>"
				+ "<tr><td>title: </td><td>" + this.title + "</td></tr>"
				+ "<tr><td>course: </td><td>" + this.course + "</td></tr>";
	}// end toHTML
}//end Textbook