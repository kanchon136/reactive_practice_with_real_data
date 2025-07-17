package spring_reactive.com.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Long id;
    private String name;
    private String email;
}
