package com.notmarra.notcore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Updater {

    /**
     * Checks if the plugin is up to date.
     *
     * @param plugin_version Plugin version.
     * @param github Link to the api of repo.
     * @return 2 failed fetching version, 1 is up to date, 0 is not up to date.
     */
    public int isUpToDate(String plugin_version, String github) {
        String latest_version = getResponse(github);

        if (latest_version == null) {
            return 2;
        } else if (latest_version.equals(plugin_version) || Double.parseDouble(latest_version) < Double.parseDouble(plugin_version)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get latest version from github.
     * @param github Link to the api of repo.
     * @return Latest version.
     */
    public String getLatestVersion(String github) {
        return getResponse(github);
    }

    private String getResponse(String url) {
        try {
            String realUrl = url;
            HttpURLConnection connection = (HttpURLConnection) new URL(realUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            String response = stringBuilder.toString();
            if (response.contains("\"message\":\"Not Found\"")) {
                return null;
            }
            String version = response.substring(response.indexOf("\"tag_name\":\"") + 12, response.indexOf("\",\"target_commitish\""));
            if (version.startsWith("v")) {
                version = version.substring(1);
                return version;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
