package com.adminTool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adminTool.bo.SystemMailInternal;
import com.adminTool.service.SystemMailInternalService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class SynMailInternalList extends ALDAdminPageActionSupport{

	private static final long serialVersionUID = 1L;

	private Integer sysNum;
	private Set<Integer> serverIdsSet = new HashSet<Integer>();
	private Set<Integer> successServerIdsSet = new HashSet<Integer>();
	private String failIds = "";
	private String successIds = "";
	private String serverIds;
	private int mailId = -1;
	private String responseMsg = "";
	private List<Integer> serverIdsList = new ArrayList<Integer>();
	
	@Override
	public String execute() {
		
		responseMsg = "success";
		if (mailId == -1) {
			responseMsg = "no";
		} else {
			if (!serverIds.equals("") && serverIds != null) {
				String[] serverArr = serverIds.split(",");
				for (int i = 0; i < serverArr.length; i++) {
					serverIdsList.add(Integer.valueOf(serverArr[i]));
				}
			}
			sysNum = CustomerContextHolder.getSystemNum(); // 保存当前
			System.out.println("当前服务器ID：" + sysNum);
			handleSynMail(mailId);
			for (Integer s : serverIdsSet) {
				failIds += s + ",";
			}
			for (Integer s : successServerIdsSet) {
				successIds += s + ",";
			}
		}
		return SUCCESS;
	}
	
	public void handleSynMail(int mailId) {
		Map<Integer, TGameServer> map = DataSourceManager.getInstatnce().getGameServerMap();
		SystemMailInternalService service = ServiceCacheFactory.getServiceCache().getService(SystemMailInternalService.class);
		List<SystemMailInternal> list = new ArrayList<SystemMailInternal>();
		
		try {
			list = service.findByMailId(mailId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				for (SystemMailInternal mail : list) {
					service.update(mail);
				}
				// 成功
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public Integer getSysNum() {
		return sysNum;
	}
	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}
	public Set<Integer> getServerIdsSet() {
		return serverIdsSet;
	}
	public void setServerIdsSet(Set<Integer> serverIdsSet) {
		this.serverIdsSet = serverIdsSet;
	}
	public Set<Integer> getSuccessServerIdsSet() {
		return successServerIdsSet;
	}
	public void setSuccessServerIdsSet(Set<Integer> successServerIdsSet) {
		this.successServerIdsSet = successServerIdsSet;
	}
	public String getFailIds() {
		return failIds;
	}
	public void setFailIds(String failIds) {
		this.failIds = failIds;
	}
	public String getSuccessIds() {
		return successIds;
	}
	public void setSuccessIds(String successIds) {
		this.successIds = successIds;
	}
	public String getServerIds() {
		return serverIds;
	}
	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
}
