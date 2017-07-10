package server;

import Laboratory.*;
import server.cameras.CameraI;
import server.cameras.NightCameraI;
import server.cameras.VariableFocalLenCameraI;
import server.robots.RobotI;
import server.robots.RobotWithArmI;
import server.robots.RobotWithShovelI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args){
        int status = 0;
        Ice.Communicator ic = null;

        try {
            // in this try block we should put the server implementation

            Map<String, DevicePrx> proxies = new HashMap<>();
            Map<String, List<String>> devicesFunctions = new HashMap<>();
            List<String> devicesNames = new ArrayList<>();

            // initialize ICE
            ic = Ice.Util.initialize(args);


            // create object adapter 1st arg is name of adaptor, second is protocol to listen
            Ice.ObjectAdapter adapter =
                    ic.createObjectAdapter("adapter");

            String name;

            for(int i = 1; i < 3; i++){
                CameraI cameraObject = new CameraI();
                name = "Camera" + i;
                CameraPrx cameraProxy = CameraPrxHelper.checkedCast(adapter.add(cameraObject, ic.stringToIdentity(name)));
                proxies.put(name, cameraProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, cameraProxy.getDeviceFunctions());

                NightCameraI nightCameraObject = new NightCameraI();
                name = "NightCamera" + i;
                NightCameraPrx nightCameraProxy = NightCameraPrxHelper.checkedCast(adapter.add(nightCameraObject, ic.stringToIdentity(name)));
                proxies.put(name, nightCameraProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, nightCameraProxy.getDeviceFunctions());

                VariableFocalLenCamera varFocalLenCamera = new VariableFocalLenCameraI();
                name = "VarFocalLenCamera" + i;
                VariableFocalLenCameraPrx varFocalLenCameraProxy =
                         VariableFocalLenCameraPrxHelper.checkedCast(adapter.add(varFocalLenCamera, ic.stringToIdentity(name)));
                proxies.put(name, varFocalLenCameraProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, varFocalLenCameraProxy.getDeviceFunctions());

                RobotI robotObject = new RobotI();
                name = "Robot" + i;
                RobotPrx robotProxy = RobotPrxHelper.checkedCast(adapter.add(robotObject, ic.stringToIdentity(name)));
                proxies.put(name, robotProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, robotProxy.getDeviceFunctions());

                RobotWithArmI robotWithArmObject = new RobotWithArmI();
                name = "RobotWithArm" + i;
                RobotWithArmPrx robotWithArmProxy = RobotWithArmPrxHelper.checkedCast(adapter.add(robotWithArmObject, ic.stringToIdentity(name)));
                proxies.put(name, robotWithArmProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, robotWithArmProxy.getDeviceFunctions());

                RobotWithShovelI robotWithShovelObject = new RobotWithShovelI();
                name = "RobotWithShovel" + i;
                RobotWithShovelPrx robotWithShovelProxy =
                        RobotWithShovelPrxHelper.checkedCast(adapter.add(robotWithShovelObject, ic.stringToIdentity(name)));
                proxies.put(name, robotWithShovelProxy);
                devicesNames.add(name);
                devicesFunctions.put(name, robotWithShovelProxy.getDeviceFunctions());

            }

            // create servant for Printer interface
            LabI lab = new LabI(proxies, devicesNames, devicesFunctions);

            // inform adapter about new servant 1st arg servant, 2nd identifier
            adapter.add(lab, ic.stringToIdentity("Lab"));


            // activate adapter, after creation it is in holding state
            adapter.activate();


            // wait for shutdown
            ic.waitForShutdown();
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                // this must be done for every communicator
                // otherwise undefined behaviour
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
