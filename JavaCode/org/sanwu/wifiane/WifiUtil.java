package org.sanwu.wifiane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class WifiUtil {
	
	private static WifiUtil _ins=null;
	public static WifiUtil getInstance(Context context)
	{
		if(_ins==null)_ins=new WifiUtil(context);
		return _ins;
	}
	/**���ڹر�*/
	public static final int WIFI_AP_STATE_DISABLING=0;
	/**�Ѿ��ر�*/
	public static final int WIFI_AP_STATE_DISABLED=11;
	/**���ڿ���*/
	public static final int WIFI_AP_STATE_ENABLING=12;
	/**�ѿ���*/
	public static final int WIFI_AP_STATE_ENABLED=13;
	/**��ȡ״̬ʧ��*/
	public static final int WIFI_AP_STATE_FAILED=14;
	
	
	private Context _context;
	private WifiManager wifiManager;
	public  WifiUtil(Context context){
		_context=context;
		wifiManager=(WifiManager)_context.getSystemService("wifi");
	}
	/**
	 * ����һ��WIFI�ȵ�
	 * @param ssid ����
	 * @key ����
	 * */
	public   WifiConfiguration createWifiApConfig(String ssid,String key){
		WifiConfiguration netConfig=new WifiConfiguration();
		netConfig.SSID=ssid;
		netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		netConfig.preSharedKey=key;
		return netConfig;
	}
	/**
	 * �򿪻�ر�һ��WIFI�ȵ�
	 * @param netConfig һ���������ã������Ҫ�ر��ȵ�Ļ�����������Ϊnull��
	 * @param openOrClose ������ر�(true or false)
	 * */
	public  void setWifiAp(WifiConfiguration netConfig,boolean openOrClose)
	{
		
		Method method1 =null;
		try{
			method1=wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class,boolean.class);
			method1.invoke(wifiManager, netConfig,openOrClose);
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e)
		{
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
	}
	/**
	 * ����ssid��Key������WIFI�ȵ�
	 * �ر��ȵ�ʱ ssid ��key ���дһ����Ȼ�� openOrclose��ΪFalse
	 * */
	public  void setWifiAp(String ssid,String key,boolean openOrClose)
	{
		WifiConfiguration netConfig=new WifiConfiguration();
		netConfig.SSID=ssid;
		netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		netConfig.preSharedKey=key;
		Method method1 =null;
		try{
			method1=wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class,boolean.class);
			if(openOrClose==true)
			{
				method1.invoke(wifiManager, netConfig,openOrClose);
			}else{
				method1.invoke(wifiManager, null,openOrClose);
			}
			
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e)
		{
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
	}
	/**���ӵ�ָ����Wifi�ȵ�
	 * 
	 * */
	public void connectToWifiAp(String ssid,String key){
		if(wifiManager.getWifiState()==WifiManager.WIFI_STATE_ENABLED)
		{
			WifiConfiguration netConfig=new WifiConfiguration();
			netConfig.SSID=ssid;
			netConfig.preSharedKey=key;
			netConfig.status=WifiConfiguration.Status.ENABLED;
			netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			
			int nid=wifiManager.addNetwork(netConfig);
			wifiManager.enableNetwork(nid, true);
		}
	}
	/**��ȡWIFI�ȵ�״̬*/
	public int getWifiApState(){
		try{
			Method method= wifiManager.getClass().getMethod("getWifiApState");
			int i = (Integer) method.invoke(wifiManager);
			return i;
		}catch(Exception e){
			return WIFI_AP_STATE_FAILED;
		}
	}
	/**��ȡWIFI״̬*/
	public int getWIFIState(){
		int i =wifiManager.getWifiState();
		return i;
	}
	/**����WIFI״̬*/
	public void setWIFIState(Boolean b){
		wifiManager.setWifiEnabled(b);
	}
	public void scanf(){
		wifiManager.startScan();
	}
}