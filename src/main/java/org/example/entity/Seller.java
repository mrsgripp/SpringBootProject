package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long sellerID;
    @Column(unique=true)
    public String name;
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> products;
}
