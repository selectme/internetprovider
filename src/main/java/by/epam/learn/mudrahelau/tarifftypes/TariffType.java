package by.epam.learn.mudrahelau.tarifftypes;

/**
 * @author Viktar on 26.12.2019
 */

//todo может вообще убрать выбор типа ТП?
public enum TariffType {

    UNLIM("unlim"),
    LIMIT("limit");

    public final String label;

    private TariffType(String label){
        this.label = label;
    }

}
