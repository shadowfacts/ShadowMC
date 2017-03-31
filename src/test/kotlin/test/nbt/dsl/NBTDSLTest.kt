import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.shadowfacts.shadowmc.nbt.dsl.compound
import net.shadowfacts.shadowmc.nbt.dsl.list

/**
 * @author shadowfacts
 */
fun main(args: Array<String>) {
	val tag = test()
	println("done")
}

fun test(): NBTTagCompound {
	return compound {
//		"a" to ItemStack(Items.DIAMOND, 3)
		"b" to false
//		"c" to FluidStack(FluidRegistry.LAVA, 1000)
		"d" to compound {
			"ca" to "test"
			"cb" to BlockPos(1, 2, 3)
		}
		"e" to list {
			this with compound {
				"da" to "test 1"
				"db" to false
				"dc" to list {
					this with 1
					this with 2
					this with 3
				}
			}
			this with compound {
				"da" to "test 2"
				"db" to true
				"dc" to list {
					this with 4
					this with 5
					this with 6
				}
			}
		}
	}
}