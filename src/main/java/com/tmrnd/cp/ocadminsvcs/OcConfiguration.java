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

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author anwar
 */
public class OcConfiguration extends Configuration {
    
    @NotEmpty
    private String templateFilePath;
    
    @NotEmpty
    private String writeToPath;
    
    
    @NotEmpty
    private String pathToScript;
            
    @NotEmpty
    private String scriptCreateUser;    
    
    @NotEmpty
    private String scriptChangePasswd;
    
    @NotEmpty
    private String scriptDeleteUser;

    public String getScriptDeleteUser() {
        return scriptDeleteUser;
    }

    public void setScriptDeleteUser(String scriptDeleteUser) {
        this.scriptDeleteUser = scriptDeleteUser;
    }

    public String getScriptCreateUser() {
        return scriptCreateUser;
    }

    public void setScriptCreateUser(String scriptCreateUser) {
        this.scriptCreateUser = scriptCreateUser;
    }

    public String getScriptChangePasswd() {
        return scriptChangePasswd;
    }

    public void setScriptChangePasswd(String scriptChangePasswd) {
        this.scriptChangePasswd = scriptChangePasswd;
    }
    

    public String getPathToScript() {
        return pathToScript;
    }

    public void setPathToScript(String pathToScript) {
        this.pathToScript = pathToScript;
    }

//    public String getScriptExec() {
//        return scriptCreateUser;
//    }
//
//    public void setScriptExec(String scriptExec) {
//        this.scriptCreateUser = scriptExec;
//    }
     

    public String getWriteToPath() {
        return writeToPath;
    }

    public void setWriteToPath(String writeToPath) {
        this.writeToPath = writeToPath;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }
    
    
    
}
