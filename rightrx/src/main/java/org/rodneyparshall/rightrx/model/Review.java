package org.rodneyparshall.rightrx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.enumeration.Dosage;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "drugId", nullable = false)
    private Drug drug;

    private String reviewInfo;
    private Dosage dosage;
}
