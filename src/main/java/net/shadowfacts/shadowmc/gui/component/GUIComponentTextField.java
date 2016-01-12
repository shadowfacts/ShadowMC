package net.shadowfacts.shadowmc.gui.component;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;
import net.shadowfacts.shadowmc.util.Color;
import net.shadowfacts.shadowmc.util.MouseButton;
import org.lwjgl.opengl.GL11;

import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @author shadowfacts
 */
public class GUIComponentTextField extends GUIComponent {

	protected String text;
	protected Pattern validator;
	protected Consumer<String> textChangeHandler;
	protected boolean focused;
	protected boolean enabled = true;
	protected int cursorPos;
	protected int cursorCounter;
	protected int selectionEnd;
	protected int lineScrollOffset;

	protected Color enabledColor = new Color(14737632);
	protected Color disabledColor = new Color(7368816);

	protected int maxStringLength = 32;

	protected boolean drawBackground = true;

	public GUIComponentTextField(int x, int y, int width, int height, String text, Pattern validator, Consumer<String> textChangeHandler) {
		super(x, y, width, height);
		this.text = text;
		this.validator = validator;
		this.textChangeHandler = textChangeHandler;
	}

	public GUIComponentTextField(int x, int y, int width, int height, Consumer<String> textChangeHandler) {
		this(x, y, width, height, "", Pattern.compile(".+"), textChangeHandler);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		GlStateManager.disableDepth();
		if (drawBackground) {
			drawRect(x - 1, y - 1, width + 2, height + 2, new Color(-6250336), zLevel - 1);
			drawRect(x, y, width, height, Color.BLACK, zLevel - 1);
		}

		Color color = enabled ? enabledColor : disabledColor;
		int j = cursorPos - lineScrollOffset;
		int k = selectionEnd - lineScrollOffset;
		String s = mc.fontRendererObj.trimStringToWidth(text.substring(lineScrollOffset), width);
		boolean flag = j >= 0 && j <= s.length();
		boolean flag1 = focused && cursorCounter / 6 % 2 == 0 && flag;
		int l = drawBackground ? x + 4 : x;
		int i1 = drawBackground ? y + (height - 8) / 2 : y;
		int j1 = l;

		k = Math.min(k, s.length());

		if (s.length() > 0) {
			String s1 = flag ? s.substring(0, j) : s;
			j1 = mc.fontRendererObj.drawStringWithShadow(s1, l, i1, color.toARGB());
		}

		boolean flag2 = cursorPos < text.length() || text.length() >= maxStringLength;
		int k1 = j1;

		if (!flag) {
			k1 = j > 0 ? l + width : l;
		} else if (flag2) {
			k1 = j1--;
		}

		if (s.length() > 0 && flag && j < s.length()) {
			j1 = mc.fontRendererObj.drawStringWithShadow(s.substring(j), j1, i1, color.toARGB());
		}

		if (flag1) {
			if (flag2) {
				Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + mc.fontRendererObj.FONT_HEIGHT, -3092272);
			} else {
				mc.fontRendererObj.drawStringWithShadow("_", k1, i1, color.toARGB());
			}
		}

		if (k != j) {
			int l1 = l + mc.fontRendererObj.getStringWidth(s.substring(0, k));
			drawCursorVertical(k1, i1 - 1, l1 - 1, i1 + 1 + mc.fontRendererObj.FONT_HEIGHT);
		}
		GlStateManager.enableDepth();
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
		p3 = Math.min(p3, x + width);
		p1 = Math.min(p1, x + width);

		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		GlStateManager.color(0, 0, 255, 255);
		GlStateManager.disableTexture2D();
		GlStateManager.enableColorLogic();
		GlStateManager.colorLogicOp(5387);
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(p1, p4, zLevel).endVertex();
		worldRenderer.pos(p3, p4, zLevel).endVertex();
		worldRenderer.pos(p3, p2, zLevel).endVertex();
		worldRenderer.pos(p1, p2, zLevel).endVertex();
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

			textChangeHandler.accept(text);
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

		String s = mc.fontRendererObj.trimStringToWidth(text.substring(lineScrollOffset), width);
		int k = s.length() + lineScrollOffset;

		if (pos == lineScrollOffset) {
			lineScrollOffset -= mc.fontRendererObj.trimStringToWidth(text, width, true).length();
		}

		if (pos > k) {
			lineScrollOffset += pos - k;
		} else if (pos <= lineScrollOffset) {
			lineScrollOffset -= lineScrollOffset - pos;
		}

		lineScrollOffset = MathHelper.clamp_int(lineScrollOffset, 0, i);
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, MouseButton button) {
		if (button == MouseButton.LEFT) {
			focused = true;
		}
	}

	@Override
	public void handleMouseClickAnywhere(int mouseX, int mouseY, MouseButton button) {
		if (button == MouseButton.LEFT && !isWithinBounds(mouseX, mouseY)) {
			focused = false;
		}
	}

	@Override
	public void handleKeyPress(int keyCode, char charTyped) {
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
							textChangeHandler.accept(text);
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

						textChangeHandler.accept(text);

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
						if (ChatAllowedCharacters.isAllowedCharacter(charTyped)) {
							if (enabled) {
								writeText(Character.toString(charTyped));
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
		boolean flag2 = words <  0;
		int j = Math.abs(words);
		for (int k = 0; k < j; ++k) {
			if (!flag2) {
				int l =  text.length();
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

	public GUIComponentTextField setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
		return this;
	}
}
