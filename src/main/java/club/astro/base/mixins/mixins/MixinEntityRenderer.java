package club.astro.base.mixins.mixins;

import club.astro.base.events.events.AspectEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.util.glu.Project;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
    Minecraft mc = Minecraft.getMinecraft();

    @Redirect(method = "setupCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onSetupCameraTransform(float fovy, float aspect, float zNear, float zFar) {
        AspectEvent event = new AspectEvent(mc.displayWidth / mc.displayHeight);
        MinecraftForge.EVENT_BUS.post(event);
        Project.gluPerspective(fovy, (float) event.getAspectRatio(), zNear, zFar);
    }

    @Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderWorldPass(float fovy, float aspect, float zNear, float zFar) {
        AspectEvent event = new AspectEvent(mc.displayWidth / mc.displayHeight);
        MinecraftForge.EVENT_BUS.post(event);
        Project.gluPerspective(fovy, (float) event.getAspectRatio(), zNear, zFar);
    }

    @Redirect(method = "renderCloudsCheck", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderCloudsCheck(float fovy, float aspect, float zNear, float zFar) {
        AspectEvent event = new AspectEvent(mc.displayWidth / mc.displayHeight);
        MinecraftForge.EVENT_BUS.post(event);
        Project.gluPerspective(fovy, (float) event.getAspectRatio(), zNear, zFar);
    }
}
