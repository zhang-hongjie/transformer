package org.hongjie.transformer.socle;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.hongjie.transformer.socle.data.bean.Color;
import org.hongjie.transformer.socle.data.bean.Erreur;
import org.hongjie.transformer.socle.data.bean.Reference;
import org.hongjie.transformer.socle.data.bean.Report;
import org.hongjie.transformer.socle.util.CsvUtils;
import org.hongjie.transformer.socle.util.JsonUtils;
import org.hongjie.transformer.socle.util.XmlUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.hongjie.transformer.socle.util.Constant.*;

@Log4j
public class Worker {

    private Report report = new Report();

    /**
     * Parse content of CSV, and save to file XML or JSON
     * @param csv
     * @param type
     * @param destination
     * @throws IOException
     */
    public String process(File csv, String type, File destination) throws IOException {
        report.getErrors().clear();
        report.getReferences().clear();
        report.setInputFile(csv.getName());

        List<Map<String, String>> csvRecords = CsvUtils.getRecords(csv, COL_SEPERATOR, COL_NUM_REFERENCE, COL_COLOR, COL_PRICE, COL_SIZE);

        BiConsumer<Integer, Map> biConsumer = (lineNum, record) -> {
            try{
                Reference reference = getReference(record);
                report.getReferences().add(reference);
            }catch (Exception e){
                val error = Erreur.builder()
                        .message(e.getMessage())
                        .content(Joiner.on(COL_SEPERATOR).join(record.values()))
                        .line(lineNum+1).build();
                report.getErrors().add(error);
                log.info("Error: " + error.getLine() + " : " + error.getContent(), e);
            }
        };
        IntStream.range(0, csvRecords.size()).forEach(lineNum -> biConsumer.accept(lineNum, csvRecords.get(lineNum)));

        String destContent = null;
        if (type.equalsIgnoreCase("xml")){
            destContent = XmlUtils.toXml(report);
        }else if (type.equalsIgnoreCase("json")){
            destContent = JsonUtils.toJson(report);
        }

        Files.write(destContent.getBytes(StandardCharsets.UTF_8.name()), destination);
        log.warn("Created successfully " + destination.getAbsolutePath());
        return destContent;
    }

    /**
     * Get Reference from Map
     * @param record
     * @return Reference
     * @throws Exception
     */
    protected Reference getReference(Map<String, String> record) throws Exception {
        return Reference.builder()
                .size(getField(record, COL_SIZE, Integer::valueOf))
                .numReference(getField(record, COL_NUM_REFERENCE, Integer::valueOf))
                .price(getField(record, COL_PRICE, Double::valueOf))
                .color(getField(record, COL_COLOR, Color::valueOf)).build();
    }

    /**Extract value of field, and remove illegale chars, convert to T
     *
     * @param record
     * @param field
     * @param function
     * @param <T>
     * @return T
     * @throws Exception
     */
    private <T> T getField(Map<String, String> record, String field, Function<String, T> function) throws Exception {
        try {
            String str = record.get(field).trim()
                    .replaceAll( "([\\u2028\\ud800-\\udbff\\udc00-\\udfff])", "");
            return function.apply(str);
        } catch (Exception e){
            throw new Exception("Incorrect value for " + field);
        }
    }

}
