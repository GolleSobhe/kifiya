package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"numero"})
@ToString(of = {"numero", "expiration", "securite"})
@Data
@Builder
public class Carte {
    @NonNull
    @Pattern(regexp = "\\d{4}[- .]?\\d{4}[- .]?\\d{4}[- .]?\\d{4}$")
    String numero;

    @NonNull
    @Pattern(regexp = "\\d{2}[/ .]?\\d{2}$")
    String expiration;

    @NonNull
    @Pattern(regexp = "\\d{3}$")
    String securite;

}
