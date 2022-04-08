
package net.player_roles.gui;

import net.player_roles.PlayerRolesMod;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ChoiserolesGuiWindow extends ContainerScreen<ChoiserolesGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = ChoiserolesGui.guistate;

	public ChoiserolesGuiWindow(ChoiserolesGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 200;
		this.ySize = 160;
	}

	private static final ResourceLocation texture = new ResourceLocation("player_roles:textures/choiseroles.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("player_roles:textures/farmer.png"));
		this.blit(ms, this.guiLeft + 63, this.guiTop + 67, 0, 0, 24, 24, 24, 24);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("player_roles:textures/logger.png"));
		this.blit(ms, this.guiLeft + 63, this.guiTop + 103, 0, 0, 24, 24, 24, 24);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("player_roles:textures/miner.png"));
		this.blit(ms, this.guiLeft + 108, this.guiTop + 67, 0, 0, 24, 24, 24, 24);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("player_roles:textures/murder.png"));
		this.blit(ms, this.guiLeft + 108, this.guiTop + 103, 0, 0, 24, 24, 24, 24);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Choice a role", 66, 21, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 4, this.guiTop + 69, 56, 20, new StringTextComponent("Fermer"), e -> {
			if (true) {
				PlayerRolesMod.PACKET_HANDLER.sendToServer(new ChoiserolesGui.ButtonPressedMessage(0, x, y, z));
				ChoiserolesGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 105, 56, 20, new StringTextComponent("logger"), e -> {
			if (true) {
				PlayerRolesMod.PACKET_HANDLER.sendToServer(new ChoiserolesGui.ButtonPressedMessage(1, x, y, z));
				ChoiserolesGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 135, this.guiTop + 105, 61, 20, new StringTextComponent("Murder"), e -> {
			if (true) {
				PlayerRolesMod.PACKET_HANDLER.sendToServer(new ChoiserolesGui.ButtonPressedMessage(2, x, y, z));
				ChoiserolesGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 134, this.guiTop + 69, 51, 20, new StringTextComponent("Miner"), e -> {
			if (true) {
				PlayerRolesMod.PACKET_HANDLER.sendToServer(new ChoiserolesGui.ButtonPressedMessage(3, x, y, z));
				ChoiserolesGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
	}
}
