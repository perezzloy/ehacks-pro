package ehacks.mod.modulesystem.classes;

import ehacks.api.module.ModStatus;
import ehacks.api.module.Module;
import ehacks.mod.gui.EHacksClickGui;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.input.Mouse;

public class ImageFuck
        extends Module {

    public ImageFuck() {
        super(ModuleCategory.EHACKS);
    }

    @Override
    public String getName() {
        return "ImageFuck";
    }

    @Override
    public void onEnableMod() {
        try {
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.BlockRadio");
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio");
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.Gui.NGuiRadio");
        } catch (Exception ex) {
            this.off();
            EHacksClickGui.log("[ImageFuck] Not working");
        }
    }

    @Override
    public ModStatus getModStatus() {
        try {
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.BlockRadio");
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio");
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.Gui.NGuiRadio");
            return ModStatus.WORKING;
        } catch (Exception e) {
            return ModStatus.NOTWORKING;
        }
    }

    @Override
    public void onDisableMod() {

    }

    @Override
    public void onMouse(MouseEvent event) {
        MovingObjectPosition mop = Wrapper.INSTANCE.mc().objectMouseOver;
        if (mop.sideHit == -1) {
            return;
        }
        if (Mouse.isButtonDown(1)) {
            TileEntity entity = Wrapper.INSTANCE.world().getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
            try {
                if (entity != null && Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.BlockRadio").isInstance(entity)) {
                    Wrapper.INSTANCE.mc().displayGuiScreen((GuiScreen) Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.Gui.NGuiRadio").getConstructor(Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio")).newInstance(entity));
                    EHacksClickGui.log("[ImageFuck] Gui opened");
                }
            } catch (Exception ex) {

            }
        }
    }
}
