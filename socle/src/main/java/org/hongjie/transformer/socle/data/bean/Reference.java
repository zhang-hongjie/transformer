package org.hongjie.transformer.socle.data.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reference {

    Color color;
    Double price;
    Integer size;
    Integer numReference;

}
