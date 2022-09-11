package wisepanda.bean.lazy.model;

import java.beans.IntrospectionException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.ComparatorUtils;

import wisepanda.bean.lazy.sorter.AppConfigSorter;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.utils.LocaleUtil;
import wisepanda.utils.WisenoteUtil;


public class AppConfigLazyDataModel extends LazyDataModel<AppConfig> {
    private static final Logger log = LogManager.getLogger(AppConfigLazyDataModel.class);
    
    @Autowired
    private GeneralDao dao;

    List<AppConfig> result;
    
    public AppConfigLazyDataModel(List<AppConfig> config) {
        result = config;
        //this.setRowCount((Integer) dao.appConfig.getCount());
    }

    @Override
    public AppConfig getRowData(String rowKey) {
        for(AppConfig a: result) {
            if(a.getId() == Integer.parseInt(rowKey)) return a;
        }

        return null;
    }

    @Override
    public String getRowKey(AppConfig config) {
        return String.valueOf(config.getId());
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        // Integer count = dao.appConfig.getCount();
        // log.info("Count: " + count);
        // return count;
        return (int) result.stream()
                    .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                    .count();
    }

    @Override
    public List<AppConfig> load(int offset, int pageSize, Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filterBy) {

        List<AppConfig> configs = result.stream()
                                    .skip(offset)
                                    .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                                    .limit(pageSize)
                                    .collect(Collectors.toList());

        if(!sortBy.isEmpty()) {
            List<Comparator<AppConfig>> comparators = sortBy.values().stream()
                                                            .map(o -> new AppConfigSorter(o.getField(), o.getOrder()))
                                                            .collect(Collectors.toList());
            Comparator<AppConfig> ac = ComparatorUtils.chainedComparator(comparators);
            configs.sort(ac);
        }
        //List<AppConfig> result = dao.getAppConfig(first, pageSize);
        return configs;
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for(FilterMeta filter: filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(WisenoteUtil.getPropertyValueViaReflection(o, filter.getField()));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtil.getCurrentLocale());
            } catch(ReflectiveOperationException | IntrospectionException e) {
                matching = false;
            }

            if(!matching) {
                break;
            }
        }

        return matching;
    }
}
