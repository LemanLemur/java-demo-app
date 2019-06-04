package pl.leman.demoapp.domain;

import org.springframework.stereotype.Component;
import pl.leman.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
class ProductFacadeImpl implements ProductFacade {

    private final ProductRepository productRepository;

    public ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto findById(String id){
        Product product = productRepository.findById(id);
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getTag());
    }

    @Override
    public ProductListResponseDto findByTag(String tag) {
        Collection<Product> product = productRepository.findAll();
        List<ProductResponseDto> productList = new ArrayList<>();
        for(Product product1: product){
            if(product1.getTag().equals(tag)) {
                productList.add(new ProductResponseDto(product1.getId(), product1.getName(), product1.getPrice(), product1.getImage(),
                        product1.getTag()));
            }
        }
        return new ProductListResponseDto(productList);
    }

    @Override
    public ProductListResponseDto findAll() {
        Collection<Product> product = productRepository.findAll();
        List<ProductResponseDto> productList = new ArrayList<>();
        for(Product product1: product){
            productList.add(new ProductResponseDto(product1.getId(), product1.getName(), product1.getPrice(), product1.getImage(),
                    product1.getTag()));
        }
        return new ProductListResponseDto(productList);
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        //walidacja
        if(!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        //tworzenie
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createdAt, productRequest.getPrice(), productRequest.getImage(),
                productRequest.getTag());

        //zapis
        productRepository.save(product);

        ///przemapowac na resppnse i zwrocic
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImage(),
                product.getTag()
        );

    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequest) {


        //tworzenie
        Product product = productRepository.findById(id);
        product.setName(productRequest.getName());

        //aktualizacja
        product = productRepository.update(id, product);

        ///przemapowac na resppnse i zwrocic
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImage(),
                product.getTag()
        );
    }

    @Override
    public ProductResponseDto delete(String id) {
        Product product = productRepository.delete(id);

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getTag());
    }
}
