package ru.itis.maxcrossman.services;

import ru.itis.maxcrossman.dto.PostDto;
import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.models.Post;

import java.util.List;

public interface PostListingService extends ListingService<PostDto> {
    List<PostDto> getByBoard(Page page);
    List<PostDto> getByThreadId(Long id);
    List<PostDto> getByUserId(Long id);

    Long post(Post post);
    String comment(Post post);
}
