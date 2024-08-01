package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Flow;

@Entity
@Table(name = "TB_BOOK")
public class BookStoreModel implements Serializable {

    // id de controle
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // nullable = false o campo não pode ser nulo , e unique tem que ser único -_-
    @Column(nullable = false, unique = true)
    private String title;

    // relacionamento muitos para um
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;

    // Relacionamento muito para muitos
    @ManyToMany
    @JoinTable( // criando a tabela auxiliar, pois, é n para n
            name = "tb_book_author", // nome da nova tabela
            joinColumns = @JoinColumn(name = "book_id"), // chave primaria e foreign key
            inverseJoinColumns = @JoinColumn(name = "author_id") // chave primaria e foreign key
    )
    private Set<AuthorModel> authors = new HashSet<>();

    // Relacionamento um para um
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReviewModel reviewModel;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    public ReviewModel getReviewModel() {
        return reviewModel;
    }

    public void setReviewModel(ReviewModel reviewModel) {
        this.reviewModel = reviewModel;
    }
}
