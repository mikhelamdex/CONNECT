/*
 * Copyright (c) 2009-2016, United States Government, as represented by the Secretary of Health and Human Services.
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
package gov.hhs.fha.nhinc.mpi.adapter.proxy;

import gov.hhs.fha.nhinc.aspect.AdapterDelegationEvent;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.patientdiscovery.aspect.PRPAIN201305UV02AdapterEventDescBuilder;
import gov.hhs.fha.nhinc.patientdiscovery.aspect.PRPAIN201305UV02EventDescriptionBuilder;
import java.lang.reflect.Method;
import org.hl7.v3.PRPAIN201305UV02;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class AdapterMpiProxyTest {

    @Test
    public void hasAdapterDelegationEvent() throws Exception {
        Class<?>[] classes = new Class<?>[]{AdapterMpiProxyJavaImpl.class, AdapterMpiProxyNoOpImpl.class,
            AdapterMpiProxyWebServiceSecuredImpl.class, AdapterMpiProxyWebServiceUnsecuredImpl.class};
        for (Class<?> clazz : classes) {
            Method method = clazz.getMethod("findCandidates", PRPAIN201305UV02.class, AssertionType.class);
            AdapterDelegationEvent annotation = method.getAnnotation(AdapterDelegationEvent.class);
            assertNotNull(annotation);
            assertEquals(PRPAIN201305UV02EventDescriptionBuilder.class, annotation.beforeBuilder());
            assertEquals(PRPAIN201305UV02AdapterEventDescBuilder.class, annotation.afterReturningBuilder());
            assertEquals("Patient Discovery MPI", annotation.serviceType());
            assertEquals("1.0", annotation.version());
        }
    }
}
