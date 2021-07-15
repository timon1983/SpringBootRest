package com.example.SpringRestProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id" , unique = true , nullable = false)
    private long id;

    @Column(name = "path")
    private String path;

    @Column(name = "meta_data")
    private String metadata;

    @JsonIgnore
    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private Event event;

}
