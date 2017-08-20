package org.hongjie.transformer.socle;

import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.hongjie.transformer.socle.data.bean.Color;
import org.hongjie.transformer.socle.util.ResourceUtils;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hongjie.transformer.socle.util.Constant.*;

public class WorkerTest {

    @Test
    public void should_getReference() throws Exception {
        val map = ImmutableMap.<String, String>builder()
                .put(COL_NUM_REFERENCE, "1234567890")
                .put(COL_COLOR, "R")
                .put(COL_PRICE, "33.21")
                .put(COL_SIZE, "56")
                .build();

        val reference = new Worker().getReference(map);

        assertThat(reference.getColor()).isEqualTo(Color.R);
        assertThat(reference.getNumReference()).isEqualTo(1234567890);
        assertThat(reference.getPrice()).isEqualTo(33.21);
        assertThat(reference.getSize()).isEqualTo(56);
    }

    @Test
    public void should_exception_when_getReference_with_invalid_color() throws Exception {
        val map = ImmutableMap.<String, String>builder()
                .put(COL_NUM_REFERENCE, "1234567890")
                .put(COL_COLOR, "AAA")
                .put(COL_PRICE, "33.21")
                .put(COL_SIZE, "56")
                .build();

        assertThatThrownBy(() -> new Worker().getReference(map))
                .isInstanceOf(Exception.class)
                .hasMessage("Incorrect value for color");
    }

    @Test
    public void should_process_to_xml() throws Exception {
        File file = ResourceUtils.getResource("./test.txt").toFile();

        val actual = new Worker().process(file, "xml", new File("/tmp/a.xml"));

        assertThat(actual).isXmlEqualToContentOf(ResourceUtils.getResource("./test.xml").toFile());
    }

    @Test
    public void should_process_to_json() throws Exception {
        File file = ResourceUtils.getResource("./test.txt").toFile();

        val actual = new Worker().process(file, "json", new File("/tmp/a.json"));

        assertThat(actual).isEqualTo(ResourceUtils.readResource("./test.json"));
    }

}