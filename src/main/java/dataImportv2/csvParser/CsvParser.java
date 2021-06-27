package dataImportv2.csvParser;

import com.opencsv.bean.CsvToBeanBuilder;
import dataImportv2.ImportConstants;
import hibernate.entities.Pair_Candle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CsvParser {
    private List<Pair_Candle> pair_candleList = new LinkedList<>();


    private void parse() {
        List<RawCandleData> rawData = new LinkedList<>();
        try {
            rawData = new CsvToBeanBuilder(new FileReader(ImportConstants.CSV_FILE_NAME)).withType(RawCandleData.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (RawCandleData rcd : rawData) {
            pair_candleList.add(ParserUtil.migrate(rcd));
        }
    }

    public List<Pair_Candle> getPair_candleList() {
        parse();
        return pair_candleList;
    }
}
