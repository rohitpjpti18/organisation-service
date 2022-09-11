package wisepanda.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wisepanda.common.Constants;
import wisepanda.config.WiseNoteBeanConfig;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.data.repositories.AppConfigRepository;
import wisepanda.exceptions.WiseNoteException;

@Service
public class AppConfigService {
    private static final Logger log = LogManager.getLogger(AppConfigService.class);

    public static Map<String, Map<String, String>> appConfigMap = new HashMap<>();   

    @Autowired
    public static AppConfigRepository appRepository;

    @Autowired
    public GeneralDao dao;

    public static void loadConfig() throws WiseNoteException{
        try {
            log.info("Config Data Loading Started.");
            log.info("APPLICATION NAME: {}", Constants.APP_NAME);
            if(appRepository == null) {
                appRepository = WiseNoteBeanConfig.getBean(AppConfigRepository.class);
            }
            List<AppConfig> configs = appRepository.findByApplication(Constants.APP_NAME);
            
            for(AppConfig a: configs) {
                Map<String, String> result = appConfigMap.getOrDefault(a.getKeyGroup(), new HashMap<>());
                result.put(a.getKey(), a.getValue());
                appConfigMap.put(a.getKeyGroup(), result);
                log.info("Group: {} || key: {} | value: {} ", a.getKeyGroup(), a.getKey(), a.getValue());
            }
            log.info("Config Data Loading Completed.");
        } catch(Exception e) {
            throw new WiseNoteException(e);
        }
        
    }

    public List<AppConfig> getAppConfigs(int number) {
        return dao.appConfig.findAll();
    }

    public AppConfig add(AppConfig newValue) throws WiseNoteException {
        newValue.setApplication(Constants.APP_NAME);
        //newValue.setId(1L);
        newValue.setCreatedBy(Constants.APP_NAME);
        newValue.setCreatedOn(Instant.now());
        validate(newValue);

        System.out.println(newValue);

        return dao.appConfig.saveAndFlush(newValue);
    }

    public void validate(AppConfig value) throws WiseNoteException {

    } 

    public static void refreshCache() throws WiseNoteException{
        loadConfig();
    }

    public static String get(String keyGroup, String key) {
        return appConfigMap.getOrDefault(keyGroup, new HashMap<>()).getOrDefault(key, "");
    }
}
