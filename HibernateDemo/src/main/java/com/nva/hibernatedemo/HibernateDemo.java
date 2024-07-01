/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.nva.hibernatedemo;

import com.nva.repository.implement.CategoryRepositoryImplement;
import com.nva.repository.implement.ProductRepositoryImplement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class HibernateDemo {

    public static void main(String[] args) {
        CategoryRepositoryImplement r = new CategoryRepositoryImplement();
        
        r.getCates().forEach(c -> System.out.println(c.getName()));
        
        System.out.println("===");
        
        Map<String, String> params = new HashMap<>();
        
        params.put("pg", "1");
        
        ProductRepositoryImplement pr = new ProductRepositoryImplement();
        
        pr.getProds(null).forEach(p -> System.out.printf("%s - %.1f\n", p.getName(), p.getPrice()));
    }
}
