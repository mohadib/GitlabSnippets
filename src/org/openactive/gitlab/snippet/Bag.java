package org.openactive.gitlab.snippet;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JComponent;


/**
 * Using this class saves you lots of keystrokes when
 * slugging it out with {@link GridBagLayout}:
 *
 * <blockquote>
 *                 <xmp>
 *        Container c = new JPanel(new GridBagLayout());
 *        org.openactive.gitlab.snippet.Bag b = new org.openactive.gitlab.snippet.Bag();
 *
 *        // Add a label
 *        c.add(new JLabel("Foo"), b.cell(1,1));
 *
 *        // Add a textfield that spans several columns
 *        c.add(textfield, b.next().fillx().colspan(3));
 *
 *  // For the next row, add a big scrolling thing
 *        c.add(myScrollPane, b.nextRow().fillboth().colspan(4));
 *                 </xmp>
 * </blockquote>
 *
 * @see #inset(int)
 * @see #pad(int, int)
 * @see #cell(int,int)
 * @see #west()
 * @see #east()
 * @see #north()
 * @see #fillnone()
 * @see #fillboth()
 * @see #fillx()
 * @see #fillx(double)
 * @see #filly()
 * @see #filly(double)
 *
 * @author ddjohnson
 *
 * @version 1.2 Jul 02, 2006
 * @version 1.1 Aug 10, 2005
 */
public class Bag extends GridBagConstraints {
	public Bag() {
		super();
		reset();
	}

	public Bag reset() {
		fillNone();
		cell(0, 0);
		gridwidth = gridheight = 1;

		return inset(1);
	}

	public Bag cell(int x, int y) {
		gridx = x;
		gridy = y;

		return this;
	}

	public Bag inset(int amt) {
		return inset(amt, amt, amt, amt);
	}

	public Bag inset(int top, int left, int bottom, int right) {
		insets.top = top;
		insets.left = left;
		insets.bottom = bottom;
		insets.right = right;

		return this;
	}

	public Bag nextX() {
		gridx++;

		return this;
	}

	public Bag nextY() {
		gridy++;

		return this;
	}

	public Bag resetX() {
		gridx = 0;

		return this;
	}

	public Bag resetY() {
		gridy = 0;

		return this;
	}

	public Bag rowspan(int i) {
		gridheight = i;

		return this;
	}

	public Bag colspan(int i) {
		gridwidth = i;

		return this;
	}

	public Bag fillNone() {
		fill = NONE;
		weightx = weighty = 0.0;

		return this;
	}

	public Bag fillBoth() {
		fill = BOTH;
		weightx = weighty = 1.0;

		return this;
	}

	public Bag fillX() {
		fill = HORIZONTAL;
		weightx = 1.0;
		weighty = 0.0;

		return this;
	}

	public Bag fillY() {
		fill = VERTICAL;
		weighty = 1.0;
		weightx = 0;

		return this;
	}

	public static JComponent spacer() {
		return new Spacer();
	}

	public static class Spacer extends JComponent {
		public Spacer() {
			setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
			setOpaque(false);
		}
	}
}
