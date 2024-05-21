package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.MethodData;
import org.forafox.repository.MethodDataRepository;
import org.forafox.service.MethodDataService;
import org.forafox.web.controller.dto.MethodDataStatDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodDataServiceImpl implements MethodDataService {
    private final MethodDataRepository methodDataRepository;

    @Override
    public void save(MethodData methodData) {
        methodDataRepository.save(methodData);
    }

    @Override
    public List<MethodData> getAllMethodsByMethodName(String methodName) {
        return methodDataRepository.findAllByMethodName(methodName);
    }

    @Override
    public List<MethodData> getAll() {
        return methodDataRepository.findAll();
    }

    @Override
    public void clearData() {
        methodDataRepository.deleteAll();
    }

    public MethodDataStatDTO getStat(String methodName) {
        List<MethodData> dataList = getAllMethodsByMethodName(methodName);
        long sumMilli = 0;
        long maxMilli = Long.MIN_VALUE;
        long minMilli = Long.MAX_VALUE;
        long averageMilli = 0;

        long sumNano = 0;
        long maxNano = Long.MIN_VALUE;
        long minNano= Long.MAX_VALUE;
        long averageNano = 0;
        var methodData = new MethodDataStatDTO();
        if (!dataList.isEmpty()) {
            for (MethodData data : dataList) {
                long executeTimeMilli = data.getExecuteMilliTime();
                sumMilli += executeTimeMilli;
                maxMilli = Math.max(maxMilli, executeTimeMilli);
                minMilli = Math.min(minMilli, executeTimeMilli);

                long executeTimeNano = data.getExecuteNanoTime();
                sumNano += executeTimeNano;
                maxNano = Math.max(maxNano, executeTimeNano);
                minNano = Math.min(minNano, executeTimeNano);
            }
            averageMilli = sumMilli / dataList.size();

            averageNano = sumNano / dataList.size();

            methodData.setMethodName(methodName);
            methodData.setAnnotationType(dataList.get(0).getAnnotationType());
            methodData.setMaxExecuteTimeMilli(maxMilli);
            methodData.setMinExecuteTimeMilli(minMilli);
            methodData.setAvgExecuteTimeMilli(averageMilli);

            methodData.setMaxExecuteTimeNano(maxNano);
            methodData.setMinExecuteTimeNano(minNano);
            methodData.setAvgExecuteTimeNano(averageNano);
        }
        return methodData;
    }
}
