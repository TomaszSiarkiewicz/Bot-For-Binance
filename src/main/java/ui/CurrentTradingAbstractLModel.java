package ui;

import bot.Trade;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class CurrentTradingAbstractLModel extends AbstractListModel {
    List<Trade> currentlyTrading;
    String[] string;
    public CurrentTradingAbstractLModel(List<Trade> currentlyTrading) {
        currentlyTrading.add(new Trade(BigDecimal.ZERO, BigDecimal.ONE));
        currentlyTrading.add(new Trade(BigDecimal.ZERO, BigDecimal.ONE));
        currentlyTrading.add(new Trade(BigDecimal.ZERO, BigDecimal.ONE));
        currentlyTrading.add(new Trade(BigDecimal.ZERO, BigDecimal.ONE));
        this.currentlyTrading = currentlyTrading;
        string = new String[currentlyTrading.size()];
        for (int i = 0; i < currentlyTrading.size(); i++){
            string[i] = currentlyTrading.get(i).toString();
        }
    }


    @Override
    public int getSize() {
        return string.length;
    }

    @Override
    public String getElementAt(int index) {
        return string[index];
    }
}
