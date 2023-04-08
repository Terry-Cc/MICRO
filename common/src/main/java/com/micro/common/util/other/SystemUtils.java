package com.micro.common.util.other;

import com.micro.common.constant.Constants;
import com.micro.common.entity.ComputerDesc;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @program: www
 * @description: get com info
 * @author: XiongJiaMin
 * @create: 2021-09-16 11:25
 **/
public class SystemUtils implements Serializable {

    private static final long serialVersionUID = 1370255377140639720L;

    /**
     * 获取系统参数
     * @return ComputerDesc
     * @throws UnknownHostException 未知host异常
     * @throws SocketException 链接异常
     */
    public static ComputerDesc getComMsg() throws UnknownHostException, SocketException {
        ComputerDesc computerDesc = new ComputerDesc();
        Properties properties = System.getProperties();
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        Map<String, String> env = System.getenv();
        computerDesc.setUserName(env.get(Constants.SYS_USERNAME));
        computerDesc.setUserDnsDomain(env.get(Constants.SYS_USER_DNS_DOMAIN));
        computerDesc.setComputerName(env.get(Constants.SYS_COMPUTER_NAME));
        computerDesc.setUserDomain(env.get(Constants.SYS_USER_DOMAIN));
        computerDesc.setOsType(env.get(Constants.SYS_OS_TYPE));
        computerDesc.setOsName(properties.get(Constants.SYS_OS_NAME).toString());
        computerDesc.setOsArch(properties.get(Constants.SYS_OS_ARCH).toString());
        computerDesc.setOsVersion(properties.get(Constants.SYS_OS_VERSION).toString());
        computerDesc.setHostAddress(address.getHostAddress());
        byte[] mac = ni.getHardwareAddress();
        String macAddress = Constants.STR_EMPTY;
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; i++) {
            macAddress = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                    (i < mac.length - 1) ? "-" : "").toString();
        }
        computerDesc.setMacAddress(macAddress);
        return computerDesc;
    }
}
