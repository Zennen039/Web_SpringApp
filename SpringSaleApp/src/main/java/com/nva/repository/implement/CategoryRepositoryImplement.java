///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.nva.repository.implement;
//
//import com.nva.hibernatedemo.HibernateUtils;
//import com.nva.pojo.Category;
//import jakarta.persistence.Query;
//import java.util.List;
//import org.hibernate.Session;
//
///**
// *
// * @author admin
// */
//public class CategoryRepositoryImplement {
//    public List<Category> getCates() {
//        try (Session s = HibernateUtils.getFact().openSession()) {
//            Query q = s.createQuery("From Category", Category.class);
//                    
//            return q.getResultList();
//        }
//    }
//}
