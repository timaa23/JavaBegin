package program;

import models.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionUtils;

import java.util.List;
import java.util.Scanner;

public class RoleEditor {
    public static void Start() {
        System.out.println("Hello world!");
        Session context = HibernateSessionUtils.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        String option = "1";

        for (; option == "1" || option == "2" || option == "3" || option == "4"; ) {
            option = getLine("--- Enter the option ---\n\t1 - Create\n\t2 - Read\n\t3 - Update\n\t4 - Delete\n\tEnter: ");
        }

        switch (option) {
            case "1": {
                String name = getLine("Enter role name: ");
                Role role = new Role();
                role.setName(name);
                context.save(role);
                tx.commit();
                break;
            }
            case "2": {
                Query query = context.createQuery("FROM Role");
                List<Role> list = query.list();
                for (Role role : list) {
                    System.out.println("Name: " + role.getName());
                }
                break;
            }
            case "3": {
                String id = getLine("Enter role id: ");
                Role role = (Role) context.get(Role.class, Integer.parseInt(id));
                if (role != null) {
                    String name = getLine("Enter new role name: ");
                    role.setName(name);
                    context.update(role);
                    tx.commit();
                } else
                    System.out.println("Unknown role");
                break;
            }
            case "4": {
                String id = getLine("Enter role id: ");
                Role role = (Role) context.get(Role.class, Integer.parseInt(id));
                if (role != null) {
                    context.delete(role);
                    tx.commit();
                } else System.out.println("Unknown role");
                break;
            }
            default: {
                System.out.println("Error");
                break;
            }
        }

        context.close();
    }

    public static String getLine(String str) {
        System.out.println(str);
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        return string;
    }
}
