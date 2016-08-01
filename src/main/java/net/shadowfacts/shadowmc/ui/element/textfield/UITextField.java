package net.shadowfacts.shadowmc.ui.element.textfield;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.math.MathHelper;
import net.shadowfacts.shadowmc.ui.UIDimensions;
import net.shadowfacts.shadowmc.ui.UIKeyInteractable;
import net.shadowfacts.shadowmc.ui.UIMouseInteractable;
import net.shadowfacts.shadowmc.ui.element.UIElementBase;
import net.shadowfacts.shadowmc.ui.style.UIAttribute;
import net.shadowfacts.shadowmc.ui.util.UIHelper;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
public class UITextField extends UIElementBase implements UIMouseInteractable, UIKeyInteractable {

	@Getter @Setter
	protected String text;
	protected Pattern validator;
	protected Consumer<String> textChangeHandler;
	protected boolean focused;
	@Setter
	protected boolean enabled = true;
	protected int cursorPos;
	protected int cursorCounter;
	protected int selectionEnd;
	protected int lineScrollOffset;

	protected int maxStringLength = 32;

	protected boolean drawBackground = true;

	@Setter
	protected int preferredWidth = 200;


	public UITextField(String text, Pattern validator, Consumer<String> textChangeHandler, String id, String... classes) {
		super("text-field", id, classes);
		this.text = text;
		this.validator = validator;
		this.textChangeHandler = textChangeHandler;
	}

	public UITextField(Consumer<String> textChangeHandler, String id, String... classes) {
		this("", Pattern.compile(".+"), textChangeHandler, id, classes);
	}

	protected void handleChange() {
		textChangeHandler.accept(text);
	}

	@Override
	public UIDimensions getMinDimensions() {
		return getPreferredDimensions();
	}

	@Override
	public UIDimensions getPreferredDimensions() {
		return new UIDimensions(preferredWidth, 20);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		GlStateManager.disableDepth();
		if (drawBackground) {
			int borderWidth = getStyle(UIAttribute.TEXTFIELD_BORDER_WIDTH);
			UIHelper.drawRect(x - borderWidth, y - borderWidth, dimensions.width + borderWidth * 2, dimensions.height + borderWidth * 2, getStyle(UIAttribute.TEXTFIELD_CURSOR_COLOR), -1);
			UIHelper.drawRect(x, y, dimensions.width, dimensions.height, getStyle(UIAttribute.TEXTFIELD_BACKGROUND_COLOR), -1);
		}

		Color color = enabled ? getStyle(UIAttribute.TEXTFIELD_ENABLED_COLOR) : getStyle(UIAttribute.TEXTFIELD_DISABLED_COLOR);

		int j = cursorPos - lineScrollOffset;
		int k = selectionEnd - lineScrollOffset;
		String s = mc.fontRendererObj.trimStringToWidth(text.substring(lineScrollOffset), dimensions.width);
		boolean flag = j >= 0 && j <= s.length();
		boolean flag1 = focused && cursorCounter / 6 % 2 == 0 && flag;
		int l = drawBackground ? x + 4 : x;
		int i1 = drawBackground ? y + (dimensions.height - 8) / 2 : y;
		int j1 = l;

		k = Math.min(k, s.length());

		if (s.length() > 0) {
			String s1 = flag ? s.substring(0, j) : s;
			j1 = mc.fontRendererObj.drawStringWithShadow(s1, l, i1, UIHelper.toARGB(color));
		}

		boolean flag2 = cursorCounter < text.length() || text.length() >= maxStringLength;
		int k1 = j1;

		if (!flag) {
			k1 = j > 0 ? l + dimensions.width : l;
		} else if (flag2) {
			k1 = j1--;
		}

		if (s.length() > 0 && flag && j < s.length()) {
			j1 = mc.fontRendererObj.drawStringWithShadow(s.substring(j), j1, i1, UIHelper.toARGB(color));
		}

		if (flag1) {
			if (flag2) {
				Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + mc.fontRendererObj.FONT_HEIGHT, UIHelper.toARGB(getStyle(UIAttribute.TEXTFIELD_CURSOR_COLOR)));
			} else {
				mc.fontRendererObj.drawStringWithShadow("_", k1, i1, UIHelper.toARGB(color));
			}
		}

