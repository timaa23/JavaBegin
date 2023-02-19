package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_filter_names")
public class FilterName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;

    @OneToMany(mappedBy = "filterName")
    private List<Filter> filters;
    @OneToMany(mappedBy = "filterName")
    private List<FilterNameGroup> filterNameGroups;
}
