/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fang.week9lab.dataaccess;

import com.fang.week9lab.entities.Role;
import com.fang.week9lab.entities.User;
import com.fang.week9lab.services.RoleService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityExistsException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jifang
 */
public class DBUtil {
    static public User getUserByEmail(String email) {
        User ret = null;

        EntityManager em = DBUtil.getEMF().createEntityManager();
       	ret = em.find(User.class, email);

       	return ret;
    }
    
    static public User updateUser(User user) {
        User ret = null;
        
    	if (user == null) {
            return ret;
    	}
    	
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
            ret = user;
        } catch (IllegalArgumentException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return ret;
    }
    
    static public User addUser(User user) {
        User ret = null;
        
    	if (user == null) {
            return ret;
    	}
        
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
            ret = user;
        } catch (EntityExistsException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return ret;
    }
    
    static public void deleteUser(String email) {
    	if (email == null) {
    		return;
    	}
        
        EntityManager em = DBUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            User beRemoved = em.find(User.class, email);
            if (beRemoved != null);
            em.remove(beRemoved);
            trans.commit();
        } catch (IllegalArgumentException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    static public List<User> getUsers() {
        List<User> ret = new LinkedList<>();
        
        EntityManager em = DBUtil.getEMF().createEntityManager();
        Query q = em.createNamedQuery("User.findAll");
        List<User> temp = q.getResultList();
        if (temp != null) {
            ret = temp;
        }
        em.close();

        return ret;
    }
    
    static public List<Role> getRoles() {
        List<Role> ret = new LinkedList<>();
        
        EntityManager em = DBUtil.getEMF().createEntityManager();
        Query q = em.createNamedQuery("Role.findAll");
        List<Role> temp = q.getResultList();
        if (temp != null) {
            ret = temp;
        }
        em.close();

        return ret;
    }
    
    static private EntityManagerFactory emf = null;
    static public EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Week9LabPU");
        }
        
        return emf;
    }
}
