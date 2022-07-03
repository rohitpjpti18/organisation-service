package wisepanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.CountryCodeDto;
import wisepanda.data.entities.contact.CountryCode;

@Service
public class GeneralOrgServiceJpaImpl {

    @Autowired
    private GeneralDao generalDao;

    public CountryCode addCountryCode(CountryCodeDto countryDto) throws Exception{
        CountryCode c = new CountryCode();
        countryDto.fill(c);
        return generalDao.countryCode.saveAndFlush(c);
    }
}
