package io.zensoft.share.model;

/**
 * Created by temirlan on 7/5/18.
 */
public enum RequirementType {
    REQUIRED, OPTIONAL, GENERAL;

    public static RequirementType getByString(String s) {
        if (REQUIRED.name().equals(s)) {
            return REQUIRED;
        }
        if (OPTIONAL.name().equals(s)) {
            return OPTIONAL;
        }
        if (GENERAL.name().equals(s)) {
            return GENERAL;
        }
        return null;
    }

    public String getText() {
        if (this.equals(REQUIRED)) {
            return "Основные требования";
        }
        if (this.equals(OPTIONAL)) {
            return "Плюсом будет";
        }
        if (this.equals(GENERAL)) {
            return "Владение / наличие следующих навыков и знаний определят Ваш квалификационный уровень";
        }
        return "";
    }
}
