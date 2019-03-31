package pl.leman.demoapp.domain;

import org.springframework.stereotype.Component;
import pl.leman.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
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
        return new ProductResponseDto(product.getId(), product.getName());
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
        Product product = new Product(id, productRequest.getName(), createdAt);

        //zapis
        productRepository.save(product);

        ///przemapowac na resppnse i zwrocic
        return new ProductResponseDto(
                product.getId(),
                product.getName()
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
                product.getName()
        );
    }

    @Override
    public ProductResponseDto delete(String id) {
        Product product = productRepository.delete(id);

        return new ProductResponseDto(product.getId(), product.getName());
    }
}