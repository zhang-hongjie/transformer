package org.hongjie.transformer.socle.util;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.hongjie.transformer.socle.data.bean.Color;
import org.hongjie.transformer.socle.data.bean.Erreur;
import org.hongjie.transformer.socle.data.bean.Reference;
import org.hongjie.transformer.socle.data.bean.Report;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlUtilsTest {

    @Test
    public void toXml() throws Exception {

        val report = Report.builder()
                .references(ImmutableList.of(
                        Reference.builder()
                                .numReference(1234567890)
                                .color(Color.B)
                                .price(52.20d)
                                .size(82).build()
                ))
                .errors(ImmutableList.of(
                        Erreur.builder()
                                .line(3)
                                .message("Incorrect value for size")
                                .content("1234567890;R;32.25;72.2B33").build()
                ))
                .inputFile("/path/test.txt")
                .build();

        String json = XmlUtils.toXml(report);

        assertThat(json).isXmlEqualTo("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<report>\n" +
                "   <inputFile>/path/test.txt</inputFile>\n" +
                "   <references>\n" +
                "      <reference>\n" +
                "         <color>B</color>\n" +
                "         <price>52.2</price>\n" +
                "         <size>82</size>\n" +
                "         <numReference>1234567890</numReference>\n" +
                "      </reference>\n" +
                "   </references>\n" +
                "   <errors>\n" +
                "      <error>\n" +
                "         <line>3</line>\n" +
                "         <message>Incorrect value for size</message>\n" +
                "         <content>1234567890;R;32.25;72.2B33</content>\n" +
                "      </error>\n" +
                "   </errors>\n" +
                "</report>");
    }



}