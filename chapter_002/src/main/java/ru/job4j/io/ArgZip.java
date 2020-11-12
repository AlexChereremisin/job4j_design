package ru.job4j.io;

import java.util.regex.Pattern;

public class ArgZip {

    private final String[] args;
    private final boolean validFlag;

    public ArgZip(String[] args) {
        this.args = args;
        this.validFlag = this.valid();
    }

    public boolean valid() {
        if (this.validFlag) {
            return this.validFlag;
        }
        if (this.args == null || this.args.length != 3) {
            throw new IllegalArgumentException(
                    "Parameters must be -d=PATH/TO/DIRECTORY -e=EXCLUDE_FILE_TYPE -o=OUTPUT/PATH"
            );
        }
        Pattern pattern = Pattern.compile("^-[deo]=");
        for (String str : this.args) {
            if (!pattern.matcher(str).find()) {
                throw new IllegalArgumentException(
                        "Parameters must be -d=PATH/TO/DIRECTORY -e=EXCLUDE_FILE_TYPE -o=OUTPUT/PATH"
                );
            }
        }
        return true;
    }

    public String directory() {
        return this.findElementByKey("-d").split("=")[1];
    }

    public String exclude() {
        return this.findElementByKey("-e").split("=")[1];
    }

    public String output() {
        return this.findElementByKey("-o").split("=")[1];
    }

    private String findElementByKey(String key) {
        String result = null;
        for (String str : this.args) {
            if (str.startsWith(key)) {
                result = str;
                break;
            }
        }
        return result;
    }
}
