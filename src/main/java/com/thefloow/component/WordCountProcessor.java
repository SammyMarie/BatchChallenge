package com.thefloow.component;

import com.thefloow.model.Count;
import com.thefloow.model.Page;
import com.thefloow.model.Word;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

/**
 * Created by samif on 13/07/2017.
 */
public class WordCountProcessor implements ItemProcessor<Page, List<Count>> {

    @Autowired
    private SparkSession sparkSession;

    @Override
    public List<Count> process(Page item) throws Exception {

        String input = item.getRevision().getText().replaceAll("[^\\w\\s]", "");

        String[] _words = input.split(" ");
        List<Word> words = Arrays.stream(_words).map(Word::new).collect(Collectors.toList());
        Dataset<Row> dataFrame = sparkSession.createDataFrame(words, Word.class);
        dataFrame.show();

        RelationalGroupedDataset groupedDataset = dataFrame.groupBy(col("word"));
        groupedDataset.count().show();
        List<Row> rows = groupedDataset.count().collectAsList();
        return rows.stream().map(row -> new Count(row.getString(0), row.getLong(1))).collect(Collectors.toList());
    }
}