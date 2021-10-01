package com.company;

import com.company.forms.StartForm;
import com.company.objects.Setting;

import java.io.File;
import java.util.ArrayList;

import static com.company.CONSTANTS.SETTINGS_FILE_PATH;
import static com.company.engine.XMLsupport.*;

public class Main {

    public static void main(String[] args) {

        File SettingsFile = new File(SETTINGS_FILE_PATH);
        if (!SettingsFile.exists()) {
            writeSettingsInXML(SETTINGS_FILE_PATH, true);
        }
        StartForm startForm = new StartForm();

    }
}
