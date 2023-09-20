package dev.lucho.freemarker_poc.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TemplateDTO {

    private UUID id;

    @Size(max = 255)
    private String name;

    private String content;

}
