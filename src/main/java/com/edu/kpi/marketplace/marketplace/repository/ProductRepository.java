package com.edu.kpi.marketplace.marketplace.repository;

import com.edu.kpi.marketplace.marketplace.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryId(String categoryId);

    @Query("""
            {
              'compatibility': {
                $elemMatch: {
                  make: ?0,
                  model: ?1,
                  year: ?2
                }
              }
            }
            """)
    List<Product> findByCompatibility(String make, String model, Integer year);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Product> search(String query);

}
