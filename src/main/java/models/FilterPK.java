package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilterPK implements Serializable {
    private FilterName filterName;
    private FilterValue filterValue;
    private Product product;
}
