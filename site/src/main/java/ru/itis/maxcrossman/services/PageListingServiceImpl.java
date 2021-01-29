package ru.itis.maxcrossman.services;

import ru.itis.maxcrossman.dto.PageForm;
import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.repositories.PagesRepository;

import java.util.List;

public class PageListingServiceImpl implements PageListingService {

    private PagesRepository pagesRepository;

    public PageListingServiceImpl(PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    @Override
    public List<Page> getAll() {
        return pagesRepository.findAll();
    }

    @Override
    public List<Page> getAllBoards() {
        return pagesRepository.findAllBoards();
    }

    @Override
    public void savePage(PageForm form) {
        Page page = new Page(null,
                form.getName(),
                "/" + form.getAddress(),
                true,
                true);
        pagesRepository.save(page);
    }
}
