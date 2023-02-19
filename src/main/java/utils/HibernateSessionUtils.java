package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                Configuration config = new Configuration().configure("hibernate.cfg.xml");
                config.addAnnotatedClass(Role.class);
                config.addAnnotatedClass(Question.class);
                config.addAnnotatedClass(Answer.class);
                config.addAnnotatedClass(User.class);
                config.addAnnotatedClass(UserRole.class);
                config.addAnnotatedClass(Category.class);
                config.addAnnotatedClass(Product.class);
                config.addAnnotatedClass(Order.class);
                config.addAnnotatedClass(OrderStatus.class);
                config.addAnnotatedClass(OrderItem.class);
                config.addAnnotatedClass(ProductImage.class);
                config.addAnnotatedClass(Basket.class);
                config.addAnnotatedClass(FilterName.class);
                config.addAnnotatedClass(FilterValue.class);
                config.addAnnotatedClass(Filter.class);
                config.addAnnotatedClass(FilterNameGroup.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory();
            }
        } catch (Exception ex) {
            System.out.println("Error\n---> " + ex);
        }

        return sessionFactory;
    }
}
