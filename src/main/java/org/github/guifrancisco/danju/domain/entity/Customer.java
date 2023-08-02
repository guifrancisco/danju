package org.github.guifrancisco.danju.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    private String id;
    private String name;
    private String telephone;
    private String address;

}
