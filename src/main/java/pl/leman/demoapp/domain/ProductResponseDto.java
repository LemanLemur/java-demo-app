package pl.leman.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProductResponseDto {

    private final String id;
    private final String name;
    private final Price price;
    private final Image image;
    private final Tag tag;

    @JsonCreator
    public ProductResponseDto(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("price") Price price,
                              @JsonProperty("image") Image image, @JsonProperty("tag") Tag tag) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDto that = (ProductResponseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(image, that.image) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, tag);
    }

    public Image getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}