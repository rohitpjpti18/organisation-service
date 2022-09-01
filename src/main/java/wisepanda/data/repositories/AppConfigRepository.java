package wisepanda.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wisepanda.data.entities.application.AppConfig;

public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {
    
    List<AppConfig> findByApplication(String application);
}
