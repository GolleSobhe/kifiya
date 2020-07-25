package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"droit"})
public class Role {

    Long id;

    @NonNull
    String droit;
}
