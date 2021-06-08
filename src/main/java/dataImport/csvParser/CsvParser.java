package dataImport.csvParser;

import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public  List<CandleData> parse(String csvFilename) throws FileNotFoundException {
        List<CandleData> candleDataList = new ArrayList<>();

        List<CandleDataBean> beans = new CsvToBeanBuilder(new FileReader(csvFilename))
                .withType(CandleDataBean.class).build().parse();

        for (CandleDataBean candleDataBean : beans
        ) {
            candleDataList.add(zmien(candleDataBean));
        }
        return candleDataList;
    }
    private static CandleData zmien(CandleDataBean cdb){
        CandleData candleData = new CandleData();
        candleData.setClose(cdb.getClose());
        candleData.setCloseTime(CsvParserUtil.convert(cdb.getCloseTime()));
        candleData.setHigh(cdb.getHigh());
        candleData.setIgnore(cdb.getIgnore());
        candleData.setLow(cdb.getLow());
        candleData.setNumberOfTrades(cdb.getNumberOfTrades());
        candleData.setOpenTime(CsvParserUtil.convert(cdb.getOpenTime()));
        candleData.setOpen(cdb.getOpen());
        candleData.setVolume(cdb.getVolume());
        candleData.setQuoteAssetVolume(cdb.getQuoteAssetVolume());
        candleData.setTakerBuyBaseAssetVolume(cdb.getTakerBuyBaseAssetVolume());
        candleData.setTakerBuyQuoteAssetVolume(cdb.getTakerBuyQuoteAssetVolume());

        return candleData;
}


}
