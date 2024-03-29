/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fang.week9lab.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jifang
 */
@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	private String email;
    
    @Basic(optional=false)
    @Column(name="first_name")
    private String firstName;
    
    @Basic(optional=false)
    @Column(name="last_name")
    private String lastName;
    
    @Basic(optional=false)
    @Column(name="password")
    private String password;
    
    @OneToOne
    @JoinColumn(name="role") 
    private Role role;
    
    public User() {
        role = new Role();
    }
    
    public User(String email, 
            String firstName, String lastName, 
            String password, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password; 
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}
