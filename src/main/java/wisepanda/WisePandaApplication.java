package wisepanda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import wisepanda.service.AppConfigService;

@SpringBootApplication
public class WisePandaApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(WisePandaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AppConfigService.loadConfig();
		
	}
}
