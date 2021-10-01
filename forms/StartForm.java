package com.company.forms;

import com.company.objects.Setting;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.company.CONSTANTS.SETTINGS_FILE_PATH;
import static com.company.engine.XMLsupport.readSettingsInXML;
import static com.company.engine.Utill.*;

public class StartForm extends JFrame {

    public StartForm() throws HeadlessException {

        super("start form");

        ArrayList<Setting> settings = readSettingsInXML(SETTINGS_FILE_PATH);

        setSize(Integer.valueOf(findSetting(settings, "screenWidth").getValue()),
                Integer.valueOf(findSetting(settings, "screenHeight").getValue()));

        if (Integer.valueOf(findSetting(settings, "fullScreenMode").getValue()) == 1) {
            setUndecorated(true);
        } else if (Integer.valueOf(findSetting(settings, "fullScreenMode").getValue()) == 1) {
            setUndecorated(false);
            setLocation(Integer.valueOf(findSetting(settings, "screenWidth").getValue()) / 2 - Integer.valueOf(findSetting(settings, "screenWidth").getValue()),
                    Integer.valueOf(findSetting(settings, "screenHeight").getValue()) / 2 - Integer.valueOf(findSetting(settings, "screenHeight").getValue()));
        }

        setVisible(true);

    }

}
