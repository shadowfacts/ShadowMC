package test.ui.dsl

import net.minecraft.client.gui.GuiScreen
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.shadowfacts.shadowmc.inventory.ContainerPlayerInv
import net.shadowfacts.shadowmc.ui.dsl.*

fun createGui(player: EntityPlayer): GuiScreen {
	return container(ContainerPlayerInv(player.position, player.inventory)) {
		fixed {
			id = "root"
			width = 176
			height = 166

			image {
				id = "bg"
				texture = ResourceLocation("shadowmc:textures/gui/blank.png")
				width = 176
				height = 166
			}

			fixed {
				id = "top"
				width = 176
				height = 166 / 2

				label {
					id = "label"
					text = "Hello, UI DSL!"
				}
			}
		}

		style("modtest:dsl")
	}
}