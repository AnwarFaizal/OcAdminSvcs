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

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 *
 * @author anwar
 */
public class OcApplication extends Application<OcConfiguration> {

    public static void main(String[] args) throws Exception {
        new OcApplication().run(args);
    }
    
    @Override
    public String getName() {
        return "Openshift Client Addmin Service App";
    }
    
    public void run(OcConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new AdminService(configuration));
    }
    
}
