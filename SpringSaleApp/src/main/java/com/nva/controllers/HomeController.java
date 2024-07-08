/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nva.controllers;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
public class HomeController{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @RequestMapping("/")
    @Transactional
    public String index(Model model) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Category");
        model.addAttribute("categories", q.getResultList());
        return "index";
    }
}
