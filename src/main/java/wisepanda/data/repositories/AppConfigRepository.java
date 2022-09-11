package wisepanda.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wisepanda.common.Constants;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.service.AppConfigService;

public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {
    
    List<AppConfig> findByApplication(String application);
    

    @Query("SELECT count(a.id) FROM AppConfig a")
    Integer getCount();
}
