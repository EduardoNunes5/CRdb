package com.psoft.crdb.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDTO {

    @Column(nullable = false)
    @NotEmpty
    private String name;
}
