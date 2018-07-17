package io.zensoft.share.service.jobkg.builder;

import io.zensoft.share.model.Requirement;
import io.zensoft.share.model.RequirementType;
import io.zensoft.share.model.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by temirlan on 7/12/18.
 */
@Service
@Slf4j
public class DefaultJobKgVacancyPublicationContentBuilderService implements JobKgVacancyPublicationContentBuilderService {

    @Override
    public MultiValueMap<String, String> build(Vacancy vacancy) {
        log.info("Preparing content with Vacancy body " + vacancy);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Vacancy[name]", vacancy.getPosition());
        //programming category
        map.add("VacancySpecialty[701][category_id]", "701");
        map.add("Vacancy[_category]", "701");
        map.add("Vacancy[description]", vacancy.getTitle());
        map.add("Vacancy[qualification]", prepareRequirements(vacancy.getRequirements()));
        map.add("Vacancy[duties]", vacancy.getResponsibilities());
        map.add("Vacancy[conditions]", prepareListOfString(vacancy.getWorkConditions()));
        //Bishkek region
        map.add("VacancyGeoplaces[0][geoplace_id]", "996066");
        map.add("Vacancy[_geoplaces]", "996066");
        //saves in archive
        map.add("yt0", "Сохранить в архив");
        return map;
    }

    private String prepareRequirements(List<Requirement> requirements) {
        StringBuffer required = new StringBuffer(RequirementType.REQUIRED.getText() + ":\n");
        StringBuffer general = new StringBuffer(RequirementType.GENERAL.getText() + ":\n");
        StringBuffer optional = new StringBuffer(RequirementType.OPTIONAL.getText() + ":\n");
        for (Requirement requirement : requirements) {
            if (requirement.getType().equals(RequirementType.REQUIRED)) {
                required.append("• " + requirement.getName() + "\n");
            }
            if (requirement.getType().equals(RequirementType.OPTIONAL)) {
                optional.append("• " + requirement.getName() + "\n");
            }
            if (requirement.getType().equals(RequirementType.GENERAL)) {
                general.append("• " + requirement.getName() + "\n");
            }
        }
        required.append("\n");
        required.append(general.toString());
        required.append("\n");
        return required.append(optional.toString()).toString();
    }

    private String prepareListOfString(List<String> list) {
        StringBuffer result = new StringBuffer();
        for (String s : list) {
            result.append("• " + s);
        }
        return result.toString();
    }
}
