package com.notmarra.notcore.utils;

import java.io.*;

public class File {

    /**
     * Create a file in location if it doesn't exist.
     *
     * @param location Location of file.
     *                 Example: plugins/NotCore/config.yml
     * @return True if file was created, false if file already exists.
     */

    public boolean createFile(String location) {
        java.io.File file = new java.io.File(location);
        if (!file.exists()) {
            try {
                java.io.File parentDir = file.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                return file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Copy a content from resources to file.
     *
     * @param location Location of file.
     *                 Example: plugins/NotCore/config.yml
     * @param resource Resource to copy from.
     *                 Example: config.yml
     * @return True if file was copied, false if filed.
     */

    public void copyResource(String location, String resource) {
        java.io.File file = new java.io.File(location);
        if (!file.exists() && createFile(location)) {
            try {
                InputStream inputStream = getClass().getResourceAsStream(resource);
                OutputStream outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
