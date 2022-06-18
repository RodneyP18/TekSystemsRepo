package org.rodneyparshall.rightrx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long physId;
    private String firstName;
    private String lastName;
    private long medId;
    private String facility;
    private String email;
    private String username;
    private String password;
}
