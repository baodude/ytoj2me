package org.j4me.ui.components;

import java.util.*;
import javax.microedition.lcdui.*;
import org.j4me.ui.*;

/**
 * Displays a string.
 * <p>
 * If the string is wider than the screen, it is wrapped at the first
 * non-alphanumeric character. The wrapped text is trimmed so that leading
 * spaces do not appear. There are several special markup strings for controling
 * spacing:
 * <ul>
 * <li><code>\n</code> - Goes to a newline and adds a 1/2 space separator
 * <li><code>\r</code> - Goes to a newline, but does not add a separator
 * </ul>
 * The string is displayed using the theme's font and color unless overridden by
 * the <code>setFont</code> and <code>setFontColor</code> methods.
 * <p>
 * Override this class to display strings in a new way. For example a font
 * backed by images of the characters can be used by re-implementing
 * <code>paint</code>, <code>getWidth</code>, and <code>getHeight</code>. A good
 * way to generate font images is with the <a
 * href="- http://www.angelcode.com/products/bmfont/"> AngelCode Bitmap Font
 * Generator</a>.
 */
public class Label extends Component {
	/**
	 * The string that is displayed by this label.
	 */
	private String text;

	/**
	 * Each element is a substring of <code>text</code> that appears on a single
	 * line.
	 */
	private String[] lines;

	/**
	 * The width in pixels used to create the <code>lines</code> array. If
	 * <code>lines</code> is <code>null</code> this has no meaning.
	 */
	private int widthForLines;

	/**
	 * The height, in pixels, it takes to write all the lines.
	 */
	private int height;

	/**
	 * The font to use for this label. If <code>null</code> the theme's font
	 * will be used.
	 */
	private Font font;

	/**
	 * The font color to use for this label. If negative the theme's font will
	 * be used.
	 */
	private int fontColor = -1;

	/**
	 * The minimum width of this label. If the text takes up less space the
	 * label will be widened. If the text takes up more space this value will be
	 * ignored.
	 */
	private int minWidth;

	/**
	 * Constructs an empty label. The test should be set using
	 * <code>setLabel</code>.
	 */
	public Label() {
	}

	/**
	 * Constructs a label.
	 * 
	 * @param text
	 *            is what the label displays.
	 */
	public Label(String text) {
		setLabel(text);
	}

	/**
	 * @see Component#invalidate()
	 */
	protected void invalidate() {
		lines = null;
		super.invalidate();
	}

	/**
	 * @return The text this label will display.
	 */
	public String getLabel() {
		return text;
	}

	/**
	 * @param text
	 *            is the string to display. If <code>null</code> the label will
	 *            be hidden.
	 */
	public synchronized void setLabel(String text) {
		this.text = text;
		invalidate();
	}

	/**
	 * @return The <code>Font</code> used to display the label. If
	 *         <code>null</code> the theme's font will be used.
	 * 
	 * @see Theme#getFont()
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the font used by the label. This differs from
	 * <code>getFont()</code> because if the user has not overriden the font,
	 * the theme's font will be returned.
	 * 
	 * @param theme
	 *            is the application's theme.
	 * @return The <code>Font</code> used by the label.
	 */
	private Font getFont(Theme theme) {
		if (font != null) {
			return font;
		} else {
			return theme.getFont();
		}
	}

	/**
	 * @param font
	 *            is the font to use for this label. If <code>null</code> the
	 *            theme's font will be used.
	 * 
	 * @see Theme#getFont()
	 */
	public synchronized void setFont(Font font) {
		this.font = font;
		invalidate();
	}

	/**
	 * @return The font color used to display the label. If negative the theme's
	 *         font will be used.
	 * 
	 * @see Theme#getFontColor()
	 */
	public int getFontColor() {
		return fontColor;
	}

	/**
	 * Returns the font color by the label. This differs from
	 * <code>getFontColor()</code> because if the user has not overriden the
	 * font color, the theme's font color will be returned.
	 * 
	 * @param theme
	 *            is the application's theme.
	 * @return The font color used by the label.
	 */
	private int getFontColor(Theme theme) {
		if (fontColor >= 0) {
			return fontColor;
		} else {
			return theme.getFontColor();
		}
	}

	/**
	 * @param color
	 *            is the font color used to display the label. If negative the
	 *            theme's font will be used.
	 * 
	 * @see Theme#getFontColor()
	 */
	public void setFontColor(int color) {
		this.fontColor = color;
	}

	/**
	 * @return The minimum width of this lable.
	 */
	public int getMinimumWidth() {
		return minWidth;
	}

	/**
	 * @param width
	 *            is the minimum width for the label.
	 */
	public void setMinimumWidth(int width) {
		this.minWidth = width;
	}

