/*
 * Decompiled with CFR 0_128.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C03PacketPlayer
 *  net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition
 */
package ehacks.mod.modulesystem.classes;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import ehacks.api.module.Mod;
import ehacks.mod.wrapper.ModuleCategories;
import ehacks.mod.wrapper.Wrapper;

public class NoFall
extends Mod {
    public NoFall() {
        super(ModuleCategories.PLAYER);
    }

    @Override
    public String getName() {
        return "NoFall";
    }

    @Override
    public void onTicks() {
        if (Wrapper.INSTANCE.player().fallDistance > 2.0f) {
            Wrapper.INSTANCE.player().sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Wrapper.INSTANCE.player().motionX, -999.0, -999.0, Wrapper.INSTANCE.player().motionZ, !Wrapper.INSTANCE.player().onGround));
        }
    }
}

