//package hua.it21735.thesis.security.model;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface UserDao extends JpaRepository<User, Long> {
//
//    @Query("select e from User e where e.email=?1 and e.enabled=true")
//    Optional<User> findActiveByUserName(String email);
//
//    @Query("select e from User e where e.email=?1")
//    Optional<User> findByUserName(String email);
//
//    @Query("select e from User e where e.enabled=true")
//    List<User> findAllActive();
//
//}
