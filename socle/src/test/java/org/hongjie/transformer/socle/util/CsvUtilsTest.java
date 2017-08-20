package org.hongjie.transformer.socle.util;

import lombok.val;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hongjie.transformer.socle.util.Constant.*;

public class CsvUtilsTest {

    @Test
    public void getRecords() throws Exception {

        File file = ResourceUtils.getResource("./test.txt").toFile();

        val records = new CsvUtils().getRecords(file, COL_SEPERATOR, COL_NUM_REFERENCE, COL_COLOR, COL_PRICE, COL_SIZE);

        assertThat(records.size()).isEqualTo(5);
        val firstRecord = records.get(0);
        assertThat(firstRecord.get(COL_NUM_REFERENCE)).isEqualTo("1460100040");
        assertThat(firstRecord.get(COL_COLOR)).isEqualTo("R");
        assertThat(firstRecord.get(COL_PRICE)).isEqualTo("45.12");
        assertThat(firstRecord.get(COL_SIZE)).startsWith("27");

    }

}