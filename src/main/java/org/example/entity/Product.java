package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long productID;
    public float price;
    @Column(unique = true, nullable = false)
    public String name;
    //@JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("productList")
    //@JoinColumn(name="sellerid")
    public Seller seller;
}
