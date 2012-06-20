/**
*Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services.
*All rights reserved.
*
*Redistribution and use in source and binary forms, with or without
*modification, are permitted provided that the following conditions are met:
*    * Redistributions of source code must retain the above
*      copyright notice, this list of conditions and the following disclaimer.
*    * Redistributions in binary form must reproduce the above copyright
*      notice, this list of conditions and the following disclaimer in the documentation
*      and/or other materials provided with the distribution.
*    * Neither the name of the United States Government nor the
*      names of its contributors may be used to endorse or promote products
*      derived from this software without specific prior written permission.
*
*THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
*ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
*WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
*DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
*DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
*(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
*LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
*ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
*(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
*SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package gov.hhs.fha.nhinc.properties;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * @author akong
 *
 */
public class PropertyAccessorFileUtilitiesTest {
    
    private static String EXPECTED_PROPERTY_FILE_LOCATION;
    
    protected Mockery context = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };    
    final Log mockLog = context.mock(Log.class);
        
    @Before
    public void setMockLogExpectationsToIgnoreAllLogs() {        
        context.checking(new Expectations() {
            {
                ignoring(mockLog);
            }
        });
    }
    
    @Test
    public void testGetPropertyFileLocation() {                
        PropertyAccessorFileUtilities fileUtilities = createPropertyAccessorFileUtilities();
        
        String fileDir = fileUtilities.getPropertyFileLocation();        
        assertTrue(fileDir.endsWith(File.separator));        
        assertEquals(EXPECTED_PROPERTY_FILE_LOCATION, fileDir);
    }
        
    @Test
    public void testGetPropertyFileLocation_WithFileName() {                
        PropertyAccessorFileUtilities fileUtilities = createPropertyAccessorFileUtilities();
                
        String fileLocation = fileUtilities.getPropertyFileLocation("gateway");
        assertEquals(EXPECTED_PROPERTY_FILE_LOCATION + "gateway.properties", fileLocation);
    }
    
    
    @Test
    public void testSetPropertyFileLocation() {
        PropertyAccessorFileUtilities fileUtilities = createPropertyAccessorFileUtilities();
        
        fileUtilities.setPropertyFileLocation("/test");
        assertEquals("/test/", fileUtilities.getPropertyFileLocation());
    }
    
    @Test
    public void testGetPropertyFileURL() {                
        PropertyAccessorFileUtilities fileUtilities = createPropertyAccessorFileUtilities();
        
        String fileUrl = fileUtilities.getPropertyFileURL();
        assertEquals("file:///" + EXPECTED_PROPERTY_FILE_LOCATION, fileUrl);
    }
    
    @Test
    public void testNoEnvironmentSet() {         
        System.clearProperty("nhinc.properties.dir");
        
        PropertyAccessorFileUtilities fileUtilities = new PropertyAccessorFileUtilities() {
            protected String getNhincPropertyDirValueFromSysEnv() {
                return null;
            }
            
            protected Log getLogger() {
                return mockLog;
            }
        };
        assertNull(fileUtilities.getPropertyFileLocation());
    }
    
    @Test
    public void testSystemEnvironmentSet() {         
        System.clearProperty("nhinc.properties.dir");
        
        PropertyAccessorFileUtilities fileUtilities = new PropertyAccessorFileUtilities() {
            protected String getNhincPropertyDirValueFromSysEnv() {
                return "/config/";
            }
            
            protected Log getLogger() {
                return mockLog;
            }
        };
        assertEquals("/config/", fileUtilities.getPropertyFileLocation());
    }
    
    private PropertyAccessorFileUtilities createPropertyAccessorFileUtilities() {
        URL url = this.getClass().getResource("/config");      
        System.setProperty("nhinc.properties.dir", url.getFile());
        
        EXPECTED_PROPERTY_FILE_LOCATION = url.getFile() + File.separator;
        
        return new PropertyAccessorFileUtilities() {
            protected Log getLogger() {
                return mockLog;
            }
        };
    }
    
}