package org.hongjie.transformer.socle.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtils {

    /**Read content from file CSV
     *
     * @param file
     * @param colSeperator
     * @param colNames
     * @return content of CSV in Map<String, String>
     * @throws IOException
     */
    public static List<Map<String,String>> getRecords(File file, Character colSeperator, String ... colNames) throws IOException {

        val builder = CsvSchema.builder();
        builder.setColumnSeparator(colSeperator);
        Stream.of(colNames).forEach(col-> builder.addColumn(col));
        val schema = builder.build();

        CsvMapper mapper = new CsvMapper();
        MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(file);

        return StreamUtils.asStream(it).collect(Collectors.toList());
    }

}
