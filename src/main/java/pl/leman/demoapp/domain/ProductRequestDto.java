package pl.leman.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto {

    private final String name;
    private final Price price;
    private final Image image;
    private final Tag tag;

    @JsonCreator
    public ProductRequestDto(@JsonProperty("name") String name, @JsonProperty("amount") String amount,
                             @JsonProperty("currency") String currency, @JsonProperty("imageurl") String url,
                             @JsonProperty("tag")String tag) {
        this.name = name;
        this.tag = new Tag(tag);
        this.price = new Price( amount, currency);
        this.image = new Image(url);
    }

    public Tag getTag() {
        return tag;
    }

    public Image getImage() {
        return image;
    }

    public Price getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", tag=" + tag +
                '}';
    }

    public boolean isValid(){
        return name != null && !name.isBlank() && price != null && !price.getCurrency().isBlank() && price.getAmount().isBlank()
                && !image.getUrl().isBlank() && image.getUrl() != null && !tag.getName().isBlank() && tag.getName() != null;
    }
}
