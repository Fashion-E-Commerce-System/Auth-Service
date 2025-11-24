package com.e_commerce_system.backend.domain.product;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {

    @Id
    private Long articleId;


    private String productCode;


    private String prodName;

    @Column(length = 2000)
    private String detailDesc;

    @ManyToOne
    @JoinColumn(name = "product_type_no")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "graphical_appearance_no")
    private GraphicalAppearance graphicalAppearance;

    @ManyToOne
    @JoinColumn(name = "colour_group_code")
    private ColourGroup colourGroup;

    @ManyToOne
    @JoinColumn(name = "index_code")
    private IndexGroup indexGroup;

    @ManyToOne
    @JoinColumn(name = "section_no")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "garment_group_no")
    private GarmentGroup garmentGroup;

    @ManyToOne
    @JoinColumn(name = "department_no")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_master_id")
    private PerceivedColourMaster perceivedColourMaster;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_value_id")
    private PerceivedColourValue perceivedColourValue;

}