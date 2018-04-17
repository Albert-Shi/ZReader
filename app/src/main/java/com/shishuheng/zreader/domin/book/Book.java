package com.shishuheng.zreader.domin.book;

import java.io.File;
import java.util.List;

/**
 * Created by shishuheng on 2018/3/31.
 */

public class Book {
    private String title;
    private String authorName;
    private String bookSize;
    private List<String> catalogue;
    private Integer totalPageNumber;
    private Integer page;
    private File bookFile;

    public Book(File bookFile) {
        setBookFile(bookFile);
    }

    public Book() {
    }

    public File getBookFile() {
        return bookFile;
    }

    public void setBookFile(File bookFile) {
        this.bookFile = bookFile;
        String title = bookFile.getName();
        if (title.lastIndexOf('.') != -1) {
            title = bookFile.getName().substring(0, bookFile.getName().lastIndexOf('.'));
        }
        setTitle(title);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    public List<String> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<String> catalogue) {
        this.catalogue = catalogue;
    }

    public Integer getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(Integer totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
