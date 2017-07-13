package com.thefloow.component;

import com.thefloow.model.Count;
import com.thefloow.model.Page;
import com.thefloow.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

/**
 * Created by samif on 13/07/2017.
 */
@Slf4j
public class WordCountProcessor implements ItemProcessor<Page, Page> {

    @Autowired
    private SparkSession sparkSession;

    @Override
    public Page process(Page item) throws Exception {

        String input = item.getRevision().getText();

        if (input.length() < 1) {
            log.error("Usage: WordCount <file>");
            System.exit(1);
        }

        String[] _words = input.split(" ");
        List<Word> words = Arrays.stream(_words).map(Word::new).collect(Collectors.toList());
        Dataset<Row> dataFrame = sparkSession.createDataFrame(words, Word.class);

        RelationalGroupedDataset groupedDataset = dataFrame.groupBy(col("word"));
        groupedDataset.count().show();
        List<Row> rows = groupedDataset.count().collectAsList();

        List<Count> counts = rows.stream().map((Row row) -> new Count(row.getString(0), row.getLong(1))).collect(Collectors.toList());

        Page page = new Page();
        page.setCountList(counts);

        return page;
    }
}