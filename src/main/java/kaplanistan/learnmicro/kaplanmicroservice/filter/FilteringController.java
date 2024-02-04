package kaplanistan.learnmicro.kaplanmicroservice.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import kaplanistan.learnmicro.kaplanmicroservice.model.FilterModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilteringController {

    @GetMapping("/getOne")

    public MappingJacksonValue getOne() {
        FilterModel filterModel = new FilterModel("Ahmet", "Mehmet", "Hasan");
        MappingJacksonValue jacksonValue = new MappingJacksonValue(filterModel);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");
        FilterProvider filterProvide = new SimpleFilterProvider().addFilter("KaplanFilter", filter);
        jacksonValue.setFilters(filterProvide);

        return jacksonValue;
    }

    @GetMapping("/getAll")
    public List<FilterModel> getAll() {
        return List.of(new FilterModel("Ahmet", "Mehmet", "Hasan"),
                new FilterModel("Seda", "Zeynep", "Merve"),
                new FilterModel("Ali", "Veli", "Selami"));
    }
}
