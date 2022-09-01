package wisepanda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import wisepanda.common.Constants;
import wisepanda.config.WiseNoteBeanConfig;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.data.repositories.AppConfigRepository;

public class AppConfigService {
    private static final Logger log = LogManager.getLogger(AppConfigService.class);

    public static Map<String, Map<String, String>> appConfigMap = new HashMap<>();   

    @Autowired
    public static AppConfigRepository appRepository;

    public static void loadConfig() {
        log.info("Config Data Loading Started.");
        log.info("APPLICATION NAME: {}", Constants.APP_NAME);
        if(appRepository == null) {
            appRepository = WiseNoteBeanConfig.getBean(AppConfigRepository.class);
        }
        List<AppConfig> configs = appRepository.findByApplication(Constants.APP_NAME);
        
        for(AppConfig a: configs) {
            Map<String, String> result = appConfigMap.getOrDefault(a.getGroup(), new HashMap<>());
            result.put(a.getKey(), a.getValue());
            appConfigMap.put(a.getGroup(), result);
            log.info("Group: {} || key: {} | value: {} ", a.getGroup(), a.getKey(), a.getValue());
        }
        log.info("Config Data Loading Completed.");

    }
}
