package com.thefloow.component;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samif on 12/07/2017.
 */
public class DataRangePartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> executionContextMap = new HashMap<>();

        for (int index = 1; index <= gridSize; index++) {

            ExecutionContext executionContext = new ExecutionContext();
            executionContext.putString("threadId", "Thread_" + index);
            executionContext.putInt("partitionNo", index);

            executionContextMap.put("Partition_" + index, executionContext);
        }

        return executionContextMap;
    }
}