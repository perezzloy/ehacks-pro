package ehacks.mod.gui.reeszrbteam.window;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import ehacks.mod.gui.reeszrbteam.element.YAWButton;
import ehacks.mod.gui.reeszrbteam.element.YAWWindow;
import ehacks.mod.util.GLUtils;
import java.util.ArrayList;

public class WindowRadar
extends YAWWindow {
    public WindowRadar() {
        super("Radar", 94, 300);
        this.buttons = new ArrayList();
        this.buttons.add(new ArrayList());
    }

    @Override
    public void draw(int x, int y) {
        if (this.isOpen()) {
            if (this.dragging) {
                this.windowDragged(x, y);
            }
            GLUtils.drawGradientBorderedRect(this.getX() + this.dragX, this.getY() + this.dragY, this.getX() + 90 + this.dragX, this.getY() + 12 + this.dragY, 0.5f, -16777216, -6710887, -8947849);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.getTitle(), this.getX() + 2 + this.dragX, this.getY() + this.dragY + 2, 5636095);
            GLUtils.drawGradientBorderedRect(this.getX() + 70 + this.dragX, this.getY() + 2 + this.dragY, this.getX() + 78 + this.dragX, this.getY() + 10 + this.dragY, 1.0f, -10066330, this.isPinned() ? -8947849 : -7829368, this.isPinned() ? -11184811 : -10066330);
            GLUtils.drawGradientBorderedRect(this.getX() + 80 + this.dragX, this.getY() + 2 + this.dragY, this.getX() + 88 + this.dragX, this.getY() + 10 + this.dragY, 1.0f, -10066330, this.isExtended() ? -8947849 : -7829368, this.isExtended() ? -11184811 : -10066330);
            if (this.isExtended)
            {
                int rect = 0;
                for (Object o : Minecraft.getMinecraft().theWorld.playerEntities) {
                    EntityPlayer e = (EntityPlayer)o;
                    if (e == Minecraft.getMinecraft().thePlayer || e.isDead) continue;
                    rect += 12;
                }
                GLUtils.drawGradientBorderedRect(this.getX() + this.dragX, this.getY() + 14 + this.dragY, this.getX() + 90 + this.dragX, this.getY() + rect + 14 + this.dragY, 0.5f, -16777216, -6710887, -8947849);
                int count = 0;
                for (Object o : Minecraft.getMinecraft().theWorld.playerEntities) {
                    EntityPlayer e = (EntityPlayer)o;
                    if (e == Minecraft.getMinecraft().thePlayer || e.isDead) continue;
                    int distance = (int)Minecraft.getMinecraft().thePlayer.getDistanceToEntity((Entity)e);
                    String text = "";
                    if (distance <= 20) {
                        text = "\u00a7c" + e.getDisplayName() + "\u00a7f: " + distance;
                    } else if (distance <= 50 && distance > 20) {
                        text = "\u00a76" + e.getDisplayName() + "\u00a7f: " + distance;
                    } else if (distance > 50) {
                        text = "\u00a7a" + e.getDisplayName() + "\u00a7f: " + distance;
                    }
                    int xPosition = this.getX() + 2 + this.dragX;
                    int yPosition = this.getY() + 12 * count + 16 + this.dragY;
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, xPosition, yPosition, 5636095);
                    ++count;
                }
                if (rect == 0 && count == 0) {
                    GLUtils.drawGradientBorderedRect(this.getX() + this.dragX, this.getY() + 14 + this.dragY, this.getX() + 90 + this.dragX, (double)this.getY() + 26 + (double)this.dragY, 0.5f, -16777216, -6710887, -8947849);
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("No one in range.", this.getX() + 2 + this.dragX, this.getY() + 16 + this.dragY, 5636095);
                }
            }
        }
    }
}

