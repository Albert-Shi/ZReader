package com.shishuheng.zreader.domin.book;

import java.io.File;
import java.util.List;

public class TextBook extends Book {
    //字符编码
    public enum CodedFormat {GBK, GB18130, GB2312, UTF8}

    private CodedFormat codedFormat;

    //书签列表
    private List<Integer> BookMark;

    public CodedFormat getCodedFormat() {
        return codedFormat;
    }

    public void setCodedFormat(CodedFormat codedFormat) {
        this.codedFormat = codedFormat;
    }

    public List<Integer> getBookMark() {
        return BookMark;
    }

    public void setBookMark(List<Integer> bookMark) {
        BookMark = bookMark;
    }

    public TextBook(File bookFile) {
        super(bookFile);
    }

    public TextBook() {
    }
}
