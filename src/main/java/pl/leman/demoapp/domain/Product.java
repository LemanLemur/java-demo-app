package pl.leman.demoapp.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Product {

    private final String id;
    private String name;
    private final LocalDateTime createdAt;
    private final Price price;
    private final Image image;
    private final Tag tag;

    Product(String id, String name, LocalDateTime createdAt, Price price, Image image, Tag tag) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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

    public Image getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name){this.name = name;}

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(createdAt, product.createdAt) &&
                Objects.equals(price, product.price) &&
                Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, price, image);
    }

}