package com.any.name.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column("id_container")
    private Integer idContainer;

    @Column("id_type_beer")
    private int idTypeBeer;

    @Column("alcohol_content")
    private float alcoholContent;

    @Column
    private int ibu;

    @Column
    private String count;
}
