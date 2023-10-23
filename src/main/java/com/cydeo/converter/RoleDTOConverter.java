package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;//Bu converter
import org.springframework.stereotype.Component;

@Component //Buranin da Bean icin create edildigini belirtiyoruz.
@ConfigurationPropertiesBinding //Burada Spring ten yardim istiyoruz, ve diyoruz ki
//when app is initiated, bu converter i da o zaman calistir. Cünkü ben aksi halde
//hata ile karsilasacagim bunu convert etmezsem.
public class RoleDTOConverter implements Converter<String, RoleDTO> {

    RoleService roleDTOService;//Interface üzerinden wiring / injection
//yapiyoruz.

//Injecting
    public RoleDTOConverter(RoleService roleDTOService) {
        this.roleDTOService = roleDTOService; //Autowiring icin
//constructor i kullaniyoruz.
    }

    @Override
    public RoleDTO convert(String source) {
//Son olarak => view den gelen role id String, ama RoleDTO objesinin
//id si Long olarak tanimlanmistir. View dan java ya gelen role id yi
//Long a parse ediyoruz.

        /*
        view daki id kismi value da String olarak yer aliyor.
        <option value="2">Manager</option>
         */

        if (source == null || source.equals("")) {

            return null;
        }

        return roleDTOService.findById(Long.parseLong(source));
//Peki bu method ne zaman run edecek? => Bunun icin Spring den @ConfigurationPropertiesBinding
// kullaniyoruz. Whenever the app is up, Spring runs this method at the same time.
    }

}
