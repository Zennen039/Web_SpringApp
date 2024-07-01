/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nva.hibernatedemo;

import com.nva.pojo.Category;
import com.nva.pojo.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author admin
 */
public class HibernateUtils {

    private static final SessionFactory factory;

    static {
        Configuration conf = new Configuration();

        Properties props = new Properties();

        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        
        props.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
        
        props.put(Environment.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/saledb");
        
        props.put(Environment.JAKARTA_JDBC_USER, "root");
        
        props.put(Environment.JAKARTA_JDBC_PASSWORD, "Admin@123");
        
        props.put(Environment.SHOW_SQL, true);

        conf.setProperties(props);
        
        conf.addAnnotatedClass(Category.class);
        
        conf.addAnnotatedClass(Product.class);

        ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

        factory = conf.buildSessionFactory(service);
    }

    /**
     * @return the fact
     */
    public static SessionFactory getFact() {
        return factory;
    }
}
