/*
 * $Id$
 *
 * Copyright 2009 by Bruno Lowagie.
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is 'iText, a free JAVA-PDF library'.
 *
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999-2009 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000-2009 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the "GNU LIBRARY GENERAL PUBLIC LICENSE"), in which case the
 * provisions of LGPL are applicable instead of those above.  If you wish to
 * allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under
 * the MPL, indicate your decision by deleting the provisions above and
 * replace them with the notice and other provisions required by the LGPL.
 * If you do not delete the provisions above, a recipient may use your version
 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MPL as stated above or under the terms of the GNU
 * Library General Public License as published by the Free Software Foundation;
 * either version 2 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * http://www.lowagie.com/iText/
 */

package com.lowagie.text.pdf.richmedia;

import com.lowagie.text.exceptions.IllegalPdfSyntaxException;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfIndirectReference;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfObject;
import com.lowagie.text.pdf.PdfString;

/**
 * A video file can contain cue points that are encoded in a video stream
 * or may be created by an associated ActionScript within the Flash content.
 * The CuePoint dictionary contains a state that relates the cue points to
 * an action that may be passed to the conforming application or may be used
 * to change the appearance. Cue points in the Flash content are matched to
 * the cue points declared in the PDF file by the values specified by the
 * Name or Time keys. (See ExtensionLevel 3 p91)
 * @since	2.1.6
 */
public class CuePoint extends PdfDictionary {

	/**
	 * Constructs a CuePoint object.
	 * A <code>Navigation</code> cue point is an event encoded in a Flash movie (FLV).
	 * A chapter stop may be encoded so that when the user requests to go to or skip
	 * a chapter, a navigation cue point is used to indicate the location of the chapter.
	 * An <code>Event</code> is a generic cue point of no specific significance other
	 * than a corresponding action is triggered.
	 * @param	subtype	possible values: PdfName.NAVIGATION or PdfName.EVENT
	 */
	public CuePoint(PdfName subtype) {
		super(PdfName.CUEPOINT);
		put(PdfName.SUBTYPE, subtype);
	}
	
	/**
	 * Set the name of the cue point to match against the cue point within
	 * Flash content and for display purposes.
	 * @param	name	the name of the cue point
	 */
	public void setName(PdfString name) {
		put(PdfName.NAME, name);
	}
	
	/**
	 * Sets the time value of the cue point in milliseconds to match against
	 * the cue point within Flash content and for display purposes.
	 * @param	time	the time value of the cue point
	 */
	public void setTime(int time) {
		put(PdfName.TIME, new PdfNumber(time));
	}

	/**
	 * Sets an action dictionary defining the action that is executed
	 * if this cue point is triggered, meaning that the Flash content
	 * reached the matching cue point during its playback.
	 * @param	action	an action
	 */
	public void setAction(PdfObject action) {
		if (action instanceof PdfDictionary || action instanceof PdfIndirectReference)
			put(PdfName.A, action);
		else
			throw new IllegalPdfSyntaxException(
				"An action should be defined as a dictionary");
	}
}
