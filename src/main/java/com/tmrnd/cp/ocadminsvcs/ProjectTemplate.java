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


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anwar
 */
public class ProjectTemplate {
    
    private Logger log = LoggerFactory.getLogger(getClass().getName());

    private Map<String, String> project = new HashMap<String,String>();

    public Map<String, String> getProject() {
        return project;
    }

//    public void setProject(Map<String, String> project) {
//        this.project = project;
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ProjectTemplate rhs = (ProjectTemplate) obj;
        Map<String, String> rhsMap = rhs.getProject();
        Map<String, String> thisMap = getProject();
        
        log.debug("thisUser: " + thisMap.get("user") + "::" + rhsMap.get("user"));
        log.debug("thisPasswd: " + thisMap.get("passwd") + "::" + rhsMap.get("passwd"));
        log.debug("thisLevel: " + thisMap.get("level") + "::" + rhsMap.get("level"));
        
        return new EqualsBuilder()
//                .appendSuper(super.equals(obj))
                .append(thisMap.get("user"),rhsMap.get("user"))
                .append(thisMap.get("passwd"),rhsMap.get("passwd"))
                .append(thisMap.get("level"),rhsMap.get("level"))
                .isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Map<String, String> thisMap = getProject();
        builder.append(thisMap.get("admin"))
                .append(thisMap.get("name"))
                .append(thisMap.get("passwd"))
                .append(thisMap.get("level"));
        return builder.hashCode();
    }

}
