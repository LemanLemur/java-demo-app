package pl.leman.demoapp.infrastructure;

import pl.leman.demoapp.domain.Product;

import java.util.Collection;

public interface ProductRepository {

    void save(Product product);

    Product findById(String id);

    Collection<Product> findAll();

    Product update(String id, Product product);

    Product delete(String id);

}
