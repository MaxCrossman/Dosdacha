package ru.itis.maxcrossman.services;

import ru.itis.maxcrossman.dto.PostDto;
import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.models.Post;
import ru.itis.maxcrossman.repositories.PagesRepository;
import ru.itis.maxcrossman.repositories.PostsRepository;
import ru.itis.maxcrossman.repositories.UsersRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostListingServiceImpl implements PostListingService {

    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private PagesRepository pagesRepository;

    public PostListingServiceImpl(PostsRepository postsRepository,
                                  UsersRepository usersRepository,
                                  PagesRepository pagesRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
        this.pagesRepository = pagesRepository;
    }

    @Override
    public List<PostDto> getByBoard(Page page) {
        return convert(postsRepository.findByBoardId(page.getId()));
    }

    @Override
    public List<PostDto> getByThreadId(Long id) {
        return convert(postsRepository.findByThreadId(id));
    }

    @Override
    public List<PostDto> getByUserId(Long id) {
        return convert(postsRepository.findByAuthorId(id));
    }

    @Override
    public Long post(Post post) {
        Long newId = postsRepository.getNewId();
        post.setIsThread(true);
        post.setThreadId(newId);
        postsRepository.save(post);
        return newId;
    }

    @Override
    public String comment(Post post) {
        post.setIsThread(false);
        postsRepository.save(post);
        return post.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm"));
    }

    @Override
    public List<PostDto> getAll() {
        return convert(postsRepository.findAll());
    }

    private List<PostDto> convert(List<Post> posts) {
        return posts.stream().map(x -> new PostDto(
                usersRepository.findById(x.getAuthorId()).get().getFirstName() + " " +
                        usersRepository.findById(x.getAuthorId()).get().getLastName(),
                x.getName(),
                x.getValue(),
                x.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                "/board" +
                        pagesRepository.findById(x.getBoardId()).get().getAddress() +
                        "?thread=" + x.getThreadId() +
                        "#" + x.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm")))
        ).collect(Collectors.toList());
    }
}
