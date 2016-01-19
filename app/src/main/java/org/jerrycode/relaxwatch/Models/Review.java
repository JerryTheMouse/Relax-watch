package org.jerrycode.relaxwatch.Models;

/**
 * Created by Shady Atef (shadyoatef@gmail.com) on 1/19/16.
 */
public class Review {
    String author;
    String content;

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return getContent().concat("\n").concat("by : ").concat(getAuthor());
    }
}
