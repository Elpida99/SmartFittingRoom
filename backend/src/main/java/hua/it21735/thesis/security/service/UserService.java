//package hua.it21735.thesis.security.service;
//
//
//import hua.it21735.thesis.security.model.User;
//import hua.it21735.thesis.security.model.UserDao;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService { //} implements UserDetailsService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
////    @Autowired
//    private UserDao userDao;
//    private final PasswordEncoder passwordEncoder;
//    //@Autowired
////    private SecurityService securityService;
//
//    @Autowired
//    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public boolean isAdmin() {
//        return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() == 3);
//    }
//
//    public User createUser(User user) {
//        logger.info("User service, create user: " + user);
//        List<User> users = getUsers();
//        for (User tuser : users) {
//            if (user.getEmail().equals(tuser.getEmail())) {
//                throw new IllegalStateException("User with email " + user.getEmail() + " already exists!");
//            }
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userDao.save(user);
//        return user;
//    }
//
//    public List<User> getUsers() {
//        return userDao.findAll();
//    }
//
//    public User getUserByUsername(String username) throws UsernameNotFoundException {
//
//        logger.info("User service, getting user by username: " + username);
//        User user;
//        if (isAdmin()) {
//            user = userDao.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
//        } else {
//            user = userDao.findActiveByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
//        }
////        if ((!user.getUsername().equals(securityService.getCurrentUser())) && !isAdmin())
////            throw new IllegalStateException("You cannot see other users profiles!");
//        logger.info("Retrieved user: " + user);
//        return user;
//    }
//
//    public User disableUser(String username) {
//        logger.info("User service, disabling: " + username);
//
//        User user = getUserByUsername(username);
//        user.setEnabled(false);
//        return user;
//    }
//
//    public User enableUser(String username) {
//        logger.info("User service, enabling: " + username);
//
//        User user = getUserByUsername(username);
//        user.setEnabled(true);
//        return user;
//    }
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////
////        User user = userDao.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
////        return new MyUserPrincipal(user);
////    }
//
//    public User deleteUser(String username) {
//        User user = getUserByUsername(username);
//        userDao.delete(user);
//
//        return user;
//    }
//
////    public User login(String username, String password) {
////        User found = userDao.save(new User(username, passwordEncoder.encode(password)));
////        if(saved.getId() == null) {
////            throw new RuntimeException("Bad credentials");
////        }
////
////
////    }
//}
