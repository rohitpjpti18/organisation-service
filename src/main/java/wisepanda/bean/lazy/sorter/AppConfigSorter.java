package wisepanda.bean.lazy.sorter;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import wisepanda.data.entities.application.AppConfig;
import wisepanda.utils.WisenoteUtil;

public class AppConfigSorter implements Comparator<AppConfig> {
    private String sortField;
    private SortOrder sortOrder;

    public AppConfigSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(AppConfig config1, AppConfig config2) {
        try {
            Object value1 = WisenoteUtil.getPropertyValueViaReflection(config1, sortField);
            Object value2 = WisenoteUtil.getPropertyValueViaReflection(config2, sortField);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch(Exception e) {    
            throw new RuntimeException(e);
        }
    }
}
