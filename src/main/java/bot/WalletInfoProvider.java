package bot;

import api.client.model.spot.wallet.CoinsInWalletInfo;

import java.math.BigDecimal;
import java.util.List;

public class WalletInfoProvider implements Runnable{

    private List<CoinsInWalletInfo> coinsInWallet;
    private final BinanceDataRepository dataRepository;
    private boolean run = true;

    public WalletInfoProvider(BinanceDataRepository dataRepository) {
        this.dataRepository = dataRepository;
        initData(dataRepository);
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(" ----WALLET UPDATE----");
            dataRepository.updateCoins();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private void initData(BinanceDataRepository dataRepository){
        coinsInWallet =  dataRepository.updateCoins();
    }
    public List<CoinsInWalletInfo> getAvailableCoins(){
        coinsInWallet.removeIf(i -> (i.getFree().doubleValue() > 0)|| (i.getLocked().doubleValue() > 0));
        return coinsInWallet;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}