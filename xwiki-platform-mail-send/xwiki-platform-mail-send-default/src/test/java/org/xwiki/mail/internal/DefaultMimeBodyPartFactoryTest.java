/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.mail.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeBodyPart;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link DefaultMimeBodyPartFactory}.
 *
 * @version $Id$
 * @since 6.1M2
 */
public class DefaultMimeBodyPartFactoryTest
{
    @Test
    public void createDefaultBodyPart() throws Exception
    {
        // Step 1: Create a DefaultMimeBodyPartFactory object
        DefaultMimeBodyPartFactory defaultPartFactory = new DefaultMimeBodyPartFactory();

        // Step 2: Create the body part
        MimeBodyPart bodyPart = defaultPartFactory.create("Lorem ipsum");

        assertEquals("Lorem ipsum", bodyPart.getContent());
        assertEquals("text/plain", bodyPart.getContentType());
    }

    @Test
    public void createDefaultBodyPartWithHeaders() throws Exception
    {
        // Step 1: Create a DefaultMimeBodyPartFactory object
        DefaultMimeBodyPartFactory defaultPartFactory = new DefaultMimeBodyPartFactory();

        // Step 2: Add parameters Map
        Map<String, String> headers = Collections.singletonMap("Content-Transfer-Encoding", "quoted-printable");
        //Map<String, Object> parameters = Collections.<String, Object>singletonMap("headers", headers);

        Map<String, Object> parameters = new HashMap();
        parameters.put("headers", headers);
        parameters.put("mimetype", "text/calendar");

        // Step 3: Create the body part
        MimeBodyPart bodyPart = defaultPartFactory.create("Lorem ipsum", parameters);

        assertEquals("Lorem ipsum", bodyPart.getContent());
        assertEquals("text/calendar", bodyPart.getContentType());
        assertArrayEquals(new String[]{ "quoted-printable" }, bodyPart.getHeader("Content-Transfer-Encoding"));
    }
}
