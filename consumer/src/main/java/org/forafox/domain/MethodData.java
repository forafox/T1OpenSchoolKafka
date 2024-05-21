package org.forafox.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.forafox.domain.enums.AnnotationType;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date executeDate;
    private String methodName;
    private Long executeNanoTime;
    private Long executeMilliTime;
    private AnnotationType annotationType;
}
