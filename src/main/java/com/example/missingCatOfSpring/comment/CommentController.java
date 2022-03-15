package com.example.missingCatOfSpring.comment;


import com.example.missingCatOfSpring.cat.Cat;
import com.example.missingCatOfSpring.cat.CatRepository;
import com.example.missingCatOfSpring.owner.Owner;
import com.example.missingCatOfSpring.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping
    List<Comment> getComment() {
        return commentRepository.findAll();
    }

    @GetMapping("{commentId}")
    public Optional<Comment> getSpecificComment(@PathVariable Long commentId) {
        return commentRepository.findById(commentId);
    }

    @PostMapping
    Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{commentId}/cat/{catId}")
    Comment addCatToComment(
            @PathVariable Long commentId,
            @PathVariable Long catId
    ) {
        Comment comment = commentRepository.findById(commentId).get();
        Cat cat = catRepository.findById(catId).get();
        comment.cats.add(cat);
        return commentRepository.save(comment);
    }

    @PutMapping("/{commentId}/owner/{ownerId}")
    Comment assignOwnerToComment(
            @PathVariable Long commentId,
            @PathVariable Long ownerId
    ) {
        Comment comment = commentRepository.findById(commentId).get();
        Owner owner = ownerRepository.findById(ownerId).get();
        comment.setOwner(owner);
        return commentRepository.save(comment);
    }
}

