package pl.leman.demoapp.infrastructure;

import org.springframework.stereotype.Repository;
import pl.leman.demoapp.domain.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
class InMemoryProductRepository implements ProductRepository {

    private  final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(String id) {
        return products.get(id);
    }

    @Override
    public Collection<Product> findAll() {
        return products.values();
    }

    @Override
    public Product update(String id, Product product) {
        products.replace(id, product);
        return products.get(id);
    }

    @Override
    public Product delete(String id) {
       return products.remove(id);
    }


}