package ru.itis.maxcrossman.repositories;

import ru.itis.maxcrossman.models.Page;
import java.util.List;

public interface PagesRepository extends CrudRepository<Page> {
    List<Page> findAllBoards();
}
