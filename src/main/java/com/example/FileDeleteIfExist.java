package com.example;

import java.io.File;

public class FileDeleteIfExist {
    public static void deleteFiles(String directoryPath) {
        // 创建 File 对象
        File directory = new File(directoryPath);

        // 判断目录是否存在
        if (directory.exists()) {
            // 判断是否为目录
            if (directory.isDirectory()) {
                // 删除目录及其内容
                deleteDirectory(directory);
                System.out.println("目录已删除");
            } else {
                System.out.println("指定路径不是一个目录");
            }
        } else {
            System.out.println("目录不存在");
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


