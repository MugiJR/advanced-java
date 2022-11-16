package serialization;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book() {}

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString() {
        return "Title:" + title + "\nAuthor:" + author + "\nYear:" + year + "\nPages:" + pages;
    }
}
