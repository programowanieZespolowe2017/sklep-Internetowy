package com.smcebi.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * 4/30/2017 12:21 AM
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findByName(String name);
    Set<Product> findByCategoryName(String category);
    List<Product> findTop3ByOrderByIdDesc();

    @Query("select p from OrderEntry oe right outer join oe.product p group by p order by count(oe) desc")
    List<Product> findTopSelling();
}
