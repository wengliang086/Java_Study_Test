package com.test.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 启动本类
 * 可以通过 http://localhost:8082/ 访问
 */
public class HelloWorldAgent {

    public static void main(String[] args) throws MalformedObjectNameException,
            NullPointerException, InstanceAlreadyExistsException,
            MBeanRegistrationException, NotCompliantMBeanException, IOException {

        int rmiPort = 1099;
        String jmxServerName = "TestJMXServer";

        // jdkfolder/bin/rmiregistry.exe 9999
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        MBeanServer mbs = MBeanServerFactory.createMBeanServer(jmxServerName);
        //MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        ObjectName adapterName = new ObjectName(jmxServerName + ":name=" + "htmladapter");
        adapter.setPort(8082);
        adapter.start();
        mbs.registerMBean(adapter, adapterName);

        // 绑定需要被管理的类
        ObjectName objName = new ObjectName(jmxServerName + ":name=" + "HelloWorld");
        mbs.registerMBean(new HelloWorld(), objName);

        // 将服务绑定到固定的URL上
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + jmxServerName);
        System.out.println("JMXServiceURL: " + url.toString());
        JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        jmxConnServer.start();
    }
}
