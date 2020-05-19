package prelucrare;

import java.util.List;

public class BuilderStatisticaPrajitura {

    public StatisticaPrajituri creareStatisticaPrajituri(List<model.Prajitura> prajituraLista){
        StatisticaPrajituri statisticaPrajituri= new StatisticaPrajituri();
        for(model.Prajitura p : prajituraLista) {
            statisticaPrajituri.AdaugarePrajitura(p);
        }
        return statisticaPrajituri;
    }
}
