package delivery.model.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private long accountSum=0L;
    private Role role = Role.GUEST;
    private List<Order> orders = new ArrayList<>();

    public enum Role {
        GUEST("WEB-INF/view/index.jsp"){},
        USER("user:redirect"){},
        ADMIN("admin:redirect"){};

        private String roleBasePath;

        public String getRoleBasePath() {
            return roleBasePath;
        }

        Role(String path){
            roleBasePath = path;
        }
    }

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String email, Role role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(long id, String login, String password, String firstName, String lastName, String email, Role role, List<Order> orders) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getAccountSum() {
        return accountSum;
    }
    public void setAccountSum(long accountSum) {
        this.accountSum = accountSum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountSum=" + accountSum +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
