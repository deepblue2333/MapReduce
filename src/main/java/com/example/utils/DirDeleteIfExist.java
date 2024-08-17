package com.example.utils;

import java.io.File;
import org.apache.log4j.Logger;

public class DirDeleteIfExist {
    // 获取日志记录器实例
    private static final Logger logger = Logger.getLogger(DirDeleteIfExist.class);

    public static void deleteDir (String directoryPath) {
        // 创建 File 对象
        File directory = new File(directoryPath);

        // 判断目录是否存在
        if (directory.exists()) {
            // 判断是否为目录
            if (directory.isDirectory()) {
                // 删除目录及其内容
                deleteDirectory(directory);
                logger.info("Deleted " + directory.getAbsolutePath());
            } else {
                logger.warn("Directory is not a directory");
            }
        } else {
            logger.warn("Directory " + directory.getAbsolutePath() + " does not exist");
        }

    }

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

