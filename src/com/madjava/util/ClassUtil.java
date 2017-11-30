package com.madjava.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ClassUtil {
    /**获取指定包名下的所有类
     * @param packageName
     * @param isRecursive
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClassList(String packageName,boolean isRecursive) throws ClassNotFoundException{
        List<Class<?>> classList = new ArrayList<>();
        try {
            //获取url枚举
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    //获取URL协议名称
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath();
                        addClass(classList, packagePath, packageName, isRecursive);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while(jarEntries.hasMoreElements()){
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
             if (jarEntryName.endsWith(".class")) {
                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
              if (isRecursive || className.substring(0, className.lastIndexOf(".")).equals(packageName)) {
                      classList.add(Class.forName(className));
                                   }
                              }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }
        //获取指定包名下指定注解的所有类
    
        //获取包名下指定父类的所有类
    
        // 获取指定包名下指定接口的所有实现类
    
    /**
     * 把class添加到指定的集合中
     * @throws ClassNotFoundException 
     */
    public static void addClass(List<Class<?>> classList,String packagePath,String packageName,boolean isRecursive) throws ClassNotFoundException{
        //通过包路径获取类文件
        File[] files = getClassFiles(packagePath);
        if(files != null){
            for(File file: files){
                String fileName = file.getName();
                if(file.isFile()){
                    String className = getClassName(packageName,fileName);
                    classList.add(Class.forName(className));
                }else{
                    if(isRecursive){
                        String subPackagePath = getSubPackagePath(packagePath,fileName); 
                        String subPackageName = getSubPackageName(packageName,fileName);
                        addClass(classList,subPackagePath,subPackageName,isRecursive);
                    }
                }
            }
        }
    }



    /**
     * 通过文件过滤器（匿名函数，用到了策略模式），获取路径下的类文件及文件夹，
     * @param packagePath
     * @return
     */
    private static File[] getClassFiles(String packagePath) {
        
        return new File(packagePath).listFiles(new FileFilter(){

            @Override
            public boolean accept(File file) {
                
                return (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory();
            }
            
        });
    }
    
    /**
     * 获取类文件全限定名
     * @param packageName
     * @param fileName
     * @return
     */
    private static String getClassName(String packageName,String fileName) {
        String className = fileName.substring(0,fileName.lastIndexOf("."));
        if(packageName != null){
            className = packageName + "." + className;
        }
        return className;
    }
    
    
    /**获取子路径
     * @param packagePath
     * @param fileName
     * @return
     */
    private static String getSubPackagePath(String packagePath,
            String fileName) {
        return packagePath + "/" + fileName;
    }
    
    /**获取子包名
     * @param packageName
     * @param fileName
     * @return
     */
    private static String getSubPackageName(String packageName, String fileName) {
        return packageName + "." + fileName;
    }
}