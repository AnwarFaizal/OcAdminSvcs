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

import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anwar
 */
public class AdminUtilsTest {
    
    private Logger log = LoggerFactory.getLogger(getClass().getName());
    
    public AdminUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readTemplate method, of class AdminUtils.
     */
    @Test
    public void testReadTemplate() {
        log.info("readTemplate");
        String templateFilePath = "projectTempl.yml";
        AdminUtils instance = new AdminUtils();
        String expResult = "project";
        String result = instance.readTemplate(templateFilePath);
//        assertEquals(expResult, result);
        assertNotNull(result);
        assertThat(result.contains(expResult), is(true));
    }

    /**
     * Test of replaceVarsWith method, of class AdminUtils.
     */
    @Test
    public void testReplaceVarsWith() {
        log.info("replaceVarsWith");
        String template = "## YAML Template.---project:  name: $user_name  org: tmrnd  admin: $user_name  passwd: $user_passwd  level: $user_level";
        String user = "test-user";
        String passwd = "test-passwd";
        String level = "test-level";
        String packageName = "test-package";
        AdminUtils instance = new AdminUtils();
        String expResult = "## YAML Template.---project:  name: test-user  org: tmrnd  admin: test-user  passwd: test-passwd  level: test-level";
        String result = instance.replaceVarsWith(template, user, passwd, level, packageName);
        log.debug("result: " + result);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of writeVarsFile method, of class AdminUtils.
     */
    @Ignore
    @Test
    public void testWriteVarsFile() {
        log.info("writeVarsFile");
        String writeToPath = "";
        String filename = "";
        String vars = "";
        AdminUtils instance = new AdminUtils();
        instance.writeVarsFile(writeToPath, filename, vars);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFromTemplate method, of class AdminUtils.
     */
    @Test
    public void testCreateFromTemplate() {
        log.info("createFromTemplate");
        String user = "test-user";
        String passwd = "test-passwd";
        String level = "test-level";
        String packageName = "test-packageName";
        AdminUtils instance = new AdminUtils();
        ProjectTemplate expResult = new ProjectTemplate();
        Map<String, String> projectMap = expResult.getProject();
        projectMap.put("admin", "test-user");
        projectMap.put("name", "test-user");
        projectMap.put("passwd", "test-passwd");
        projectMap.put("level","test-level");
        projectMap.put("packageName","test-packageName");
        ProjectTemplate result = instance.createFromTemplate(user, passwd, level, packageName);
        assertThat(result, is(notNullValue()));
//        assertThat(result, is(equalTo(expResult)));
        log.debug("Equality: " + expResult.equals(result));
        assertEquals(result, expResult);
        assertThat(result.getProject().size(),is(expResult.getProject().size()));
    }

    /**
     * Test of writeToFile method, of class AdminUtils.
     */
    @Ignore
    @Test
    public void testWriteToFile() {
        log.info("writeToFile");
        String filePath = "";
        ProjectTemplate templ = null;
        AdminUtils instance = new AdminUtils();
        instance.writeToFile(filePath, templ);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readUserFile method, of class AdminUtils.
     */
    @Test
    public void testReadUserFile() {
        log.info("readUserFile");
        String filepath = "test-user.yml";
        String user = "test-user";
        AdminUtils instance = new AdminUtils();
        ProjectTemplate expResult = new ProjectTemplate();
        Map<String, String> expMap = expResult.getProject();
        expMap.put("admin", "test-user");
        expMap.put("name", "test-user");
        expMap.put("passwd", "test-passwd");
        expMap.put("level","test-level");
        expMap.put("packageName","test-packageName");
        ProjectTemplate result = instance.readUserFile(filepath, user, true);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of runShellCommand method, of class AdminUtils.
     */
    @Test
    public void testRunShellCommand() {
        log.info("runShellCommand");
        String pathToScript = "/home/anwar/playbooks/origin-devops";
        String scriptCreateUser = "runChangePasswdSIT.sh";
        String filename = " testuser";
        AdminUtils instance = new AdminUtils();
        String expResult = "";
        String result = instance.runShellCommand(pathToScript, scriptCreateUser, filename);
        log.debug("Result: " + result);
//        assertEquals(expResult, result);
        assertNotNull("Result shouldn't be null.", result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