	/**
	 * Paints this label component.
	 * 
	 * @param g
	 *            is the <code>Graphics</code> object to be used for rendering
	 *            the item.
	 * @param theme
	 *            is the application's theme. Use it to get fonts and colors.
	 * @param width
	 *            is the width, in pixels, to paint the component.
	 * @param height
	 *            is the height, in pixels, to paint the component.
	 * @param selected
	 *            is <code>true</code> when this components is currently
	 *            selected and <code>false</code> when it is not.
	 * 
	 * @see org.j4me.ui.components.Component#paintComponent(Graphics, Theme,
	 *      int, int, boolean)
	 */
	protected synchronized void paintComponent(Graphics g, Theme theme,
			int width, int height, boolean selected) {
		if (text != null) {
			// Set the font on the graphics object.
			Font font = getFont(theme);
			g.setFont(font);

			int fontColor = getFontColor(theme);
			g.setColor(fontColor);

			int fontHeight = font.getHeight();
			int paragraphSpacing = fontHeight / 2;

			// Determine the positioning of the line.
			int x;
			int y = 0;
			int horizontalAlignment = getHorizontalAlignment();
			int anchor = horizontalAlignment | Graphics.TOP;

			if (horizontalAlignment == Graphics.LEFT) {
				x = 0;
			} else if (horizontalAlignment == Graphics.HCENTER) {
				x = width / 2;
			} else // horizontalAlignment == Graphics.RIGHT
			{
				x = width;
			}

			// Do we need to break apart the text again?
			// If we have not fit this label's text into the
			// specified width for the component, reparse it.
			if ((lines == null) || (widthForLines != width)) {
				lines = breakIntoLines(font, text, width);
				widthForLines = width;
			}

			// Get the top and bottom of current graphics clip.
			int clipTop = g.getClipY();
			int clipBottom = clipTop + g.getClipHeight();

			// Draw each line.
			for (int i = 0; i < lines.length; i++) {
				if (lines[i] == null) {
					y += paragraphSpacing;
				} else {
					if ((y + fontHeight >= clipTop) && (y <= clipBottom)) {
						g.drawString(lines[i], x, y, anchor);
					}

					y += fontHeight;
				}
			}

			// Record the height of this component.
			this.height = y;
		}
	}

	/**
	 * Returns the dimensions of the label for the current string. The width is
	 * equal to the width of the longest line of text. The height is equal to
	 * the sum of all the lines including line breaks between paragraphs.
	 * 
	 * @see org.j4me.ui.components.Component#getPreferredComponentSize(Theme,
	 *      int, int)
	 */
	public int[] getPreferredComponentSize(Theme theme, int viewportWidth,
			int viewportHeight) {
		int width = 0;
		height = 0;

		if (text != null) {
			// Get the font.
			Font font = getFont(theme);

			// Parse the text by line breaks.
			if ((lines == null) || (widthForLines != viewportWidth)) {
				lines = breakIntoLines(font, text, viewportWidth);
				widthForLines = viewportWidth;
			}

			// The width of a wide character used as a safety
			// margin in calculating the line width. Not all font
			// width calculations are reliable (e.g. the default
			// font on the Motorola SLVR). Adjusting the line
			// width by this amount assures no text will get clipped.
			int charWidth = font.charWidth('O');

			// Get the width and height.
			int fontHeight = font.getHeight();
			int paragraphSpacing = fontHeight / 2;

			for (int i = 0; i < lines.length; i++) {
				if (lines[i] == null) {
					// Space between paragraphs.
					height += paragraphSpacing;
				} else {
					int lineWidth = font.stringWidth(lines[i]) + charWidth;

					if (lineWidth > width) {
						width = lineWidth;
					}

					height += fontHeight;
				}
			}
		}

		if (width < minWidth) {
			width = minWidth;
		}

		if (width > viewportWidth) {
			// The safety margin we add for text width (since some phones do
			// not report a width that is actually big enough) means we can
			// wind up with a width bigger than the screen. Truncate that here.
			width = viewportWidth;
		}

		return new int[] { width, height };
	}

	/**
	 * @return The height, in pixels, for this component.
	 */
	public int getHeight() {
		if (lines == null) {
			return super.getHeight();
		} else {
			return height;
		}
	}

