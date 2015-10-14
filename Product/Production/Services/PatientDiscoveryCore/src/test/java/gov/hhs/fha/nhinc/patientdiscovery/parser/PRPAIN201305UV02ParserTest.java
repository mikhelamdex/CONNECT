/*
 * Copyright (c) 2009-2015, United States Government, as represented by the Secretary of Health and Human Services.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of the United States Government nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gov.hhs.fha.nhinc.patientdiscovery.parser;

import org.hl7.v3.II;
import org.hl7.v3.PRPAIN201305UV02;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tjafri
 */
public class PRPAIN201305UV02ParserTest {

    private PRPAIN201305UV02 request;

    @Before
    public void setup() {
        request = TestPatientDiscoveryMessageHelper.createPRPAIN201305UV02Request();
    }

    @Test
    public void testPatientId() {
        II objPid = PRPAIN201305UV02Parser.getPatientId(request);
        assertEquals("PatientId Root mismatch", TestPatientDiscoveryMessageHelper.ROOT, objPid.getRoot());
        assertEquals("PatientId Extension mismatch", TestPatientDiscoveryMessageHelper.EXTENSION,
            objPid.getExtension());
    }

    @Test
    public void testParticipantObjectId() {
        String oid = PRPAIN201305UV02Parser.getQueryId(request);
        assertEquals("QueryId mismatch", TestPatientDiscoveryMessageHelper.QUERY_ID_EXTENSION, oid);
    }
}
