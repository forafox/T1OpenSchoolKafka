package org.forafox.service;

import org.forafox.domain.MethodData;
import org.forafox.web.dto.MethodDataStatDTO;

import java.util.List;

public interface MethodDataService {
    void save(MethodData methodData);

    List<MethodData> getAllMethodsByMethodName(String methodName);

    List<MethodData> getAll();

    void clearData();

    MethodDataStatDTO getStat(String methodName);

}
