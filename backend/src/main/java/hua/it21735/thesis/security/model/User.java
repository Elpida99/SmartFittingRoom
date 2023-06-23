//package hua.it21735.thesis.security.model;
//
//import javax.persistence.*;
//
//@Entity(name = "User")
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String email;
//    private String password;
//    private boolean enabled;
//
//    public User() {
//    }
//
//    public User(final String email, final String password) {
//        this.email = email;
//        this.password = password;
//        this.enabled = true;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(final String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(final String password) {
//        this.password = password;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(final boolean enabled) {
//        this.enabled = enabled;
//    }
//}
