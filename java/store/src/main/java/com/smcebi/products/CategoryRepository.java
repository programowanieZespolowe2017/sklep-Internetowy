package com.smcebi.products;

import org.springframework.data.repository.CrudRepository;

/**
 * 4/30/2017 12:31 AM
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findOneByName(String name);
}
