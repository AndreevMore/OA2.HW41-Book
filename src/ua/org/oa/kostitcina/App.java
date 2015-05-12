package ua.org.oa.kostitcina;
	
import java.io.*;

public class App {

	public static void main(String[] args) throws IOException {
		Library lib = new Library("file1.txt");
		lib.readFile();
		System.out.println(lib);
		Book aaa = new Book("kos", "andre", 88);
		lib.addBook(aaa);
		lib.deleteBook(new Book("Преступление и наказание","unkonown2",1999));
		lib.updateBook(new Book("kos", "andre", 88), new Book("koss", "andres", 888));

	}
}
