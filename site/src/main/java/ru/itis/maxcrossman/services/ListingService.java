package ru.itis.maxcrossman.services;

import java.util.List;

public interface ListingService<T> {
    List<T> getAll();
}
