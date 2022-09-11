package wisepanda.controller.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.app.ServiceResponse;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.service.AppConfigService;

@RestController
@RequestMapping(ApiConstants.REST_URL_APP_ROOT)
public class AppConfigRestApi {
    
    @GetMapping(ApiConstants.REST_URL_REFRESH_CACHE)
    public ResponseEntity<ServiceResponse> refreshCache() throws WiseNoteException {
        ServiceResponse s = new ServiceResponse();

        AppConfigService.refreshCache();
        s.setHttpStatus(HttpStatus.OK);
        s.setResult("App Cache Refreshed Successfully.");
        
        return new ResponseEntity<>(s, s.getHttpStatus());
    }
}
