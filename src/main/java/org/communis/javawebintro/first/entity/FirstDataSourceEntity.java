package org.communis.javawebintro.first.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "first")
public class FirstDataSourceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(
            name = "NAME",
            nullable = false
    )
    private String name;

    public FirstDataSourceEntity() {
    }
}
