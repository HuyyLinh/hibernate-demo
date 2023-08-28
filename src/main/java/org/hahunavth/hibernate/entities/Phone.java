package org.hahunavth.hibernate.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Phone implements Comparable<Phone>{

    @Id
    private Integer id;

    private String type;
//    @Column(name = "`number`")
    private String number;

    @Override
    public int compareTo(Phone o) {
//        return type.compareTo(o.getType());
        return o.getType().compareTo(type);
    }
//    @ManyToOne
//    private EntityWithList entityWithList;

    //Getters and setters are omitted for brevity

}
