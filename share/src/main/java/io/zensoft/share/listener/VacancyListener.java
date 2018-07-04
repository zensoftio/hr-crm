package io.zensoft.share.listener;

import io.zensoft.share.dto.VacancyDto;

public interface VacancyListener {

    void publish(VacancyDto vacancyDto);

    void getInfo(VacancyDto vacancyDto);
}