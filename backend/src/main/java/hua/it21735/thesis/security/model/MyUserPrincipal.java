//package hua.it21735.thesis.security.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MyUserPrincipal implements UserDetails {
//
//    private String userName;
//    private String password;
//    private boolean isActive;
//    private List<GrantedAuthority> authorities;
//
//    public MyUserPrincipal(User user) {
//
//        this.userName = user.getUsername();
//        this.password = user.getPassword();
//        this.isActive = user.isEnabled();
//        this.authorities = user.getRole().getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.isActive;
//    }
//
//    @Override
//    public String toString() {
//        return "MyUserPrincipal{" +
//                "userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", isActive=" + isActive +
//                '}';
//    }
//}
