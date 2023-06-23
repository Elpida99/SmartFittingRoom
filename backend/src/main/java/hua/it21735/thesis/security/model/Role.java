//package hua.it21735.thesis.security.model;
//
//
//import com.google.common.collect.Sets;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static hua.it21735.thesis.security.model.Permission.*;
//
//public enum Role {
//    ADMIN(Sets.newHashSet(READ, WRITE, DELETE)),
//    REPORTER(Sets.newHashSet(READ, WRITE)),
//    CONSULT(Sets.newHashSet(READ));
//
//    private final Set<Permission> permissions;
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<GrantedAuthority> getGrantedAuthorities(){
//        Set<GrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//
//        return permissions;
//    }
//
//}
