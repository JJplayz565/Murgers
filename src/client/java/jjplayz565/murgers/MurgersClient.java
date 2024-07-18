package jjplayz565.murgers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class MurgersClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.


		FabricLoader.getInstance().getModContainer("murgers").ifPresent(murgers -> ResourceManagerHelper.registerBuiltinResourcePack(locate("murgereatinganimation"), murgers, ResourcePackActivationType.NORMAL));
		FabricLoader.getInstance().getModContainer("murgers").ifPresent(murgers -> ResourceManagerHelper.registerBuiltinResourcePack(locate("hd-textures"), murgers, ResourcePackActivationType.NORMAL));
    }

    public static Identifier locate(String path) {
        return Identifier.ofVanilla(path);
    }
}