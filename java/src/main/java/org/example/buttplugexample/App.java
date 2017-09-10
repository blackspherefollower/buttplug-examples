package org.example.buttplugexample;

import org.metafetish.buttplug.client.ButtplugClientDevice;
import org.metafetish.buttplug.client.ButtplugWSClient;
import org.metafetish.buttplug.core.Messages.SingleMotorVibrateCmd;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException, Exception
    {
    	ButtplugWSClient client = new ButtplugWSClient("Java Test");
        client.Connect(new URI("wss://localhost:12345/buttplug"));
        client.startScanning();

        Thread.sleep(5000);
        client.requestDeviceList();
        for (ButtplugClientDevice dev : client.getDevices()) {
            if (dev.allowedMessages.contains(SingleMotorVibrateCmd.class.getSimpleName())) {
                client.sendDeviceMessage(dev, new SingleMotorVibrateCmd(dev.index, 0.5, client.getNextMsgId()));
            }
        }

        Thread.sleep(1000);
        client.stopAllDevices();

        client.Disconnect();
    }
}
