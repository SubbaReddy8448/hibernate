package com.klef.jfsdexam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Department.class);
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        insertDepartment(sessionFactory);
        deleteDepartment(sessionFactory, 1); // Replace 1 with the actual ID to delete
    }

    public static void insertDepartment(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Department department = new Department();
        department.setName("alex");
        department.setLocation("Block A");
        department.setHodName("bob");
        session.save(department);
        transaction.commit();
        session.close();
        System.out.println("Department inserted successfully.");
    }

    public static void deleteDepartment(SessionFactory sessionFactory, int deptId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Department WHERE deptId = :deptId");
        query.setParameter("deptId", deptId);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println(result > 0 ? "Department deleted successfully." : "Department not found.");
    }
}
