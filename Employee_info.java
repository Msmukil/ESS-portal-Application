package com.te.essportalapplication;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee_info{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
int Employee_ID;
@Column  (length = 50)
String Employee_Name;
String Employee_Type;
@Column(length = 100)
String Email;
@Column(length = 50)
String Password;

@OneToMany
List <Employee_leave> employee_leave;

}
