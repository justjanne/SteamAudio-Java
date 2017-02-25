package de.kuschku.steamaudio.demo;

import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.compute.ComputeApi;
import de.kuschku.steamaudio.lib.compute.IPLComputeDeviceType;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.scene.SceneApi;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.util.logging.Logger;

public class Main {
    public static void main(String... args) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());

        ComputeApi computeApi = ComputeApi.getInstance();
        SceneApi sceneApi = SceneApi.getInstance();

        IPLContext.ByValue context = new IPLContext.ByValue();
        context.logCallback = message -> logger.info(message.getString(0));
        PointerByReference device = new PointerByReference();
        IPLerror computeError =
                computeApi.iplCreateComputeDevice(IPLComputeDeviceType.Enum.IPL_COMPUTEDEVICE_GPU.wrap(), 32, device);
        System.out.println(computeError.getEnumValue().name());
        System.out.println(device.getValue());
        IPLSimulationSettings.ByValue simulationSettings = new IPLSimulationSettings.ByValue();
        PointerByReference scene = new PointerByReference();
        IPLerror sceneError = sceneApi.iplCreateScene(context, device.getValue(), simulationSettings, 32, scene);
        System.out.println(sceneError.getEnumValue().name());
        System.out.println(scene.getValue());
    }
}
