package com.xabber.android.data.extension.groupchat.invite.incoming

import com.xabber.android.data.message.chat.groupchat.GroupChat
import com.xabber.android.data.message.chat.groupchat.GroupchatManager
import org.jivesoftware.smack.packet.IQ
import org.jxmpp.jid.FullJid
import org.jxmpp.jid.Jid

class DeclineGroupInviteIQ(jid: Jid): IQ(DECLINE_ELEMENT, GroupchatManager.NAMESPACE + HASH_BLOCK) {

    init {
        to = jid
        type = Type.set
    }

    constructor(groupChat: GroupChat): this(groupChat.fullJidIfPossible ?: groupChat.contactJid.jid)

    override fun getIQChildElementBuilder(xml: IQChildElementXmlStringBuilder) = xml.apply {
        rightAngleBracket()
    }

    companion object{
        private const val HASH_BLOCK = "#invite"
        private const val DECLINE_ELEMENT = "decline"
    }

}