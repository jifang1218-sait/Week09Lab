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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author jifang
 */
public class DBUtil {
    static public User getUserByEmail(String email) {
        User ret = null;
        
        EntityManagerFactory emf = DBUtil.getEMF();
        EntityManager em = emf.createEntityManager();
       	ret = em.find(User.class, email);

        /*Connection conn = ConnectionPool.instance().getConnection();
        
        try (PreparedStatement stmt = 
                conn.prepareStatement("select * from user where email=?")) {
            stmt.setString(1, email.trim().toLowerCase());
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                User user = new User();
                int col = 1;
                user.setEmail(results.getString(col++));
                user.setFirstName(results.getString(col++));
                user.setLastName(results.getString(col++));
                user.setPassword(results.getString(col++));
                long roleId = results.getLong(col++);
                RoleService roleMgr = RoleService.instance();
                user.setRole(new Role(roleId, roleMgr.getNameById(roleId)));
                ret = user;
                break;
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ConnectionPool.instance().closeConnection(conn);*/
       	
       	return ret;
    }
    
    static public User updateUser(User user) {
    	if (user == null) {
    		return user;
    	}
    	
        Connection conn = ConnectionPool.instance().getConnection();
        
        String sql = "update user set first_name=?, "
        		+ "last_name=?, "
        		+ "password=?, "
        		+ "role=? "
        		+ "where email=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            int col = 1;
            stmt.setString(col++, user.getFirstName().trim());
            stmt.setString(col++, user.getLastName().trim());
            stmt.setString(col++, user.getPassword());
            stmt.setInt(col++, user.getRole().getId());
            stmt.setString(col++, user.getEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ConnectionPool.instance().closeConnection(conn);
        
        return user;
    }
    
    static public User addUser(User user) {
    	if (user == null) {
    		return user;
    	}
    	
        Connection conn = ConnectionPool.instance().getConnection();

        String sql = "insert into user values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            int col = 1;
            stmt.setString(col++, user.getEmail().trim());
            stmt.setString(col++, user.getFirstName().trim());
            stmt.setString(col++, user.getLastName().trim());
            stmt.setString(col++, user.getPassword());
            stmt.setInt(col++, user.getRole().getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ConnectionPool.instance().closeConnection(conn);
        
        return user;
    }
    
    static public void deleteUser(String email) {
    	if (email == null) {
    		return;
    	}
    	
    	Connection conn = ConnectionPool.instance().getConnection();
    	
    	String sql = "delete from user where email=?";
    	try {
    		PreparedStatement stmt = conn.prepareStatement(sql);
    		int col = 1;
    		stmt.setString(col++, email.trim());
    		stmt.executeUpdate();
    		stmt.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	ConnectionPool.instance().closeConnection(conn);
    }
    
    static public List<User> getUsers() {
        List<User> ret = new LinkedList<>();
        
        Connection conn = ConnectionPool.instance().getConnection();
        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            stmt = conn.prepareStatement("select * from user");
            results = stmt.executeQuery();
            while (results.next()) {
                User user = new User();
                int col = 1;
                user.setEmail(results.getString(col++));
                user.setFirstName(results.getString(col++));
                user.setLastName(results.getString(col++));
                user.setPassword(results.getString(col++));
                int roleId = results.getInt(col++);
                RoleService roleMgr = RoleService.instance();
                user.setRole(new Role(roleId, roleMgr.getNameById(roleId)));
                ret.add(user);
            }
            results.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        ConnectionPool.instance().closeConnection(conn);
        
        return ret;
    }
    
    static public List<Role> getRoles() {
        List<Role> ret = new LinkedList<>();
        
        Connection conn = ConnectionPool.instance().getConnection();
        PreparedStatement stmt = null;
        ResultSet results = null;
                
        try {
            stmt = conn.prepareStatement("select * from role"); 
            results = stmt.executeQuery();
            while (results.next()) {
            	Role role = new Role();
            	int col = 1;
            	role.setId(results.getInt(col++));
            	role.setName(results.getString(col++));
                ret.add(role);
            }
            results.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ConnectionPool.instance().closeConnection(conn);
        
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
