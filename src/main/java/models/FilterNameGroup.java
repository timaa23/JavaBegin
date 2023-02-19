package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_filter_name_groups")
@IdClass(FilterNameGroupPK.class)
public class FilterNameGroup {
    @Id
    @ManyToOne
    @JoinColumn(name = "filter_name_id", nullable = false)
    private FilterName filterName;
    @Id
    @ManyToOne
    @JoinColumn(name = "filter_value_id", nullable = false)
    private FilterValue filterValue;
}
