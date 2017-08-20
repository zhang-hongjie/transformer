package org.hongjie.transformer.socle.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j;

@Log4j
public class XmlUtils {

    /**Serialize the POJO to xml
     *
     * @param report
     * @return content of XML
     */
    public static String toXml(Object report) {
        String xml = null;

        XmlMapper mapper = new XmlMapper();
        try {
            xml = mapper.writeValueAsString(report);
        } catch (JsonProcessingException e) {
            log.error("Erreur when convert to xml:", e);
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
    }

}
