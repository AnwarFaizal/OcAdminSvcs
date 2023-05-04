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

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anwar
 */
@Path("/admin")
public class AdminService {
    
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    
    private OcConfiguration config;
    
    private AdminUtils utils;
    
    public AdminService(OcConfiguration config){
        this.config = config;
        utils = new AdminUtils();
    }
    
    @POST
    @Path("/createUser")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createUser(@FormParam("user") String user,
            @FormParam("passwd") String passwd,
            @FormParam("level") String level,
            @FormParam("package") String packageName){
        log.debug("User info received.");
        

        String filename = user;
        
        ProjectTemplate templ = utils.createFromTemplate(user, passwd, level, packageName);
        utils.writeToFile(config.getWriteToPath() +"/"+ filename + ".yml", templ);
        log.info("Config written: " + config.getWriteToPath() +"/"+ filename + ".yml");

        String result = utils.
                runShellCommand(config.getPathToScript(), 
                            config.getScriptCreateUser(),
                            filename);
       
        
        return "Create User executed. ";
    }
    
    @POST
    @Path("/changePasswd")
    public String changePassword(@FormParam("user") String user,
            @FormParam("passwd") String passwd){
        log.debug("User passwd received.");
        if(user != null && !user.isEmpty()){
            ProjectTemplate templ = 
                    utils.readUserFile(config.getWriteToPath() +"/"+ user + ".yml", user, false);
            if(passwd != null && !passwd.isEmpty()){
                templ.getProject().put("passwd", passwd);
                utils.writeToFile(config.getWriteToPath() +"/"+ user + ".yml", templ);
                log.info("Config written: " + config.getWriteToPath() +"/"+ user + ".yml");
                log.debug(config.getPathToScript() + " >> " + 
                            config.getScriptChangePasswd() + " >> " +
                            user);
                String result = utils.
                runShellCommand(
                        config.getPathToScript(), 
                        config.getScriptChangePasswd(),
                        user);
            }else{
                log.info("Password is empty.");
            }
        }else{
            log.info("User param is empty.");
        }
        
        return "User password change executed. ";
    }
    
    @DELETE
    @Path("/deleteUser")
    public String deleteUser(@FormParam("user") String user){
        log.debug("User to be deleted received.");
        String result = "";
        if(user != null && !user.isEmpty()){
        utils.
                runShellCommand(
                        config.getPathToScript(), 
                        config.getScriptDeleteUser(),
                        user);
        }else{
            log.info("User param is empty.");
        }
        return "User deletion executed. ";
    }
    
}
