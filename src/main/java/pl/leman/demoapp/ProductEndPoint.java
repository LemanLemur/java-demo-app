package pl.leman.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.leman.demoapp.domain.ProductFacade;
import pl.leman.demoapp.domain.ProductListResponseDto;
import pl.leman.demoapp.domain.ProductRequestDto;

@RestController
@RequestMapping("/products")
class ProductEndPoint {

    private final ProductFacade productFacade;

    @Autowired
    ProductEndPoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/{id}")
    ResponseEntity getProduct(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(productFacade.findById(id), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity createProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            return new ResponseEntity<>(productFacade.create(productRequestDto), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteProduct(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(productFacade.delete(id), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity updateProduct(@PathVariable("id") String id, ProductRequestDto productRequestDto) {
        try {
            return new ResponseEntity<>(productFacade.update(id, productRequestDto), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity getProducts() {
        try {
            return new ResponseEntity<>(productFacade.findAll(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}