package chapter7.mappedsuperclass.attributeoverrides;

//@Entity
public class Seller extends BaseEntity {
    //ID 상속
    //NAME 상속
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
