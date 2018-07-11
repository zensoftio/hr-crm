package io.zensoft.share.model;

/**
 * Created by temirlan on 7/10/18.
 */
public enum WorkingHours {
    FULL_TIME, FLEX_TIME, REMOTE_JOB;

    public static WorkingHours getByString(String text) {
        if (FULL_TIME.name().equals(text)) {
            return FULL_TIME;
        }
        if (FLEX_TIME.equals(text)) {
            return FLEX_TIME;
        }
        if (REMOTE_JOB.name().equals(text)) {
            return REMOTE_JOB;
        }
        return null;
    }

    public String getText() {
        if (this == FULL_TIME) {
            return "Полный рабочий день";
        }
        if (this == FLEX_TIME) {
            return "Гибкий график";
        }
        if (this == REMOTE_JOB) {
            return "Удаленная работа";
        }
        return "";
    }
}
