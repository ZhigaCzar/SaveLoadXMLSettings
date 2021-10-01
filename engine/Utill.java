package com.company.engine;

import com.company.objects.Setting;

import java.util.ArrayList;

public class Utill {

    public static Setting findSetting(ArrayList<Setting> settingList, String settingName) {

        Setting result = new Setting();

        for (Setting setting : settingList) {
            if (setting.getName() == settingName)
                result = setting;
        }

        return result;
    }

}
