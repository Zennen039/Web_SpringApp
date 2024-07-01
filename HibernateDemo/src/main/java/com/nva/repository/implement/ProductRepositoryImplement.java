/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nva.repository.implement;

import com.nva.hibernatedemo.HibernateUtils;
import com.nva.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class ProductRepositoryImplement {

    private static final int PAGE_SIZE = 3;

    public List<Product> getProds(Map<String, String> params) {
        try ( Session s1 = HibernateUtils.getFact().openSession()) {
            CriteriaBuilder b = s1.getCriteriaBuilder();

            CriteriaQuery<Product> q = b.createQuery(Product.class);

            Root rt = q.from(Product.class);

            q.select(rt);

            if (params != null) {
                List<Predicate> preds = new LinkedList<>();

                String kw = params.get("q");

                if (kw != null && !kw.isEmpty()) {
                    Predicate p1 = b.like(rt.get("name"), String.format("%%%s%%", kw));

                    preds.add(p1);
                }

                String fromPrice = params.get("fromPrice");

                if (fromPrice != null && !fromPrice.isEmpty()) {
                    Predicate p2 = b.greaterThanOrEqualTo(rt.get("price"), Double.parseDouble(fromPrice));

                    preds.add(p2);
                }

                String toPrice = params.get("toPrice");

                if (toPrice != null && !toPrice.isEmpty()) {
                    Predicate p3 = b.lessThanOrEqualTo(rt.get("price"), Double.parseDouble(toPrice));

                    preds.add(p3);
                }

                String cateId = params.get("cateId");

                if (cateId != null && !cateId.isEmpty()) {
                    Predicate p4 = b.lessThanOrEqualTo(rt.get("category").as(Integer.class), Integer.parseInt(cateId));

                    preds.add(p4);
                }

                q.where(preds.toArray(Predicate[]::new));
            }
            
            q.orderBy(b.desc(rt.get("id")));

            Query qr = s1.createQuery(q);

            if (params != null) {
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int pg = Integer.parseInt(page);

                    int start = (pg - 1) * PAGE_SIZE;
                    
                    qr.setMaxResults(PAGE_SIZE);
                    qr.setFirstResult(start);
                }
            }

            return qr.getResultList();
        }
    }
    
    public void addOrUpdateProduct(Product pr) {
        try (Session se = HibernateUtils.getFact().openSession()) {
            if (pr.getId() > 0) {
                se.merge(se);
            } else {
                se.persist(se);
            }
        }
    }
    
    public void deleteProduct(int prodId) {
        try (Session si = HibernateUtils.getFact().openSession()) {
            Product pt = this.getProdId(prodId);
            
            si.remove(pt);
        }
    }
    
    public Product getProdId(int prodId) {
        try (Session ss = HibernateUtils.getFact().openSession()) {
            return ss.get(Product.class, prodId);
        }
    }
}
