/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fang.week9lab.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jifang
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;
    
    @Basic(optional=false)
    @Column(name="role_name")
    private String name;
    
    public Role() {
        id = 2;
        name = "regular user";
    }
    
    public Role(int id, String name) {
    	this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
