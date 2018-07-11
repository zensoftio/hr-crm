package io.zensoft.share.model;

/**
 * Created by temirlan on 7/5/18.
 */
public enum RequirementType {
    REQUIRED, HARD_SKILLS, ADDITIONAL;

    public static RequirementType getByInt(int i) {
        if (i == 0) {
            return REQUIRED;
        }
        if (i == 1) {
            return HARD_SKILLS;
        }
        if (i == 2) {
            return ADDITIONAL;
        }
        return null;
    }

}
