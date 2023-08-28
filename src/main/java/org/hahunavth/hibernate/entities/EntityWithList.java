package org.hahunavth.hibernate.entities;

import lombok.Data;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class EntityWithList {
    @Id
    private Integer id;
    @ElementCollection
    private Collection<String> arrayString = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    @OrderBy("number desc ")
//    private List<Phone> phones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @SortNatural
    private SortedSet<Phone> phoneSet= new TreeSet<>();
}
