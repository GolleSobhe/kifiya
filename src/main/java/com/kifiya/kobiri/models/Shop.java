package com.kifiya.kobiri.models;

import com.kifiya.kobiri.models.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "telephone"})
@ToString(of = {"id", "ville", "nom", "telephone"})
@Data
@Builder
@Table(name = "SHOP")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "*Please provide your ville")
    String ville;
    @NotEmpty(message = "*Please provide your point de vente")
    String nom;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    String email;
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;
    Date date;
}
