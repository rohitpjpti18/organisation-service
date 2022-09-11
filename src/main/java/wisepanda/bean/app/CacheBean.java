package wisepanda.bean.app;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import wisepanda.bean.lazy.model.AppConfigLazyDataModel;
import wisepanda.config.WiseNoteBeanConfig;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.data.repositories.AppConfigRepository;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.service.AppConfigService;
import wisepanda.service.FacesService;

@Component
@ManagedBean
@RequestScoped
public class CacheBean implements Serializable{
    private static final Logger log = LogManager.getLogger(CacheBean.class);
    
    private LazyDataModel<AppConfig> result;
    private AppConfig selectedAppConfig;

    @Autowired
    private GeneralDao dao;

    @Autowired
    private AppConfigRepository appConfig;

    private AppConfig newValue;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    public FacesService faces;

    CacheBean() {
        log.debug("CONSTRUCTOR: CacheBean");
    }

    @PostConstruct
    public void init() {
        if(appConfig == null) {
            dao = WiseNoteBeanConfig.getBean(GeneralDao.class);
            appConfig = WiseNoteBeanConfig.getBean(AppConfigRepository.class);
        }
        System.out.print("Here");
        //result = dao.getAppConfig(1, 100000);
        result = new AppConfigLazyDataModel(appConfig.findAll());

        newValue = new AppConfig();
    }

    public void addValue() {
        log.info("here");
        try {
            newValue = appConfigService.add(newValue);
        } catch (WiseNoteException e) {
            e.printStackTrace();
        }
        faces.addMessage("Added New Value", "New Value has been added", FacesMessage.SEVERITY_INFO);
        result = new AppConfigLazyDataModel(appConfig.findAll());
        newValue = new AppConfig();
    }

    public void onRowSelect(SelectEvent<AppConfig> event) {
        FacesMessage msg = new FacesMessage("Config Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage("messages", msg);
    }    

    public LazyDataModel<AppConfig> getResult(int page) {
        return result;
    }

    public LazyDataModel<AppConfig> getResult() {
        return result;
    }

    public void setResult(LazyDataModel<AppConfig> result) {
        this.result = result;
    }

    public AppConfig getSelectedAppConfig() {
        return selectedAppConfig;
    }

    public void setSelectedAppConfig(AppConfig selectedAppConfig) {
        this.selectedAppConfig = selectedAppConfig;
    }

    public AppConfig getNewValue() {
        return newValue;
    }

    public void setNewValue(AppConfig newValue) {
        this.newValue = newValue;
    }
}
