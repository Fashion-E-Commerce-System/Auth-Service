package com.e_commerce_system.backend.domain;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
public class Article {

    @Id
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "product_code", length = 50)
    private String productCode;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "product_group_name")
    private String productGroupName;

    @Column(name = "detail_desc")
    private String detailDesc;

    @ManyToOne
    @JoinColumn(name = "product_type_no", referencedColumnName = "product_type_no")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "graphical_appearance_no", referencedColumnName = "graphical_appearance_no")
    private GraphicalAppearance graphicalAppearance;

    @ManyToOne
    @JoinColumn(name = "colour_group_code", referencedColumnName = "colour_group_code")
    private ColourGroup colourGroup;

    @ManyToOne
    @JoinColumn(name = "garment_group_no", referencedColumnName = "garment_group_no")
    private GarmentGroup garmentGroup;

    @ManyToOne
    @JoinColumn(name = "section_no", referencedColumnName = "section_no")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "department_no", referencedColumnName = "department_no")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "index_code", referencedColumnName = "index_code")
    private IndexGroup indexGroup;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_value_id", referencedColumnName = "perceived_colour_value_id")
    private PerceivedColourValue perceivedColourValue;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_master_id", referencedColumnName = "perceived_colour_master_id")
    private PerceivedColourMaster perceivedColourMaster;
}
