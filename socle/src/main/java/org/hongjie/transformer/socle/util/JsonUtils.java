package org.hongjie.transformer.socle.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j;

@Log4j
public class JsonUtils {

    /** Serialize the POJO to JSON
     *
     * @param report
     * @return content of JSON
     */
    public static String toJson(Object report){
        String json = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            json = mapper.writeValueAsString(report);
        } catch (JsonProcessingException e) {
            log.error("Error when convert to json:", e);
        }

        return json;
    }

}
