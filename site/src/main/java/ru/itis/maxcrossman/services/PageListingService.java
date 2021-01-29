package ru.itis.maxcrossman.services;

import ru.itis.maxcrossman.dto.PageForm;
import ru.itis.maxcrossman.models.Page;

import java.util.List;

public interface PageListingService extends ListingService<Page>{
    List<Page> getAllBoards();

    void savePage(PageForm form);
}
