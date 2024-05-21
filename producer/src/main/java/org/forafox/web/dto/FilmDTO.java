package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO for Film entity")
public class FilmDTO {
    @Schema(description = "Unique identifier of the film", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Title name of the film", example = "Catch Me If You Can")
    private String titleName;
    @Schema(description = "Genre of the film", example = "Comedy")
    private String genre;
    @Schema(description = "Country of the film", example = "United States")
    private String country;
    @Schema(description = "Producer of the film", example = "Steven Spielberg")
    private String producer;
}
