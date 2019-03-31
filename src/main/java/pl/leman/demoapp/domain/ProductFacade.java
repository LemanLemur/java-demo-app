package pl.leman.demoapp.domain;

public interface ProductFacade {
    //get

    ProductResponseDto findById(String id);

    //create

    ProductResponseDto create(ProductRequestDto productRequest);

    //update

    ProductResponseDto update(String id, ProductRequestDto productRequest);

    //delete

    ProductResponseDto delete(String id);

}