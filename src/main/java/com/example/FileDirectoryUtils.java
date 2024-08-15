package com.example;

import java.io.File;

public class FileDirectoryUtils {
    // 删除目录及其内容的方法
    public static void deleteDirectory(File directory) {
        // 目录中的所有文件和子目录
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file); // 递归删除子目录
                } else {
                    file.delete(); // 删除文件
                }
            }
        }
        directory.delete();
    }
}

