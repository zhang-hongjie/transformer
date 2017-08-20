package org.hongjie.transformer.socle.util;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.hongjie.transformer.socle.data.bean.Color;
import org.hongjie.transformer.socle.data.bean.Erreur;
import org.hongjie.transformer.socle.data.bean.Reference;
import org.hongjie.transformer.socle.data.bean.Report;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    @Test
    public void toJson() throws Exception {

        val report = Report.builder()
                .references(ImmutableList.of(
                        Reference.builder()
                                .numReference(1234567890)
                                .color(Color.B)
                                .price(52.20d)
                                .size(82)
                                .build()
                ))
                .errors(ImmutableList.of(
                        Erreur.builder()
                                .line(3)
                                .message("Incorrect value for size")
                                .content("1234567890;R;32.25;72.2B33")
                                .build()
                ))
                .inputFile("/path/csv.txt")
                .build();

        String json = JsonUtils.toJson(report);

        assertThat(json).isEqualTo("{\n" +
                "  \"inputFile\" : \"/path/csv.txt\",\n" +
                "  \"references\" : [ {\n" +
                "    \"color\" : \"B\",\n" +
                "    \"price\" : 52.2,\n" +
                "    \"size\" : 82,\n" +
                "    \"numReference\" : 1234567890\n" +
                "  } ],\n" +
                "  \"errors\" : [ {\n" +
                "    \"line\" : 3,\n" +
                "    \"message\" : \"Incorrect value for size\",\n" +
                "    \"content\" : \"1234567890;R;32.25;72.2B33\"\n" +
                "  } ]\n" +
                "}");
    }

}