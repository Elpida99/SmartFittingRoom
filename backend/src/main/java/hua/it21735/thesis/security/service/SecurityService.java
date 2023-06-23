//package hua.it21735.thesis.security.service;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecurityService {
//
//    public String getCurrentUser(){
//        String name = SecurityContextHolder
//                .getContext()
//                .getAuthentication().getName();
//        if(name.equals( "anonymousUser")){
//            return null;
//        }
//        return name;
//    }
//
//}