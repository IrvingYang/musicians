package com.qushop.sms.mos;

import java.util.ArrayList;
import java.util.UUID;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;

public class MosGateway {

	private static final int WEBSERVICE_PORT = 9070;
	private static final int GATEWAY_PORT = 9080;
	private static final String SERVERIP = "211.147.239.62";
	public static final MosGateway INSTANCE = new MosGateway();
	private final Account lexingAccount;
	private final PostMsg pm;

	private MosGateway() {
		pm = new PostMsg();
		pm.getCmHost().setHost(SERVERIP, GATEWAY_PORT);// 设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost(SERVERIP, WEBSERVICE_PORT);
		lexingAccount = new Account("lxly@lxly", "Lachine2015$");
	}

	public GsmsResponse sendMessage(String packName,String phoneNumber,String msgConent) throws Exception {
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName(packName);
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		pack.setSendType(MTPack.SendType.MASS);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>(1);
		msgs.add(new MessageData(phoneNumber, msgConent));
		pack.setMsgs(msgs);
		return pm.post(this.lexingAccount, pack);
	}
}
