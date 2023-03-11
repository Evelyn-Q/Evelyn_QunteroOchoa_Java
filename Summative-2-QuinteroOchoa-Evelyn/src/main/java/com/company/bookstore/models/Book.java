package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Integer bookId;
    private String isbn;
    private LocalDate publishDate;

    @Column(name="author_id")
    private Integer authorId;
    private String title;
    @Column(name="publisherId")
    private Integer publisherId;
    @PositiveOrZero
    private BigDecimal price;

    public Integer getId() {
        return bookId;
    }

    public void setId(int id) {
        this.bookId = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getPublishDate(), book.getPublishDate()) &&
                Objects.equals(getAuthorId(), book.getAuthorId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublisherId(), book.getPublisherId()) &&
                Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getPublishDate(),
                getAuthorId(), getTitle(), getPublisherId(), getPrice());
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", authorId='" + authorId + '\'' +
                ", title='" + title + '\'' +
                ", publisherId='" + publisherId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
