/*
 * © 2018.Telekom Malaysia Berhad. ALL RIGHTS RESERVED. All content 
 * appearing on this site/manual/contract/page/document/book/e-book/video/
 * movie/sound track/song /etc are the exclusive property of Telekom Malaysia 
 * Berhad and are protected under the Copyright Act 1987.
 * 
 * You are respectfully reminded that any unauthorized capture or copying of these 
 * content by whatever means is an offence under the Copyright Act 1987. None of 
 * the content mentioned above may be directly or indirectly published, reproduced, 
 * copied, stored, manipulated, modified, sold, transmitted, redistributed, 
 * projected, used in any way or redistributed in any medium without the explicit 
 * permission of Telekom Malaysia Berhad.
 */
package com.tmrnd.cp.ocadminsvcs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anwar
 */
public class AdminUtils {
    
    private Logger log = LoggerFactory.getLogger(getClass().getName());

    public AdminUtils(){
        
    }

    String readTemplate(String templateFilePath) {
        
        String line = "";
        String retLine = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(templateFilePath);
            log.debug("template file path: " + templateFilePath);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                log.debug("reading line: " + line);
                retLine += line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            log.error(
                "Unable to open file '" + 
                templateFilePath + "'");                
        }
        catch(IOException ex) {
            log.error(
                "Error reading file '" 
                + templateFilePath + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        log.debug("retLine: " + retLine);
        return retLine;
    }

    String replaceVarsWith(String template, String user, String passwd, String level, String packageName) {
        
        log.debug("template: " + template);
        
        log.debug("user: " + user
                + "; passwd: " + passwd
                + "; level: " + level
                + "; packageName: " + packageName);
        
        user = user==null?"":user;
        passwd = passwd==null?"":passwd;
        level = level==null?"":level;
        packageName =packageName == null ? "":packageName;
        
        log.debug("user: " + user
                + "; passwd: " + passwd
                + "; level: " + level
                + "; packageName: " + packageName);
        
        String returnStr = template.replaceAll("\\$user_name", user)
                .replaceAll("\\$user_passwd", passwd)
                .replaceAll("\\$user_level", level);
        
        if(returnStr.contains("\\$user_package")){
            returnStr = returnStr.replaceAll("\\$user_package", packageName);
        }
        log.debug("returnStr: " + returnStr);
        return returnStr;
        
                
    }

    void writeVarsFile(String writeToPath, String filename, String vars) {
        try{
            FileWriter write = new FileWriter(writeToPath + "/" + filename, false);
            PrintWriter printer = new PrintWriter(write);
            printer.print(vars);
            printer.flush();
            printer.close();
        }catch(IOException ex){
            log.error("Error writing to " + writeToPath + "/" + filename);
        };
    }

    ProjectTemplate createFromTemplate(String user, String passwd, String level, String packageName) {
        ProjectTemplate templ = null;
        templ = new ProjectTemplate();
        Map<String, String> projectMap = templ.getProject();
        projectMap.put("admin", user);
        projectMap.put("name", user);
        projectMap.put("passwd", passwd);
        projectMap.put("level", level);
        projectMap.put("packageName", packageName);
        
        return templ;
    }

    void writeToFile(String filePath, ProjectTemplate templ) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try{
            mapper.writeValue(new File(filePath), templ);
        }catch(Exception ex){
            log.error("Exception while writing info to file: ", ex);
        }
    }

    ProjectTemplate readUserFile(String filepath, String user, boolean isTest) {
        ProjectTemplate templ = null;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ClassLoader classLoader = getClass().getClassLoader();
        log.debug("Userfile is at: " + filepath);
	File file = null;
        if(isTest){
            file = new File(classLoader.getResource(filepath).getFile());
        }else{
            file = new File(filepath);
        }

        if(file != null){
            log.debug("File: " + file.getAbsolutePath());
            try{
                templ = mapper.readValue(file, ProjectTemplate.class);

            }catch(Exception ex){
                log.error("Exception while reading user file: ", ex);
            }
        }else{
            log.error("Error finding file");
        }
        
        return templ;
    }

    String runShellCommand(String pathToScript, String scriptCreateUser, String filename) {
        String result = "";
        Executor exec = new DefaultExecutor();
        exec.setWorkingDirectory(new File( pathToScript));
        CommandLine cl = new CommandLine("./" + scriptCreateUser);
        cl.addArgument(filename);
        
        try{
            log.debug("Executing: " + cl.toString());
            int exitvalue = exec.execute(cl);
            log.info("Script exited: " + exitvalue);
            result = "Script exited: " + exitvalue;
        }catch (IOException ex){
            log.error("Error executing user creation script.", ex);
        }
        
        return result;
        
    }
    
}
