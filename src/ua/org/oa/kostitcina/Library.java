package ua.org.oa.kostitcina;

import java.io.*;
import java.util.*;

public class Library {

	private List<Book> library = new ArrayList<>();
	private String pathToFile;

	public Library(String pathToFile) {
		this.setPathToFile(pathToFile);
	}

	public void addBook(Book book) throws IOException {
		FileOutputStream fw = new FileOutputStream(pathToFile, true);
		
		String title = book.getTitle();
		String author = book.getAuthor();
		int year = book.getYear();
		String s = System.lineSeparator() + title + ";" + author + ";" + year;
		fw.write(s.getBytes());
		fw.close();
	}

	public void deleteBook(Book book) throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(pathToFile));
		StringBuilder temp = new StringBuilder();

		String s = book.getTitle() + ";" + book.getAuthor() + ";"
				+ book.getYear();

		while (fr.ready()) {
			String line = fr.readLine();
			if (s.equals(line)) {
				continue;
			}
			temp.append(line).append(System.lineSeparator());
		}
		fr.close();
		FileOutputStream fw = new FileOutputStream(pathToFile, false);
		String text;
		if (temp.length() > 0) {
			text = temp.substring(0, temp.length() - System.lineSeparator().length());
		} else {
			text = "";
		}
		fw.write(text.getBytes());
		fw.close();
	}

	public void updateBook(Book oldBook, Book newBook) throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(pathToFile));
		StringBuilder temp = new StringBuilder();

		String oldB = oldBook.getTitle() + ";" + oldBook.getAuthor() + ";"
				+ oldBook.getYear();
		String newB = newBook.getTitle() + ";" + newBook.getAuthor() + ";"
				+ newBook.getYear();
		while (fr.ready()) {
			String line = fr.readLine();
			if (oldB.equals(line)) {
				temp.append(newB).append(System.lineSeparator());
			} else {
				temp.append(line).append(System.lineSeparator());
			}
		}
		fr.close();
		FileOutputStream fw = new FileOutputStream(pathToFile, false);
		String text;
		if (temp.length() > 0) {
			text = temp.substring(0, temp.length() - System.lineSeparator().length());
		} else {
			text = "";
		}
		fw.write(text.getBytes());
		fw.close();
	}

	public void readFile() throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(pathToFile));
		while (fr.ready()) {
			String line = fr.readLine();
			//если строка пустая - не обрабатываем её
			if (line.isEmpty()) {
				continue;
			}
			// разбиваем строку по разделителю ";"
			String[] bookInfo = line.split(";");
			String title = bookInfo[0];
			String author = bookInfo[1];
			int year = Integer.parseInt(bookInfo[2]);
			Book book = new Book(title, author, year);
			//добавляем книгу в библиотеку
			library.add(book);
		}
		fr.close();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < library.size(); i++) {
			sb.append(library.get(i)).append(System.lineSeparator());
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - System.lineSeparator().length());
		} else {
			return "";
		}
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}
}
