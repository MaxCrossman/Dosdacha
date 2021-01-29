package ru.itis.maxcrossman.repositories;

import ru.itis.maxcrossman.models.Post;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post> {
    Long getNewId();
    List<Post> findByBoardId(Long id);
    List<Post> findByThreadId(Long id);
    List<Post> findByAuthorId(Long id);
}
