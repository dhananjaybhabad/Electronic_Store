package com.infosys.electronicstore.entities;import com.infosys.electronicstore.baseEntity.BaseEntity;import lombok.AllArgsConstructor;import lombok.Getter;import lombok.NoArgsConstructor;import lombok.Setter;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Entity@Table(name = "categories")public class Category extends BaseEntity {    @Id    private long categoryId;    @Column(name = "category_title",length =50,nullable = false )    private String title;    @Column(name = "category_description",length = 200,nullable = false)    private String description;    @Column(name = "cover_image")    private String coverImage;}