package edufarm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by jazzbach on 15. 4. 20.
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("id")
    private Set<LineItem> lineItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Purchase",
            joinColumns = {@JoinColumn(name = "Purchase_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    private User customer;

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}")
    private String customerName;

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}")
    private String customerMobilePhone;

    @Pattern(regexp = "^0[\\d]{9,10}")
    private String customerHomePhone;

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}")
    private String receiverName;

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}")
    private String receiverMobilePhone;

    @Pattern(regexp = "^0[\\d]{9,10}")
    private String receiverHomePhone;

    @NotNull
    @Size(min = 5, max = 50, message = "도로명 주소를 입력하세요(2자 이상 80자 이내).")
    private String address0;

    @NotNull
    @Size(min = 3, max = 50, message = "상세주소를 입력하세요(3자 이상 80자 이내).")
    private String address1;

    @NotNull
    @Pattern(regexp = "[\\d]{5,6}")
    private String zipcode;

    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordered;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobilePhone() {
        return customerMobilePhone;
    }

    public void setCustomerMobilePhone(String customerMobilePhone) {
        this.customerMobilePhone = customerMobilePhone;
    }

    public String getCustomerHomePhone() {
        return customerHomePhone;
    }

    public void setCustomerHomePhone(String customerHomePhone) {
        this.customerHomePhone = customerHomePhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobilePhone() {
        return receiverMobilePhone;
    }

    public void setReceiverMobilePhone(String receiverMobilePhone) {
        this.receiverMobilePhone = receiverMobilePhone;
    }

    public String getReceiverHomePhone() {
        return receiverHomePhone;
    }

    public void setReceiverHomePhone(String receiverHomePhone) {
        this.receiverHomePhone = receiverHomePhone;
    }

    public String getAddress0() {
        return address0;
    }

    public void setAddress0(String address0) {
        this.address0 = address0;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
