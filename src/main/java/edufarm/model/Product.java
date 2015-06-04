package edufarm.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by jazzbach on 15. 4. 18.
 */
@Entity
@PrimaryKeyJoinColumn(name = "article_id")
public class Product extends Article {

    @NotNull
    @Min(1000)
    private int price;

    @NotNull
    @Min(1000)
    private int discountedPrice;

    @NotNull
    private boolean isOnSale;

    @NotNull
    @Min(0)
    private int deliveryCharge;

    @NotNull
    @Min(0)
    private int chargePerItem;

    @NotNull
    @Min(0)
    private int stock;

    @NotNull
    @Pattern(regexp = "[\\d]{13}")
    private String serialNumber;

    @NotNull
    @Size(min = 3, max = 20)
    private String origin;

    @NotNull
    @Size(min = 3, max = 20)
    private String brand;

    @NotNull
    @Size(min = 0)
    private float weight;

    @NotNull
    @Size(min = 1, max = 10)
    private String measurement;

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public int getChargePerItem() {
        return chargePerItem;
    }

    public void setChargePerItem(int chargePerItem) {
        this.chargePerItem = chargePerItem;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