	/**
	 * Breaks <code>string</code> apart at line breaks. The returned array has
	 * strings that can be written using <code>font</code> in fewer than
	 * <code>width</code> pixels.
	 * <p>
	 * The <code>string</code> will be split apart at non-alphanumeric
	 * characters. However, if a string is too long and cannot be broken nicely,
	 * it will be split at the last alphanumeric character that fits the line
	 * <code>width</code>.
	 * <p>
	 * Newline characters (<code>'\n'</code>) are automatically made into line
	 * breaks. An additional empty line, represented by a <code>null</code>
	 * string in the returned array, is added. It can then be painted as a space
	 * to indicate a new paragraph.
	 * 
	 * @param font
	 *            is the <code>Font</code> used to write <code>string</code>.
	 * @param string
	 *            is the text to split. If <code>null</code> then
	 *            <code>null</code> will be returned.
	 * @param width
	 *            is the maximum number of pixels wide each string part can be.
	 * @return An array of parts of <code>string</code> broken apart at line
	 *         breaks.
	 */
	public static String[] breakIntoLines(Font font, String string, int width) {
		String[] lines = null;

		if (string != null) {
			// A container for holding lines as we parse them.
			Vector parsedLines = new Vector();
			String newLine;

			// Flag indicating if the current line starts a paragraph.
			// Spaces at the beginning of a paragraph are kept for
			// indentation or labels used as spacers.
			boolean startsParagraph = true;

			// The index of the character that starts this line.
			int lineStart = 0;

			// The index of a character that may appear on the
			// next line. For example a space can appear on this
			// line or the next, but a letter in the middle of the
			// word must stay on the same line as the entire word.
			int lastBreakableSpot = 0;

			// The index of the last character that wasn't a
			// whitespace character such as ' '.
			int lastNonWhiteSpace = 0;

			// The width of a wide character used as a safety
			// margin in calculating the line width. Not all font
			// width calculations are reliable (e.g. the default
			// font on the Motorola SLVR). Adjusting the line
			// width by this amount assures no text will get clipped.
			int charWidth = font.charWidth('O');
			width -= charWidth;

			// Scan lengths character-by-character.
			char[] chars = string.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				boolean isSeparator = (chars[i] == '-') || (chars[i] == '/');
				boolean isWhiteSpace = (chars[i] == ' ');

				boolean isNewLine = (chars[i] == '\n');
				boolean isCarrageReturn = (chars[i] == '\r');

				boolean isLineBreak = isNewLine || isCarrageReturn;
				int lineWidth = font
						.charsWidth(chars, lineStart, i - lineStart);

				// Are we done with this line?
				if ((isLineBreak) || (lineWidth > width)) {
					int lineEnd;

					if (isLineBreak) {
						lineEnd = i;
					} else if (lastBreakableSpot > lineStart) {
						lineEnd = lastBreakableSpot;
					} else {
						// This word is longer than the line so do a mid-word
						// break
						// with the current letter on the next line.
						lineEnd = i - 1;
					}

					// Record the line.
					newLine = string.substring(lineStart, lineEnd);
					newLine = smartTrim(newLine, startsParagraph);
					parsedLines.addElement(newLine);

					// Setup for the next line.
					if (isLineBreak) {
						lineStart = lineEnd + 1; // +1 to advance over the '\n'

						// Add an empty line between the paragraphs.
						if (isNewLine) {
							parsedLines.addElement(null);
						}

						startsParagraph = true;
					} else // line break in the middle of a paragraph
					{
						lineStart = lineEnd;
						startsParagraph = false;
					}
				}

				// Is this a good place for a line-break?
				if (isSeparator) {
					// Put the separator char on this line (e.g. "full-").
					lastBreakableSpot = i + 1;
				}

				if (isWhiteSpace) {
					// Break at the whitespace. It will be trimmed later.
					lastBreakableSpot = lastNonWhiteSpace + 1;
				} else {
					// Record this character as the last non-whitespace
					// processed.
					lastNonWhiteSpace = i;
				}
			}

			// Add the last line.
			newLine = string.substring(lineStart);
			newLine = smartTrim(newLine, startsParagraph);
			parsedLines.addElement(newLine);

			// Convert the vector into a string array.
			lines = new String[parsedLines.size()];
			Enumeration e = parsedLines.elements();
			int i = 0;

			while (e.hasMoreElements()) {
				lines[i++] = (String) e.nextElement();
			}
		}

		return lines;
	}

	/**
	 * Trims whitespace from a string. However, it does not trim leading
	 * whitespace if the string starts a paragraph. That leading whitespace may
	 * be an indentation or used for spacing.
	 * 
	 * @param string
	 *            is the string to trim.
	 * @param startsParagraph
	 *            is <code>true</code> when <code>string</code> is the start of
	 *            a paragraph and <code>false</code> when it is not.
	 * @return A smartly trimmed version of <code>string</code>.
	 */
	private static String smartTrim(String string, boolean startsParagraph) {
		String trimmed;

		if (startsParagraph) {
			// Trim the right side of the string only.
			int len = string.length() - 1;

			while ((len >= 0) && (string.charAt(len) <= ' ')) {
				len--;
			}

			if (len <= 0) {
				// All spaces.
				trimmed = string;
			} else {
				trimmed = string.substring(0, len + 1);
			}
		} else {
			trimmed = string.trim();
		}

		return trimmed;
	}
}
