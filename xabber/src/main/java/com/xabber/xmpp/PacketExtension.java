/**
 * Copyright (c) 2013, Redsolution LTD. All rights reserved.
 *
 * This file is part of Qtunr project; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.
 *
 * Qtunr is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * along with this program. If not, see http://www.gnu.org/licenses/.
 */
package com.xabber.xmpp;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * Packet extension.
 * <p/>
 * Note: we are going to remove underlying smack package.
 *
 * @author alexander.ivanov
 */
public abstract class PacketExtension implements Container,
        org.jivesoftware.smack.packet.ExtensionElement {

    @Override
    public void serialize(XmlSerializer serializer) throws IOException {
        SerializerUtils.serialize(serializer, this);
    }

    @Override
    public String toXML() {
        return SerializerUtils.toXml(this);
    }

}
