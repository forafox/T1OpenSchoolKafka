package org.forafox.repository;

import org.forafox.domain.MethodData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MethodDataRepository  extends JpaRepository<MethodData, Long> {
    List<MethodData> findAllByMethodName(String methodName);
}