		if (k != j) {
			int l1 = l + mc.fontRendererObj.getStringWidth(s.substring(0, k));
			drawCursorVertical(k1, i1 - 1, l1 - 1, i1 + 1 + mc.fontRendererObj.FONT_HEIGHT);
		}
		GlStateManager.enableDepth();
	}

	@Override
	public void drawTooltip(int mouseX, int mouseY) {

	}

	private void drawCursorVertical(int p1, int p2, int p3, int p4) {
		if (p1 < p3) {
			int temp = p1;
			p1 = p3;
			p3 = temp;
		}
		if (p2 < p4) {
			int temp = p2;
			p2 = p4;
			p4 = temp;
		}
		p3 = Math.min(p3, x + dimensions.width);
		p1 = Math.min(p1, x + dimensions.width);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		Color selectionColor = getStyle(UIAttribute.TEXTFIELD_SELECTION_COLOR);
		GlStateManager.color(selectionColor.getRed(), selectionColor.getGreen(), selectionColor.getBlue(), 255);
		GlStateManager.disableTexture2D();
		GlStateManager.enableColorLogic();
		GlStateManager.colorLogicOp(GL11.GL_OR_REVERSE);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		buffer.pos(p1, p4, 0).endVertex();
		buffer.pos(p3, p4, 0).endVertex();
		buffer.pos(p3, p2, 0).endVertex();
		buffer.pos(p1, p2, 0).endVertex();
		tessellator.draw();
		GlStateManager.disableColorLogic();
		GlStateManager.enableTexture2D();
	}

	public String getSelectedText() {
		int start = cursorPos < selectionEnd ? cursorPos : selectionEnd;
		int end = cursorPos < selectionEnd ? selectionEnd : cursorPos;
		return text.substring(start, end);
	}

	public void writeText(String input) {
		String s = "";
		String s1 = ChatAllowedCharacters.filterAllowedCharacters(input);
		int i = this.cursorPos < this.selectionEnd ? this.cursorPos : this.selectionEnd;
		int j = this.cursorPos < this.selectionEnd ? this.selectionEnd : this.cursorPos;
		int k = maxStringLength - text.length() - (i - j);
		int l = 0;

		if (text.length() > 0) {
			s = s + text.substring(0, i);
		}

		if (k < s1.length()) {
			s = s + s1.substring(0, k);
			l = k;
		} else {
			s = s + s1;
			l = s1.length();
		}

		if (text.length() > 0 && j < text.length()) {
			s = s + text.substring(j);
		}

		if (validator.matcher(s).matches()) {
			text = s;
			moveCursorBy(i - selectionEnd + l);

			handleChange();
		}
	}

	protected void moveCursorBy(int amount) {
		setCursorPos(selectionEnd + amount);
	}

	protected void setCursorPos(int pos) {
		cursorPos = pos;
		cursorPos = MathHelper.clamp_int(cursorPos, 0, text.length());
		setSelectionPos(cursorPos);
	}

	protected void setSelectionPos(int pos) {
		int i = text.length();

		pos = Math.max(Math.min(pos, i), 0);

		selectionEnd = pos;

		lineScrollOffset = Math.min(lineScrollOffset, i);

		String s = mc.fontRendererObj.trimStringToWidth(text.substring(lineScrollOffset), dimensions.width);
		int k = s.length() + lineScrollOffset;

		if (pos == lineScrollOffset) {
			lineScrollOffset -= mc.fontRendererObj.trimStringToWidth(text, dimensions.width, true).length();
		}

		if (pos > k) {
			lineScrollOffset += pos - k;
		} else if (pos <= lineScrollOffset) {
			lineScrollOffset -= lineScrollOffset - pos;
		}

		lineScrollOffset = MathHelper.clamp_int(lineScrollOffset, 0, i);
	}

	@Override
	public void mouseClickDown(int mouseX, int mouseY, MouseButton button) {
		if (button == MouseButton.LEFT) {
			focused = true;
		}
	}

	@Override
	public void mouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {
		if (button == MouseButton.LEFT && !UIHelper.isWithinBounds(mouseX, mouseY, this)) {
			focused = false;
		}
	}

	@Override
	public void keyPress(int keyCode, char keyChar) {
		if (focused) {
			if (GuiScreen.isKeyComboCtrlA(keyCode)) {
				setSelectionPos(0);
				cursorPos = text.length();
			} else if (GuiScreen.isKeyComboCtrlC(keyCode)) {
				GuiScreen.setClipboardString(getSelectedText());
			} else if (GuiScreen.isKeyComboCtrlV(keyCode)) {
				if (enabled) {
					writeText(GuiScreen.getClipboardString());
				}
			} else if (GuiScreen.isKeyComboCtrlX(keyCode)) {
				GuiScreen.setClipboardString(getSelectedText());
				if (enabled) {
					writeText("x");
				}
			} else {
				switch (keyCode) {
					case 14:
						if (enabled) {
							if (GuiScreen.isCtrlKeyDown()) {
								deleteWords(-1);
							} else {
								deleteFromCursor(-1);
							}
							handleChange();
						}
						break;
					case 199:
						if (GuiScreen.isShiftKeyDown()) {
							setSelectionPos(0);
						} else {
							cursorPos = 0;
						}
						break;
					case 203:
						if (GuiScreen.isShiftKeyDown()) {
							if (GuiScreen.isCtrlKeyDown()) {
								setSelectionPos(getNthWordFromPos(-1, selectionEnd));
							} else {
								setSelectionPos(selectionEnd - 1);
							}
						} else if (GuiScreen.isCtrlKeyDown()) {
							setCursorPos(getNthWordFromCursor(-1));
						} else {
							moveCursorBy(-1);
						}

						handleChange();

						break;
					case 205:
						if (GuiScreen.isShiftKeyDown()) {
							if (GuiScreen.isCtrlKeyDown()) {
								setSelectionPos(getNthWordFromPos(1, selectionEnd));
							} else {
								setSelectionPos(selectionEnd);
							}
						} else if (GuiScreen.isCtrlKeyDown()) {
							setCursorPos(getNthWordFromCursor(1));
						} else {
							moveCursorBy(1);
						}
						break;
					case 207:
						if (GuiScreen.isShiftKeyDown()) {
							setSelectionPos(text.length());
						} else {
							setCursorPos(text.length());
						}
						break;
					case 211:
						if (enabled) {
							if (GuiScreen.isCtrlKeyDown()) {
								deleteWords(1);
							} else {
								deleteFromCursor(1);
							}
						}
						break;
					default:
						if (ChatAllowedCharacters.isAllowedCharacter(keyChar)) {
							if (enabled) {
								writeText(Character.toString(keyChar));
							}
						}
						break;
				}
			}
		}
	}

	private void deleteWords(int words) {
		if (text.length() != 0) {
			if (selectionEnd != cursorPos) {
				writeText("");
			} else {
				deleteFromCursor(getNthWordFromCursor(words) - cursorPos);
			}
		}
	}

	private void deleteFromCursor(int amount) {
		if (text.length() != 0) {
			if (selectionEnd != cursorPos) {
				writeText("");
			} else {
				boolean flag = amount < 0;
				int i = flag ? cursorPos + amount : cursorPos;
				int j = flag ? cursorPos : cursorPos + amount;
				String s = "";

				if (i >= 0) {
					s = text.substring(0, i);
				}

				if (j < text.length()) {
					s = s + text.substring(j);
				}

				text = s;
				if (flag) {
					moveCursorBy(amount);
				}
			}
		}
	}

	private int getNthWordFromCursor(int words) {
		return getNthWordFromPos(words, cursorPos);
	}

	private int getNthWordFromPos(int words, int position) {
		return getNthWordFromPos(words, position, true);
	}

	private int getNthWordFromPos(int words, int position, boolean flag) {
		int i = position;
		boolean flag2 = words < 0;
		int j = Math.abs(words);
		for (int k = 0; k < j; ++k) {
			if (!flag2) {
				int l = text.length();
				i = text.indexOf(32, i);
				if (i == -1) {
					i = l;
				} else {
					while (flag && text.charAt(i) == 32) {
						++i;
					}
				}
			} else {
				while (flag && i > 0 && text.charAt(i - 1) == 32) {
					--i;
				}
				while (i > 0 && text.charAt(i - 1) != 32) {
					--i;
				}
			}
		}

		return i;
	}

}
