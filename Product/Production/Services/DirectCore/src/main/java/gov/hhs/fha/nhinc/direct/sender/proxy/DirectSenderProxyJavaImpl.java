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
package gov.hhs.fha.nhinc.direct.sender.proxy;

import gov.hhs.fha.nhinc.direct.DirectSender;
import javax.mail.Address;
import javax.mail.internet.MimeMessage;
import org.nhindirect.xd.common.DirectDocuments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class DirectSenderProxyJavaImpl.
 *
 * @author svalluripalli
 */
public class DirectSenderProxyJavaImpl {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(DirectSenderProxyJavaImpl.class);

    /** The direct sender. */
    @Autowired
    private DirectSender directSender;

    /**
     * Send outbound direct.
     *
     * @param message the message
     */
    public void sendOutboundDirect(MimeMessage message) {
        LOG.debug("Begin DirectSenderProxyJavaImpl.sendOutboundDirect(MimeMessage)");
        directSender.sendOutboundDirect(message);
        LOG.debug("End DirectSenderProxyJavaImpl.sendOutboundDirect(MimeMessage)");
    }

    /**
     * Send outbound direct.
     *
     * @param sender the sender
     * @param recipients the recipients
     * @param documents the documents
     * @param messageId the message id
     */
    public void sendOutboundDirect(Address sender, Address[] recipients, DirectDocuments documents, String messageId) {
        LOG.debug("Begin DirectSenderProxyJavaImpl.sendOutboundDirect(Address, Address[], DirectDocuments, messageId)");
        directSender.sendOutboundDirect(sender, recipients, documents, messageId);
        LOG.debug("End DirectSenderProxyJavaImpl.sendOutboundDirect(Address, Address[], DirectDocuments, messageId)");
    }
}
