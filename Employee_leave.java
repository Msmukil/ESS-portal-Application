package com.te.essportalapplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee_leave {
@Id
int Employee_ID;
@Column(length = 50)  
String Leave_Date;
@Column(length = 50)
String Leave_Status;



}



