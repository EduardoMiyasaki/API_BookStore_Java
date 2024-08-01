package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PUBLISHER")
public class PublisherModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "publisher" , fetch = FetchType.LAZY) // o mapeamento foi feito pela injeção feita em bookStoreModel
    private Set<BookStoreModel> books = new HashSet<>();

    // ao invés de set poderia ser List<>
    // mas poderia ter problema pois tem muitos relacionamentos

    // FetchType.LAZY = rota que vai buscar de maneira lenta é so vai trazer o que voce procura

    // FetchType.EAGER = rota que vai buscar rápido mas busca tudo,pode gerar loops infinitos

    // @JsonProperty = para não dar erro


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookStoreModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookStoreModel> books) {
        this.books = books;
    }
}
