//package hua.it21735.thesis.security.rest;
//
//import hua.it21735.thesis.security.model.User;
//import hua.it21735.thesis.security.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
//@RequestMapping(path = "/",
//        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class LoginController {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    @Autowired
//    private UserService userService;
//
////    @PostMapping(path = "login")
////    public ResponseEntity<User> getLoginView(@RequestParam String username, @RequestParam String password) {
////        logger.info("User resource: post request for log in \"login page\"");
////        return ResponseEntity.ok(userService.login(username, password));
////    }
//
//    @PostMapping(path = "sign-up")
//    public ResponseEntity<User> userSignUp(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) Role
//    role) {
//
//        User user = new User(username, password);
//
//        if (role != null) {
//            user.setRole(role);
//        }
//        logger.info("User resource: post request for sign-up: user = " + user);
//
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    @GetMapping(path = "profile/{username}")
//    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
//        logger.info("Get request: get user with username: " + username);
//
//        return ResponseEntity.ok(userService.getUserByUsername(username));
//    }
//
//    @DeleteMapping(path = "profile/{username}/delete")
//    public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
//        logger.info("User resource: delete request for deleting user = " + username);
//
//        return ResponseEntity.ok(userService.deleteUser(username));
//    }
//
//    @GetMapping(path = "users")
////    @PreAuthorize("hasAuthority('delete')")
//    public ResponseEntity<List<User>> getAllUsers() {
//        logger.info("User resource: get request for viewing all users");
//        return ResponseEntity.ok(userService.getUsers());
//    }
//
//    @DeleteMapping(path = "users/{username}/delete")
////    @PreAuthorize("hasAuthority('delete')")
//    public ResponseEntity<User> adminDeleteUser(@PathVariable String username) {
//
//        logger.info("ADMIN-User resource: delete request for deleting user = " + username);
//
//        return ResponseEntity.ok(userService.deleteUser(username));
//    }
//
//    @DeleteMapping(path = "users/{username}")
////    @PreAuthorize("hasAuthority('delete')")
//    public ResponseEntity<User> disableUser(@PathVariable String username) {
//        logger.info("ADMIN-User resource: delete request for suspending user = " + username);
//
//        return ResponseEntity.ok(userService.disableUser(username));
//    }
//
//    @PutMapping(path = "users/{username}")
////    @PreAuthorize("hasAuthority('delete')")
//    public ResponseEntity<User> enableUser(@PathVariable String username) {
//        logger.info("ADMIN-User resource: put request for enabling user = " + username);
//
//        return ResponseEntity.ok(userService.enableUser(username));
//    }
//
//}
