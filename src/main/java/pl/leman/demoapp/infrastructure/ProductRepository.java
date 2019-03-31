package pl.leman.demoapp.infrastructure;

import pl.leman.demoapp.domain.Product;
import pl.leman.demoapp.domain.ProductResponseDto;

public interface ProductRepository {

    void save(Product product);

    Product findById(String id);

    Product update(String id, Product product);

    Product delete(String id);

}
