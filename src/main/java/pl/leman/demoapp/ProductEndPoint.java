package pl.leman.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.leman.demoapp.domain.ProductFacade;
import pl.leman.demoapp.domain.ProductRequestDto;
import pl.leman.demoapp.domain.ProductResponseDto;

@RestController
@RequestMapping("/products")
class ProductEndpoint {

    private final ProductFacade productFacade;

    @Autowired
    ProductEndpoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/{id}")
    ProductResponseDto getProduct(@PathVariable("id") String id) {
        try {
            return productFacade.findById(id);
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @PostMapping
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            return productFacade.create(productRequestDto);
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    ProductResponseDto deleteProduct(@PathVariable("id") String id) {
        try {
            return productFacade.delete(id);
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable("id") String id, ProductRequestDto productRequestDto) {
        try {
            return productFacade.update(id, productRequestDto);
        } catch (NullPointerException e) {
            throw e;
        }
    }

}