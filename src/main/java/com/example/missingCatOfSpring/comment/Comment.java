package com.example.missingCatOfSpring.comment;

import com.example.missingCatOfSpring.cat.Cat;
import com.example.missingCatOfSpring.owner.Owner;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String body;

    @ManyToMany
    @JoinTable(
            name = "cats",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id")
    )
    Set<Cat> cats = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Cat> getCats() {
        return cats;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
