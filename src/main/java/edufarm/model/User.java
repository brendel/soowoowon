package edufarm.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 가입한 회원 정보를 나타내는 클래스.
 * <p>같은 이름의 DB 테이블(User)와 맵핑된다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.service.UserService
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true)
    @Size(min = 4, max = 20)
    private String username;

    @NotNull
    @Column(unique = true)
    @Size(min = 7, max = 100)
    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}")
    private String displayName;

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}")
    private String phone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date joined;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastVisited;

    @NotNull
    private byte role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("id desc")
    private Set<Address> addresses = new HashSet<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(Date lastVisited) {
        this.lastVisited = lastVisited;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}